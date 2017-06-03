package com.dson.blog.mvp.activity.base;


import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dson.blog.R;
import com.dson.blog.util.Logger;

public abstract class BaseActivity extends ToolbarActivity {

    protected FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        frameLayout = (FrameLayout) findViewById(R.id.base_layout_container);
        initActionBar();
    }

    @CallSuper
    @Override
    public void setContentView(int layoutResID) {
        frameLayout.addView(LayoutInflater.from(this).inflate(layoutResID, frameLayout, false));
    }

    @CallSuper
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        frameLayout.addView(view, params);
    }

    @CallSuper
    @Override
    public void setContentView(View view) {
        frameLayout.addView(view);
    }

    @Override
    public void onBindToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.base_toolbar);
    }

    public abstract String getActivityTag();

    public void debug(String msg) {
        Logger.debug(getActivityTag(), msg);
    }

    public void error(String msg) {
        Logger.error(getActivityTag(), msg);
    }
}
