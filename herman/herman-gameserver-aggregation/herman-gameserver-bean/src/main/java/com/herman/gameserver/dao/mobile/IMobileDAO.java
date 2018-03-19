package com.herman.gameserver.dao.mobile;

/**
 * android手机信息表 数据库操作接口
 */
public interface IMobileDAO {

    /**
     * 生成唯一设备标识dgUdid
     * @return
     */
    String getDgUdid();

}
