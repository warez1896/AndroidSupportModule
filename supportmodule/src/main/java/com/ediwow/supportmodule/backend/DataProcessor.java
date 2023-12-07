package com.ediwow.supportmodule.backend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.TypedValue;

import androidx.annotation.NonNull;

import com.ediwow.supportmodule.objectholder.DeviceInformation;

import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;

public class DataProcessor {
    public static String toMD5(String plainText) throws Exception {
        String hashedText;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        hashedText = sb.toString();
        return hashedText;
    }

    public static String getClassAndMethodName(Class<?> execClass) {
        if (execClass.getEnclosingMethod() != null)
            return String.format("%s.%s", execClass.getName(), execClass.getEnclosingMethod().getName());
        else
            return String.format("%s", execClass.getName());

    }

    @SuppressLint("HardwareIds")
    public static DeviceInformation loadDeviceInformation(Context context) {
        return new DeviceInformation(Build.MANUFACTURER, Build.MODEL, Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
    }

    public static BigDecimal getFixedDecimal(Cursor cursor, int index, int scale) {
        BigDecimal finalDecimal = null;
        double dValue;
        if (!cursor.isNull(index)) {
            dValue = cursor.getDouble(index);
            finalDecimal = new BigDecimal(dValue).setScale(scale, RoundingMode.HALF_UP);
        }
        return finalDecimal;
    }

    public static String getJSONStringOrNull(JSONObject obj, String keyword) throws Exception {
        return (obj.has(keyword) && !obj.isNull(keyword)) ? obj.getString(keyword) : null;
    }

    public static BigDecimal getFixedDecimal(String value, int scale) throws Exception {
        BigDecimal finalDecimal = null;
        if (value != null) {
            double dValue = Double.parseDouble(value);
            finalDecimal = new BigDecimal(dValue).setScale(scale, RoundingMode.HALF_UP);
        }
        return finalDecimal;
    }

    public static int toPixels(Context context, int unitType, int unitValue) {
        return (int) TypedValue.applyDimension(unitType, unitValue, context.getResources().getDisplayMetrics());
    }

    public static File verifyImageFile(@NonNull String path, @NonNull String folderContext) {
        String[] exts = {".jpg", ".jpeg"};
        String parentDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + File.separator + folderContext;
        if (!path.endsWith(".jpg"))
            path = path + ".jpg";
        File imageFile = new File(parentDir, path);
        for (String ext : exts) {
            if (imageFile.exists())
                break;
            imageFile = new File(parentDir, path + ext);
        }
        if (!imageFile.exists())
            imageFile = null;
        return imageFile;
    }
}
