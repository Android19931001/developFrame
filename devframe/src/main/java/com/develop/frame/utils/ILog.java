package com.develop.frame.utils;

import android.text.TextUtils;
import android.util.Log;

import com.develop.frame.base.BaseAppActivity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * Created by ht on 2017/9/18.
 */

public class ILog {


    private static String TAG = "FRAME_TAG";

    private static StringBuilder builder = null;

    /**
     * 是否开启了日志打印
     *
     * @return
     */
    private static boolean isLog() {
        if (null != BaseAppActivity.mActivity) {
            return ((BaseAppActivity) BaseAppActivity.mActivity).IS_OPEN_LOG;
        }
        return false;
    }

    /**
     * 错误级别的
     *
     * @param msg
     */
    public static void e(Object msg) {
        if (isLog()) {
            Log.e(TAG, msg.toString());
        }
    }

    /**
     * 信息级别的log
     *
     * @param msg
     */
    public static void i(String msg) {
        if (isLog()) {
            Log.i(TAG, msg);
        }
    }

    /**
     * 警告级别的log ----- 基本不用
     *
     * @param msg
     */
    public static void w(String msg) {
        if (isLog()) {
            Log.w(TAG, msg);
        }
    }

    /**
     * debug级别的log ----- 基本不用
     *
     * @param msg
     */
    public static void d(String msg) {
        if (isLog()) {
            Log.d(TAG, msg);
        }
    }

    /**
     * 打印出Map中的健值对
     * 如果Map集合中的值是自定义的类，需要从写类的toString()方法，就可打印出类中的内容，否则只能打印出类的地址
     *
     * @param map
     */
    public static void map(Map map) {
        if (isLog() && map != null) {
            if (map.size() <= 0) {
                e("Map中没有添加任何的值");
                return;
            }
            e("打印出Map的值：\n" + handleMap(map));
        } else if (map == null) {
            e("Map没有实例化");
        }
    }

    /**
     * 打印出请求的路径以及请求的参数
     *
     * @param url
     * @param map
     */
    public static void map(String url, Map map) {
        if (isLog() && map != null) {
            if (map.size() <= 0) {
                e("请求的路径：" + url + "\nMap中没有添加任何的值");
                return;
            }
            e("请求的路径：" + url + "\n打印出Map的值：\n" + handleMap(map));
        } else if (map == null) {
            e("请求的路径：" + url + "Map没有实例化");
        }
    }

    /**
     * 打印出List集合中的值
     * 如果List集合中的值是自定义的类，需要从写类的toString()方法，就可但已出类中的内容，否则只能打印出类的地址
     *
     * @param list
     */
    public static void list(List list) {
        if (isLog() && list != null) {
            if (list.size() <= 0) {
                e("List集合中没有添加任何的值");
                return;
            }
            StringBuilder builder = new StringBuilder();
            for (Object obj : list) {
                builder.append(obj.toString() + "\n");
            }
            e("打印出List中的值：\n" + builder.toString());
        } else if (list == null) {
            e("List没有实例化");
        }
    }

    /**
     * 获取Map中的内容
     *
     * @param map
     * @return
     */

    private static String handleMap(Map map) {
        if (builder == null)
            builder = new StringBuilder();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            String value = map.get(key).toString();
            builder.append("key--> " + key + "：").append(value + " -->value").append("\n");
        }
        return builder.toString();
    }

    /**
     * 打印json字符串
     *
     * @param json
     */
    public static void logJson(String json) {
        if (isLog() && !TextUtils.isEmpty(json)) {
            e(handleJson(json));
        }
    }

    /**
     * 处理json对象
     *
     * @param json
     * @return
     */
    private static String handleJson(String json) {
        StringBuilder bd = new StringBuilder();
        for (int i = 0; i < json.length(); i++) {
            int preiousIndex = i - 1 == -1 ? i : i - 1;
            char c = json.charAt(i);
            char preiousChar = json.charAt(preiousIndex);
            if (c == '{') {
                if (preiousChar == ':')
                    bd.append(c + "\n\t");
                else
                    bd.append("\n" + c + "\n\t");
            } else if (c == '}') {
                bd.append("\n" + c);
            } else if (c == ',') {
                bd.append(c + "\n\t");
            } else if (c == '[') {
                bd.append(c + "\n\t");
            } else {
                if (preiousChar == '{' || preiousChar == ',')
                    bd.append("\t" + c);
                else
                    bd.append(c);
            }
        }
        return bd.toString();
    }
}
