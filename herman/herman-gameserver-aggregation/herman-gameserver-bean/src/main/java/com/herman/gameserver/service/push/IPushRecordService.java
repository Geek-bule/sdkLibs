package com.herman.gameserver.service.push;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.push.PushRecord;

import java.util.List;



/**
 * 
 * 游戏推送记录表 业务接口
 * 
 */
public interface IPushRecordService {

	/**
	 * 根据条件查询列表
	 */
	List<PushRecord> findListByParam(PushRecord param);

	/**
	 * 新增
	 */
	Integer add(PushRecord bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<PushRecord> listBean);

	/**
	 * 根据IdAndGameId修改
	 */
	Integer updateByIdAndGameId(PushRecord bean, Long id, Long gameId);

	/**
	 * 根据IdAndGameId修改
	 */
	Integer updateByPushIdAndGameId(PushRecord bean, Long pushId, Long gameId,String dgUdid,String pushVersion);


	/**
	 * 根据IdAndGameId修改
	 */
	Integer updateByPushIdAndDgudid(PushRecord bean, Long pushId, String dgUdid,String pushVersion);


	/**
	 * 根据IdAndGameId删除
	 */
	Integer deleteByIdAndGameId(Long id, Long gameId);


	/**
	 * 根据IdAndGameId查询对象
	 */
	PushRecord getPushRecordByIdAndGameId(Long id, Long gameId);


	/**
	 * 根据pushIdAndGameId查询对象
	 */
	PushRecord getPushRecordByPushIdAndGameId(Long pushId, Long gameId,String dgUdid,String pushVersion);


	/**
	 * 根据pushIdAndDgudid查询对象
	 */
	List<PushRecord> getPushRecordByPushIdAndDguduid(Long pushId,Long status,String dgUdid,String pushVersion);


	/**
	 * 分页查询集合
	 */
	List<PushRecord> queryListPage(PageParameter pageParameter, PushRecord param);

	/**
	 * 查询数量
	 */
	Integer getCount();


	/**
	 * 异步激活
	 */
	void activePushGame(Long gameId, String dgUdid);

}