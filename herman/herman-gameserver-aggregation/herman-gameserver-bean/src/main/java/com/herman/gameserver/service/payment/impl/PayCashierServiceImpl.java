package com.herman.gameserver.service.payment.impl;

import java.util.List;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.dao.payment.PayCashierDAO;
import com.herman.gameserver.entity.payment.PayCashier;
import com.herman.gameserver.service.payment.IPayCashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 
 * 游戏表 业务接口实现
 * 
 */
@Service
public class PayCashierServiceImpl implements IPayCashierService {

	@Autowired
	private PayCashierDAO payCashierDAO;

	/**
	 * 根据条件查询列表
	 */
	@Override	public List<PayCashier> findListByParam(PayCashier param) {
		List<PayCashier> list = this.payCashierDAO.selectList(param);
		return list;
	}

	/**
	 * 新增
	 */
	@Override	public Integer add(PayCashier bean) {
			return this.payCashierDAO.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override	public Integer addBatch(List<PayCashier> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.payCashierDAO.insertBatch(listBean);
	}

	/**
	 * 根据Id修改
	 */
	@Override	public Integer updateById(PayCashier bean,Long id){
		return this.payCashierDAO.updateById(bean,id);
	}

	/**
	 * 根据Id修改
	 */
	@Override	public Integer updateByTransactionId(PayCashier bean,String transactionId){
		return this.payCashierDAO.updateByTransactionId(bean,transactionId);
	}

	/**
	 * 根据Id删除
	 */
	@Override	public Integer deleteById(Long id){
		return this.payCashierDAO.deleteById(id);
	}


	/**
	 * 根据Id获取对象
	 */
	@Override	public PayCashier getPayCashierById(Long id){
		return this.payCashierDAO.selectById(id);
	}

	/**
	 * 根据Id获取对象
	 */
	@Override	public PayCashier getPayCashierByTransactionId(String TransactionId){
		return this.payCashierDAO.selectByTransactionId(TransactionId);
	}

	/**
	 * 分页查询集合
	 */
	@Override	public List<PayCashier> queryListPage(PageParameter pageParameter, PayCashier param) {
		List<PayCashier> list = this.payCashierDAO.selectListPage(pageParameter,param);
		return list;
	}

	/**
	 * 查询数量
	 */
	@Override	public Integer getCount(){
		return this.payCashierDAO.selectCount();
	}

}