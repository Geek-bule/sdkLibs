package com.herman.gameserver.service.mobile.impl;

import java.util.List;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.dao.mobile.MobileGameDAO;
import com.herman.gameserver.entity.mobile.MobileGame;
import com.herman.gameserver.service.mobile.IMobileGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



/**
 * 
 * 用户游戏表 业务接口实现
 * 
 */
@Service
public class MobileGameServiceImpl implements IMobileGameService {

	@Autowired
	private MobileGameDAO mobileGameDAO;

	/**
	 * 根据条件查询列表
	 */
	@Override	public List<MobileGame> findListByParam(MobileGame param) {
		List<MobileGame> list = this.mobileGameDAO.selectList(param);
		return list;
	}

	/**
	 * 新增
	 */
	@Override	public Integer add(MobileGame bean) {
			return this.mobileGameDAO.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override	public Integer addBatch(List<MobileGame> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.mobileGameDAO.insertBatch(listBean);
	}

	/**
	 * 根据IdAndGameId修改
	 */
	@Override	public Integer updateByIdAndGameId(MobileGame bean,Long id,Long gameId){
		return this.mobileGameDAO.updateByIdAndGameId(bean,id,gameId);
	}


	/**
	 * 根据IdAndGameId删除
	 */
	@Override	public Integer deleteByIdAndGameId(Long id,Long gameId){
		return this.mobileGameDAO.deleteByIdAndGameId(id,gameId);
	}


	/**
	 * 根据IdAndGameId获取对象
	 */
	@Override	public MobileGame getMobileGameByIdAndGameId(Long id,Long gameId){
		return this.mobileGameDAO.selectByIdAndGameId(id,gameId);
	}

	/**
	 * 根据udidAndGameId查询对象
	 */
	@Override	public MobileGame getMobileGameByDgudidAndGameId(String dgUdid, Long gameId) {
		return this.mobileGameDAO.selectByDgudidAndGameId(dgUdid, gameId);
	}


	/**
	 * 分页查询集合
	 */
	@Override	public List<MobileGame> queryListPage(PageParameter pageParameter, MobileGame param) {
		List<MobileGame> list = this.mobileGameDAO.selectListPage(pageParameter,param);
		return list;
	}

	/**
	 * 查询数量
	 */
	@Override	public Integer getCount(){
		return this.mobileGameDAO.selectCount();
	}


	/**
	 * 异步用户id与游戏绑定
	 */
	@Async
	@Override	public boolean bindingGameMember(Long gameId, String dgUdid) {
		//用户游戏绑定
		MobileGame game = this.getMobileGameByDgudidAndGameId(dgUdid,gameId);
		if (game == null) {
			MobileGame mobileGame = new MobileGame();
			mobileGame.setDgUdid(dgUdid);
			mobileGame.setGameId(gameId);
			mobileGame.setVersion("1.0");
			this.add(mobileGame);
			return true;
		}
		return false;
	}

}