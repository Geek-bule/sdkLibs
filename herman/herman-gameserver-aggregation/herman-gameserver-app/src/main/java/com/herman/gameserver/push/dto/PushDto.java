package com.herman.gameserver.push.dto;

import java.io.Serializable;

/**
 * Created by herman on 2018/2/6.
 */
public class PushDto implements Serializable {

    private static final long serialVersionUID = 5775464728264025935L;

    /**
     * 游戏code
     */
    private String gameCode;

    /**
     * 游戏code
     */
    private String pushGameCode;

    /**
     * 账号内部识别标识
     */
    private String dgAccount;

    /**
     * 本平台生成的设备唯一标识
     */
    private String dgUdid;

    /**
     * 绑定平台账号类型
     */
    private String platformType;

    /**
     * 互推比例
     */
    private Long percent;

    /**
     * 手机系统类型（android，ios）
     */
    private String mobileType;

    /**
     * 当前时间 年月日 Long
     */
    private Long currentDate;

    /**
     * 用户头像
     */
    private String head;

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getPushGameCode() {
        return pushGameCode;
    }

    public void setPushGameCode(String pushGameCode) {
        this.pushGameCode = pushGameCode;
    }

    public String getDgAccount() {
        return dgAccount;
    }

    public void setDgAccount(String dgAccount) {
        this.dgAccount = dgAccount;
    }

    public String getDgUdid() {
        return dgUdid;
    }

    public void setDgUdid(String dgUdid) {
        this.dgUdid = dgUdid;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public Long getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Long currentDate) {
        this.currentDate = currentDate;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "PushDto{" +
                "gameCode='" + gameCode + '\'' +
                ", pushGameCode='" + pushGameCode + '\'' +
                ", dgAccount='" + dgAccount + '\'' +
                ", dgUdid='" + dgUdid + '\'' +
                ", platformType='" + platformType + '\'' +
                ", percent='" + percent + '\'' +
                ", mobileType='" + mobileType + '\'' +
                ", currentDate='" + currentDate + '\'' +
                ", head='" + head + '\'' +
                '}';
    }
}
