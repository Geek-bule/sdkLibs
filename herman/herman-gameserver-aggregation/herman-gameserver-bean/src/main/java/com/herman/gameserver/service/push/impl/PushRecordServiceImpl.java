package com.herman.gameserver.service.push.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.dao.push.PushRecordDAO;
import com.herman.gameserver.entity.push.PushRecord;
import com.herman.gameserver.entity.push.PushStatistics;
import com.herman.gameserver.service.push.IPushRecordService;
import com.herman.gameserver.service.push.IPushStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



/**
 * 
 * 游戏推送记录表 业务接口实现
 * 
 */
@Service
public class PushRecordServiceImpl implements IPushRecordService {

	@Autowired
	private PushRecordDAO pushRecordDAO;
	@Autowired
	private IPushStatisticsService pushStatisticsService;

	/**
	 * 根据条件查询列表
	 */
	@Override	public List<PushRecord> findListByParam(PushRecord param) {
		List<PushRecord> list = this.pushRecordDAO.selectList(param);
		return list;
	}

	/**
	 * 新增
	 */
	@Override	public Integer add(PushRecord bean) {
			return this.pushRecordDAO.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override	public Integer addBatch(List<PushRecord> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.pushRecordDAO.insertBatch(listBean);
	}

	/**
	 * 根据IdAndGameId修改
	 */
	@Override	public Integer updateByIdAndGameId(PushRecord bean,Long id,Long gameId){
		return this.pushRecordDAO.updateByIdAndGameId(bean,id,gameId);
	}

	/**
	 * 根据IdAndGameId修改
	 */
	@Override	public Integer updateByPushIdAndGameId(PushRecord bean,Long pushId, Long gameId,String dgUdid,String pushVersion){
		return this.pushRecordDAO.updateByPushIdAndGameId(bean,pushId,gameId,dgUdid,pushVersion);
	}

	/**
	 * 根据IdAndGameId修改
	 */
	@Override	public Integer updateByPushIdAndDgudid(PushRecord bean, Long pushId, String dgUdid,String pushVersion){
		return this.pushRecordDAO.updateByPushIdAndDgudid(bean,pushId,dgUdid,pushVersion);
	}


	/**
	 * 根据IdAndGameId删除
	 */
	@Override	public Integer deleteByIdAndGameId(Long id,Long gameId){
		return this.pushRecordDAO.deleteByIdAndGameId(id,gameId);
	}


	/**
	 * 根据IdAndGameId获取对象
	 */
	@Override	public PushRecord getPushRecordByIdAndGameId(Long id,Long gameId){
		return this.pushRecordDAO.selectByIdAndGameId(id,gameId);
	}


	/**
	 * 根据pushIdAndGameId查询对象
	 */
	@Override	public PushRecord getPushRecordByPushIdAndGameId(Long pushId, Long gameId,String dgUdid,String pushVersion) {
		return this.pushRecordDAO.selectByPushIdAndGameId(pushId,gameId,dgUdid,pushVersion);
	}

	/**
	 * 根据pushIdAndDgudid查询对象
	 */
	@Override	public List<PushRecord> getPushRecordByPushIdAndDguduid(Long pushId,Long status,String dgUdid,String pushVersion) {
		return this.pushRecordDAO.selectByPushIdAndDgudid(pushId,status,dgUdid,pushVersion);
	}


	/**
	 * 分页查询集合
	 */
	@Override	public List<PushRecord> queryListPage(PageParameter pageParameter, PushRecord param) {
		List<PushRecord> list = this.pushRecordDAO.selectListPage(pageParameter,param);
		return list;
	}

	/**
	 * 查询数量
	 */
	@Override	public Integer getCount(){
		return this.pushRecordDAO.selectCount();
	}



	/**
	 * 异步激活
	 */
	@Async
	@Override	public void activePushGame(Long gameId, String dgUdid) {
		//查询推送记录表，是否有激活
		Long status = new Long(0);
		List<PushRecord> pushRecords = this.getPushRecordByPushIdAndDguduid(gameId,status,dgUdid,"1.0");
		if (pushRecords != null) {
			for (PushRecord pushRecord : pushRecords) {
				pushRecord.setActivetime(new Date());
				status = new Long(1);
				pushRecord.setStatus(status);
				this.updateByIdAndGameId(pushRecord,pushRecord.getId(),pushRecord.getGameId());
				//在统计表中增加激活数据
				Date date = new Date();
				DateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
				String strDate = sdf2.format(date);
				Long statisticsDate = Long.parseLong(strDate);
				PushStatistics pushStatistics = pushStatisticsService.getPushStatisticsByGameIdAndStatisticsDate(pushRecord.getGameId(),pushRecord.getPushGameId(),statisticsDate);
				if (pushStatistics == null) {
					pushStatistics = new PushStatistics();
					pushStatistics.setGameId(pushRecord.getGameId());
					pushStatistics.setPushGameId(pushRecord.getPushGameId());
					pushStatistics.setStatisticsDate(statisticsDate);
					pushStatistics.setIconActive(Long.parseLong("1"));
					pushStatisticsService.add(pushStatistics);
				}else{
					pushStatistics.setIconActive(pushStatistics.getIconActive()+1);
					pushStatisticsService.updateByIdAndStatisticsDate(pushStatistics,pushStatistics.getId(),statisticsDate);
				}
			}
		}
	}
}