package com.ediwow.supportmodule.backend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.TypedValue;

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
    public static String[] loadDeviceInformation(Context context) {
        String[] deviceInfo = new String[]{"", "", ""};
        deviceInfo[0] = Build.MANUFACTURER;
        deviceInfo[1] = Build.MODEL;
        deviceInfo[2] = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return deviceInfo;
    }

    public static int toPixels(Context context, int unitType, int unitValue) {
        return (int) TypedValue.applyDimension(unitType, unitValue, context.getResources().getDisplayMetrics());
    }
}
