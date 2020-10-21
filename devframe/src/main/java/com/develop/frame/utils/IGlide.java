package com.develop.frame.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by wangning on 2019/12/22.
 */

public class IGlide {
    /**
     * 展示图片
     *
     * @param context
     * @param url
     * @param iv
     */
    public static void showImage(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(iv);
    }
}
