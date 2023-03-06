package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends PagerAdapter {

    private Context mContext;
    private List<Image> mListImage;

    public ImageAdapter(Context mContext, List<Image> mListImage) {
        this.mContext = mContext;
        this.mListImage = mListImage;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_carousel, container, false);
        ImageView imgView = view.findViewById(R.id.img_1);

        Image img = mListImage.get(position);
        if (img != null) {
            Glide.with(mContext).load(img.getSrc_Id()).into(imgView);
        }

        //add to viewgroup
        container.addView(view);

        return view;
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public int getCount() {
        if (mListImage != null) {
            return mListImage.size();
        } else
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //remove view
       container.removeView((View) object);
    }
}

