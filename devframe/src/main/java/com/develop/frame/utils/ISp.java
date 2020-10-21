package com.develop.frame.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by ht on 2017/9/11.
 */

public class ISp {

    private static SharedPreferences.Editor mEditor;

    private static SharedPreferences mPreference;

    private static ISp sp;

    /**
     * 初始化共享参数工具类
     *
     * @param context
     */
    private static void init(Context context) {
        if (sp == null) {
            mEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            mPreference = PreferenceManager.getDefaultSharedPreferences(context);
            sp = new ISp();
        }
    }

    /**
     * 保存字符串
     *
     * @param key
     * @param value
     */

    public static void setValue(Context context, String key, String value) {
        init(context);
        mEditor.putString(key, value).commit();
    }


    /**
     * 保存长整形
     *
     * @param key
     * @param value
     */
    public static void setValue(Context context, String key, long value) {
        init(context);
        mEditor.putLong(key, value).commit();
    }

    /**
     * 保存整形
     *
     * @param key
     * @param value
     */
    public static void setValue(Context context, String key, int value) {
        init(context);
        mEditor.putInt(key, value).commit();
    }

    /**
     * 保存小数
     *
     * @param key
     * @param value
     */
    public static void setValue(Context context, String key, float value) {
        init(context);
        mEditor.putFloat(key, value).commit();
    }

    /**
     * 保存布尔值
     *
     * @param key
     * @param value
     */
    public static void setValue(Context context, String key, boolean value) {
        init(context);
        mEditor.putBoolean(key, value).commit();
    }

    /**
     * 获取保存的字符串
     *
     * @param key
     * @return
     */

    public static String getStringValue(Context context, String key) {
        init(context);
        return mPreference.getString(key, "");
    }

    /**
     * 获取保存的布尔值
     *
     * @param key
     * @return
     */
    public static Boolean getBooleanValue(Context context, String key) {
        init(context);
        return mPreference.getBoolean(key, false);
    }


    /**
     * 获取保存的小数
     *
     * @param key
     * @return
     */
    public static Float getFloatValue(Context context, String key) {
        init(context);
        return mPreference.getFloat(key, Float.MAX_VALUE);
    }


    /**
     * 获取保存的整形
     *
     * @param key
     * @return
     */
    public static Integer getIntValue(Context context, String key) {
        init(context);
        return mPreference.getInt(key, -1);
    }


    /**
     * 获取保存的长整形
     *
     * @param key
     * @return
     */
    public static Long getLongValue(Context context, String key) {
        init(context);
        return mPreference.getLong(key, Long.MAX_VALUE);
    }
}
