package com.herman.gameserver.dao.mobile;

import com.herman.common.bean.PageParameter;
import com.herman.gameserver.entity.mobile.MobileIos;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * ios手机信息表 数据库操作接口
 */
public interface IMobileIosDAO {

    /**
     * 根据Id更新
     */
    Integer updateById(@Param("bean") MobileIos bean, @Param("id") Long id);


    /**
     * 根据Id删除
     */
    Integer deleteById(@Param("id") Long id);


    /**
     * 根据Id获取对象
     */
    MobileIos selectById(@Param("id") Long id);

    /**
     * 根据dg_udid获取对象
     */
    MobileIos selectByDgudid(@Param("dg_udid") String dg_udid);

    /**
     * 根据idfa获取对象
     */
    MobileIos selectByIdfa(@Param("idfa") String idfa);

    /**
     * 查询集合
     */
    List<MobileIos> selectList(MobileIos entity);


    /**
     * 查询数量
     */
    Integer selectCount();


    /**
     * 插入 （匹配有值的字段）
     */
    Integer insert(MobileIos entity);


    /**
     * 添加 （批量插入）
     */
    Integer insertBatch(List<MobileIos> listBean);


    /**
     * 分页查询集合（匹配有值的字段）
     */
    List<MobileIos> selectListPage(@Param("page") PageParameter pageParameter, @Param("entity") MobileIos entity);


}
