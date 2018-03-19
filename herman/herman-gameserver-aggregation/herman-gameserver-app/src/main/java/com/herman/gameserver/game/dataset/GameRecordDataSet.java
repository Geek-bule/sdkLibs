package com.herman.gameserver.game.dataset;

import java.io.Serializable;

/**
 * 游戏记录dataset
 * Created by herman on 2018/1/31.
 */
public class GameRecordDataSet implements Serializable {
    private static final long serialVersionUID = -6378905811035091340L;

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

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getDgAccount() {
        return dgAccount;
    }

    public void setDgAccount(String dgAccount) {
        this.dgAccount = dgAccount;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "GameRecordDataSet{" +
                "gameId=" + gameId +
                ", dgAccount='" + dgAccount + '\'' +
                ", record='" + record + '\'' +
                '}';
    }
}
