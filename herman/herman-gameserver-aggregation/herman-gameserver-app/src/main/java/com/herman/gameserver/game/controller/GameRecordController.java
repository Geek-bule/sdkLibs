package com.herman.gameserver.game.controller;

import com.herman.common.bean.ResponseBean;
import com.herman.gameserver.common.controller.BaseController;
import com.herman.gameserver.entity.game.GameRecord;
import com.herman.gameserver.service.game.IGameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 游戏记录管理controller
 * Created by herman on 2017/11/27.
 */

@Controller
@RequestMapping("/gamerecord")
public class GameRecordController extends BaseController {

    @Autowired
    private IGameRecordService gameRecordService;

    @RequestMapping(value = "/getGameRecord", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getGameRecord(@RequestParam("gameId") Long gameId, @RequestParam("dgAccount") String dgAccount) throws Exception {
        GameRecord gameRecord = gameRecordService.getGameRecord(gameId, dgAccount);
        if(gameRecord == null) {
            ResponseBean result = new ResponseBean();
            result.fail();
            return result;
        }
        String id = "" + System.currentTimeMillis(); // 先这么写吧，获取时间
        return new ResponseBean(gameRecord.getRecord());
    }

    @RequestMapping(value = "/pullGameRecord", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean pullGameRecord(@RequestParam("gameId") Long gameId, @RequestParam("dgAccount") String dgAccount) throws Exception {
        GameRecord gameRecord = gameRecordService.getGameRecord(gameId, dgAccount);
        if(gameRecord == null) {
            ResponseBean result = new ResponseBean();
            result.fail();
            return result;
        }
        String id = "" + System.currentTimeMillis(); // 先这么写吧，获取时间
        return new ResponseBean(gameRecord.getRecord());
    }

}
