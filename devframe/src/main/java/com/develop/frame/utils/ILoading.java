package com.develop.frame.utils;

import android.content.Context;

import com.android.tu.loadingdialog.LoadingDailog;

/**
 * Created by wangning on 2019/12/17.
 */

public class ILoading {

    private static LoadingDailog loadingDailog;

    /**
     * init a dialog
     */
    private static void init(Context context) {
        if (null != loadingDailog) {
            return;
        }
        loadingDailog = new LoadingDailog.Builder(context)
                .setMessage("加载中...")
                .setCancelable(false)
                .setCancelOutside(false)
                .create();
    }

    /**
     * init a dialog with text
     *
     * @param text
     */
    private static void init(Context context, String text) {
        if (null != loadingDailog) {
            return;
        }
        loadingDailog = new LoadingDailog.Builder(context)
                .setMessage(text + "...")
                .setCancelable(false)
                .setCancelOutside(false)
                .create();
    }

    /**
     * show this dialog
     */
    public static void open(Context context) {
        init(context);
        if (!isLoading()) {
            loadingDailog.show();
        }
    }

    /**
     * show this dialog with text
     *
     * @param text
     */
    public static void open(Context context, String text) {
        init(context, text);
        if (!isLoading()) {
            loadingDailog.show();
        }
    }

    /**
     * close this dialog which is loading
     */
    public static void close() {
        if (null != loadingDailog && loadingDailog.isShowing()) {
            loadingDailog.dismiss();
        }
        loadingDailog = null;
    }

    /**
     * judge this dialog is loading
     *
     * @return
     */
    public static boolean isLoading() {
        return null != loadingDailog && loadingDailog.isShowing() ? true : false;
    }

}
