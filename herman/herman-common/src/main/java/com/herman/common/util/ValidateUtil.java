package com.herman.common.util;

import java.util.List;

/**
 * Created by herman on 2018/2/6.
 */
public class ValidateUtil {

    /**
     * 验证字符串是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            if ("".equals(obj))
                return false;
        } else if (obj instanceof List) {
            if (((List) obj).size() <= 0)
                return false;
        } else if (obj instanceof Object[]) {
            if (((Object[]) obj).length <= 0)
                return false;
        }
        return true;
    }

    /**
     * 验证字符串是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return !isNotEmpty(obj);
    }


    /**
     * 验证字符串最大长度
     *
     * @param obj
     * @param maxLength
     * @return
     */
    public static boolean isAllowMaxLength(Object obj, int maxLength) {
        if (obj == null)
            return false;
        if (obj instanceof String) {
            if (String.valueOf(obj).length() <= maxLength)
                return true;
        } else if (obj instanceof Integer) {
            if (String.valueOf(obj).length() <= maxLength)
                return true;
        } else if (obj instanceof Float) {
            if (String.valueOf(Math.abs((Float) obj)).length() <= maxLength)
                return true;
        } else if (obj instanceof List) {
            if (((List) obj).size() <= maxLength)
                return true;
        } else if (obj instanceof Object[]) {
            if (((Object[]) obj).length <= maxLength)
                return true;
        }
        return false;
    }


    /**
     * 验证字符串最小长度
     *
     * @param obj
     * @param maxLength
     * @return
     */
    public static boolean isAllowMinLength(Object obj, int maxLength) {
        if (obj == null)
            return false;
        if (obj instanceof String) {
            if (String.valueOf(obj).length() >= maxLength)
                return true;
        } else if (obj instanceof Integer) {
            if (String.valueOf(obj).length() >= maxLength)
                return true;
        } else if (obj instanceof Float) {
            if (String.valueOf(Math.abs((Float) obj)).length() >= maxLength)
                return true;
        } else if (obj instanceof List) {
            if (((List) obj).size() >= maxLength)
                return true;
        } else if (obj instanceof Object[]) {
            if (((Object[]) obj).length >= maxLength)
                return true;
        }
        return false;
    }

}
