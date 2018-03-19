package com.herman.gameserver.dao.push;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.push.PushStatistics;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * 
 * 游戏推送统计表 数据库操作接口
 * 
 */
public interface PushStatisticsDAO {

	/**
	 * 根据IdAndStatisticsDate更新
	 */
	 Integer updateByIdAndStatisticsDate(@Param("bean") PushStatistics bean, @Param("id") Long id, @Param("statisticsDate") Long statisticsDate);


	/**
	 * 根据IdAndStatisticsDate更新
	 */
	Integer updateByGameIdAndStatisticsDate(@Param("bean") PushStatistics bean, @Param("gameId") Long gameId, @Param("pushGameId") Long pushGameId, @Param("statisticsDate") Long statisticsDate);


	/**
	 * 根据IdAndStatisticsDate删除
	 */
	 Integer deleteByIdAndStatisticsDate(@Param("id") Long id, @Param("statisticsDate") Long statisticsDate);


	/**
	 * 根据IdAndStatisticsDate获取对象
	 */
	 PushStatistics selectByIdAndStatisticsDate(@Param("id") Long id, @Param("statisticsDate") Long statisticsDate);


	/**
	 * 根据GameIdAndStatisticsDate获取对象
	 */
	PushStatistics selectByGameIdAndStatisticsDate(@Param("gameId") Long gameId, @Param("pushGameId") Long pushGameId, @Param("statisticsDate") Long statisticsDate);


	/**
	 * 查询集合
	 */
	 List<PushStatistics> selectList(PushStatistics entity);


	/**
	 * 查询数量
	 */
	 Integer selectCount();


	/**
	 * 插入 （匹配有值的字段）
	 */
	 Integer insert(PushStatistics entity);



	/**
	 * 添加 （批量插入）
	 */
	 Integer insertBatch(List<PushStatistics> listBean);


	/**
	 * 分页查询集合（匹配有值的字段）
	 */
	 List<PushStatistics> selectListPage(@Param("page") PageParameter pageParameter, @Param("entity") PushStatistics entity);


}
