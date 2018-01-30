package com.herman.gameserver.service.game.impl;

import com.herman.gameserver.dao.mobile.IMobileIosDAO;
import com.herman.gameserver.entity.mobile.MobileIos;
import com.herman.gameserver.service.game.IGameMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameMobileServiceImpl implements IGameMobileService {

    @Autowired
    private IMobileIosDAO mobileIosDAO;


    @Override
    public MobileIos getMobileIosByDgudid(String dg_udid) {
        MobileIos iosInfo = mobileIosDAO.selectByDgudid(dg_udid);
        return iosInfo;
    }

    @Override
    public MobileIos getMobileIosByIdfa(String idfa) {
        MobileIos iosInfo = mobileIosDAO.selectByIdfa(idfa);
        return iosInfo;
    }

    @Override
    public void insertMobileInfo(MobileIos iosInfo) {
        mobileIosDAO.insert(iosInfo);
    }
}
