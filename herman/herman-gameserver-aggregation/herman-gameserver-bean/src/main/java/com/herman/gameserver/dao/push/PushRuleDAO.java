package com.herman.gameserver.dao.push;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.push.PushRule;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * 
 * 游戏推送规则表 数据库操作接口
 * 
 */
public interface PushRuleDAO {

	/**
	 * 根据Id更新
	 */
	 Integer updateById(@Param("bean") PushRule bean, @Param("id") Long id);


	/**
	 * 根据Id删除
	 */
	 Integer deleteById(@Param("id") Long id);


	/**
	 * 根据Id获取对象
	 */
	 PushRule selectById(@Param("id") Long id);

    
    /**
     * 根据GameId获取对象
     */
    List<PushRule> selectByGameCode(@Param("gameCode") String gameCode, @Param("mobileType") String mobileType);


	/**
	 * 查询集合
	 */
	 List<PushRule> selectList(PushRule entity);


	/**
	 * 查询数量
	 */
	 Integer selectCount();


	/**
	 * 插入 （匹配有值的字段）
	 */
	 Integer insert(PushRule entity);



	/**
	 * 添加 （批量插入）
	 */
	 Integer insertBatch(List<PushRule> listBean);


	/**
	 * 分页查询集合（匹配有值的字段）
	 */
	 List<PushRule> selectListPage(@Param("page") PageParameter pageParameter, @Param("entity") PushRule entity);


}
