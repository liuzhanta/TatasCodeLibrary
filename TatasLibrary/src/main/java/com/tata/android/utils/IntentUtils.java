package com.tata.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

/**
 * 
 * 
 * 名称：IntentUtils
 * 
 * 描述：IntentUtils工具类
 * 
 * 修改时间：2013-12-30 下午4:31:57
 * 
 * @author lzt
 * 
 */
public class IntentUtils {
    /** {@hide} */
    private IntentUtils() {
    };
    
    public static final String EXTRA_KEY_DATA = "extra_key_data";
    public static final String EXTRA_KEY_SERIALIBLE_DATA = "extra_key_serialible_data";
    
    public static void jumpToActivity(Context context, Class<? extends Activity> clz) {
        jumpToActivityWithData(context, clz, null);
    }
    
    public static void jumpToActivityWithData(Context context, Class<? extends Activity> c, Bundle data) {
        Intent it = new Intent(context, c);
        if (data != null) {
            it.putExtra(EXTRA_KEY_DATA, data);
        }
        context.startActivity(it);
    }
    
    public static void jumpToActivity(Context context, Class<? extends Activity> c, Intent intent) {
        intent.setClass(context, c);
        context.startActivity(intent);
    }
    
    public static <T extends Serializable> void jumpToActivity(Context context, Class<? extends Activity> clz, T data) {
        jumpToActivity(context, clz, EXTRA_KEY_SERIALIBLE_DATA, data);
    }
    
    public static <T extends Serializable> void jumpToActivity(Context context, Class<? extends Activity> clz, String name, T data) {
        Intent intent = new Intent();
        intent.setClass(context, clz);
        intent.putExtra(name, data);
        context.startActivity(intent);
    }
    
    public static void jumpToActivityForResult(Activity activity, Class<? extends Activity> c, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, c);
    }
    
    public static void jumpToActivity(Context context, Class<? extends Activity> c, String action) {
        jumpToActivity(context, c, new Intent(action));
    }
    
    public static <T extends Serializable> Serializable getSerializableData(Intent intent) {
        if (intent == null)
            return null;
        return intent.getSerializableExtra(EXTRA_KEY_SERIALIBLE_DATA);
    }
    
}
