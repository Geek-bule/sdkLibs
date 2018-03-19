package com.herman.gameserver.mobile.controller;

import com.herman.common.bean.ResponseBean;
import com.herman.common.constant.DeviceType;
import com.herman.gameserver.common.constant.ErrorMsg;
import com.herman.gameserver.common.controller.BaseController;
import com.herman.gameserver.common.util.MyValidateUtil;
import com.herman.gameserver.entity.mobile.MobileAndroid;
import com.herman.gameserver.entity.mobile.MobileGame;
import com.herman.gameserver.entity.mobile.MobileIos;
import com.herman.gameserver.entity.push.PushRecord;
import com.herman.gameserver.mobile.dataset.MobileDataSet;
import com.herman.gameserver.mobile.dto.MobileDto;
import com.herman.gameserver.service.game.IGameCache;
import com.herman.gameserver.service.mobile.IMobileAndroidService;
import com.herman.gameserver.service.mobile.IMobileGameService;
import com.herman.gameserver.service.mobile.IMobileIosService;
import com.herman.gameserver.service.mobile.IMobileService;
import com.herman.gameserver.service.push.IPushRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by herman on 2018/1/31.
 */
@Controller
public class MobileController extends BaseController {

    private static final Logger logger = Logger.getLogger(MobileController.class);

    @Autowired
    private IMobileIosService mobileIosService;
    @Autowired
    private IMobileAndroidService mobileAndroidService;
    @Autowired
    private IMobileService mobileService;
    @Autowired
    private IGameCache gameCache;
    @Autowired
    private IMobileGameService mobileGameService;
    @Autowired
    private IPushRecordService pushRecordService;

