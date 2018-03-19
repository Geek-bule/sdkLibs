package com.herman.gameserver.service.mobile;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.mobile.MobileGame;

import java.util.List;



/**
 * 
 * 用户游戏表 业务接口
 * 
 */
public interface IMobileGameService {

	/**
	 * 根据条件查询列表
	 */
	List<MobileGame> findListByParam(MobileGame param);

	/**
	 * 新增
	 */
	Integer add(MobileGame bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<MobileGame> listBean);

	/**
	 * 根据IdAndGameId修改
	 */
	Integer updateByIdAndGameId(MobileGame bean, Long id, Long gameId);


	/**
	 * 根据IdAndGameId删除
	 */
	Integer deleteByIdAndGameId(Long id, Long gameId);


	/**
	 * 根据IdAndGameId查询对象
	 */
	MobileGame getMobileGameByIdAndGameId(Long id, Long gameId);

	/**
	 * 根据udidAndGameId查询对象
	 */
	MobileGame getMobileGameByDgudidAndGameId(String dgUdid, Long gameId);

	/**
	 * 分页查询集合
	 */
	List<MobileGame> queryListPage(PageParameter pageParameter, MobileGame param);

	/**
	 * 查询数量
	 */
	Integer getCount();


	/**
	 * 异步用户id与游戏绑定
	 */
	boolean bindingGameMember(Long gameId, String dgUdid);

}