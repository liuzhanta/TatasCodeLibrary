package com.tata.android.ui.widget;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.tata.android.R;


/**
 * @Description：AbstractSharePopMenu
 * 
 * @author： lzt
 * 
 * @date：2014年5月8日 下午4:03:14
 */
public abstract class AbstractSharePopMenu implements OnClickListener, OnDismissListener {
    
    private Activity context;
    private PopupWindow mPopupWindow;
    private View mParentlayout;
    private WindowManager.LayoutParams lp;
    private Window window;
    
    public AbstractSharePopMenu(final Activity context) {
        this.context = context;
        mParentlayout = this.onCreatePopMenuView(LayoutInflater.from(context));
        if (mParentlayout == null) {
            throw new RuntimeException(
                    "Have you declared your layout id  in the method of 'onPopMenuLayoutInflate()' ?");
        }
        mPopupWindow = new PopupWindow(mParentlayout, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        
        initializePopSetting();
        onItemViewCreated(mParentlayout);
    }
    
    /**
     * Called when the popmenu has been created.
     * 
     * @param inflater
     * @return the parent view
     */
    protected abstract View onCreatePopMenuView(LayoutInflater inflater);
    
    /**
     * Called when the popmenu has been created.
     * 
     * @param parenteView
     */
    protected abstract void onItemViewCreated(View parenteView);
    
    /**
     * Called when the item view has been clicked
     * 
     * @param v The view that was clicked.
     */
    protected abstract void onItemClick(View v);
    
    private final void initializePopSetting() {
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(this);
        mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }
    
    @Override
    public void onDismiss() {
        // TODO Auto-generated method stub
        this.dismiss();
    }
    
    public void show() {
        if (mPopupWindow != null) {
            setWindowFake(true);
            mPopupWindow.showAtLocation(window.getDecorView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            mPopupWindow.update();
        }
    }
    
    /**
     * set the window fake
     * 
     * @param isTransluncent if true, the activity window will turn dark ,else
     *            turn back.
     */
    private void setWindowFake(boolean isTransluncent) {
        window = ((Activity) context).getWindow();
        lp = window.getAttributes();
        lp.alpha = isTransluncent ? 0.5f : 1.0f;
        window.setAttributes(lp);
    }
    
    public void dismiss() {
        if (mPopupWindow != null) {
            setWindowFake(false);
            mPopupWindow.dismiss();
        }
    }
    
    @Override
    public void onClick(View v) {
        onItemClick(v);
        dismiss();
    }
    
    public Activity getContext() {
        return context;
    }
}
