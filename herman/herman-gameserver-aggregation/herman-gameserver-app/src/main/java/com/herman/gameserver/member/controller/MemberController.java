package com.herman.gameserver.member.controller;

import com.herman.common.bean.ResponseBean;
import com.herman.common.constant.DeviceType;
import com.herman.gameserver.common.constant.ErrorMsg;
import com.herman.gameserver.common.controller.BaseController;
import com.herman.gameserver.common.util.MyValidateUtil;
import com.herman.gameserver.entity.member.Member;
import com.herman.gameserver.member.dataset.MemberDataSet;
import com.herman.gameserver.member.dto.MemberDto;
import com.herman.gameserver.service.game.IGameCache;
import com.herman.gameserver.service.member.IMemberService;
import com.herman.gameserver.service.mobile.IMobileAndroidService;
import com.herman.gameserver.service.mobile.IMobileIosService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by herman on 2018/1/31.
 */
@Controller
public class MemberController extends BaseController {

    private static final Logger logger = Logger.getLogger(MemberController.class);

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IMobileIosService mobileIosService;
    @Autowired
    private IMobileAndroidService mobileAndroidService;
    @Autowired
    private IGameCache gameCache;

    @RequestMapping(value = "/app/v1/member/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean getOrAddMember(MemberDto dto) throws Exception {
        logger.debug("[注册帐号]-" + dto);
        String gameCode = dto.getGameCode();
        String dgUdid = dto.getDgUdid();
        String mobileType = dto.getMobileType();
        String platformType = dto.getPlatformType();
        // 校验
        if(MyValidateUtil.isEmpty(gameCode)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_GAMECODE_ERR_CODE);
        }
        if(!MyValidateUtil.isDgUdid(dgUdid)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_DGUDID_ERR_CODE);
        }
        if(!MyValidateUtil.isMobileType(mobileType)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_MOBILETYPE_ERR_CODE);
        }
        if(MyValidateUtil.isEmpty(platformType)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_PLATFORMTYPE_ERR_CODE);
        }
        Long gameId = gameCache.getGameIdByCode(gameCode);
        if(gameId == null) {
            return getErrResponseBean(ErrorMsg.INVALID_GAMECODE_ERR_CODE);
        }
        Member member = memberService.getMember(gameId, dgUdid);
        if(member != null) {
            // 帐号存在，返回查询结果
            MemberDataSet dataSet = new MemberDataSet();
            BeanUtils.copyProperties(member, dataSet);
            return new ResponseBean(dataSet);
        } else {
            // 帐号不存在，创建帐号

            // 判断设备号dgUdid是否有效
            if (DeviceType.IOS.equals(mobileType) && mobileIosService.getMobileByDgUdid(dgUdid) == null) {
                return getErrResponseBean(ErrorMsg.INVALID_DGUDID_ERR_CODE);
            }
            if(DeviceType.ANDROID.equals(mobileType) && mobileAndroidService.getMobileByDgUdid(dgUdid) == null) {
                return getErrResponseBean(ErrorMsg.INVALID_DGUDID_ERR_CODE);
            }

            Member memberToSave = new Member();
            BeanUtils.copyProperties(dto, memberToSave);
            String dgAccount = memberService.getDgAccount(gameId);
            memberToSave.setDgAccount(dgAccount);
            memberToSave.setGameId(gameId);
            memberService.saveMember(memberToSave);
            logger.debug("[注册帐号]-新增帐号dgAccount:" + dgAccount);

            MemberDataSet dataSet = new MemberDataSet();
            BeanUtils.copyProperties(memberToSave, dataSet);
            return new ResponseBean(dataSet);
        }
    }

}
