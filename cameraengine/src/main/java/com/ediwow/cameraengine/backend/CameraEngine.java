package com.ediwow.cameraengine.backend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.Size;

import androidx.annotation.NonNull;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageInfo;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.ediwow.cameraengine.interfaces.BitmapGeneratedCallback;
import com.ediwow.cameraengine.interfaces.OnViewfinderToggle;
import com.google.common.util.concurrent.ListenableFuture;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;

public class CameraEngine {
    private CameraSelector cameraSelector;
    private ImageCapture imageCapture;
    private Runnable cameraRunnable;
    private Executor cameraExecutor = null;
    private ListenableFuture<ProcessCameraProvider> cameraFuture;
    private ProcessCameraProvider cameraProvider;
    private Camera camera;

    private Preview preview;
    private final Preview.SurfaceProvider surfaceProvider;

    private Size size = null;
    private final Context context;
    private final LifecycleOwner lifecycleOwner;

    private ImageCapture.OnImageCapturedCallback captureCallback;
    private final BitmapGeneratedCallback bitmapGeneratedCallback;
    private final OnViewfinderToggle onViewfinderToggle;

    private int imageRotation = 0;

    public CameraEngine(Context context, Preview.SurfaceProvider surfaceProvider, OnViewfinderToggle onViewfinderToggle, LifecycleOwner lifecycleOwner, BitmapGeneratedCallback bitmapGeneratedCallback) {
        this.context = context;
        this.surfaceProvider = surfaceProvider;
        this.onViewfinderToggle = onViewfinderToggle;
        this.lifecycleOwner = lifecycleOwner;
        this.bitmapGeneratedCallback = bitmapGeneratedCallback;
        getCameraSpecs();
        setupCamera();
    }

    /**
     * @noinspection DataFlowIssue
     */
    private void getCameraSpecs() {
        try {
            CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);

            for (String cameraID : cameraManager.getCameraIdList()) {
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraID);
                if (cameraCharacteristics.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_BACK) {
                    for (Size size : cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG)) {
                        if (size.getHeight() == size.getWidth()) this.size = size;
                    }
                    break;
                }
            }
            if (size == null)
                size = new Size(3840, 2160);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupCamera() {
        this.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
        this.cameraFuture = ProcessCameraProvider.getInstance(context);
        this.captureCallback = new ImageCapture.OnImageCapturedCallback() {
            @Override
            public void onCaptureSuccess(@NonNull ImageProxy imageProxy) {
                Bitmap bitmap = buildBitmapFromImageProxy(imageProxy);
                Bitmap croppedBitmap = cropAndScaleBitmap(bitmap);
                bitmapGeneratedCallback.onFinalBitmapGenerated(croppedBitmap);
            }
        };

        cameraRunnable = () -> {
            try {
                cameraProvider = cameraFuture.get();
                Preview.Builder builder = new Preview.Builder();
                builder.setTargetResolution(size);
                preview = builder.build();
                preview.setSurfaceProvider(surfaceProvider);
                cameraProvider.unbindAll();
                camera = cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture);

                onViewfinderToggle.toggleViewFinder(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        ImageCapture.Builder builder = new ImageCapture.Builder();
        builder.setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY);
        builder.setTargetResolution(this.size);
        builder.setJpegQuality(80);
        imageCapture = builder.build();
    }

    public void startCamera() {
        this.cameraExecutor = ContextCompat.getMainExecutor(context);
        cameraFuture.addListener(cameraRunnable, this.cameraExecutor);
    }

    public boolean stopCamera() {
        if (cameraProvider != null) {
            cameraProvider.unbindAll();
            return true;
        }
        return false;
    }

    public boolean captureImage() {
        if (this.cameraExecutor != null) {
            imageCapture.takePicture(this.cameraExecutor, captureCallback);
            return true;
        } else
            return false;
    }

    private Bitmap buildBitmapFromImageProxy(ImageProxy imageProxy) {
        ImageInfo imageInfo = imageProxy.getImageInfo();
        this.imageRotation = imageInfo.getRotationDegrees();
        ImageProxy.PlaneProxy planeProxy = imageProxy.getPlanes()[0];
        ByteBuffer byteBuffer = planeProxy.getBuffer();
        byte[] byteArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(byteArray);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    private Bitmap cropAndScaleBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth(), height = bitmap.getHeight();
        boolean isLandscape = width > height, isSquare = width == height;
        int minDimension = Math.min(width, height);
        int maxDimension = Math.max(width, height);
        int finalDimension = 1440;

        int startPoint = (maxDimension - minDimension) / 2;
        int x, y;
        if (isSquare) {
            x = 0;
            y = 0;
        } else if (isLandscape) {
            x = startPoint;
            y = 0;
        } else {
            y = startPoint;
            x = 0;
        }
        Matrix matrix = new Matrix();
        //Rotate image
        matrix.postRotate(imageRotation);
        Bitmap croppedBitmap = Bitmap.createBitmap(bitmap, x, y, minDimension, minDimension, matrix, true);
        return Bitmap.createScaledBitmap(croppedBitmap, finalDimension, finalDimension, true);
    }
}
