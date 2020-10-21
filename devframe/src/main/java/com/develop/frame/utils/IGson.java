package com.develop.frame.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by wangning on 2019/12/23.
 */

public class IGson<T> {

    private static Gson iGson = null;


    /**
     * 解析成实体类
     *
     * @param json
     * @param classz
     * @param <T>
     * @return
     */
    public static <T> T iObject(String json, Class<T> classz) {
        try {
            if (null == iGson) iGson = new Gson();
            return iGson.fromJson(json, classz);
        } catch (Exception e) {
            ILog.e("something wrong has happend " + e.getMessage());
        }
        return null;
    }

    /**
     * 解析字符串数据
     *
     * @param json
     * @return
     */
    public static String[] iStrArray(String json) {
        try {
            if (null == iGson) iGson = new Gson();
            return iGson.fromJson(json, String[].class);
        } catch (Exception e) {
            ILog.e("something wrong has happend " + e.getMessage());
        }
        return null;
    }

    /**
     * 解析成数据集合
     *
     * @param json
     * @param classzz
     * @param <T>
     * @return
     */
    public static <T> List<T> iArray(String json, Class<T> classzz) {
        try {
            if (null == iGson) iGson = new Gson();
            return iGson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
            ILog.e("something wrong has happend " + e.getMessage());
        }
        return null;
    }

    /**
     * 将对象转换成对象
     *
     * @param object
     * @return
     */
    public static String iJsonStr(Object object) {
        try {
            if (null == iGson) iGson = new Gson();
            return iGson.toJson(object);
        } catch (Exception e) {
            ILog.e("something wrong has happend " + e.getMessage());
        }
        return null;
    }
}
