package com.herman.common.bean;

import com.herman.common.util.MD5;
import com.herman.common.util.ObjectUtil;
import com.herman.common.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

/**
 * 签名加密基类
 * 加密方式：
 * 1. 将字段升序排列
 * 2. 将字段值按顺序用&拼接起来
 * 3. 在字段末尾拼接&和密钥
 * 3. 最后将拿到的字符串进行md5加密，得到签名
 * Created by herman on 2018/2/13.
 */
public class SignDto implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);

    private String time; // 时间戳
    private String ran; // 随机数
    private String sign; // 签名

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRan() {
        return ran;
    }

    public void setRan(String ran) {
        this.ran = ran;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "SignDto{" +
                "time='" + time + '\'' +
                ", ran='" + ran + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    /**
     * 校验签名
     *
     * @param secret 密钥
     * @return
     */
    public boolean checkSign(String secret) {
        if (ValidateUtil.isEmpty(this.sign)) {
            return false;
        }
        try {
            String signTemp = "";
            Map<String, Object> map = ObjectUtil.objectToTreeMap(this);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String k = entry.getKey();
                Object o = entry.getValue();
                if (!"sign".equals(k) && ValidateUtil.isNotEmpty(o)) {
                    if (signTemp.length() > 0) {
                        signTemp += "&";
                    }
                    signTemp += o;
                }
            }
            if (signTemp.length() > 0) {
                signTemp += "&";
            }
            signTemp += secret;
            System.out.println(signTemp);
            signTemp = MD5.encodeUtf8(signTemp);
            System.out.println(signTemp);
            if (this.sign.equals(signTemp)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("[校验签名]-发生异常: " + e.getMessage());
        }
        return false;
    }


    /**
     * 校验签名,带时间戳(默认10)
     *
     * @param secret 密钥
     * @param now
     * @return
     */
    public boolean checkSignWithTime(String secret, long now) {
        return checkSignWithTime(secret, now, 10 * 60 * 1000); // 10min
    }


    /**
     * 校验签名,带时间戳
     *
     * @param secret 密钥
     * @param now
     * @param timeAllowMill
     * @return
     */
    public boolean checkSignWithTime(String secret, long now, long timeAllowMill) {
        if (ValidateUtil.isEmpty(this.sign)) {
            return false;
        }
        try {
            long mTime = Long.parseLong(this.time);
            long timeDiff = now - mTime;
            System.out.println(timeDiff);
            if (timeDiff <= timeAllowMill && timeDiff >= timeAllowMill * (-1)) {
                return checkSign(secret);
            }
        } catch (Exception e) {
            logger.error("[校验签名]-发生异常: " + e.getMessage());
        }
        return false;
    }

}
