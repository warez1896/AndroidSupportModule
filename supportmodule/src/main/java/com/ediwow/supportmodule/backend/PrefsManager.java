package com.ediwow.supportmodule.backend;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {
    public static void setPrefs(Context context, String masterKey, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setPrefsInt(Context context, String masterKey, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setPrefsBool(Context context, String masterKey, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getPrefs(Context context, String masterKey, String key) {
        SharedPreferences sp = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static int getPrefsInt(Context context, String masterKey, String key) {
        SharedPreferences sp = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        return sp.getInt(key, -1);
    }

    public static boolean getPrefsBool(Context context, String masterKey, String key) {
        SharedPreferences sp = context.getSharedPreferences(masterKey, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
}
