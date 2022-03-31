package com.develop.frame.base;

import android.app.Application;
import android.content.Context;

import com.develop.frame.network.ApiGenerator;
import com.develop.frame.utils.ISp;

/**
 * Created by wangning on 2019/12/24.
 */

public class BaseAppActivity extends Application {

    public static Context mActivity;

    public boolean IS_OPEN_LOG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mActivity = getApplicationContext();

    }

    /**
     * 初始化各种配置
     */
    public void initSetting() {
        ISp.initSp();
    }


    /**
     * Init retrofit settings
     *
     * @param url Your project's request url
     */
    public void initNetWork(String url) {
        ApiGenerator.init(url);
    }


    /**
     * Is need open log print
     *
     * @param isOpenLog It suggest user your project's "BuildConfig.Debug" as param,it can
     *                  distinguish with debug and release model
     */
    public void isOpenLog(boolean isOpenLog) {
        this.IS_OPEN_LOG = isOpenLog;
    }

}
