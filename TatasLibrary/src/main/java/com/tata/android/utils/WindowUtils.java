package com.tata.android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

/**
 * Desc:
 * Author: Terry
 * Date:2016-05-08
 */
public class WindowUtils {
    /**
     *  动态设置Activity是否全屏
     * @param context
     * @param isFullScreenEnabled
     */
    public static void setActivityFullScreen(Context context, boolean isFullScreenEnabled) {
        Activity activity = (Activity) context;
        final Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if (isFullScreenEnabled) {
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setAttributes(params);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            window.setAttributes(params);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }
}
