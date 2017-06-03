package com.dson.blog.data.source;


import android.support.annotation.NonNull;

import com.dson.blog.data.entity.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogRepository implements BlogDataSource {

    private static BlogRepository sInstance;

    private BlogRepository() {
    }

    public static BlogRepository getInstance() {
        if (sInstance == null) {
            synchronized (BlogRepository.class) {
                sInstance = new BlogRepository();
            }
        }
        return sInstance;
    }

    @Override
    public void getBlog(@NonNull LoadBlogCallback callback) {
        callback.onBlogLoaded(getAllBlog());
    }

    private List<Blog> getAllBlog() {
        List<Blog> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Blog entity = new Blog();
            entity.setTitle("title:" + i);
            entity.setSummary("summary:" + i);
            list.add(entity);
        }
        return list;
    }

    @Override
    public void refreshBlog() {

    }

    @Override
    public void deleteAllBlog() {

    }

    @Override
    public void deleteBlog(@NonNull String BlogId) {

    }
}
