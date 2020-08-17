package com.alexanderdimaris.bookapiapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.alexanderdimaris.bookapiapp.model.Book;
import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<Book> mBooks;

    public MyAdapter(Context context, List<Book> books) {
        mContext = context;
        mBooks = books;
    }

    @Override
    public int getCount() {
        return mBooks.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(375, 600));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        String url = mBooks.get(position).getImage();
        Glide.with(mContext).load(url).centerCrop().into(imageView);
        return imageView;
    }
}
