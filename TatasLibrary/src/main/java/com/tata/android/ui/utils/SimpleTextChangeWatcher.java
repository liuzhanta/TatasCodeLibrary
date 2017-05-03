package com.tata.android.ui.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.widget.EditText;

/**
 * Desc: A simple Text watcher to watch whether the all of TextFields you have been put in the array is filled or not.
 * Author: Terry
 * Date:2015-07-30
 */
public class SimpleTextChangeWatcher implements TextWatcher {

    public static final String TAG = "SimpleTextChangeWatcher";

    private final SparseArray<EditTextContent> mEditTextContentSparseArray = new SparseArray<>();

    static class EditTextContent {
        public EditTextContent(EditText et, boolean isFilled) {
            this.et = et;
            this.isFilled = isFilled;
        }

        public EditText et;
        public boolean isFilled;
    }

    public void watch(EditText... editTexts) {
        if (editTexts != null)
            for (EditText e : editTexts) {
                mEditTextContentSparseArray.put(e.getId(), new EditTextContent(e, false));
                e.addTextChangedListener(this);
            }
    }

    private OnTextFillListener onTextFillListener;

    public void setOnTextFillListener(OnTextFillListener onTextFillListener) {
        this.onTextFillListener = onTextFillListener;
    }

    @Override
    public void afterTextChanged(Editable s) {
        for (int i = 0; i < mEditTextContentSparseArray.size(); i++) {
            final EditTextContent etc = mEditTextContentSparseArray.valueAt(i);
            etc.isFilled = !TextUtils.isEmpty(etc.et.getText().toString());
            mEditTextContentSparseArray.put(mEditTextContentSparseArray.keyAt(i), etc);
        }
        for (int j = 0, size = mEditTextContentSparseArray.size(); j < size; j++) {
            if (!mEditTextContentSparseArray.valueAt(j).isFilled) {
                onTextFillListener.onAllTextFill(false);
                break;
            }
            //if all text fields has been filled , then invoked the method of onAllTextFill(true).
            if (j == (size - 1) && mEditTextContentSparseArray.valueAt(j).isFilled) {
                onTextFillListener.onAllTextFill(true);
            }

        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    /**
     * Interface definition for a callback to be invoked when the text field is filled。
     */
    public interface OnTextFillListener {
        /**
         * Called when the all text field has been filled.
         *
         * @param isFilled
         */
        void onAllTextFill(boolean isFilled);
    }

}
