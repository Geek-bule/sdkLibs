package com.herman.gameserver.game.controller;

import com.herman.common.bean.ResponseBean;
import com.herman.gameserver.common.constant.ErrorMsg;
import com.herman.gameserver.common.controller.BaseController;
import com.herman.gameserver.common.util.MyValidateUtil;
import com.herman.gameserver.entity.game.GameRecord;
import com.herman.gameserver.game.dataset.GameRecordDataSet;
import com.herman.gameserver.game.dto.GameRecordDto;
import com.herman.gameserver.service.game.IGameCache;
import com.herman.gameserver.service.game.IGameRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 游戏记录管理controller
 * Created by herman on 2017/11/27.
 */

@Controller
public class GameRecordController extends BaseController {

    @Autowired
    private IGameRecordService gameRecordService;
    @Autowired
    private IGameCache gameCache;

    @RequestMapping(value = "/app/v1/game/record", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getGameRecord(GameRecordDto dto) throws Exception {
        String gameCode = dto.getGameCode();
        String dgAccount = dto.getDgAccount();
        if(MyValidateUtil.isEmpty(gameCode)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_GAMECODE_ERR_CODE);
        }
        if(!MyValidateUtil.isDgAccount(dgAccount)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_DGACCOUNT_ERR_CODE);
        }
        Long gameId = gameCache.getGameIdByCode(gameCode);
        if(gameId == null) {
            return getErrResponseBean(ErrorMsg.INVALID_GAMECODE_ERR_CODE);
        }
        GameRecord gameRecord = gameRecordService.getGameRecord(gameId, dgAccount);
        if(gameRecord == null) {
            return getErrResponseBean(ErrorMsg.INVALID_RECORD_ERR_CODE);
        }
        GameRecordDataSet dataSet = new GameRecordDataSet();
        BeanUtils.copyProperties(gameRecord, dataSet);
        return new ResponseBean(dataSet);
    }


    @RequestMapping(value = "/app/v1/game/record", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean saveGameRecord(GameRecordDto dto) throws Exception {
        String gameCode = dto.getGameCode();
        String dgAccount = dto.getDgAccount();
        String record = dto.getRecord();
        if(MyValidateUtil.isEmpty(gameCode)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_GAMECODE_ERR_CODE);
        }
        if(!MyValidateUtil.isDgAccount(dgAccount)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_DGACCOUNT_ERR_CODE);
        }
        if(MyValidateUtil.isEmpty(record)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_RECORD_ERR_CODE);
        }
        Long gameId = gameCache.getGameIdByCode(gameCode);
        if(gameId == null) {
            return getErrResponseBean(ErrorMsg.INVALID_GAMECODE_ERR_CODE);
        }

        GameRecord gameRecord = gameRecordService.getGameRecord(gameId, dgAccount);
        GameRecord gameRecordToSave = new GameRecord();
        if(gameRecord == null) {
            // 新增
            gameRecordToSave.setGameId(gameId);
            gameRecordToSave.setDgAccount(dgAccount);
            gameRecordToSave.setRecord(record);
        } else {
            // 更新
            gameRecordToSave.setId(gameRecord.getId());
            gameRecordToSave.setRecord(record);
        }
        gameRecordService.saveGameRecord(gameRecordToSave);
        return new ResponseBean();
    }

}
