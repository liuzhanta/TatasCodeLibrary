package com.tata.android.ui.base;


import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tata.mnbz.R;

/**
 * Desc:
 * Author: Terry
 * Date:2016-04-07
 */
public abstract class BaseToolBarActivity extends BaseActivity {

    public Toolbar toolbar;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Toolbar getToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setFitsSystemWindows(true);
        }
        if (toolbar == null) {
            throw new IllegalArgumentException("Have you put a Toolbar layout in your content view ");
        }
        //Toolbar initializing
        toolbar.setTitleTextColor(Color.WHITE);
        return toolbar;
    }

    public void setupToolBar() {
        setupToolBar(null);

    }

    public void setupToolBar(int titleId) {
        setupToolBar(titleId, null);
    }

    public void setupToolBar(final View.OnClickListener onBackClickListener) {
        setupToolBar(0, onBackClickListener);
    }

    public void setupToolBar(int resId, final View.OnClickListener onBackClickListener) {
        Toolbar toolbar = this.getToolbar();
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_navi_back);
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
        if (resId != 0) {
            setTitle(resId);
        }
    }

}
