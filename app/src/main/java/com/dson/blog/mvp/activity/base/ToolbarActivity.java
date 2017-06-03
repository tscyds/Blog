package com.dson.blog.mvp.activity.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public abstract class ToolbarActivity extends AppCompatActivity {

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void onBindToolbar();

    @CallSuper
    @Override
    public void setTitle(int titleResId) {
        setTitle(getString(titleResId));
    }

    @CallSuper
    @Override
    public void setTitle(CharSequence title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    protected void setNavigationIcon(@DrawableRes int resId) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(resId);
        }
    }

    protected void initActionBar() {
        onBindToolbar();
        if (mToolbar == null) {
            return;
        }
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getTitle());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
