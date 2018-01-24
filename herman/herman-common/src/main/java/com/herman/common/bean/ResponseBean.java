package com.herman.common.bean;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * 返回客户端响应基类
 * Created by herman on 2018/1/19.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ResponseBean<T> implements Serializable {
    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_MSG = "成功";
    public static final int FAIL_CODE = 0;
    public static final String FAIL_MSG = "失败";
    public static final String SERVER_ERROR_MSG = "系统错误";
    private int code = SUCCESS_CODE;
    private String msg = SUCCESS_MSG;
    private T content;

    public ResponseBean(){
    }

    public ResponseBean(T content){
        this.content = content;
    }

    public ResponseBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(int code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public void fail() {
        this.code = FAIL_CODE;
        this.msg = FAIL_MSG;
    }

    public void serverError() {
        this.code = FAIL_CODE;
        this.msg = SERVER_ERROR_MSG;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
