package com.tata.android.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 名称：T
 * <p/>
 * 描述：Toast工具类
 * <p/>
 * 修改时间：2013-12-30 下午4:24:43
 *
 * @author lzt
 */
public class ToastUtils {

    public static void showToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }
}
