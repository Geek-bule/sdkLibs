package com.herman.gameserver.push.controller;

import com.herman.common.bean.ResponseBean;
import com.herman.gameserver.common.constant.ErrorMsg;
import com.herman.gameserver.common.controller.BaseController;
import com.herman.gameserver.common.util.MyValidateUtil;
import com.herman.gameserver.entity.game.Game;
import com.herman.gameserver.entity.push.PushRule;
import com.herman.gameserver.member.dataset.MemberDataSet;
import com.herman.gameserver.push.dataset.PushDataSet;
import com.herman.gameserver.push.dto.PushDto;
import com.herman.gameserver.service.push.IPushRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.herman.gameserver.service.game.IGameCache;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 游戏推送规则表 控制层
 * 
 */
@Controller
public class PushRuleController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PushRuleController.class);

	@Autowired
	private IPushRuleService pushRuleService;
	@Autowired
	private IGameCache gameCache;

	@RequestMapping(value = "/app/v1/push/recommend", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean getRecommendGame(PushDto dto) throws Exception {
		logger.debug("[获取推荐游戏信息]-" + dto);
		String gameCode = dto.getGameCode();
		String dgUdid = dto.getDgUdid();
		String mobileType = dto.getMobileType();

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

		Long gameId = gameCache.getGameIdByCode(gameCode);
		if(gameId == null) {
			return getErrResponseBean(ErrorMsg.INVALID_GAMECODE_ERR_CODE);
		}

		List<PushRule> pushRules = pushRuleService.getPushRuleByGameCode(gameCode,mobileType);
		if (pushRules != null) {
			List<PushDataSet> dataSets = new ArrayList<PushDataSet>();
			for (PushRule pushRule: pushRules) {
				PushDataSet dataSet = new PushDataSet();
				Game game = gameCache.getGameByCode(pushRule.getPushGameCode());
				dataSet.setGameCode(game.getCode());
				dataSet.setMobileType(game.getMobileType());
				dataSet.setPlatformId(game.getPlatformId());
				dataSet.setPlatformUrl(game.getPlatformUrl());
				dataSet.setPlatformVersion(game.getPlatformVersion());
				dataSet.setPercent(pushRule.getPercent());
				dataSets.add(dataSet);
			}
			return new ResponseBean(dataSets);
		}
		return getErrResponseBean(ErrorMsg.SERVER_ERR_CODE);
	}

	/**
	 * 添加互推规则
	 *
	 */
	@RequestMapping(value = "/app/v1/web/push/modify", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean getPushGame(PushDto dto) throws Exception {
		logger.debug("[添加互推信息]-" + dto);
		String gameCode = dto.getGameCode();
		String mobileType = dto.getMobileType();
		String pushGameCode = dto.getPushGameCode();
		Long percent = dto.getPercent();

		// 校验
		if(MyValidateUtil.isEmpty(gameCode)) {
			return getErrResponseBean(ErrorMsg.REQUEST_PARAM_GAMECODE_ERR_CODE);
		}

		// 校验
		if(MyValidateUtil.isEmpty(pushGameCode)) {
			return getErrResponseBean(ErrorMsg.REQUEST_PARAM_GAMECODE_ERR_CODE);
		}

		if(!MyValidateUtil.isMobileType(mobileType)) {
			return getErrResponseBean(ErrorMsg.REQUEST_PARAM_MOBILETYPE_ERR_CODE);
		}

		PushRule pushRule = pushRuleService.getPushRuleByGameIdAndPushId(gameCode,pushGameCode,mobileType);
		if (pushRule == null) {
			pushRule = new PushRule();
			pushRule.setGameCode(gameCode);
			pushRule.setPushGameCode(pushGameCode);
			pushRule.setMobileType(mobileType);
			pushRule.setPercent(percent);
			pushRuleService.add(pushRule);
		}else{
			//根据传入的percent判断，如果为0，则删除掉这个互推规则
			if (percent == 0) {
				pushRuleService.deleteById(pushRule.getId());
			}else {
				//更新
				pushRule.setPercent(percent);
				pushRuleService.updateById(pushRule,pushRule.getId());
			}
		}
		return new ResponseBean();
	}

}