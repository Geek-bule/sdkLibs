package com.herman.gameserver.service.game;

import com.herman.gameserver.dao.mobile.IMobileIosDAO;
import com.herman.gameserver.entity.mobile.MobileIos;
import org.springframework.beans.factory.annotation.Autowired;

public interface IGameMobileService {

    /**
     *查询手机信息记录
     * @param dg_udid
     * @return MobileIos
     */
    public MobileIos getMobileIosByDgudid(String dg_udid) ;

    /**
     *查询手机信息记录
     * @param idfa
     * @return MobileIos
     */
    public MobileIos getMobileIosByIdfa(String idfa) ;

    /**
     *插入手机信息记录
     * @param iosInfo
     */
    public void insertMobileInfo(MobileIos iosInfo) ;

    /**
     * 异步更新手机信息记录
     */
    public void updateMobileData(MobileIos iosInfo);

    /**
     * 异步合并同样key的手机信息记录
     */
    public void mergerMobileData();
}
