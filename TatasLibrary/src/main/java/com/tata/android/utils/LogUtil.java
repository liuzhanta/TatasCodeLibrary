package com.tata.android.utils;

import android.util.Log;

import com.tata.android.app.App;


public class LogUtil {
    private static final int V = 5;
    private static final int D = 4;
    private static final int I = 3;
    private static final int W = 2;
    private static final int E = 1;
    private static final int N = 0;
    //更改level
    public static int level = App.isDebug ? V : N;

    public static void v(String tag, String msg) {
        if (level >= V) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level >= D) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (level >= I) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (level >= W) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (level >= E) {
            Log.e(tag, msg);
        }
    }

}
