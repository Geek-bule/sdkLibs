package com.herman.demo.game.controller;

import com.herman.demo.common.controller.BaseController;
import com.herman.demo.entity.game.GameRecord;
import com.herman.demo.service.game.IGameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by herman on 2017/11/27.
 */

@Controller
@RequestMapping("/gameRecord")
public class GameRecordController extends BaseController {

    @Autowired
    private IGameRecordService gameRecordService;

    @RequestMapping("/get")
    @ResponseBody
    public Map<String, Object> getGameRecord(@RequestParam("gameId") Long gameId, @RequestParam("dgAccount") String dgAccount){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
//            GameRecord gameRecord = gameRecordService.getGameRecord(gameId, dgAccount);
            result.put("code", "0"); // success
            result.put("message", "success");
//            result.put("record", gameRecord.getRecord());
        } catch (Exception e) {
            result.put("code", "1"); // fail
            result.put("message", e.getMessage());
        }
        return result;
    }

}
