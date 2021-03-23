package com.wb.wxchatutil;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;

public class Utils {


    public static void openActivity(Context context, String appName) {
        PackageManager manager = context.getPackageManager();
        Intent intent = manager.getLaunchIntentForPackage(appName);
        context.startActivity(intent);
    }

    public static void openAccessible(Context context) {
        Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        context.startActivity(accessibleIntent);
    }
}
