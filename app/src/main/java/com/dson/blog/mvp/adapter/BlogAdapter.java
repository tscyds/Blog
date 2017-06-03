package com.dson.blog.mvp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dson.blog.R;
import com.dson.blog.data.entity.Blog;

import java.util.List;

public class BlogAdapter extends BaseAdapter {

    private Context mContext;
    private List<Blog> mData;

    public BlogAdapter(Context context, List<Blog> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.blog_list_item, parent,
                    false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.blog_img);
            holder.title = (TextView) convertView.findViewById(R.id.blog_title);
            holder.summary = (TextView) convertView.findViewById(R.id.blog_summary);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Blog entity = (Blog) getItem(position);
        holder.title.setText(entity.getTitle());
        holder.summary.setText(entity.getSummary());
        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView title;
        TextView summary;
    }
}
