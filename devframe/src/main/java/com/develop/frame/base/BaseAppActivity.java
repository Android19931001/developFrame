package com.develop.frame.base;

import android.app.Application;
import android.content.Context;

import com.develop.frame.network.ApiGenerator;

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
     * 初始化retrofit
     *
     * @param url
     */
    public void initNetWork(String url) {
        ApiGenerator.init(url);
    }

    /**
     * 是否开启日志打印
     */
    public void isOpenLog(boolean isOpenLog) {
        this.IS_OPEN_LOG = isOpenLog;
    }

}
