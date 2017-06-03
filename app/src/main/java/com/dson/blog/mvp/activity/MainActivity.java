package com.dson.blog.mvp.activity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.dson.blog.R;
import com.dson.blog.data.source.BlogRepository;
import com.dson.blog.mvp.fragment.MainFragment;
import com.dson.blog.mvp.presenter.MainPresenter;
import com.dson.blog.mvp.widget.SearchView;

public class MainActivity extends SearchToolbarActivity implements SearchView.SearchListener {

    ViewGroup mContainer;

    MainPresenter mPresenter;

    MainFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = (ViewGroup) findViewById(R.id.blog_container);
        mSearchView.setSearchListener(this);
        showMainFragment();
        initPresenter();
    }

    private void showMainFragment() {
        if (mFragment == null) {
            mFragment = MainFragment.getMainFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.blog_container, mFragment,
                MainFragment.class.getSimpleName()).commit();
    }

    private void initPresenter() {
        mPresenter = new MainPresenter(BlogRepository.getInstance(), mFragment);
    }

    @Override
    public void onQueryTextChanged(String text) {
        debug("onQueryTextChanged:" + text);
    }

    @Override
    public void onQueryTextClear() {

    }

    @Override
    public void onExecuteQuery(String text) {

    }

    @Override
    public void onQueryError() {

    }

    @Override
    public String getActivityTag() {
        return MainActivity.class.getSimpleName();
    }
}
