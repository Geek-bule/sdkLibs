package com.herman.gameserver.service.push.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.dao.push.PushStatisticsDAO;
import com.herman.gameserver.entity.push.PushStatistics;
import com.herman.gameserver.service.push.IPushStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



/**
 * 
 * 游戏推送统计表 业务接口实现
 * 
 */
@Service
public class PushStatisticsServiceImpl implements IPushStatisticsService {

	@Autowired
	private PushStatisticsDAO pushStatisticsDAO;

	/**
	 * 根据条件查询列表
	 */
	@Override	public List<PushStatistics> findListByParam(PushStatistics param) {
		List<PushStatistics> list = this.pushStatisticsDAO.selectByStatisticsDate(param.getStatisticsDate());
		return list;
	}

	/**
	 * 新增
	 */
	@Override	public Integer add(PushStatistics bean) {
			return this.pushStatisticsDAO.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override	public Integer addBatch(List<PushStatistics> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.pushStatisticsDAO.insertBatch(listBean);
	}

	/**
	 * 根据IdAndStatisticsDate修改
	 */
	@Override	public Integer updateByIdAndStatisticsDate(PushStatistics bean,Long id,Long statisticsDate){
		return this.pushStatisticsDAO.updateByIdAndStatisticsDate(bean,id,statisticsDate);
	}


	/**
	 * 根据GameIdAndStatisticsDate修改
	 */
	@Override	public Integer updateByGameIdAndStatisticsDate(PushStatistics bean,Long gameId,Long pushGameId,Long statisticsDate){
		return this.pushStatisticsDAO.updateByGameIdAndStatisticsDate(bean,gameId,pushGameId,statisticsDate);
	}

	/**
	 * 根据IdAndStatisticsDate删除
	 */
	@Override	public Integer deleteByIdAndStatisticsDate(Long id,Long statisticsDate){
		return this.pushStatisticsDAO.deleteByIdAndStatisticsDate(id,statisticsDate);
	}


	/**
	 * 根据IdAndStatisticsDate获取对象
	 */
	@Override	public PushStatistics getPushStatisticsByIdAndStatisticsDate(Long id,Long statisticsDate){
		return this.pushStatisticsDAO.selectByIdAndStatisticsDate(id,statisticsDate);
	}

	/**
	 * 根据GameIdAndStatisticsDate获取对象
	 */
	@Override	public PushStatistics getPushStatisticsByGameIdAndStatisticsDate(Long gameId,Long pushGameId,Long statisticsDate){
		return this.pushStatisticsDAO.selectByGameIdAndStatisticsDate(gameId,pushGameId,statisticsDate);
	}


	/**
	 * 分页查询集合
	 */
	@Override	public List<PushStatistics> queryListPage(PageParameter pageParameter, PushStatistics param) {
		List<PushStatistics> list = this.pushStatisticsDAO.selectListPage(pageParameter,param);
		return list;
	}

	/**
	 * 查询数量
	 */
	@Override	public Integer getCount(){
		return this.pushStatisticsDAO.selectCount();
	}


	/**
	 * 更新点击统计
	 */
	@Async
	@Override	public void updateClickStatistics(Long gameId,Long pushGameId){
		Date date = new Date();
		DateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		String strDate = sdf2.format(date);
		Long statisticsDate = Long.parseLong(strDate);
		PushStatistics pushStatistics = this.getPushStatisticsByGameIdAndStatisticsDate(gameId,pushGameId,statisticsDate);
		if (pushStatistics == null) {
			pushStatistics = new PushStatistics();
			pushStatistics.setGameId(gameId);
			pushStatistics.setPushGameId(pushGameId);
			pushStatistics.setIconClick(Long.parseLong("1"));
			pushStatistics.setStatisticsDate(statisticsDate);
			this.add(pushStatistics);
		}else{
			pushStatistics.setIconClick(pushStatistics.getIconClick()+1);
			this.updateByIdAndStatisticsDate(pushStatistics,pushStatistics.getId(),pushStatistics.getStatisticsDate());
		}
	}

}