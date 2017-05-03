package com.tata.android.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

public class DeviceUtils {
    
    /**
     * 获取设备号
     * 
     * @param context
     * @return
     * @modifiedTime 下午5:20:34
     * @author lzt
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        return deviceId != null ? deviceId : "-1";
    }
    
    /**
     * 得到屏幕的高度
     * 
     * @param context
     * @return
     * @modifiedTime 下午5:18:46
     * @author lzt
     */
    public static int getScreenHeight(Activity context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    
    /**
     * 得到屏幕的宽度
     * 
     * @param context
     * @return
     * @modifiedTime 下午5:18:46
     * @author lzt
     */
    public static int getScreenWidth(Activity context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    
}
