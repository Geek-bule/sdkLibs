package com.herman.gameserver.service.game;

import com.herman.gameserver.entity.member.Member;

public interface IGameMemberService {

    /**
     * 根据game_id、platform_type和platform_account查询是否存在记录
     * @param game_id
     * @param platform_type
     * @param platform_account
     * @return Member
     */
    public Member getMemberAccountData(Long game_id,String platform_type,String platform_account);


    /**
     * 根据game_id、dg_udid查询是否存在记录
     * @param game_id
     * @param dg_udid
     * @return Member
     */
    public Member getMemberGuestData(Long game_id,String dg_udid);


    /**
     * 更新数据库记录
     * @param bean
     */
    public void updateMemberData(Member bean);

    /**
     * 插入账号记录
     * @param bean
     */
    public void insertMemberData(Member bean);
}
