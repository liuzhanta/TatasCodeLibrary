package com.tata.android.app;

import android.app.Application;

import com.tata.android.utils.ScreenUtils;

/**
 * Description:
 * Creator:Terry
 * Date:2017/5/3 下午4:39
 */

public class App extends Application {
    public static final boolean isDebug= false;

    @Override
    public void onCreate() {
        super.onCreate();

        ScreenUtils.initDeviceInfo(this);
    }
}
