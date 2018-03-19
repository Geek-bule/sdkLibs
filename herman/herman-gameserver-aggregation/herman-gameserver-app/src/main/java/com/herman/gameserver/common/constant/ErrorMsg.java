package com.herman.gameserver.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 失败信息统一管理类
 * Created by herman on 2018/2/6.
 */
public class ErrorMsg {

    // 失败代码和失败原因映射关系
    public static Map<String, String> errMap = new HashMap<String, String>();

    // 服务器异常，返回-1
    public static final String SERVER_ERR_CODE = "-1";

    // 接口参数错误，返回错误代码1000~1999
    public static final String REQUEST_PARAM_ERR_CODE = "1000";
    public static final String REQUEST_PARAM_DGACCOUNT_ERR_CODE = "1001";
    public static final String REQUEST_PARAM_GAMECODE_ERR_CODE = "1002";
    public static final String REQUEST_PARAM_DGUDID_ERR_CODE = "1003";
    public static final String REQUEST_PARAM_RECORD_ERR_CODE = "1004";
    public static final String REQUEST_PARAM_MOBILETYPE_ERR_CODE = "1005";
    public static final String REQUEST_PARAM_PLATFORMTYPE_ERR_CODE = "1006";
    public static final String REQUEST_PARAM_IDFA_ERR_CODE = "1007";
    public static final String REQUEST_PARAM_IMEI_ERR_CODE = "1008";

    // 数据错误，返回错误代码2000~2999
    // 关于设备相关错误，返回错误代码2000~2009
    public static final String INVALID_DGUDID_ERR_CODE = "2000";
    // 关于游戏记录相关错误，返回错误代码2010~2019
    public static final String INVALID_RECORD_ERR_CODE = "2010";

    public static final String INVALID_GAMECODE_ERR_CODE = "2020";


    static {
        errMap.put(SERVER_ERR_CODE, "系统错误");

        // 接口参数错误
        errMap.put(REQUEST_PARAM_ERR_CODE, "请求参数错误");
        errMap.put(REQUEST_PARAM_DGACCOUNT_ERR_CODE, "请求参数dgAccount错误");
        errMap.put(REQUEST_PARAM_GAMECODE_ERR_CODE, "请求参数gameCode错误");
        errMap.put(REQUEST_PARAM_MOBILETYPE_ERR_CODE, "请求参数mobileType错误");
        errMap.put(REQUEST_PARAM_PLATFORMTYPE_ERR_CODE, "请求参数platformType错误");
        errMap.put(REQUEST_PARAM_IDFA_ERR_CODE, "请求参数idfa错误");
        errMap.put(REQUEST_PARAM_IMEI_ERR_CODE, "请求参数imei错误");

        errMap.put(INVALID_DGUDID_ERR_CODE, "无效dgUdid");
        errMap.put(INVALID_RECORD_ERR_CODE, "无效游戏记录");
        errMap.put(INVALID_GAMECODE_ERR_CODE, "无效游戏code");
    }


    /**
     * 通过失败码查询对应的失败原因
     * @param code
     * @return
     */
    public static String getMsg(String code) {
        return errMap.get(code);
    }

}
