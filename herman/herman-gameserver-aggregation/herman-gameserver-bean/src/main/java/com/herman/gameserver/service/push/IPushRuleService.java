package com.herman.gameserver.service.push;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.push.PushRule;

import java.util.List;



/**
 * 
 * 游戏推送规则表 业务接口
 * 
 */
public interface IPushRuleService {

	/**
	 * 根据条件查询列表
	 */
	List<PushRule> findListByParam(PushRule param);

	/**
	 * 新增
	 */
	Integer add(PushRule bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<PushRule> listBean);

	/**
	 * 根据Id修改
	 */
	Integer updateById(PushRule bean, Long id);


	/**
	 * 根据Id删除
	 */
	Integer deleteById(Long id);


	/**
	 * 根据Id查询对象
	 */
	PushRule getPushRuleById(Long id);

    /**
     * 根据gameId、mobileType查询对象
     */
    List<PushRule> getPushRuleByGameCode(String gameCode, String mobileType);

	/**
	 * 分页查询集合
	 */
	List<PushRule> queryListPage(PageParameter pageParameter, PushRule param);

	/**
	 * 查询数量
	 */
	Integer getCount();

}
