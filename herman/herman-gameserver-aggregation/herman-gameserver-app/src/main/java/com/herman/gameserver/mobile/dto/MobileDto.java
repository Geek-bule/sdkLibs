package com.herman.gameserver.mobile.dto;

import java.io.Serializable;

/**
 * Created by herman on 2018/2/5.
 */
public class MobileDto implements Serializable {

    private static final long serialVersionUID = 1768200248661578770L;

    /**
     * 本平台生成的设备唯一标识
     */
    private String dgUdid;

    /**
     * 设备类型ios,android
     */
    private String mobileType;

    /**
     * 当前游戏的code
     */
    private String gameCode;

    /**
     * ios手机标识
     */
    private String idfa;

    /**
     * ios手机标识
     */
    private String idfv;

    /**
     * ios手机标识
     */
    private String openUdid;

    /**
     * android手机标识
     */
    private String imei;

    /**
     * android手机标识
     */
    private String androidId;

    /**
     * android手机标识
     */
    private String address;

    public String getDgUdid() {
        return dgUdid;
    }

    public void setDgUdid(String dgUdid) {
        this.dgUdid = dgUdid;
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getIdfv() {
        return idfv;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public String getOpenUdid() {
        return openUdid;
    }

    public void setOpenUdid(String openUdid) {
        this.openUdid = openUdid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MobileDto{" +
                "dgUdid='" + dgUdid + '\'' +
                ", mobileType='" + mobileType + '\'' +
                ", gameCode='" + gameCode + '\'' +
                ", idfa='" + idfa + '\'' +
                ", idfv='" + idfv + '\'' +
                ", openUdid='" + openUdid + '\'' +
                ", imei='" + imei + '\'' +
                ", androidId='" + androidId + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
