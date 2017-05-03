package com.tata.android.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.tata.android.R;


/**
 * 
 * @Description： a little dot view that you can define the normal color and
 *               selected color decided by the state of the whether the view is
 *               selected or not.
 *
 * @Author : Terry Liu
 *
 * @Date：2014年8月14日 下午3:10:50
 */
public class DotView extends View {
    
    private int dotColorNormal = Color.WHITE;
    private int dotColorSelected = Color.GRAY;
    private int dotColor = dotColorNormal;
    private int dotCircle = 0;
    private Paint paint = new Paint();
    
    public DotView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        
    }
    
    public DotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        
    }
    
    public void setSelected(boolean selected) {
        dotColor = selected ? dotColorSelected : dotColorNormal;
        invalidate();
    }
    
    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        TypedArray ty = context.obtainStyledAttributes(attrs, R.styleable.DotView);
        int attrSize = ty.getIndexCount();
        for (int i = 0; i < attrSize; i++) {
            int attr = ty.getIndex(i);
            if (attr == R.styleable.DotView_dotColor_Normal) {
                dotColorNormal = ty.getColor(attr, Color.WHITE);

            } else if (attr == R.styleable.DotView_dotColor_Selected) {
                dotColorSelected = ty.getColor(attr, Color.GRAY);

            } else if (attr == R.styleable.DotView_dotCircle) {
                dotCircle = ty.getInteger(attr, 10);

            }
        }
        dotColor = dotColorNormal;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(dotColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
        canvas.restore();
    }
    
}
