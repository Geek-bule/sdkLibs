package com.herman.gameserver.service.payment;

import java.util.List;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.payment.PayCashier;


/**
 * 
 * 游戏表 业务接口
 * 
 */
public interface IPayCashierService {

	/**
	 * 根据条件查询列表
	 */
	List<PayCashier> findListByParam(PayCashier param);

	/**
	 * 新增
	 */
	Integer add(PayCashier bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<PayCashier> listBean);

	/**
	 * 根据Id修改
	 */
	Integer updateById(PayCashier bean, Long id);

	/**
	 * 根据Id修改
	 */
	Integer updateByTransactionId(PayCashier bean, String transactionId);


	/**
	 * 根据Id删除
	 */
	Integer deleteById(Long id);


	/**
	 * 根据Id查询对象
	 */
	PayCashier getPayCashierById(Long id);

	/**
	 * 根据Id查询对象
	 */
	PayCashier getPayCashierByTransactionId(String transactionId);

	/**
	 * 分页查询集合
	 */
	List<PayCashier> queryListPage(PageParameter pageParameter, PayCashier param);

	/**
	 * 查询数量
	 */
	Integer getCount();

}