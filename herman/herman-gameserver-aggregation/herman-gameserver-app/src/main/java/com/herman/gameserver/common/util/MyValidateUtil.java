package com.herman.gameserver.common.util;

import com.herman.common.constant.DeviceType;
import com.herman.common.util.ValidateUtil;

/**
 * Created by herman on 2018/2/6.
 */
public class MyValidateUtil extends ValidateUtil {

    /**
     * 验证设备类型是否合法
     * @param str
     * @return
     */
    public static boolean isMobileType(String str) {
        return isNotEmpty(str) && (DeviceType.IOS.equals(str) || DeviceType.ANDROID.equals(str));
    }

    /**
     * 验证设备号是否合法
     * @param str
     * @return
     */
    public static boolean isDgUdid(String str) {
        return isNotEmpty(str) && isAllowMinLength(str, 9) && isAllowMaxLength(str, 100) && str.startsWith("d");
    }

    /**
     * 验证设备号是否合法
     * @param str
     * @return
     */
    public static boolean isDgAccount(String str) {
        return isNotEmpty(str) && isAllowMinLength(str, 10) && isAllowMaxLength(str, 100) && str.contains("d");
    }


}
