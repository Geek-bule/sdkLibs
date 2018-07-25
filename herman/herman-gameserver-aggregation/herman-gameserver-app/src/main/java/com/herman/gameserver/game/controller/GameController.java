package com.herman.gameserver.game.controller;

import com.herman.common.bean.ResponseBean;
import com.herman.gameserver.common.controller.BaseController;
import com.herman.gameserver.entity.game.Game;
import com.herman.gameserver.game.dataset.GameDataSet;
import com.herman.gameserver.game.dto.GameDto;
import com.herman.gameserver.game.dto.GameRecordDto;
import com.herman.gameserver.member.controller.MemberController;
import com.herman.gameserver.service.game.IGameCache;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GameController extends BaseController {

    private static final Logger logger = Logger.getLogger(MemberController.class);
    @Autowired
    private IGameCache gameCache;

    @RequestMapping(value = "/app/v1/web/game/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getGame(Game dto) throws Exception {
        logger.debug("[获取游戏信息列表]-" + dto);
        List<Game> gameList = gameCache.getGameList();
        return new ResponseBean(gameList);

    }


    @RequestMapping(value = "/app/v1/web/game/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean addGame(GameDto dto) throws Exception {
        logger.debug("[添加游戏信息]-" + dto);
        Game game = new Game();
        game.setMobileType(dto.getMobileType());
        game.setName(dto.getName());
        game.setPlatformId(dto.getPlatformId());
        game.setPlatformUrl(dto.getPlatformUrl());
        game.setPlatformVersion(dto.getPlatformVersion());
        //ios的gameCode暂时用appid来填写
        game.setCode(dto.getPlatformId());
        gameCache.add(game);
        return new ResponseBean();
    }


    @RequestMapping(value = "/app/v1/web/game/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean updateGame(GameDto dto) throws Exception {
        logger.debug("[更新游戏信息]-" + dto);
        Game game = gameCache.getGameByCode(dto.getCode());
        game.setMobileType(dto.getMobileType());
        game.setName(dto.getName());
        game.setPlatformId(dto.getPlatformId());
        game.setPlatformUrl(dto.getPlatformUrl());
        game.setPlatformVersion(dto.getPlatformVersion());
        gameCache.update(game);
        return new ResponseBean();
    }
}
