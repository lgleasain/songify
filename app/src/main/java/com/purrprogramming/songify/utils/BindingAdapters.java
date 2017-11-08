package com.purrprogramming.songify.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public final class BindingAdapters {

    private BindingAdapters(){

    }

    @BindingAdapter("songUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        RequestOptions options = new RequestOptions();
        options.fitCenter();
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
