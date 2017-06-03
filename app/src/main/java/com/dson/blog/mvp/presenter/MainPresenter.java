package com.dson.blog.mvp.presenter;


import com.dson.blog.data.entity.Blog;
import com.dson.blog.data.source.BlogDataSource;
import com.dson.blog.data.source.BlogRepository;
import com.dson.blog.mvp.contract.MainContract;

import java.util.List;



public class MainPresenter implements MainContract.Presenter {

    private BlogRepository mRepository;
    private MainContract.View mView;

    public MainPresenter(BlogRepository blogRepository, MainContract.View view) {
        this.mRepository = blogRepository;
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadBlog();
    }

    @Override
    public void loadBlog() {
        mRepository.getBlog(new BlogDataSource.LoadBlogCallback() {
            @Override
            public void onBlogLoaded(List<Blog> blog) {
                mView.showBlog(blog);
            }

            @Override
            public void onDataNotAvailable() {
                mView.showNoBlog();
            }
        });
    }
}
