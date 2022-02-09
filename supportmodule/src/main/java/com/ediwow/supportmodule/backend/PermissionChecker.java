package com.ediwow.supportmodule.backend;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionChecker {
    private static final int GRANTED = PackageManager.PERMISSION_GRANTED, DENIED = PackageManager.PERMISSION_DENIED;

    private PermissionChecker() {

    }

    public static void checkPermissions(Activity activity, String[] permissions) {
        boolean allPermissionsApproved = true;
        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) == DENIED) {
                allPermissionsApproved = false;
                break;
            }
        }
        if (!allPermissionsApproved)
            requestPermissions(activity, permissions);

    }

    private static void requestPermissions(Activity activity, String[] permissions) {
        ActivityCompat.requestPermissions(activity, permissions, 100);
    }
}
