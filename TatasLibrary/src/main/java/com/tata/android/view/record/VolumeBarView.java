package com.tata.android.view.record;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;


import com.tata.android.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Creator:Terry
 * Date:2017/4/20 下午3:25
 */
public class VolumeBarView extends LinearLayout {

    private static final String TAG = "VolumeBarView";

    public static final int MAX_BAR_SIZE = 8;
    private static final int singleBarHeight = ScreenUtils.dipToPx(3);
    private static final int singleBarWidth = ScreenUtils.dipToPx(15);
    private static final int barSpacing = ScreenUtils.dipToPx(2);

    private final List<VolumeView> volumeViews = new ArrayList<>(MAX_BAR_SIZE);

    public VolumeBarView(Context context) {
        super(context);
        init(context);
    }

    public VolumeBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        refreshVolumeBar();
    }

    public VolumeBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        refreshVolumeBar();
    }

    /**
     * Current volume value
     */
    private int mVolumeValue = 0;

    public int getVolumeValue() {
        return this.mVolumeValue;
    }

    public void setVolumeValue(int mVolumeValue) {
        this.mVolumeValue = mVolumeValue;
        refreshVolumeBar();
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        int width = singleBarWidth;
        for (int i = 0; i < MAX_BAR_SIZE; i++) {
            VolumeView volumeView = new VolumeView(context);
            LayoutParams lp = new LayoutParams(width, singleBarHeight);
            lp.topMargin = barSpacing;
            width--;
            addView(volumeView, lp);
            volumeViews.add(volumeView);
        }
    }

    private void refreshVolumeBar() {
        int j = 0;
        for (int i = volumeViews.size() - 1; i >= 0; i--) {
            final VolumeView volumeView = volumeViews.get(i);
            if (mVolumeValue == 0) {
                volumeView.setFilled(false);
            } else {
                if (mVolumeValue > j) {
                    volumeView.setFilled(true);
                } else {
                    volumeView.setFilled(false);
                }
            }
            j++;
        }
    }

}
