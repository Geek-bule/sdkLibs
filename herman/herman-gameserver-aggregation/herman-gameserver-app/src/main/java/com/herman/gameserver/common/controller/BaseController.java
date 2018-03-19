package com.herman.gameserver.common.controller;

import com.herman.common.bean.ResponseBean;
import com.herman.gameserver.common.constant.ErrorMsg;

/**
 * 公共controller
 * Created by herman on 2018/1/2.
 */
public class BaseController {

    /**
     * 获取失败的错误对象
     * @param code
     * @return
     */
    public ResponseBean getErrResponseBean(String code) {
        return new ResponseBean(code, ErrorMsg.getMsg(code));
    }


//    @PostConstruct
//    public void init() {
//        // 设置DES加密密钥
//        DesUtil.KEY = "duogu123";
//    }
//    /**
//     * 将json字符串解密后，转为对象
//     * @param str
//     * @param beanClass
//     * @return
//     */
//    public Object getDecryptObject(String str, Class beanClass) {
//        String jsonStr = DesUtil.decrypt(str);
//        JSONObject jo = JSONObject.fromObject(jsonStr);
//        return JSONObject.toBean(jo, beanClass);
//    }


}
