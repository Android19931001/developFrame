package com.develop.frame.utils;

import android.os.Build;

/**
 * Created by wangning on 2020/8/24.
 */

public class IPhone {

    /**
     * 获取手机系统版本号
     *
     * @return
     */
    public static String iVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 手机品牌
     *
     * @return
     */
    public static String iModel() {
        return Build.MODEL;
    }

    /**
     * 手机厂商
     *
     * @return
     */
    public static String iBrand() {
        return Build.BRAND;
    }

}
