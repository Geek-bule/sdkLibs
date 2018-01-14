package com.herman.demo.entity.mobile;

import com.herman.common.bean.CommonEntity;

/**
 * android手机信息表
 */
public class MobileAndroid extends CommonEntity {

    private static final long serialVersionUID = -5207838346327784993L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 本平台生成的设备唯一标识
     */
    private String dgUdid;

    /**
     * 手机标识
     */
    private String imei;

    /**
     * 手机标识
     */
    private String androidId;

    /**
     * 手机标识
     */
    private String address;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setDgUdid(String dgUdid) {
        this.dgUdid = dgUdid;
    }

    public String getDgUdid() {
        return this.dgUdid;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImei() {
        return this.imei;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAndroidId() {
        return this.androidId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    @Override
    public String toString() {
        return "MobileAndroid{" +
                "id=" + id +
                ", dgUdid='" + dgUdid + '\'' +
                ", imei='" + imei + '\'' +
                ", androidId='" + androidId + '\'' +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }
}
