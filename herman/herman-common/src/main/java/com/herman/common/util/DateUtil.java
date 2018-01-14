package com.herman.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间操作工具类
 * Created by herman on 2017/11/27.
 */
public class DateUtil {

    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";


    /**
     * 将时间格式化为字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


    /**
     * 将字符串解析为时间
     * @param str
     * @param pattern
     * @return
     */
    public static Date parse(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDateStr() {
        return format(new Date(), FORMAT_DATE);
    }


    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTimeStr() {
        return format(new Date(), FORMAT_TIME);
    }


    /**
     * 获取当前日期时间
     * @return
     */
    public static String getCurrentDateTimeStr() {
        return format(new Date(), FORMAT_DATE_TIME);
    }

}
