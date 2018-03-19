package com.herman.gameserver.service.mobile;

import com.herman.gameserver.entity.mobile.MobileAndroid;

/**
 * Created by herman on 2018/2/1.
 */
public interface IMobileAndroidService {

    /**
     * 查询android手机信息
     *
     * @param imei
     * @return
     */
    public MobileAndroid getMobileByImei(String imei);


    /**
     * 查询ios手机信息
     *
     * @param dgUdid
     * @return
     */
    public MobileAndroid getMobileByDgUdid(String dgUdid);


    /**
     * 保存android手机信息
     *
     * @param mobileAndroid
     * @return
     */
    public int saveMobile(MobileAndroid mobileAndroid);

}
