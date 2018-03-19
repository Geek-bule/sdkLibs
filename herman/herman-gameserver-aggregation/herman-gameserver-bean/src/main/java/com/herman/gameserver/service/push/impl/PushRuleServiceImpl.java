package com.herman.gameserver.service.push.impl;

import java.util.List;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.dao.push.PushRuleDAO;
import com.herman.gameserver.entity.push.PushRule;
import com.herman.gameserver.service.push.IPushRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 
 * 游戏推送规则表 业务接口实现
 * 
 */
@Service
public class PushRuleServiceImpl implements IPushRuleService {

	@Autowired
	private PushRuleDAO pushRuleDAO;

	/**
	 * 根据条件查询列表
	 */
	@Override	public List<PushRule> findListByParam(PushRule param) {
		List<PushRule> list = this.pushRuleDAO.selectList(param);
		return list;
	}

	/**
	 * 新增
	 */
	@Override	public Integer add(PushRule bean) {
			return this.pushRuleDAO.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override	public Integer addBatch(List<PushRule> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.pushRuleDAO.insertBatch(listBean);
	}

	/**
	 * 根据Id修改
	 */
	@Override	public Integer updateById(PushRule bean,Long id){
		return this.pushRuleDAO.updateById(bean,id);
	}


	/**
	 * 根据Id删除
	 */
	@Override	public Integer deleteById(Long id){
		return this.pushRuleDAO.deleteById(id);
	}


	/**
	 * 根据Id获取对象
	 */
	@Override	public PushRule getPushRuleById(Long id){
		return this.pushRuleDAO.selectById(id);
	}

    
    /**
     * 根据code获取对象
     */
    @Override    public List<PushRule> getPushRuleByGameCode(String gameCode,String mobileType){
        return this.pushRuleDAO.selectByGameCode(gameCode,mobileType);
    }

	/**
	 * 分页查询集合
	 */
	@Override	public List<PushRule> queryListPage(PageParameter pageParameter, PushRule param) {
		List<PushRule> list = this.pushRuleDAO.selectListPage(pageParameter,param);
		return list;
	}

	/**
	 * 查询数量
	 */
	@Override	public Integer getCount(){
		return this.pushRuleDAO.selectCount();
	}

}
