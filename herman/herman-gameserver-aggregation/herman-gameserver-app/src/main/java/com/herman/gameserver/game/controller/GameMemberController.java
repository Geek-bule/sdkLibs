package com.herman.gameserver.game.controller;


import com.herman.common.bean.ResponseBean;
import com.herman.gameserver.common.controller.BaseController;
import com.herman.gameserver.entity.member.Member;
import com.herman.gameserver.service.game.IGameMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/gamemember")
public class GameMemberController extends BaseController {

    @Autowired
    private IGameMemberService gameMemberService;

    /**
     * 账号登录
     */
    @RequestMapping(value = "/thirdPartLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean thirdPartLogin(@RequestParam("dg_udid") String dg_udid,
                                       @RequestParam("platform_type") String platform_type,
                                       @RequestParam("platform_account") String platform_account,
                                       @RequestParam("nikename") String nikename,
                                       @RequestParam("head") String head,
                                       @RequestParam("game_id") Long game_id){

        //根据game_id、platform_type和platform_account查询是否存在记录
        Member accountData = gameMemberService.getMemberAccountData(game_id,platform_type,platform_account);
        if (accountData != null) {
            //如果存在账号记录，对比dg_udid,nikename和head,如果不同，则更新数据库
            accountData.setNikename(nikename);
            accountData.setHead(head);
            accountData.setDgUdid(dg_udid);
            gameMemberService.updateMemberData(accountData);
            return new ResponseBean(accountData);
        }else {
            //如果不存在账号记录，创建账号，生成dg_account.
            accountData = new Member();
            accountData.setDgUdid(dg_udid);
            accountData.setGameId(game_id);
            accountData.setPlatformAccount(platform_account);
            accountData.setPlatformType(platform_type);
            accountData.setNikename(nikename);
            accountData.setHead(head);
            //生成dg_account;
            String generateID = "" + System.currentTimeMillis(); // 先这么写吧，获取时间
            accountData.setDgAccount(generateID);
            gameMemberService.insertMemberData(accountData);
            return new ResponseBean(accountData);
        }
    }

    /**
     * 游客登录
     */
    @RequestMapping(value = "/guestLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean guestLogin(@RequestParam("dg_udid") String dg_udid,
                                   @RequestParam("game_id") Long game_id){
        //根据game_id、platform_type和platform_account查询是否存在记录
        Member guestData = gameMemberService.getMemberGuestData(game_id,dg_udid);
        if (guestData != null) {
            return new ResponseBean(guestData);
        }else {
            //如果不存在账号记录，创建账号，生成dg_account.
            guestData = new Member();
            guestData.setDgUdid(dg_udid);
            guestData.setGameId(game_id);
            //生成dg_account,nikename;
            String generateID = "" + System.currentTimeMillis(); // 先这么写吧，获取时间
            guestData.setDgAccount(generateID);
            guestData.setNikename(generateID);
            gameMemberService.insertMemberData(guestData);
            return new ResponseBean(guestData);
        }
    }

    /**
     * 游客账号绑定账号
     *
     */
    @RequestMapping(value = "/bindingThirdPart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean bindingThirdPart(@RequestParam("dg_udid") String dg_udid,
                                         @RequestParam("platform_type") String platform_type,
                                         @RequestParam("platform_account") String platform_account,
                                         @RequestParam("nikename") String nikename,
                                         @RequestParam("head") String head,
                                         @RequestParam("game_id") Long game_id) {
        //根据game_id、platform_type和platform_account查询是否存在记录
        Member guestData = gameMemberService.getMemberGuestData(game_id,dg_udid);
        if (guestData != null) {
            //如果存在账号记录，对比dg_udid,nikename和head,如果不同，则更新数据库
            guestData.setPlatformType(platform_type);
            guestData.setPlatformAccount(platform_account);
            guestData.setNikename(nikename);
            guestData.setHead(head);
            gameMemberService.updateMemberData(guestData);
            return new ResponseBean(guestData);
        }else {
            //返回异常，服务器自己也输出错误log
            return new ResponseBean(guestData);
        }
    }
}
