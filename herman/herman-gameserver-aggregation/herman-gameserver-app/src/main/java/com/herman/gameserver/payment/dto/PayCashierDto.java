package com.herman.gameserver.payment.dto;

import java.io.Serializable;


/**
 * 
 * 游戏表
 * 
 */
public class PayCashierDto implements Serializable {

	private static final long serialVersionUID = 1L;


	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 用户代码
	 */
	private String dgUdid;

	/**
	 * 游戏代码
	 */
	private String gameId;

	/**
	 * 支付记录代码
	 */
	private String payCode;

	/**
	 * 商户号
	 */
	private String partnerCode;

	/**
	 * 商品名
	 */
	private String productName;

	/**
	 * 商品订单id
	 */
	private String orderId;

	/**
	 * 用户邮箱
	 */
	private String userEmail;

	/**
	 * 币种符号
	 */
	private String currencyCode;
	/**
	 *
	 */
	private  String settleCurrency;

	/**
	 * 支付金额
	 */
	private Float settleAmount;

	/**
	 * 交易流水号
	 */
	private String transactionId;

	/**
	 * 用户支付地址
	 */
	private String payUrl;

	/**
	 * 支付状态
	 */
	private String payStatus;

	/**
	 * 用户支付时间
	 */
	private java.util.Date payTime;

	/**
	 * 创建时间
	 */
	private java.util.Date gmtCreate;

	/**
	 * 更新时间
	 */
	private java.util.Date gmtModified;

	/**
	 * 是否删除（0：否，1：是）
	 */
	private Long isDeleted;

	/**
	 *
	 */
	private String sign;


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setDgUdid(String dgUdid){
		this.dgUdid = dgUdid;
	}

	public String getDgUdid(){
		return this.dgUdid;
	}

	public void setGameId(String gameId){
		this.gameId = gameId;
	}

	public String getGameId(){
		return this.gameId;
	}

	public void setPayCode(String payCode){
		this.payCode = payCode;
	}

	public String getPayCode(){
		return this.payCode;
	}

	public void setPartnerCode(String partnerCode){
		this.partnerCode = partnerCode;
	}

	public String getPartnerCode(){
		return this.partnerCode;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return this.productName;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return this.orderId;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return this.userEmail;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return this.currencyCode;
	}

	public void setSettleAmount(Float settleAmount){
		this.settleAmount = settleAmount;
	}

	public Float getSettleAmount(){
		return this.settleAmount;
	}

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return this.transactionId;
	}

	public void setPayUrl(String payUrl){
		this.payUrl = payUrl;
	}

	public String getPayUrl(){
		return this.payUrl;
	}

	public void setPayStatus(String payStatus){
		this.payStatus = payStatus;
	}

	public String getPayStatus(){
		return this.payStatus;
	}

	public void setPayTime(java.util.Date payTime){
		this.payTime = payTime;
	}

	public java.util.Date getPayTime(){
		return this.payTime;
	}

	public void setGmtCreate(java.util.Date gmtCreate){
		this.gmtCreate = gmtCreate;
	}

	public java.util.Date getGmtCreate(){
		return this.gmtCreate;
	}

	public void setGmtModified(java.util.Date gmtModified){
		this.gmtModified = gmtModified;
	}

	public java.util.Date getGmtModified(){
		return this.gmtModified;
	}

	public void setIsDeleted(Long isDeleted){
		this.isDeleted = isDeleted;
	}

	public Long getIsDeleted(){
		return this.isDeleted;
	}

	public String toString (){
		return "主键:"+(id == null ? "空" : id)+"，用户代码:"+(dgUdid == null ? "空" : dgUdid)+"，游戏代码:"+(gameId == null ? "空" : gameId)+"，支付记录代码:"+(payCode == null ? "空" : payCode)+"，商户号:"+(partnerCode == null ? "空" : partnerCode)+"，商品名:"+(productName == null ? "空" : productName)+"，商品订单id:"+(orderId == null ? "空" : orderId)+"，用户邮箱:"+(userEmail == null ? "空" : userEmail)+"，币种符号:"+(currencyCode == null ? "空" : currencyCode)+"，支付金额:"+(settleAmount == null ? "空" : settleAmount)+"，交易流水号:"+(transactionId == null ? "空" : transactionId)+"，用户支付地址:"+(payUrl == null ? "空" : payUrl)+"，支付状态:"+(payStatus == null ? "空" : payStatus)+"，用户支付时间:"+(payTime == null ? "空" : payTime)+"，创建时间:"+(gmtCreate == null ? "空" : gmtCreate)+"，更新时间:"+(gmtModified == null ? "空" : gmtModified)+"，是否删除（0：否，1：是）:"+(isDeleted == null ? "空" : isDeleted);
	}

	public String getSettleCurrency() {
		return settleCurrency;
	}

	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
