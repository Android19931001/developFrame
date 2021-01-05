package com.develop.frame.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.develop.frame.base.BaseAppActivity;

/**
 * Created by wangning on 2018/2/26.
 */

public class IVersion {

    /**
     * 获取版本号  versionCode 1.0
     *
     * @return
     */
    public static String getVersionCode() {
        try {
            return getPackageInfo().versionCode + "";
        } catch (Exception e) {
            ILog.e("获取版本号异常：" + IGson.iJsonStr(e));
        }
        return null;
    }

    /**
     * 获取版本名 versionName --1.1.1
     *
     * @return
     */
    public static String getVersionName() {
        try {
            return getPackageInfo().versionName;
        } catch (Exception e) {
            ILog.e("获取版本号异常：" + IGson.iJsonStr(e));
        }
        return null;
    }

    /**
     * 获取包类信息类
     *
     * @return
     */
    private static PackageInfo getPackageInfo() {
        PackageInfo pi = null;
        try {
            PackageManager pm = BaseAppActivity.mActivity.getPackageManager();
            pi = pm.getPackageInfo(BaseAppActivity.mActivity.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
}
