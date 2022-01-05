package com.develop.frame.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by wangning on 2019/12/22.
 */

public class IGlide {
    /**
     * 默认的圆角角度
     */
    public static final int DEFAULT_RADIUS = 5;

    /**
     * 展示图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void showImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImg(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .apply(circleOptions())
                .into(imageView);
    }


    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param radius
     */
    public static void loadRoundImg(Context context, String url, ImageView imageView, int radius) {
        Glide.with(context).load(url)
                .apply(roundCornersOptions(radius))
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRoundImg(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .apply(roundCornersOptions(DEFAULT_RADIUS))
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param radius
     * @return
     */
    private static RequestOptions roundCornersOptions(int radius) {
        return new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).transform(new RoundedCorners(radius));
    }

    /**
     * 加载圆形图片
     *
     * @return
     */
    private static RequestOptions circleOptions() {
        return new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).transform(new CircleCrop());
    }
}
