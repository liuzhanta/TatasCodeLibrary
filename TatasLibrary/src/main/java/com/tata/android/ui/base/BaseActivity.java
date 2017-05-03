package com.tata.android.ui.base;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tata.android.R;
import com.tata.android.utils.ToastUtils;

public abstract class BaseActivity extends AppCompatActivity {

    //Common tag for all classes which implement the BaseActivity
    public String TAG = this.getClass().getSimpleName();

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
    }

    protected Toolbar getActionBarToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        if (mToolbar != null) {
            mToolbar.setFitsSystemWindows(true);
        }
        return mToolbar;
    }


    public void initToolBar(int resId, final View.OnClickListener onBackClickListener) {
        if (resId == 0) {
            throw new IllegalArgumentException("The resId of Toolbar must not be 0 .");
        }
        initToolbar(getString(resId), onBackClickListener);
    }

    public void initToolBar(int resId) {
        if (resId == 0) {
            throw new IllegalArgumentException("The resId of Toolbar must not be 0 .");
        }
        initToolbar(getString(resId), null);
    }

    private void initToolbar(CharSequence title, final View.OnClickListener onBackClickListener) {
        Toolbar toolbar = this.getActionBarToolbar();
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBackClickListener != null)
                    onBackClickListener.onClick(view);
                else {
                    finish();
                }
            }
        });
        setTitle(title);
    }


    private void initActionBar() {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT || Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT_WATCH) {
            //if the current activity is the Login Activity,then do not set set translucent status
            //which is aimed to adapt the login page.
            setTranslucentStatus(true);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //load from /src/res/values-v21
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(getResources().getColor(R.color.statusbar_bg));
        }
    }

    /**
     * set status bar color dynamically
     *
     * @param color
     */
    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * Get the current activity layout res id
     *
     * @return
     */
    protected abstract int getContentViewId();


    public void setAppBarElevation(float elevation) {
        if (mToolbar == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setElevation(elevation);
        } else {

        }
    }

    public void show(int resId) {
        ToastUtils.showToast(this, resId);
    }

    public void show(CharSequence message) {
        ToastUtils.showToast(this, String.valueOf(message));
    }
}
