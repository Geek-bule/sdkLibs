package com.herman.gameserver.push.dataset;

import java.io.Serializable;

/**
 * Created by herman on 2018/2/1.
 */
public class PushDataSet implements Serializable {

    private static final long serialVersionUID = -1889540522534079994L;

    /**
     * 游戏code
     */
    private String gameCode;

    /**
     * 平台id
     */
    private String platformId;

    /**
     * 平台下载地址
     */
    private String platformUrl;

    /**
     * 游戏版本号
     */
    private String platformVersion;

    /**
     * 手机系统类型（android，ios）
     */
    private String mobileType;

    /**
     * 百分比
     */
    private Long percent;


    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformUrl() {
        return platformUrl;
    }

    public void setPlatformUrl(String platformUrl) {
        this.platformUrl = platformUrl;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "PushDataSet{" +
                "gameCode=" + gameCode +
                ", platformId='" + platformId + '\'' +
                ", platformUrl='" + platformUrl + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", mobileType='" + mobileType + '\'' +
                ", percent='" + percent + '\'' +
                '}';
    }
}
