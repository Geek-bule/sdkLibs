package com.herman.gameserver.service.game.impl;

import com.herman.gameserver.dao.member.IMemberDAO;
import com.herman.gameserver.entity.member.Member;
import com.herman.gameserver.service.game.IGameMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameMemberServiceImpl implements IGameMemberService{

    @Autowired
    private IMemberDAO gameMemberDAO;


    /**
     * 根据game_id、platform_type和platform_account查询是否存在记录
     * @param game_id
     * @param platform_type
     * @param platform_account
     * @return Member
     */
    @Override
    public Member getMemberAccountData(Long game_id,String platform_type,String platform_account){
        return gameMemberDAO.selectByGameAccount(game_id, platform_type, platform_account);
    }

    /**
     * 根据game_id、dg_udid查询是否存在记录
     * @param game_id
     * @param dg_udid
     * @return Member
     */
    @Override
    public Member getMemberGuestData(Long game_id,String dg_udid) {
        return gameMemberDAO.selectByGameGuest(game_id,dg_udid);
    }


    /**
     * 更新数据库记录
     * @param bean
     */
    @Override
    public void updateMemberData(Member bean){
        gameMemberDAO.updateByIdAndGameId(bean,bean.getId(),bean.getGameId());
    }

    /**
     * 插入账号记录
     * @param bean
     */
    @Override
    public void insertMemberData(Member bean) {
        gameMemberDAO.insert(bean);
    }
}
