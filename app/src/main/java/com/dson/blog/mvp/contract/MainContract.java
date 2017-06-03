package com.dson.blog.mvp.contract;


import com.dson.blog.data.entity.Blog;
import com.dson.blog.mvp.BasePresenter;
import com.dson.blog.mvp.BaseView;

import java.util.List;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void showBlog(List<Blog> blogList);

        void showNoBlog();
    }

    interface Presenter extends BasePresenter {
        void loadBlog();
    }
}
