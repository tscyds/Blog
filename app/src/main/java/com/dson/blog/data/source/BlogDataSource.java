package com.dson.blog.data.source;


import android.support.annotation.NonNull;

import com.dson.blog.data.entity.Blog;

import java.util.List;

public interface BlogDataSource {

    interface LoadBlogCallback {

        void onBlogLoaded(List<Blog> blog);

        void onDataNotAvailable();
    }

    void getBlog(@NonNull LoadBlogCallback callback);

    void refreshBlog();

    void deleteAllBlog();

    void deleteBlog(@NonNull String BlogId);
}
