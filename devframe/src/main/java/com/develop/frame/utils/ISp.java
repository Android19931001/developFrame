package com.develop.frame.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.develop.frame.base.BaseAppActivity;

/**
 * Created by ht on 2017/9/11.
 */

public class ISp {

    private static SharedPreferences.Editor mEditor;

    private static SharedPreferences mPreference;

    private static ISp sp;

    private ISp() {

    }

    /**
     * 初始化共享参数工具类
     */
    public static void initSp() {
        if (sp == null) {
            synchronized (ISp.class) {
                mEditor = PreferenceManager.getDefaultSharedPreferences(BaseAppActivity.mActivity).edit();
                mPreference = PreferenceManager.getDefaultSharedPreferences(BaseAppActivity.mActivity);
                sp = new ISp();
            }
        }
    }

    /**
     * 保存字符串
     *
     * @param key
     * @param value
     */

    public static void setValue(String key, String value) {
        try {
            mEditor.putString(key, value).commit();
        } catch (Exception e) {
            System.out.println("This Object has not init!");
        }
    }


    /**
     * 保存长整形
     *
     * @param key
     * @param value
     */
    public static void setValue(String key, long value) {
        try {
            mEditor.putLong(key, value).commit();
        } catch (Exception e) {
            System.out.println("This Object has not init!");
        }
    }

    /**
     * 保存整形
     *
     * @param key
     * @param value
     */
    public static void setValue(String key, int value) {
        try {
            mEditor.putInt(key, value).commit();
        } catch (Exception e) {
            System.out.println("This Object has not init!");
        }
    }

    /**
     * 保存小数
     *
     * @param key
     * @param value
     */
    public static void setValue(String key, float value) {
        try {
            mEditor.putFloat(key, value).commit();
        } catch (Exception e) {
            System.out.println("This Object has not init!");
        }
    }

    /**
     * 保存布尔值
     *
     * @param key
     * @param value
     */
    public static void setValue(String key, boolean value) {
        try {
            mEditor.putBoolean(key, value).commit();
        } catch (Exception e) {
            System.out.println("This Object has not init!");
        }
    }

    /**
     * 获取保存的字符串
     *
     * @param key
     * @return
     */

    public static String getStringValue(String key) {
        try {
            return mPreference.getString(key, "");
        } catch (Exception e) {
            System.out.println("This Object has not init!");
        }
        return "";
    }

    /**
     * 获取保存的布尔值
     *
     * @param key
     * @return
     */
    public static Boolean getBooleanValue(String key) {
        try {
            return mPreference.getBoolean(key, false);
        } catch (Exception e) {
            System.out.println("This Object has not init!");
        }
        return false;
    }


    /**
     * 获取保存的小数
     *
     * @param key
     * @return
     */
    public static Float getFloatValue(String key) {
        try {
            return mPreference.getFloat(key, 0f);
        } catch (Exception e) {
            System.out.println("This Object has not init!");
        }
        return 0f;
    }


    /**
     * 获取保存的整形
     *
     * @param key
     * @return
     */
    public static Integer getIntValue(String key) {
        try {
            return mPreference.getInt(key, -1);
        } catch (Exception e) {
            System.out.println("This Object has not init!");
        }
        return -1;
    }


    /**
     * 获取保存的长整形
     *
     * @param key
     * @return
     */
    public static Long getLongValue(String key) {
        try {
            return mPreference.getLong(key, 0L);
        } catch (Exception e) {
            System.out.println("This Object has not init!");
        }
        return 0L;
    }
}
