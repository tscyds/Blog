package com.dson.blog.mvp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dson.blog.R;
import com.dson.blog.data.entity.Blog;
import com.dson.blog.mvp.adapter.BlogAdapter;
import com.dson.blog.mvp.contract.MainContract;

import java.util.List;

public class MainFragment extends Fragment implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private ListView mListView;

    public static MainFragment getMainFragment() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_main, container,
                false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = (ListView) getActivity().findViewById(R.id.list_view);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showBlog(List<Blog> blogList) {
        BlogAdapter blogAdapter = new BlogAdapter(this.getContext(), blogList);
        mListView.setAdapter(blogAdapter);
    }

    @Override
    public void showNoBlog() {

    }
}
