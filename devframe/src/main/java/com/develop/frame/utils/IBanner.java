package com.develop.frame.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.develop.frame.bridge.OnBannerClickListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

/**
 * Created by wangning on 2018/9/5.
 */

public class IBanner implements OnBannerListener {

    private static IBanner bannerUtil;

    private Banner banner;

    private OnBannerClickListener bannerClickListener;

    public static IBanner getBanner(Banner banner) {
        return bannerUtil = new IBanner(banner);
    }

    private IBanner(Banner banner) {
        this.banner = banner;
        initBanner();
    }

    private void initBanner() {
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new MyLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置banner点击事件
        banner.setOnBannerListener(this);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.NOT_INDICATOR);
    }

    /**
     * 设置图片路径集合和标题集合
     *
     * @param imgList
     * @param titleList
     * @return
     */
    public IBanner setBannerContent(List<String> imgList, List<String> titleList) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        if (imgList != null && titleList != null && imgList.size() == titleList.size()) {
            banner.update(imgList, titleList);
        } else {
            ILog.e("设置banner的参数格式不正确");
        }
        return bannerUtil;
    }

    /**
     * 设置内容
     *
     * @param imgList
     * @return
     */
    public IBanner setBannerContent(List<String> imgList) {
        if (imgList != null) {
            banner.update(imgList);
        }
        return bannerUtil;
    }

    /**
     * 设置banner的点击事件
     *
     * @param bannerClickListener
     * @return
     */
    public IBanner setOnBannerClickListener(OnBannerClickListener bannerClickListener) {
        this.bannerClickListener = bannerClickListener;
        return bannerUtil;
    }

    /**
     * 开始播放
     *
     * @return
     */
    public IBanner start() {
        banner.start();
        return bannerUtil;
    }

    @Override
    public void OnBannerClick(int position) {
        if (bannerClickListener != null) {
            bannerClickListener.bannerClick(position);
        }
    }

    private class MyLoader extends ImageLoader {

        @Override

        public void displayImage(Context context, Object path, ImageView imageView) {

            Glide.with(context).load((String) path).into(imageView);       //传入路径,因为list为String格式,path为Object格式,所以强制类型转换.
        }
    }
}
