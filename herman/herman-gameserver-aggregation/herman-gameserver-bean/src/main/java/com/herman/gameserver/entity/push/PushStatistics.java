package com.herman.gameserver.entity.push;

import java.io.Serializable;


/**
 * 
 * 游戏推送统计表
 * 
 */
public class PushStatistics implements Serializable {

	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	private Long id;

	/**
	 * 游戏ID
	 */
	private Long gameId;

	/**
	 * 推送的游戏ID
	 */
	private Long pushGameId;

	/**
	 * icon展示数
	 */
	private Long iconShow;

	/**
	 * icon点击数
	 */
	private Long iconClick;

	/**
	 * icon激活数
	 */
	private Long iconActive;

	/**
	 * 统计日期
	 */
	private Long statisticsDate;

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

	public void setGameId(Long gameId){
		this.gameId = gameId;
	}

	public Long getGameId(){
		return this.gameId;
	}

	public void setPushGameId(Long pushGameId){
		this.pushGameId = pushGameId;
	}

	public Long getPushGameId(){
		return this.pushGameId;
	}

	public void setIconShow(Long iconShow){
		this.iconShow = iconShow;
	}

	public Long getIconShow(){
		return this.iconShow;
	}

	public void setIconClick(Long iconClick){
		this.iconClick = iconClick;
	}

	public Long getIconClick(){
		return this.iconClick;
	}

	public void setIconActive(Long iconActive){
		this.iconActive = iconActive;
	}

	public Long getIconActive(){
		return this.iconActive;
	}

	public void setStatisticsDate(Long statisticsDate){
		this.statisticsDate = statisticsDate;
	}

	public Long getStatisticsDate(){
		return this.statisticsDate;
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
		return "id:"+(id == null ? "空" : id)+"，游戏ID:"+(gameId == null ? "空" : gameId)+"，推送的游戏ID:"+(pushGameId == null ? "空" : pushGameId)+"，icon展示数:"+(iconShow == null ? "空" : iconShow)+"，icon点击数:"+(iconClick == null ? "空" : iconClick)+"，icon激活数:"+(iconActive == null ? "空" : iconActive)+"，统计日期:"+(statisticsDate == null ? "空" : statisticsDate)+"，创建时间:"+(gmtCreate == null ? "空" : gmtCreate)+"，更新时间:"+(gmtModified == null ? "空" : gmtModified)+"，是否删除（0：否，1：是）:"+(isDeleted == null ? "空" : isDeleted);
	}
}
