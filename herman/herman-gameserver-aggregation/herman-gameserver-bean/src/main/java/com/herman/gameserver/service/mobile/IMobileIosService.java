package com.herman.gameserver.service.mobile;

import com.herman.gameserver.entity.mobile.MobileIos;

/**
 * Created by herman on 2018/2/1.
 */
public interface IMobileIosService {

    /**
     * 查询ios手机信息
     *
     * @param idfa
     * @return
     */
    public MobileIos getMobileByIdfa(String idfa);


    /**
     * 查询ios手机信息
     *
     * @param dgUdid
     * @return
     */
    public MobileIos getMobileByDgUdid(String dgUdid);


    /**
     * 保存ios手机信息
     *
     * @param mobileIos
     * @return
     */
    public int saveMobile(MobileIos mobileIos);


}
