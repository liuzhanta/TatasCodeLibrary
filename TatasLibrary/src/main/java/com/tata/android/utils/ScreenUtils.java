package com.tata.android.utils;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.tata.android.config.Config;

public class ScreenUtils {

    public static int dipToPx(float dip) {
        return (int) (dip * Config.DeviceInfo.density + 0.5f);
    }

    public static void initDeviceInfo(Application application) {
        DisplayMetrics dm = application.getResources().getDisplayMetrics();
        Config.DeviceInfo.screenHeight = dm.heightPixels;
        Config.DeviceInfo.screenWidth = dm.widthPixels;
        Config.DeviceInfo.density = dm.density;
        Config.DeviceInfo.densityDpi = dm.densityDpi;
        Config.DeviceInfo.scaledDensity = dm.scaledDensity;
        Config.DeviceInfo.densityX = dm.xdpi;
        Config.DeviceInfo.densityY = dm.ydpi;
    }
}