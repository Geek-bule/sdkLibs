package com.herman.gameserver.dao.game;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.game.GameRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 游戏记录表 数据库操作接口
 */
public interface IGameRecordDAO {

    /**
     * 根据IdAndGameId更新
     */
    Integer updateByIdAndGameId(@Param("bean") GameRecord bean, @Param("id") Long id, @Param("gameId") Long gameId);


    /**
     * 根据IdAndGameId删除
     */
    Integer deleteByIdAndGameId(@Param("id") Long id, @Param("gameId") Long gameId);


    /**
     * 根据IdAndGameId获取对象
     */
    GameRecord selectByIdAndGameId(@Param("id") Long id, @Param("gameId") Long gameId);


    /**
     * 查询集合
     */
    List<GameRecord> selectList(GameRecord entity);


    /**
     * 查询数量
     */
    Integer selectCount();


    /**
     * 插入 （匹配有值的字段）
     */
    Integer insert(GameRecord entity);


    /**
     * 添加 （批量插入）
     */
    Integer insertBatch(List<GameRecord> listBean);


    /**
     * 分页查询集合（匹配有值的字段）
     */
    List<GameRecord> selectListPage(@Param("page") PageParameter pageParameter, @Param("entity") GameRecord entity);


    /**
     * 根据GameIdAndDgAccount获取对象
     */
    GameRecord selectByGameIdAndDgAccount(@Param("gameId") Long gameId, @Param("dgAccount") String dgAccount);

}
