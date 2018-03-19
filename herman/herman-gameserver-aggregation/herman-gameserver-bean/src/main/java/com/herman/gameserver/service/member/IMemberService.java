package com.herman.gameserver.service.member;

import com.herman.gameserver.entity.member.Member;

/**
 * Created by Administrator on 2018/2/1.
 */
public interface IMemberService {

    /**
     * 查询会员信息
     *
     * @param gameId
     * @param dgUdid
     * @return
     */
    public Member getMember(Long gameId, String dgUdid);


    /**
     * 查询会员信息
     *
     * @param member
     * @return
     */
    public int saveMember(Member member);


    /**
     * 生成帐号dgAccount
     *
     * @param gameId
     * @return
     */
    public String getDgAccount(Long gameId);

}
