package com.dson.blog.mvp.activity;

import android.view.LayoutInflater;

import com.dson.blog.R;
import com.dson.blog.mvp.activity.base.BaseActivity;
import com.dson.blog.mvp.widget.SearchView;

public class SearchToolbarActivity extends BaseActivity {

    protected SearchView mSearchView;

    @Override
    public void onBindToolbar() {
        super.onBindToolbar();
        LayoutInflater.from(this).inflate(R.layout.search_view, mToolbar, true);
        mSearchView = (SearchView) mToolbar.findViewById(R.id.searchview);
    }

    @Override
    public String getActivityTag() {
        return SearchToolbarActivity.class.getSimpleName();
    }
}
