package com.herman.gameserver.dao.member;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.member.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户账号表 数据库操作接口
 */
public interface IMemberDAO {

    /**
     * 根据IdAndGameId更新
     */
    Integer updateByIdAndGameId(@Param("bean") Member bean, @Param("id") Long id, @Param("gameId") Long gameId);


    /**
     * 根据IdAndGameId删除
     */
    Integer deleteByIdAndGameId(@Param("id") Long id, @Param("gameId") Long gameId);


    /**
     * 根据IdAndGameId获取对象
     */
    Member selectByIdAndGameId(@Param("id") Long id, @Param("gameId") Long gameId);

    /**
     * 根据game_id、platform_type和platform_account获取对象
     */
    Member selectByGameAccount(@Param("game_id") Long game_id, @Param("platform_type") String platform_type, @Param("platform_account") String platform_account);

    /**
     * 根据game_id、dg_udid获取对象
     */
    Member selectByGameGuest(@Param("game_id") Long game_id, @Param("dg_udid") String dg_udid);


    /**
     * 查询集合
     */
    List<Member> selectList(Member entity);


    /**
     * 查询数量
     */
    Integer selectCount();


    /**
     * 插入 （匹配有值的字段）
     */
    Integer insert(Member entity);


    /**
     * 添加 （批量插入）
     */
    Integer insertBatch(List<Member> listBean);


    /**
     * 分页查询集合（匹配有值的字段）
     */
    List<Member> selectListPage(@Param("page") PageParameter pageParameter, @Param("entity") Member entity);


}
