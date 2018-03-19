package com.herman.gameserver.service.mobile.impl;

import com.herman.gameserver.dao.mobile.IMobileDAO;
import com.herman.gameserver.service.mobile.IMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by herman on 2018/2/1.
 */
@Service
public class MobileServiceImpl implements IMobileService {

    @Autowired
    private IMobileDAO mobileDao;

    /**
     * 获取唯一设备号
     *
     * @return
     */
    @Override
    public String getDgUdid() {
        return mobileDao.getDgUdid();
    }

}
