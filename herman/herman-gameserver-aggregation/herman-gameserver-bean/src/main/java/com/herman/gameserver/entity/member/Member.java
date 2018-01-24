package com.herman.gameserver.entity.member;

import com.herman.common.bean.CommonEntity;

/**
 * 用户账号表
 */
public class Member extends CommonEntity {

    private static final long serialVersionUID = 5352659943349719516L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 游戏id
     */
    private Long gameId;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getGameId() {
        return this.gameId;
    }

    public void setDgAccount(String dgAccount) {
        this.dgAccount = dgAccount;
    }

    public String getDgAccount() {
        return this.dgAccount;
    }

    public void setDgUdid(String dgUdid) {
        this.dgUdid = dgUdid;
    }

    public String getDgUdid() {
        return this.dgUdid;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getPlatformType() {
        return this.platformType;
    }

    public void setPlatformAccount(String platformAccount) {
        this.platformAccount = platformAccount;
    }

    public String getPlatformAccount() {
        return this.platformAccount;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public String getMobileType() {
        return this.mobileType;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getNikename() {
        return this.nikename;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHead() {
        return this.head;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", dgAccount='" + dgAccount + '\'' +
                ", dgUdid='" + dgUdid + '\'' +
                ", platformType='" + platformType + '\'' +
                ", platformAccount='" + platformAccount + '\'' +
                ", mobileType='" + mobileType + '\'' +
                ", nikename='" + nikename + '\'' +
                ", head='" + head + '\'' +
                "} " + super.toString();
    }
}
