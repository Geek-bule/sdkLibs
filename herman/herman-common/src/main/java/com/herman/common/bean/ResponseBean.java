package com.herman.common.bean;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import java.io.Serializable;

/**
 * 返回客户端响应基类
 * Created by herman on 2018/1/19.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ResponseBean<T> implements Serializable {
    private static final long serialVersionUID = 1296061247555319545L;
    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MSG = "操作成功";
    private String code = SUCCESS_CODE;
    private String msg = SUCCESS_MSG;
    private T content;

    public ResponseBean(){
    }

    public ResponseBean(T content){
        this.content = content;
    }

    public ResponseBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(String code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", content=" + content +
                '}';
    }
}
