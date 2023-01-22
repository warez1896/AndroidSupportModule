package com.ediwow.supportmodule.backend;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionChecker {
    private static final int GRANTED = PackageManager.PERMISSION_GRANTED, DENIED = PackageManager.PERMISSION_DENIED;

    private PermissionChecker() {

    }

    public static String[] getDeclaredPermissionsInManifest(Context context) throws Exception {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS).requestedPermissions;
    }

    public static void checkPermissions(Activity activity, String[] permissions, int requestCode) {
        boolean allPermissionsApproved = true;
        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) == DENIED) {
                allPermissionsApproved = false;
                break;
            }
        }
        if (!allPermissionsApproved)
            requestPermissions(activity, permissions, requestCode);
    }

    private static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }
}
