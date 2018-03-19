package com.herman.gameserver.member.dto;

import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

/**
 * Created by herman on 2018/2/6.
 */
public class MemberDto implements Serializable {

    private static final long serialVersionUID = 5775464728264025935L;

    /**
     * 游戏code
     */
    private String gameCode;

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
     * 绑定平台账号标识
     */
    private String platformAccount;

    /**
     * 手机系统类型（android，ios）
     */
    private String mobileType;

    /**
     * 用户昵称
     */
    private String nikename;

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

    public String getPlatformAccount() {
        return platformAccount;
    }

    public void setPlatformAccount(String platformAccount) {
        this.platformAccount = platformAccount;
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
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
                ", dgAccount='" + dgAccount + '\'' +
                ", dgUdid='" + dgUdid + '\'' +
                ", platformType='" + platformType + '\'' +
                ", platformAccount='" + platformAccount + '\'' +
                ", mobileType='" + mobileType + '\'' +
                ", nikename='" + nikename + '\'' +
                ", head='" + head + '\'' +
                '}';
    }
}
