package com.herman.gameserver.game.controller;

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

/**
 * 游戏记录管理controller
 * Created by herman on 2017/11/27.
 */

@Controller
@RequestMapping("/gamerecord")
public class GameRecordController extends BaseController {

    @Autowired
    private IGameRecordService gameRecordService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getGameRecord(@RequestParam("gameId") Long gameId, @RequestParam("dgAccount") String dgAccount){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            GameRecord gameRecord = gameRecordService.getGameRecord(gameId, dgAccount);
            result.put("code", "0"); // success
            result.put("message", "success");
            result.put("record", gameRecord.getRecord());
        } catch (Exception e) {
            result.put("code", "1"); // fail
            result.put("message", e.getMessage());
        }
        return result;
    }

}
