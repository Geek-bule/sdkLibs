package com.herman.gameserver.service.game;

import com.herman.gameserver.entity.game.Game;

import java.util.List;

/**
 * 游戏信息缓存
 * Created by herman on 2018/3/6.
 */
public interface IGameCache {

    /**
     * 通过code获取game信息
     * @param code
     * @return
     */
    public Game getGameByCode(String code);

    /**
     * 通过code获取gameId
     * @param code
     * @return
     */
    public Long getGameIdByCode(String code);

    /**
     * 通过gameId获取game信息
     * @param gameId
     * @return
     */
    public Game getGameById(Long gameId);

    /**
     * 通过gameId获取code
     * @param gameId
     * @return
     */
    public String getGameCodeById(Long gameId);

    /**
     * 获取所有游戏信息
     */
    public List<Game> getGameList();


    /**
     *增加游戏信息
     */
    public int add(Game game);


    /**
     * 更新游戏信息
     */
    public int update(Game game);

}
