package com.contactdata.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class AppManager {
    private static AppManager appManager;

    public static AppManager getInstance() {
        if (appManager == null)
            appManager = new AppManager();

        return appManager;
    }

    public boolean askUserToGrantPermission(final Activity activity, String[] permissions, int requestCode) {
        String permissionRequired = null;

        for (String permission : permissions)
            if (!isPermissionGranted(activity, permission)) {
                permissionRequired = permission;
                break;
            }

        // Check if the Permission is ALREADY GRANTED
        if (permissionRequired == null) return true;

        // Check if there is a need to show the PERMISSION DIALOG
        boolean explanationRequired = ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionRequired);

        ActivityCompat.requestPermissions(activity, permissions, requestCode);

        return false;
    }

    public boolean isPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
