package com.herman.demo.dao.mobile;

import com.herman.common.bean.PageParameter;
import com.herman.demo.entity.mobile.MobileAndroid;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * android手机信息表 数据库操作接口
 */
public interface IMobileAndroidDAO {

    /**
     * 根据Id更新
     */
    Integer updateById(@Param("bean") MobileAndroid bean, @Param("id") Long id);


    /**
     * 根据Id删除
     */
    Integer deleteById(@Param("id") Long id);


    /**
     * 根据Id获取对象
     */
    MobileAndroid selectById(@Param("id") Long id);


    /**
     * 查询集合
     */
    List<MobileAndroid> selectList(MobileAndroid entity);


    /**
     * 查询数量
     */
    Integer selectCount();


    /**
     * 插入 （匹配有值的字段）
     */
    Integer insert(MobileAndroid entity);


    /**
     * 添加 （批量插入）
     */
    Integer insertBatch(List<MobileAndroid> listBean);


    /**
     * 分页查询集合（匹配有值的字段）
     */
    List<MobileAndroid> selectListPage(@Param("page") PageParameter pageParameter, @Param("entity") MobileAndroid entity);


}
