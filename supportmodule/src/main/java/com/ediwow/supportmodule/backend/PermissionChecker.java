package com.ediwow.supportmodule.backend;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;

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
            try {
                PermissionInfo permissionInfo = activity.getPackageManager().getPermissionInfo(permission, 0);
                int permissionResult = activity.checkSelfPermission(permission);
                int protectionLevel = permissionInfo.protectionLevel;
                String sProtectionLevel = "";
                switch (protectionLevel) {
                    case PermissionInfo.PROTECTION_DANGEROUS:
                        sProtectionLevel = "Dangerous";
                        break;
                    case PermissionInfo.PROTECTION_NORMAL:
                        sProtectionLevel = "Normal";
                        break;
                    case PermissionInfo.PROTECTION_SIGNATURE:
                        sProtectionLevel = "Signature";
                        break;
                }
                System.out.println("Permission check: " + permission + " - " + sProtectionLevel + " -" + ((permissionResult == GRANTED) ? "Approved" : "Denied"));
                if (protectionLevel == PermissionInfo.PROTECTION_DANGEROUS) {
                    if (permissionResult == DENIED) {
                        allPermissionsApproved = false;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!allPermissionsApproved)
            requestPermissions(activity, permissions, requestCode);
    }

    private static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }
}
