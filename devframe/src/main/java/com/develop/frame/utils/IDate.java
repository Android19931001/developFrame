package com.develop.frame.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by wangning on 2020/9/8.
 */

public class IDate {
    /**
     * 格式化时间
     *
     * @param formatDate
     * @param formatType
     * @return
     */
    public static String format(String formatDate, String formatType) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        try {
            return sdf.format(sdf.parse(formatDate));
        } catch (ParseException e) {
            return "---";
        }
    }
}
