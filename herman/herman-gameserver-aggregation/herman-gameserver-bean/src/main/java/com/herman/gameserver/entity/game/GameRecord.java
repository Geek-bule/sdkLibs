package com.herman.gameserver.entity.game;

import com.herman.common.bean.CommonEntity;

/**
 * 游戏记录表
 */
public class GameRecord extends CommonEntity {

    private static final long serialVersionUID = 8364980631933744226L;
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
     * 游戏记录
     */
    private String record;

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

    public void setRecord(String record) {
        this.record = record;
    }

    public String getRecord() {
        return this.record;
    }

    @Override
    public String toString() {
        return "GameRecord{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", dgAccount='" + dgAccount + '\'' +
                ", record='" + record + '\'' +
                "} " + super.toString();
    }
}
