package com.herman.gameserver.entity.push;

import java.io.Serializable;


/**
 * 
 * 游戏推送规则表
 * 
 */
public class PushRule implements Serializable {

	private static final long serialVersionUID = 1L;


	/**
	 * 物理ID
	 */
	private Long id;

	/**
	 * 游戏ID
	 */
	private String gameCode;

	/**
	 * 手机系统类型（android，ios）
	 */
	private String mobileType;

	/**
	 * 推送的游戏ID
	 */
	private String pushGameCode;

	/**
	 * 百分比
	 */
	private Long percent;

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


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setGameCode(String gameCode){
		this.gameCode = gameCode;
	}

	public String getGameCode(){
		return this.gameCode;
	}

	public void setMobileType(String mobileType){
		this.mobileType = mobileType;
	}

	public String getMobileType(){
		return this.mobileType;
	}

	public void setPushGameCode(String pushGameCode){
		this.pushGameCode = pushGameCode;
	}

	public String getPushGameCode(){
		return this.pushGameCode;
	}

	public void setPercent(Long percent){
		this.percent = percent;
	}

	public Long getPercent(){
		return this.percent;
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
		return "物理ID:"+(id == null ? "空" : id)+"，游戏ID:"+(gameCode == null ? "空" : gameCode)+"，手机系统类型（android，ios）:"+(mobileType == null ? "空" : mobileType)+"，推送的游戏ID:"+(pushGameCode == null ? "空" : pushGameCode)+"，百分比:"+(percent == null ? "空" : percent)+"，创建时间:"+(gmtCreate == null ? "空" : gmtCreate)+"，更新时间:"+(gmtModified == null ? "空" : gmtModified)+"，是否删除（0：否，1：是）:"+(isDeleted == null ? "空" : isDeleted);
	}
}
