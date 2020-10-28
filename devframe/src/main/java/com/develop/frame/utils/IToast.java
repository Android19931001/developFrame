package com.develop.frame.utils;

import android.content.Context;
import android.widget.Toast;

import com.develop.frame.base.BaseAppActivity;

/**
 * Created by wangning on 2020/8/21.
 */

public class IToast {

    /**
     * 吐司时间短
     *
     * @param msg
     */
    public static void show(final String msg) {
        Toast.makeText(BaseAppActivity.mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 吐司时间长
     *
     * @param msg
     */
    public static void showLong(String msg) {
        Toast.makeText(BaseAppActivity.mActivity, msg, Toast.LENGTH_LONG).show();
    }


    /**
     * 传入上下文弹出吐司
     *
     * @param context
     * @param msg
     */
    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