    /**
     * 注册设备
     *
     * @param mobileDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/app/v1/mobile/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean getOrAddMobile(MobileDto mobileDto) throws Exception {
        logger.debug("[注册设备]-" + mobileDto);
        String mobileType = mobileDto.getMobileType();
        String idfa = mobileDto.getIdfa();
        String imei = mobileDto.getImei();
        String gameCode = mobileDto.getGameCode();
        //校验gameCode
        if(MyValidateUtil.isEmpty(gameCode)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_GAMECODE_ERR_CODE);
        }
        // 判断mobileType是否正确
        if(!MyValidateUtil.isMobileType(mobileType)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_MOBILETYPE_ERR_CODE);
        }
        // 判断idfa是否正确
        if(DeviceType.IOS.equals(mobileType) && MyValidateUtil.isEmpty(idfa)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_IDFA_ERR_CODE);
        }
        // 判断imei是否正确
        if(DeviceType.ANDROID.equals(mobileType) && MyValidateUtil.isEmpty(imei)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_IMEI_ERR_CODE);
        }

        Long gameId = gameCache.getGameIdByCode(gameCode);
        if(gameId == null) {
            return getErrResponseBean(ErrorMsg.INVALID_GAMECODE_ERR_CODE);
        }

        if(DeviceType.IOS.equals(mobileType)) {
            MobileIos mobile = getOrAddMobileIos(mobileDto);
            //用户游戏绑定
            if (mobileGameService.bindingGameMember(gameId,mobile.getDgUdid())) {
                //查询推送记录表，是否有激活
                pushRecordService.activePushGame(gameId, mobile.getDgUdid());
            }

            MobileDataSet dataSet = new MobileDataSet();
            BeanUtils.copyProperties(mobile, dataSet);
            return new ResponseBean(dataSet);
        } else {
            MobileAndroid mobile = getOrAddMobileAndroid(mobileDto);
            //用户游戏绑定
            MobileGame game = mobileGameService.getMobileGameByDgudidAndGameId(mobile.getDgUdid(),gameId);
            if (game == null) {
                MobileGame mobileGame = new MobileGame();
                mobileGame.setDgUdid(mobile.getDgUdid());
                mobileGame.setGameId(gameId);
                mobileGame.setVersion("1.0");
                mobileGameService.add(mobileGame);
            }
            MobileDataSet dataSet = new MobileDataSet();
            BeanUtils.copyProperties(mobile, dataSet);
            return new ResponseBean(dataSet);
        }
    }


    /**
     * 修改设备
     *
     * @param mobileDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/app/v1/mobile/m", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean modifyMobile(MobileDto mobileDto) throws Exception {
        logger.debug("[修改设备]-" + mobileDto);
        String mobileType = mobileDto.getMobileType();
        String duUdid = mobileDto.getDgUdid();
        String idfa = mobileDto.getIdfa();
        String imei = mobileDto.getImei();
        if(!MyValidateUtil.isDgUdid(duUdid)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_DGUDID_ERR_CODE);
        }
        // 判断mobileType是否正确
        if(!MyValidateUtil.isMobileType(mobileType)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_MOBILETYPE_ERR_CODE);
        }
        // 判断idfa是否正确
        if(DeviceType.IOS.equals(mobileType) && MyValidateUtil.isEmpty(idfa)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_IDFA_ERR_CODE);
        }
        // 判断imei是否正确
        if(DeviceType.ANDROID.equals(mobileType) && MyValidateUtil.isEmpty(imei)) {
            return getErrResponseBean(ErrorMsg.REQUEST_PARAM_IMEI_ERR_CODE);
        }

        if(DeviceType.IOS.equals(mobileType)) {
            MobileIos mobile = mobileIosService.getMobileByDgUdid(duUdid);
            if(mobile == null) {
                // 设备信息不存在，无法修改
                return getErrResponseBean(ErrorMsg.INVALID_DGUDID_ERR_CODE);
            } else {
                // 更新数据
                MobileIos mobileToSave = new MobileIos();
                mobileToSave.setId(mobile.getId());
                mobileToSave.setIdfa(mobileDto.getIdfa());
                mobileToSave.setIdfv(mobileDto.getIdfv());
                mobileToSave.setOpenUdid(mobileDto.getOpenUdid());
                mobileIosService.saveMobile(mobileToSave);
                // 封装返回结果
                MobileDataSet dataSet = new MobileDataSet();
                BeanUtils.copyProperties(mobileToSave, dataSet);
                dataSet.setDgUdid(mobile.getDgUdid());
                return new ResponseBean(dataSet);
            }
        } else {
            MobileAndroid mobile = mobileAndroidService.getMobileByDgUdid(duUdid);
            if(mobile == null) {
                // 设备信息不存在，无法修改
                return getErrResponseBean(ErrorMsg.INVALID_DGUDID_ERR_CODE);
            } else {
                // 更新数据
                MobileAndroid mobileToSave = new MobileAndroid();
                mobileToSave.setId(mobile.getId());
                mobileToSave.setImei(mobileDto.getImei());
                mobileToSave.setAndroidId(mobileDto.getAndroidId());
                mobileToSave.setAddress(mobileDto.getAddress());
                mobileAndroidService.saveMobile(mobileToSave);
                // 封装返回结果
                MobileDataSet dataSet = new MobileDataSet();
                BeanUtils.copyProperties(mobileToSave, dataSet);
                dataSet.setDgUdid(mobile.getDgUdid());
                return new ResponseBean(dataSet);
            }
        }
    }


    /**
     * 查询或新建ios设备信息
     *
     * @param mobileDto
     * @return
     */
    private MobileIos getOrAddMobileIos(MobileDto mobileDto) {
        MobileIos mobile = mobileIosService.getMobileByIdfa(mobileDto.getIdfa());
        if(mobile != null) {
            return mobile;
        } else {
            String dgUdid = mobileService.getDgUdid();
            MobileIos mobileToSave = new MobileIos();
            mobileToSave.setDgUdid(dgUdid);
            mobileToSave.setIdfa(mobileDto.getIdfa());
            mobileToSave.setIdfv(mobileDto.getIdfv());
            mobileToSave.setOpenUdid(mobileDto.getOpenUdid());
            mobileIosService.saveMobile(mobileToSave);
            logger.debug("[注册设备]-获取新设备号dgUdid:" + dgUdid);
            return mobileToSave;
        }
    }


    /**
     * 查询或新建android设备信息
     *
     * @param mobileDto
     * @return
     */
    private MobileAndroid getOrAddMobileAndroid(MobileDto mobileDto) {
        MobileAndroid mobile = mobileAndroidService.getMobileByImei(mobileDto.getImei());
        if(mobile != null) {
            return mobile;
        } else {
            String dgUdid = mobileService.getDgUdid();
            MobileAndroid mobileToSave = new MobileAndroid();
            mobileToSave.setDgUdid(dgUdid);
            mobileToSave.setImei(mobileDto.getImei());
            mobileToSave.setAndroidId(mobileDto.getAndroidId());
            mobileToSave.setAddress(mobileDto.getAddress());
            mobileAndroidService.saveMobile(mobileToSave);
            logger.debug("[注册设备]-获取新设备号dgUdid:" + dgUdid);
            return mobileToSave;
        }
    }

}
