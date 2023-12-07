package com.ediwow.cameraengine.interfaces;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

public interface BitmapGeneratedCallback {
    void onFinalBitmapGenerated(@NonNull Bitmap bitmap);
}
