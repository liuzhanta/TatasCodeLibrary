package com.tata.android.ui.base;

import android.os.Bundle;
import android.os.Handler;

import com.tata.mnbz.R;
import com.tata.mnbz.activity.MainActivity;

/**
 * Desc:
 * Author: Terry
 * Date:2016-04-10
 */
public class WelcomeActivity extends BaseActivity {

    private static final long DURATION = 5*1000;

    @Override
    protected void onContentViewCreated(Bundle savedInstanceState) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpToActivity(MainActivity.class);
                finish();
            }
        },DURATION);



    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_welcome;
    }
}
