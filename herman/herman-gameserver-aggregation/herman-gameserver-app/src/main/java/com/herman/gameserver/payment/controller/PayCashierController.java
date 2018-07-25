package com.herman.gameserver.payment.controller;

import com.herman.common.bean.ResponseBean;
import com.herman.common.util.HttpClientUitl;
import com.herman.common.util.MD5;
import com.herman.gameserver.common.constant.ErrorMsg;
import com.herman.gameserver.common.controller.BaseController;
import com.herman.gameserver.entity.payment.PayCashier;
import com.herman.gameserver.payment.dataset.PayDataSet;
import com.herman.gameserver.payment.dto.PayCashierDto;
import com.herman.gameserver.push.dto.PushDto;
import com.herman.gameserver.service.payment.IPayCashierService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 游戏表 控制层
 * 
 */
@Controller
public class PayCashierController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PayCashierController.class);

	@Autowired
	private IPayCashierService payCashierService;


	@RequestMapping(value = "/app/v1/pay/Prepayment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean getRecommendGame(PayCashierDto dto) throws Exception {
		logger.debug("[支付调用接口]-" + dto);
		String partnerCode = dto.getPartnerCode();
		String productName = dto.getProductName();
		String orderId = dto.getOrderId();
		String userEmail = dto.getUserEmail();
		String currencyCode = dto.getCurrencyCode();
		Float settleAmount = dto.getSettleAmount();
		String gameId = dto.getGameId();
		String dgUdid = dto.getDgUdid();
		//写入partner_code，product_name，order_id，user_email，currency_code，settle_amount
		//dgUdid，gameId，之后调用支付接口，接口返回transaction_id，payment_url，将这两个
		//也写入数据库，然后将transaction_id，payment_url返还客户端
		Map<String,String> header = new HashMap<String, String>();
		header.put("partner_code",partnerCode);
		String md5Sign = MD5.encode(partnerCode+"|"+"f849a6028b9f474a94ae41b8eb12bab9").toLowerCase();
		header.put("sign",md5Sign);

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("product_name",productName);
		params.put("order_id",orderId);
		params.put("user_email",userEmail);
		params.put("currency_code",currencyCode);
		params.put("settle_amount",settleAmount);
		params.put("success_url","http://www.syduogu.com:8080/app/v1/pay/payresult");
		params.put("failure_url","http://www.syduogu.com:8080/app/v1/pay/payresult");
		params.put("back_url","http://cashier.kavip.com/partner/failurepage");
		params.put("callback_url","http://www.syduogu.com:8080/app/v1/pay/paycallback");
		String result = HttpClientUitl.javahttpPost(params, header, "http://cashier.kavip.com/payment/pretreatment/");
		JSONObject jsonObject = JSONObject.fromObject(result);
		String transactionId = jsonObject.getString("transaction_id");
		String payment_url = jsonObject.getString("payment_url");
		PayCashier payCashier = payCashierService.getPayCashierByTransactionId(transactionId);
		if (payCashier == null) {
			//写入数据库
			payCashier.setGameId(gameId);
			payCashier.setDgUdid(dgUdid);
			payCashier.setPartnerCode(partnerCode);
			payCashier.setProductName(productName);
			payCashier.setOrderId(orderId);
			payCashier.setUserEmail(userEmail);
			payCashier.setCurrencyCode(currencyCode);
			payCashier.setSettleAmount(settleAmount);
			payCashier.setTransactionId(transactionId);
			payCashier.setPayUrl(payment_url);
			payCashier.setPayStatus("waitpay");
			payCashierService.add(payCashier);
			//返回
			PayDataSet dataSet = new PayDataSet();
			dataSet.setTransactionId(transactionId);
			dataSet.setPaymentUrl(payment_url);
			return new ResponseBean(dataSet);
		}

		return getErrResponseBean(ErrorMsg.SERVER_ERR_CODE);
	}


	@RequestMapping(value = "/app/v1/pay/payresult", method = RequestMethod.GET)
	@ResponseBody
	public String payResult(PayCashierDto dto) throws Exception {
		logger.debug("[支付回调]-" + dto);
		String orderId = dto.getOrderId();
		String transactionId = dto.getTransactionId();
		String payStatus = dto.getPayStatus();
		//根据transactionId，写入支付状态
		PayCashier payCashier = payCashierService.getPayCashierByTransactionId(transactionId);
		if (payCashier != null) {
			if (!orderId.equals(payCashier.getOrderId())) {
				logger.error("[orderId 校验不通过]");
			}
			payCashier.setPayStatus(payStatus);
			payCashierService.updateByTransactionId(payCashier,transactionId);
		}else {
			logger.error("[支付结果没有调用记录？]");
		}
		return "OK";
	}


	@RequestMapping(value = "/app/v1/pay/paycallback", method = RequestMethod.POST)
	@ResponseBody
	public String payCallback(PayCashierDto dto) throws Exception {
		logger.debug("[异步支付结果回调]-" + dto);
		String transactionId = dto.getTransactionId();
		Float settleAmount = dto.getSettleAmount();
		String settleCurrency = dto.getSettleCurrency();
		String payStatus = dto.getPayStatus();
		String orderId = dto.getOrderId();
		java.util.Date payTime = dto.getPayTime();
		String sign = dto.getSign();
		//根据transactionId写入payStatus，payTime，同时验证settleAmount，settleCurrency，
		// orderId，sign
		PayCashier payCashier = payCashierService.getPayCashierByTransactionId(transactionId);
		if (payCashier != null) {
			//校验
			if(!settleCurrency.equals(payCashier.getCurrencyCode())){
				logger.error("[settleCurrency 校验不通过]");
			}
			if (!orderId.equals(payCashier.getOrderId())) {
				logger.error("[orderId 校验不通过]");
			}
			String md5Sign = MD5.encode(payCashier.getPartnerCode()+"|"+"f849a6028b9f474a94ae41b8eb12bab9").toLowerCase();
			if (!sign.equals(md5Sign)) {
				logger.error("[md5 sign 校验不通过]");
			}
			payCashier.setPayStatus(payStatus);
			payCashier.setPayTime(payTime);
			payCashierService.updateByTransactionId(payCashier,transactionId);
		}else {
			logger.error("[异步回调支付结果没有调用记录？]");
		}
		return "OK";
	}


	@RequestMapping(value = "/app/v1/pay/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseBean search(PayCashierDto dto) throws Exception {
		logger.debug("[查询支付结果]-" + dto);
		String transactionId = dto.getTransactionId();
		//根据transactionId查询payStatus，并返还客户端
		PayCashier payCashier = payCashierService.getPayCashierByTransactionId(transactionId);
		if (payCashier != null) {

			//返回数据
			PayDataSet dataSet = new PayDataSet();
			dataSet.setPayStatus(payCashier.getPayStatus());
			return new ResponseBean(dataSet);
		}
		return getErrResponseBean(ErrorMsg.SERVER_ERR_CODE);
	}
}