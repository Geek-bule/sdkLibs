package com.herman.gameserver.game.controller;

import com.herman.common.bean.ResponseBean;
import com.herman.gameserver.common.controller.BaseController;
import com.herman.gameserver.entity.game.GameRecord;
import com.herman.gameserver.entity.mobile.MobileIos;
import com.herman.gameserver.service.game.IGameMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/gamemobile")
public class GameMobileController  extends BaseController {

    @Autowired
    private IGameMobileService gameMobileService;

    @RequestMapping(value = "/registeriOS", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean reginsteriOS(@RequestParam("dg_udid") String dg_udid,
                                      @RequestParam("idfa") String iDFA,
                                      @RequestParam("idfv") String iDFV,
                                      @RequestParam("open_udid") String openUdid) throws Exception {

        //返回值
        ResponseBean result = new ResponseBean();
        boolean isDgudidExsit =true;
        //如果duoguUdid非空，查询是否存在，另外如果没有查到duoguUDid，抛出异常
        if (!dg_udid.isEmpty()) {
            MobileIos iosInfo = gameMobileService.getMobileIosByDgudid(dg_udid);
            //如果存在，异步检测并更新idfa等参数，注意检查idfa等参数是否存在，进行合并.
            if (iosInfo != null) {
                result.setCode(CON_SUCCESS_CODE);
                result.setMsg(CON_SUCCESS_MSG);
                HashMap hashMap = new HashMap();
                hashMap.put("dg_udid",iosInfo.getDgUdid());
                result.setContent(hashMap);
                return result;
            }else{
                isDgudidExsit=  false;
            }
        }else {
            isDgudidExsit = false;
        }
        //dg_udid 不存在的时候
        if (!isDgudidExsit) {
            //查idfa有没有，如果idfa有，返回查到的duogu_udid
            MobileIos iosInfo = gameMobileService.getMobileIosByIdfa(iDFA);
            if (iosInfo != null) {

                return new ResponseBean(iosInfo.getDgUdid());
            }else {

                iosInfo = new MobileIos();
                String generateID = "" + System.currentTimeMillis(); // 先这么写吧，获取时间
                iosInfo.setDgUdid(generateID);
                iosInfo.setIdfa(iDFA);
                iosInfo.setIdfv(iDFV);
                iosInfo.setOpenUdid(openUdid);
                gameMobileService.insertMobileInfo(iosInfo);
                return new ResponseBean(generateID);
            }

            //如果没有idfa，查idfv，如果idfv有，返回查到的duogu_udid，并且更新idfa。注意检查idfa是否存在，进行合并
            //如果没有idfa，idfv，查openUdid，如果openUdid有，则返回查到的duogu_udid,并且更新idfa，idfv。注意检查idfa，idfv是否存在，进行合并
            //如果上面3个id不存在或者出现全0的id，则生成duoguUdid，并创建数据库记录
            //注意上面的信息合并异步处理。
        }
        return result;
    }

    @RequestMapping(value = "/registerAndroid", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean registerAndroid(@RequestParam("imei") String iMEI,
                                      @RequestParam("androidId") String androidId,
                                      @RequestParam("macAdress") String macAdress) throws Exception {
        String ok="ss";
        return new ResponseBean(ok);
    }
}
