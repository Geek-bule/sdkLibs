package com.herman.gameserver.push.controller;

import com.herman.common.bean.ResponseBean;
import com.herman.gameserver.common.constant.ErrorMsg;
import com.herman.gameserver.common.controller.BaseController;
import com.herman.gameserver.common.util.MyValidateUtil;
import com.herman.gameserver.entity.push.PushRecord;
import com.herman.gameserver.entity.push.PushStatistics;
import com.herman.gameserver.push.dataset.PushDataSet;
import com.herman.gameserver.push.dto.PushDto;
import com.herman.gameserver.service.game.IGameCache;
import com.herman.gameserver.service.push.IPushRecordService;
import com.herman.gameserver.service.push.IPushStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 
 * 游戏推送记录表 控制层
 * 
 */
@Controller
public class PushRecordController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PushRecordController.class);

	@Autowired
	private IPushRecordService pushRecordService;
	@Autowired
	private IGameCache gameCache;
	@Autowired
	private IPushStatisticsService pushStatisticsService;

	@RequestMapping(value = "/app/v1/push/record", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean getRecommendGame(PushDto dto) throws Exception {
		logger.debug("[点击推荐游戏记录]-" + dto);
		String gameCode = dto.getGameCode();
		String dgUdid = dto.getDgUdid();
		String pushGameCode = dto.getPushGameCode();

		// 校验
		if (MyValidateUtil.isEmpty(gameCode)) {
			return getErrResponseBean(ErrorMsg.REQUEST_PARAM_GAMECODE_ERR_CODE);
		}
		if (MyValidateUtil.isEmpty(pushGameCode)) {
			return getErrResponseBean(ErrorMsg.REQUEST_PARAM_GAMECODE_ERR_CODE);
		}
		if (!MyValidateUtil.isDgUdid(dgUdid)) {
			return getErrResponseBean(ErrorMsg.REQUEST_PARAM_DGUDID_ERR_CODE);
		}
		//转化code
		Long gameId = gameCache.getGameIdByCode(gameCode);
		if (gameId == null) {
			return getErrResponseBean(ErrorMsg.INVALID_GAMECODE_ERR_CODE);
		}

		Long pushGameId = gameCache.getGameIdByCode(pushGameCode);
		if (pushGameId == null) {
			return getErrResponseBean(ErrorMsg.INVALID_GAMECODE_ERR_CODE);
		}

		String pushVersion = "1.0";
		PushRecord pushRecord = pushRecordService.getPushRecordByPushIdAndGameId(pushGameId,gameId,dgUdid,pushVersion);
		if (pushRecord == null) {
			pushRecord = new PushRecord();
			pushRecord.setGameId(gameId);
			pushRecord.setPushGameId(pushGameId);
			pushRecord.setDgUdid(dgUdid);
			pushRecord.setPushVersion(pushVersion);
			pushRecord.setLastJumpTime(new Date());
			pushRecordService.add(pushRecord);
		}else{
			pushRecord.setJumpTimes(pushRecord.getJumpTimes()+1);
			pushRecord.setLastJumpTime(new Date());
			pushRecordService.updateByIdAndGameId(pushRecord,pushRecord.getId(),pushRecord.getGameId());
		}

		//更新点击数据
		pushStatisticsService.updateClickStatistics(gameId,pushGameId);

		PushDataSet dataSet = new PushDataSet();
		return new ResponseBean(dataSet);
	}


	@RequestMapping(value = "/app/v1/push/statistics/show", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean statisticsShowGame(PushDto dto) throws Exception {
		logger.debug("[统计icon展示]-" + dto);
		String gameCode = dto.getGameCode();
		String pushGameCode = dto.getPushGameCode();

		// 校验
		if (MyValidateUtil.isEmpty(gameCode)) {
			return getErrResponseBean(ErrorMsg.REQUEST_PARAM_GAMECODE_ERR_CODE);
		}
		if (MyValidateUtil.isEmpty(pushGameCode)) {
			return getErrResponseBean(ErrorMsg.REQUEST_PARAM_GAMECODE_ERR_CODE);
		}
		//转化code
		Long gameId = gameCache.getGameIdByCode(gameCode);
		if (gameId == null) {
			return getErrResponseBean(ErrorMsg.INVALID_GAMECODE_ERR_CODE);
		}

		Long pushGameId = gameCache.getGameIdByCode(pushGameCode);
		if (pushGameId == null) {
			return getErrResponseBean(ErrorMsg.INVALID_GAMECODE_ERR_CODE);
		}

		Date date = new Date();
		DateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		String strDate = sdf2.format(date);
		Long statisticsDate = Long.parseLong(strDate);
		PushStatistics pushStatistics = pushStatisticsService.getPushStatisticsByGameIdAndStatisticsDate(gameId,pushGameId,statisticsDate);
		if (pushStatistics == null) {
			pushStatistics = new PushStatistics();
			pushStatistics.setGameId(gameId);
			pushStatistics.setPushGameId(pushGameId);
			pushStatistics.setIconShow(Long.parseLong("1"));
			pushStatistics.setStatisticsDate(statisticsDate);
			pushStatisticsService.add(pushStatistics);
		}else{
			pushStatistics.setIconShow(pushStatistics.getIconShow()+1);
			pushStatisticsService.updateByIdAndStatisticsDate(pushStatistics,pushStatistics.getId(),pushStatistics.getStatisticsDate());
		}
		PushDataSet dataSet = new PushDataSet();
		return new ResponseBean(dataSet);
	}


	@RequestMapping(value = "/app/v1/web/statistics/get", method = RequestMethod.GET)
	@ResponseBody
	public ResponseBean getStatisticsData(PushDto dto) throws Exception {
		logger.debug("[获取统计数据]-" + dto);
		Long statisticsDate = dto.getCurrentDate();

		PushStatistics pushStatistics = new PushStatistics();
		pushStatistics.setStatisticsDate(statisticsDate);
		List<PushStatistics> pushStatisticses = pushStatisticsService.findListByParam(pushStatistics);

		return new ResponseBean(pushStatisticses);
	}
}