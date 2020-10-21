package com.develop.frame.utils;

import android.text.TextUtils;

/**
 * Created by wangning on 2020/5/20.
 */

public class IString {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (TextUtils.isEmpty(str) || "null".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 显示问题文本
     *
     * @param str
     * @return
     */
    public static String showStr(String str) {
        return isEmpty(str) ? "---" : str;
    }

    /**
     * 显示EditText文本
     *
     * @param str
     * @return
     */
    public static String showEtStr(String str) {
        return isEmpty(str) ? "" : str;
    }

    /**
     * 处理时间
     *
     * @param time
     * @return
     */
    public static String showTime(String time) {
        return isEmpty(time) ? "---" : time.contains(" ") ? time.substring(0, time.indexOf(" ")) : time;
    }
}
