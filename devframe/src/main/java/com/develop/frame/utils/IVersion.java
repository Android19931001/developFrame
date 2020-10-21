package com.develop.frame.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by wangning on 2018/2/26.
 */

public class IVersion {

    /**
     * 获取版本号  versionCode 1.0
     *
     * @param context
     * @return
     */
    public static long getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    /**
     * 获取版本名 versionName --1.1.1
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    /**
     * 获取包类信息类
     *
     * @param context
     * @return
     */
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
}
