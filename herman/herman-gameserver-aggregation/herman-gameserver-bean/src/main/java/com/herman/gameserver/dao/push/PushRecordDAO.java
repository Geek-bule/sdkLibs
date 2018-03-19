package com.herman.gameserver.dao.push;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.push.PushRecord;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * 
 * 游戏推送记录表 数据库操作接口
 * 
 */
public interface PushRecordDAO {

	/**
	 * 根据IdAndGameId更新
	 */
	 Integer updateByIdAndGameId(@Param("bean") PushRecord bean, @Param("id") Long id, @Param("gameId") Long gameId);


	/**
	 * 根据pushIdAndGameId更新
	 */
	Integer updateByPushIdAndGameId(@Param("bean") PushRecord bean, @Param("pushGameId") Long pushGameId, @Param("gameId") Long gameId, @Param("dgUdid") String dgUdid, @Param("pushVersion") String pushVersion);


	/**
	 * 根据pushIdAndGameId更新
	 */
	Integer updateByPushIdAndDgudid(@Param("bean") PushRecord bean, @Param("pushGameId") Long pushGameId, @Param("dgUdid") String dgUdid, @Param("pushVersion") String pushVersion);


	/**
	 * 根据IdAndGameId删除
	 */
	 Integer deleteByIdAndGameId(@Param("id") Long id, @Param("gameId") Long gameId);


	/**
	 * 根据IdAndGameId获取对象
	 */
	 PushRecord selectByIdAndGameId(@Param("id") Long id, @Param("gameId") Long gameId);


	/**
	 * 根据pushIdAndGameId获取对象
	 */
	PushRecord selectByPushIdAndGameId(@Param("pushGameId") Long pushGameId, @Param("gameId") Long gameId, @Param("dgUdid") String dgUdid, @Param("pushVersion") String pushVersion);


	/**
	 * 根据pushIdAndDgudid获取对象
	 */
	List<PushRecord> selectByPushIdAndDgudid(@Param("pushGameId") Long pushGameId, @Param("Status") Long status, @Param("dgUdid") String dgUdid, @Param("pushVersion") String pushVersion);


	/**
	 * 查询集合
	 */
	 List<PushRecord> selectList(PushRecord entity);


	/**
	 * 查询数量
	 */
	 Integer selectCount();


	/**
	 * 插入 （匹配有值的字段）
	 */
	 Integer insert(PushRecord entity);



	/**
	 * 添加 （批量插入）
	 */
	 Integer insertBatch(List<PushRecord> listBean);


	/**
	 * 分页查询集合（匹配有值的字段）
	 */
	 List<PushRecord> selectListPage(@Param("page") PageParameter pageParameter, @Param("entity") PushRecord entity);


}
