package com.ediwow.supportmodule.backend;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {
    public static void setPrefs(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefs(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
}
