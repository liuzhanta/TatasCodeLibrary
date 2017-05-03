package com.tata.android.utils;


import android.app.Activity;
import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * Desc: Define a handler to avoid Handler leak
 * Author: Terry
 * Date:2015-10-29
 */
public class WeakHandler extends Handler {
    private WeakReference<Activity> mActivityWeakReference = null;

    public WeakHandler(Activity activity) {
        mActivityWeakReference = new WeakReference<Activity>(activity);
    }
}
