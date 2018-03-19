package com.herman.gameserver.service.push;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.push.PushStatistics;

import java.util.List;



/**
 * 
 * 游戏推送统计表 业务接口
 * 
 */
public interface IPushStatisticsService {

	/**
	 * 根据条件查询列表
	 */
	List<PushStatistics> findListByParam(PushStatistics param);

	/**
	 * 新增
	 */
	Integer add(PushStatistics bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<PushStatistics> listBean);

	/**
	 * 根据IdAndStatisticsDate修改
	 */
	Integer updateByIdAndStatisticsDate(PushStatistics bean, Long id, Long statisticsDate);


	/**
	 * 根据GameIdAndStatisticsDate修改
	 */
	Integer updateByGameIdAndStatisticsDate(PushStatistics bean, Long gameId, Long pushGameId, Long statisticsDate);


	/**
	 * 根据IdAndStatisticsDate删除
	 */
	Integer deleteByIdAndStatisticsDate(Long id, Long statisticsDate);


	/**
	 * 根据IdAndStatisticsDate查询对象
	 */
	PushStatistics getPushStatisticsByIdAndStatisticsDate(Long id, Long statisticsDate);


	/**
	 * 根据GameIdAndStatisticsDate查询对象
	 */
	PushStatistics getPushStatisticsByGameIdAndStatisticsDate(Long gameId, Long pushGameId, Long statisticsDate);


	/**
	 * 分页查询集合
	 */
	List<PushStatistics> queryListPage(PageParameter pageParameter, PushStatistics param);

	/**
	 * 查询数量
	 */
	Integer getCount();

}