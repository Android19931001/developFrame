package com.develop.frame.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by wangning on 2020/9/8.
 */

public class IDate {

    //年月日时分秒
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    //年月日
    public static final String YMD = "yyyy-MM-dd";

    //时分秒
    public static final String HMS = "HH:mm:ss";

    public static final String ERROR_RETURN = "日期格式化错误";


    //UTC时间格式
    public static final String YMDTHMSSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String YMDTHMSZ = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String YMDTHMS = "yyyy-MM-dd'T'HH:mm:ss";


    /**
     * 传入需要格式的日期，返回相应格式时间字符串
     *
     * @param type
     * @param date
     * @return
     */
    public static String format(String type, long date) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return ERROR_RETURN;
        }
    }

    /**
     * 传入需要格式的日期，返回相应格式时间字符串
     *
     * @param type
     * @param date
     * @return
     */
    public static String format(String type, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        try {
            return sdf.format(sdf.parse(date));
        } catch (ParseException e) {
            return ERROR_RETURN;
        }
    }

    /**
     * 默认返回yyyy-MM-dd HH:mm:ss格式
     *
     * @param date
     * @return
     */
    public static String format(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YMDHMS);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return ERROR_RETURN;
        }
    }

    /**
     * 默认返回yyyy-MM-dd HH:mm:ss格式
     *
     * @param date
     * @return
     */
    public static String format(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YMDHMS);
        try {
            return sdf.format(sdf.parse(date));
        } catch (ParseException e) {
            return ERROR_RETURN;
        }
    }


    /**
     * 格式化UTC时间格式
     *
     * @param inType  根据当前输入的日期格式决定，
     *                如：输入日期：2020-10-19T16:23:32，则inType为yyyy-MM-dd'T'HH:mm:ss;
     *                如：输入日期：2020-10-19T16:23:32.323Z，则inType为yyyy-MM-dd'T'HH:mm:ss.SSS'Z';
     * @param outType 想要返回的日期格式
     * @param date
     * @return
     */
    public static String formatUTC(String inType, String outType, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(inType);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date formatDate = sdf.parse(date);
            sdf.applyPattern(outType);
            sdf.setTimeZone(TimeZone.getDefault());
            return sdf.format(formatDate);
        } catch (ParseException e) {
            return ERROR_RETURN;
        }
    }

    /**
     * 格式化日期格式
     *
     * @param inType  需要格式化的日期的类型
     * @param outType 格式化之后的类型
     * @param date    需要格式化的日期
     * @return
     */
    public static String format(String inType, String outType, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(inType);
        try {
            Date formatDate = sdf.parse(date);
            sdf.applyPattern(outType);
            sdf.setTimeZone(TimeZone.getDefault());
            return sdf.format(formatDate);
        } catch (ParseException e) {
            return ERROR_RETURN;
        }
    }
}
