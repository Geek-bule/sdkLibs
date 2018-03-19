package com.herman.gameserver.game.dto;

import java.io.Serializable;

/**
 * Created by herman on 2018/2/6.
 */
public class GameRecordDto implements Serializable {
    private static final long serialVersionUID = -8514873482014581653L;

    /**
     * 游戏code
     */
    private String gameCode;

    /**
     * 账号内部识别标识
     */
    private String dgAccount;

    /**
     * 游戏记录
     */
    private String record;

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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "GameRecordDto{" +
                "gameCode='" + gameCode + '\'' +
                ", dgAccount='" + dgAccount + '\'' +
                ", record='" + record + '\'' +
                "} " + super.toString();
    }

}
