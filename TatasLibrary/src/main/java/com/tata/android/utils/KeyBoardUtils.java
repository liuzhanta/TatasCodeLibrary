package com.tata.android.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Desc:软键盘工具
 * Author: Terry
 * Date:2016-04-28
 */
public class KeyBoardUtils {

    private KeyBoardUtils() {

    }

    public static void hideKeyBoard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View focusView = ((Activity) context).getCurrentFocus();
            if (focusView != null) {
                inputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static void hideKeyBoard(Context context, EditText... editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (editText != null) {
            for (int i = 0; i < editText.length; i++) {
                imm.hideSoftInputFromWindow(editText[i].getWindowToken(), 0);
            }
        }
    }

    public static void showKeyBoard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View focusView = ((Activity) context).getCurrentFocus();
            if (focusView != null) {
                inputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.SHOW_FORCED);
            }
        }
    }

    /**
     * @param context
     * @param currentFocusView 手动指定当前的焦点
     */
    public static void showKeyBoard(Context context, View currentFocusView) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            if (currentFocusView != null) {
                inputMethodManager.showSoftInput(currentFocusView, 0);
            }
        }
    }

    public static boolean isKeyBoardShown(Context context) {
        return ((Activity) context).getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;
    }

    public static void showKeyBoardDelayed(final Context context, final View currentFocusView) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               showKeyBoard(context, currentFocusView);
                           }
                       },
                1000);
    }
}
