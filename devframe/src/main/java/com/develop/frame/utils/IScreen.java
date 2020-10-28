package com.develop.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by wangning on 2018/1/10.
 */

public class IScreen {

    private static DisplayMetrics getMetrics(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    private static Display getDisplay(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }

    /**
     * 获取高度
     *
     * @param context
     * @return
     */
    public static int height(Context context) {
        return getDisplay(context).getHeight();
    }

    /**
     * 获取宽度
     *
     * @param context
     * @return
     */
    public static int width(Context context) {
        return getDisplay(context).getWidth();
    }

    /**
     * 屏幕高度（像素）
     *
     * @param activity
     * @return
     */
    public static int height(Activity activity) {
        return getMetrics(activity).heightPixels;
    }

    /**
     * 屏幕宽度（像素）
     *
     * @param activity
     * @return
     */

    public static int width(Activity activity) {
        return getMetrics(activity).widthPixels;
    }

    /**
     * 屏幕密度（0.75 / 1.0 / 1.5）
     *
     * @param activity
     * @return
     */
    public static float density(Activity activity) {
        return getMetrics(activity).density;
    }

    /**
     * 屏幕密度DPI（120 / 160 / 240）
     *
     * @param activity
     * @return
     */
    public static int densityDpi(Activity activity) {
        return getMetrics(activity).densityDpi;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }



}
