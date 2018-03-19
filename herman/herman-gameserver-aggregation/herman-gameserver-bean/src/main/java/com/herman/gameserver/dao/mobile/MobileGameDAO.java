package com.herman.gameserver.dao.mobile;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.mobile.MobileGame;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * 
 * 用户游戏表 数据库操作接口
 * 
 */
public interface MobileGameDAO {

	/**
	 * 根据IdAndGameId更新
	 */
	 Integer updateByIdAndGameId(@Param("bean") MobileGame bean, @Param("id") Long id, @Param("gameId") Long gameId);


	/**
	 * 根据IdAndGameId删除
	 */
	 Integer deleteByIdAndGameId(@Param("id") Long id, @Param("gameId") Long gameId);


	/**
	 * 根据IdAndGameId获取对象
	 */
	 MobileGame selectByIdAndGameId(@Param("id") Long id, @Param("gameId") Long gameId);


	/**
	 * 根据udidAndGameId获取对象
	 */
	MobileGame selectByDgudidAndGameId(@Param("dgUdid") String dgUdid, @Param("gameId") Long gameId);

	/**
	 * 查询集合
	 */
	 List<MobileGame> selectList(MobileGame entity);


	/**
	 * 查询数量
	 */
	 Integer selectCount();


	/**
	 * 插入 （匹配有值的字段）
	 */
	 Integer insert(MobileGame entity);



	/**
	 * 添加 （批量插入）
	 */
	 Integer insertBatch(List<MobileGame> listBean);


	/**
	 * 分页查询集合（匹配有值的字段）
	 */
	 List<MobileGame> selectListPage(@Param("page") PageParameter pageParameter, @Param("entity") MobileGame entity);


}
