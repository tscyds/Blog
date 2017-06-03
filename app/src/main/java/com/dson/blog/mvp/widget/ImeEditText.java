package com.dson.blog.mvp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class ImeEditText extends EditText {
    private OnKeyPreImeListener mOnKeyPreImeListener;

    public ImeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImeEditText(Context context) {
        super(context);
    }

    public void setOnKeyPreImeListener(OnKeyPreImeListener listener) {
        mOnKeyPreImeListener = listener;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        return mOnKeyPreImeListener != null ? mOnKeyPreImeListener.onKeyPreIme(keyCode, event) :
                super.onKeyPreIme(keyCode, event);
    }

    public interface OnKeyPreImeListener {
        boolean onKeyPreIme(int keyCode, KeyEvent event);
    }
}
