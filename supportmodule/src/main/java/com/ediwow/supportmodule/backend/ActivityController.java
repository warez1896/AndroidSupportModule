package com.ediwow.supportmodule.backend;

import android.app.Activity;
import android.content.Intent;

import com.ediwow.supportmodule.objectholder.Meta;

public class ActivityController {
    public static void checkLogin(Activity activity, Class<?> destinationClass) {
        if (Meta.getUser() == null) {
            activity.finish();
            activity.startActivity(new Intent(activity, destinationClass));
        }
    }

    public static void checkLoginOnLogin(Activity activity, Class<?> destinationClass) {
        if (Meta.getUser() != null) {
            activity.finish();
            activity.startActivity(new Intent(activity, destinationClass));
        }
    }
}
