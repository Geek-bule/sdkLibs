package com.herman.gameserver.game.dataset;

import java.io.Serializable;

/**
 * 游戏记录dataset
 * Created by herman on 2018/1/31.
 */
public class GameDataSet implements Serializable {
    private static final long serialVersionUID = -6378905821035091340L;

    /**
     * 游戏代码
     */
    private String code;

    /**
     * 游戏名称
     */
    private String name;

    /**
     * 游戏平台
     */
    private String mobile_type;

    /**
     * 平台id
     */
    private String platform_id;

    /**
     * 平台url
     */
    private String platform_url;

    /**
     * 游戏版本号
     */
    private String platform_version;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMobileType(String mobileType) {
        this.mobile_type = mobileType;
    }

    public String getMobileType() {
        return this.mobile_type;
    }

    public void setPlatformId(String platformId) {
        this.platform_id = platformId;
    }

    public String getPlatformId() { return this.platform_id; }

    public void setPlatformUrl(String platformUrl) {
        this.platform_url = platformUrl;
    }

    public String getPlatformUrl() {
        return this.platform_url;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platform_version = platformVersion;
    }

    public String getPlatformVersion() {
        return this.platform_version;
    }

    @Override
    public String toString() {
        return "Game{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", mobile_type='" + mobile_type + '\'' +
                ", platform_id='" + platform_id + '\'' +
                ", platform_url='" + platform_url + '\'' +
                ", platform_version='" + platform_version + '\'' +
                "} " + super.toString();
    }
}
