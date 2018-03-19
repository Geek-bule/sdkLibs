package com.herman.gameserver.service.mobile.impl;

import com.herman.gameserver.dao.mobile.IMobileIosDAO;
import com.herman.gameserver.entity.mobile.MobileIos;
import com.herman.gameserver.service.mobile.IMobileIosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ios手机信息service
 * Created by herman on 2018/2/1.
 */
@Service
public class MobileIosServiceImpl implements IMobileIosService {

    @Autowired
    private IMobileIosDAO mobileIosDao;

    /**
     * 查询ios手机信息
     *
     * @param idfa
     * @return
     */
    @Override
    public MobileIos getMobileByIdfa(String idfa) {
        return mobileIosDao.selectByIdfa(idfa);
    }


    /**
     * 查询ios手机信息
     *
     * @param dgUdid
     * @return
     */
    @Override
    public MobileIos getMobileByDgUdid(String dgUdid) {
        return mobileIosDao.selectByDgUdid(dgUdid);
    }


    /**
     * 保存ios手机信息
     *
     * @param mobile
     * @return
     */
    @Override
    public int saveMobile(MobileIos mobile) {
        Long id = mobile.getId();
        if(id == null) {
            return mobileIosDao.insert(mobile);
        } else {
            mobile.setId(null);
            mobile.setDgUdid(null);
            return mobileIosDao.updateById(mobile, id);
        }
    }

}
