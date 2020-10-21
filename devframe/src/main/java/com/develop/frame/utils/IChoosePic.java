package com.develop.frame.utils;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

/**
 * Created by wangning on 2018/9/10.
 */

public class IChoosePic {

    private static ISListConfig config;

    /**
     * 配置选择图片
     *
     * @return
     */
    private static ISListConfig getConfig() {
        return config = new ISListConfig.Builder()
                // 是否多选, 默认true
                .multiSelect(true)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.parseColor("#2c68a6"))
                // “确定”按钮文字颜色
                .btnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#2c68a6"))
                // 标题
                .title("图片")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                //是否裁剪
                .needCrop(false)
                // TitleBar背景色
                .titleBgColor(Color.parseColor("#2c68a6"))
                // 裁剪大小。needCrop为true的时候配置
                .cropSize(1, 1, 600, 600)
                // 第一个是否显示相机，默认true
                .needCamera(true)
                // 最大选择图片数量，默认9
                .maxNum(1)
                .build();
    }

    /**
     * 跳到选择图片界面
     */
    public static void toListActivity(Context context, int requestCode) {
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        ISNav.getInstance().toListActivity(context, getConfig(), requestCode);
    }

}
