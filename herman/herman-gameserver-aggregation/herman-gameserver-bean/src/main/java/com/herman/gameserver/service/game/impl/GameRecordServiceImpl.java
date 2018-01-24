package com.herman.gameserver.service.game.impl;

import com.herman.gameserver.dao.game.IGameRecordDAO;
import com.herman.gameserver.entity.game.GameRecord;
import com.herman.gameserver.service.game.IGameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by herman on 2018/1/1.
 */
@Service
public class GameRecordServiceImpl implements IGameRecordService {

    @Autowired
    private IGameRecordDAO gameRecordDao;

    /**
     * 查询游戏记录
     * @param gameId
     * @param dgAccount
     * @return
     */
    @Override
    public GameRecord getGameRecord(Long gameId, String dgAccount) {
        return gameRecordDao.selectByGameIdAndDgAccount(gameId, dgAccount);
    }

}
