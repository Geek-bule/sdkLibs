package com.herman.gameserver.dao.game;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.game.Game;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 游戏信息表 数据库操作接口
 */
public interface IGameDAO {

    /**
     * 根据Id更新
     */
    Integer updateById(@Param("bean") Game bean, @Param("id") Long id);


    /**
     * 根据Id删除
     */
    Integer deleteById(@Param("id") Long id);


    /**
     * 根据Id获取对象
     */
    Game selectById(@Param("id") Long id);


    /**
     * 查询集合
     */
    List<Game> selectList(Game entity);


    /**
     * 查询数量
     */
    Integer selectCount();


    /**
     * 插入 （匹配有值的字段）
     */
    Integer insert(Game entity);


    /**
     * 添加 （批量插入）
     */
    Integer insertBatch(List<Game> listBean);


    /**
     * 分页查询集合（匹配有值的字段）
     */
    List<Game> selectListPage(@Param("page") PageParameter pageParameter, @Param("entity") Game entity);


}
