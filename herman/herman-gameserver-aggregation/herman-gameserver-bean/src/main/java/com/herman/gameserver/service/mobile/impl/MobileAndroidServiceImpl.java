package com.herman.gameserver.service.mobile.impl;

import com.herman.gameserver.dao.mobile.IMobileAndroidDAO;
import com.herman.gameserver.entity.mobile.MobileAndroid;
import com.herman.gameserver.service.mobile.IMobileAndroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by herman on 2018/2/2.
 */
@Service
public class MobileAndroidServiceImpl implements IMobileAndroidService {

    @Autowired
    private IMobileAndroidDAO mobileAndroidDao;

    /**
     * 查询android手机信息
     *
     * @param imei
     * @return
     */
    @Override
    public MobileAndroid getMobileByImei(String imei) {
        return mobileAndroidDao.selectByImei(imei);
    }


    /**
     * 查询ios手机信息
     *
     * @param dgUdid
     * @return
     */
    @Override
    public MobileAndroid getMobileByDgUdid(String dgUdid) {
        return mobileAndroidDao.selectByDgUdid(dgUdid);
    }


    /**
     * 保存android手机信息
     *
     * @param mobile
     * @return
     */
    @Override
    public int saveMobile(MobileAndroid mobile) {
        Long id = mobile.getId();
        if(id == null) {
            return mobileAndroidDao.insert(mobile);
        } else {
            mobile.setId(null);
            mobile.setDgUdid(null);
            return mobileAndroidDao.updateById(mobile, id);
        }
    }
}
