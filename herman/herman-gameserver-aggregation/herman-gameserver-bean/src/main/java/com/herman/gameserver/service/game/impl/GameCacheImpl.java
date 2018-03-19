package com.herman.gameserver.service.game.impl;

import com.herman.gameserver.dao.game.IGameDAO;
import com.herman.gameserver.entity.game.Game;
import com.herman.gameserver.service.game.IGameCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 游戏信息缓存
 * Created by herman on 2018/3/6.
 */
@Service
public class GameCacheImpl implements IGameCache {
    @Autowired
    private IGameDAO gameDao;
    private static Map<String, Game> gameCodeMap = new HashMap<String, Game>();
    private static Map<String, Game> gameIdMap = new HashMap<String, Game>();

    @PostConstruct
    public void loadToCache() {
        List<Game> gameList = gameDao.selectList(new Game());
        for(Game game : gameList) {
            gameCodeMap.put(game.getCode(), game);
            gameIdMap.put(game.getId().toString(),game);
        }
    }

    /**
     * 通过code获取game信息
     * @param code
     * @return
     */
    @Override
    public Game getGameByCode(String code) {
        return gameCodeMap.get(code);
    }

    /**
     * 通过code获取gameId
     * @param code
     * @return
     */
    @Override
    public Long getGameIdByCode(String code) {
        Game game = gameCodeMap.get(code);
        if(game == null) {
            return null;
        } else {
            return game.getId();
        }
    }

    /**
     * 通过gameId获取game信息
     * @param gameId
     * @return
     */
    @Override
    public Game getGameById(Long gameId) {
        return gameIdMap.get(gameId.toString());
    }

    /**
     * 通过gameId获取code
     * @param gameId
     * @return
     */
    @Override
    public String getGameCodeById(Long gameId) {
        Game game = gameIdMap.get(gameId.toString());
        if(game == null) {
            return null;
        } else {
            return game.getCode();
        }
    }
}
