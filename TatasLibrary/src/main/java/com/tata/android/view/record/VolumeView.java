package com.tata.android.view.record;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.tata.android.utils.ScreenUtils;

/**
 * Class: VolumeView <br>
 * Description: 声音 <br>
 * Author: Terry <br>
 * Date: 2017/4/20 下午2:27
 */
public class VolumeView extends View {

    private Paint mRectanglePaint;
    private int mBorderColor = Color.parseColor("#232528");
    private int mFillColor = Color.parseColor("#FF5E5E");
    private int mNormalColor = Color.parseColor("#4D4E52");

    private int mRoundCorderRadius = 4;
    private int singleBarHeight = ScreenUtils.dipToPx(3);
    private int singleBarWidth = ScreenUtils.dipToPx(15);

    public VolumeView(Context context) {
        super(context);
        initPaint();
    }

    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    public VolumeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    private void initPaint() {
        mRectanglePaint = new Paint();
        mRectanglePaint.setAntiAlias(true);
        mRectanglePaint.setColor(mNormalColor);
        mRectanglePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRectanglePaint.setStrokeWidth(3);
        mRectanglePaint.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rect = new RectF();
        rect.top = 0;
        rect.left = 0;
        rect.right = singleBarWidth;
        rect.bottom = singleBarHeight;
        canvas.drawRoundRect(rect, mRoundCorderRadius, mRoundCorderRadius, mRectanglePaint);
    }

    public void setFilled(boolean isFilled) {
        mRectanglePaint.setColor(isFilled ? mFillColor : mNormalColor);
        invalidate();

    }
}
