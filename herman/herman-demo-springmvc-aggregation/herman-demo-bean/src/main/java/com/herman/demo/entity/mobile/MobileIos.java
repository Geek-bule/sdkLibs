package com.herman.demo.entity.mobile;

import java.io.Serializable;


/**
 * ios手机信息表
 */
public class MobileIos implements Serializable {

    private static final long serialVersionUID = 5732711137427155212L;
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
    private String idfa;

    /**
     * 手机标识
     */
    private String idfv;

    /**
     * 手机标识
     */
    private String openUdid;

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

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getIdfa() {
        return this.idfa;
    }

    public void setIdfv(String idfv) {
        this.idfv = idfv;
    }

    public String getIdfv() {
        return this.idfv;
    }

    public void setOpenUdid(String openUdid) {
        this.openUdid = openUdid;
    }

    public String getOpenUdid() {
        return this.openUdid;
    }

    @Override
    public String toString() {
        return "MobileIos{" +
                "id=" + id +
                ", dgUdid='" + dgUdid + '\'' +
                ", idfa='" + idfa + '\'' +
                ", idfv='" + idfv + '\'' +
                ", openUdid='" + openUdid + '\'' +
                '}';
    }
}
