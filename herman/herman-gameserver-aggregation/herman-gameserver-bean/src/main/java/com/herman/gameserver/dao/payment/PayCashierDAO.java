package com.herman.gameserver.dao.payment;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.payment.PayCashier;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * 
 * 游戏表 数据库操作接口
 * 
 */
public interface PayCashierDAO {

	/**
	 * 根据Id更新
	 */
	 Integer updateById(@Param("bean") PayCashier bean, @Param("id") Long id);

	/**
	 * 根据transactionId更新
	 */
	Integer updateByTransactionId(@Param("bean") PayCashier bean, @Param("transactionId") String transactionId);


	/**
	 * 根据Id删除
	 */
	 Integer deleteById(@Param("id") Long id);


	/**
	 * 根据Id获取对象
	 */
	 PayCashier selectById(@Param("id") Long id);

	/**
	 * 根据transactionId获取对象
	 */
	PayCashier selectByTransactionId(@Param("transactionId") String transactionId);


	/**
	 * 查询集合
	 */
	 List<PayCashier> selectList(PayCashier entity);


	/**
	 * 查询数量
	 */
	 Integer selectCount();


	/**
	 * 插入 （匹配有值的字段）
	 */
	 Integer insert(PayCashier entity);



	/**
	 * 添加 （批量插入）
	 */
	 Integer insertBatch(List<PayCashier> listBean);


	/**
	 * 分页查询集合（匹配有值的字段）
	 */
	 List<PayCashier> selectListPage(@Param("page") PageParameter pageParameter, @Param("entity") PayCashier entity);


}
