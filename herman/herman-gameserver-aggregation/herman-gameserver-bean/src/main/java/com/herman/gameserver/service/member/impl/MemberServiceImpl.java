package com.herman.gameserver.service.member.impl;

import com.herman.gameserver.dao.member.IMemberDAO;
import com.herman.gameserver.entity.member.Member;
import com.herman.gameserver.service.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员信息service
 * Created by herman on 2018/2/1.
 */
@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private IMemberDAO memberDao;

    /**
     * 查询会员信息
     *
     * @param gameId
     * @param dgUdid
     * @return
     */
    @Override
    public Member getMember(Long gameId, String dgUdid) {
        return memberDao.selectByGameIdAndDgAccount(gameId, dgUdid);
    }


    /**
     * 查询会员信息
     *
     * @param member
     * @return
     */
    @Override
    public int saveMember(Member member) {
        Long id = member.getId();
        Long gameId = member.getGameId();
        if (id == null) {
            return memberDao.insert(member);
        } else {
            member.setId(null);
            member.setGameId(null);
            return memberDao.updateByIdAndGameId(member, gameId, id);
        }
    }


    /**
     * 生成帐号dgAccount
     *
     * @param gameId
     * @return
     */
    @Override
    public String getDgAccount(Long gameId) {
        return memberDao.getDgAccount(gameId);
    }

}
