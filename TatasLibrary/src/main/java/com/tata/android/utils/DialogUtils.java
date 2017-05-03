package com.tata.android.utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.tata.android.R;


public class DialogUtils {

    /**
     * 显示自定义dialog
     *
     * @param activity
     * @param title
     * @param content
     * @Version 2.8.0
     */
    public static void showCustomDialog(final Activity activity, String title, String content,
                                        final OnConfirmButtonClickListener confimListener, final OnCancleButtonClickListener canclelistener,boolean showCancel) {
        final Dialog dialog = new Dialog(activity, R.style.Theme_Light_FullScreenDialogAct);
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.dialog_template, null);

        TextView cancleButton = (TextView) dialogView.findViewById(R.id.btn_cancle);
        TextView confirmButton = (TextView) dialogView.findViewById(R.id.btn_confirm);
        TextView contentView = (TextView) dialogView.findViewById(R.id.tv_dialog_content);
        TextView tittle = (TextView) dialogView.findViewById(R.id.tv_dialog_title);

        // 设置内容
        contentView.setText(content);
        // 设置标题
        View titleView = dialogView.findViewById(R.id.ll_dialog_title);
        if (TextUtils.isEmpty(title)) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setVisibility(View.VISIBLE);
            tittle.setText(title);
        }
        if(!showCancel)cancleButton.setVisibility(View.GONE);

        // 设置监听
        cancleButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                if (canclelistener != null) {
                    canclelistener.onCancleButtonClick(v);
                }
                if (dialog != null){
                    dialog.cancel();
//                	dimBackground(activity,0.5f,1.0f);
                }
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                if (confimListener != null) {
                    confimListener.onConfirmButtonClick(v);
                }
                if (dialog != null){
                    dialog.cancel();
//                	dimBackground(activity,0.5f,1.0f);
                }
            }
        });
        dialog.setContentView(dialogView);
        configureDialog(activity, dialog, 0.85);
//        dimBackground(activity,1.0f,0.5f);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    /**
     * 显示确认按钮
     */
    public static void showOkDialog(Activity activity, String content, OnConfirmButtonClickListener listener){
        showCustomDialog(activity, activity.getString(R.string.dialog_title),
                content, listener, null,false);
    }

    /**
     * 显示退出登陆dialog2个按钮
     *
     * @param activity
     * @param listener
     * @Version 2.8.0
     */
    public static void showLogOutDialog(Activity activity, OnConfirmButtonClickListener listener) {
        showCustomDialog(activity, activity.getString(R.string.dialog_title),
                activity.getString(R.string.dialog_content_logout), listener, null,true);
    }

    /**
     * 配置dialog
     *
     * @param a
     * @param dialog
     * @param withRation dialog屏占比
     * @Author Terry Liu
     */
    public static void configureDialog(Activity a, Dialog dialog, double withRation) {
        WindowManager wm = a.getWindowManager();
        int screenWidth = wm.getDefaultDisplay().getWidth();
        Window window = dialog.getWindow();
        LayoutParams params = window.getAttributes();
        params.width = (int) (screenWidth * withRation);
        window.setAttributes(params);
    }

    /**
     * 调整窗口的透明度
     * @param from>=0&&from<=1.0f
     * @param to>=0&&to<=1.0f
     *
     * */
    public static void dimBackground(Activity a,final float from, final float to) {
        final Window window = a.getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (Float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });

        valueAnimator.start();
    }

    public interface OnConfirmButtonClickListener {
        void onConfirmButtonClick(View v);
    }

    public interface OnCancleButtonClickListener {
        void onCancleButtonClick(View v);
    }
}
