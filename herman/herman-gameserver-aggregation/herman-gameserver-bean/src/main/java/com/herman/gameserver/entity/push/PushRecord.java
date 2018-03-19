package com.herman.gameserver.entity.push;

import java.io.Serializable;


/**
 * 
 * 游戏推送记录表
 * 
 */
public class PushRecord implements Serializable {

	private static final long serialVersionUID = 1L;


	/**
	 * 物理ID
	 */
	private Long id;

	/**
	 * 用户ID
	 */
	private String dgUdid;

	/**
	 * 游戏ID
	 */
	private Long gameId;

	/**
	 * 推送的游戏ID
	 */
	private Long pushGameId;

	/**
	 * 推送游戏的版本号
	 */
	private String pushVersion;

	/**
	 * 状态（0：未激活，1：已激活，2：已领取奖励）
	 */
	private Long status;

	/**
	 * 跳转次数
	 */
	private Long jumpTimes;

	/**
	 * 跳转时间
	 */
	private java.util.Date lastJumpTime;

	/**
	 * 激活时间
	 */
	private java.util.Date activetime;

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

	public void setDgUdid(String dgUdid){
		this.dgUdid = dgUdid;
	}

	public String getDgUdid(){
		return this.dgUdid;
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

	public void setPushVersion(String pushVersion){
		this.pushVersion = pushVersion;
	}

	public String getPushVersion(){
		return this.pushVersion;
	}

	public void setStatus(Long status){
		this.status = status;
	}

	public Long getStatus(){
		return this.status;
	}

	public void setJumpTimes(Long jumpTimes){
		this.jumpTimes = jumpTimes;
	}

	public Long getJumpTimes(){
		return this.jumpTimes;
	}

	public void setLastJumpTime(java.util.Date lastJumpTime){
		this.lastJumpTime = lastJumpTime;
	}

	public java.util.Date getLastJumpTime(){
		return this.lastJumpTime;
	}

	public void setActivetime(java.util.Date activetime){
		this.activetime = activetime;
	}

	public java.util.Date getActivetime(){
		return this.activetime;
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
		return "物理ID:"+(id == null ? "空" : id)+"，用户ID:"+(dgUdid == null ? "空" : dgUdid)+"，游戏ID:"+(gameId == null ? "空" : gameId)+"，推送的游戏ID:"+(pushGameId == null ? "空" : pushGameId)+"，推送游戏的版本号:"+(pushVersion == null ? "空" : pushVersion)+"，状态（0：未激活，1：已激活，2：已领取奖励）:"+(status == null ? "空" : status)+"，跳转次数:"+(jumpTimes == null ? "空" : jumpTimes)+"，跳转时间:"+(lastJumpTime == null ? "空" : lastJumpTime)+"，激活时间:"+(activetime == null ? "空" : activetime)+"，创建时间:"+(gmtCreate == null ? "空" : gmtCreate)+"，更新时间:"+(gmtModified == null ? "空" : gmtModified)+"，是否删除（0：否，1：是）:"+(isDeleted == null ? "空" : isDeleted);
	}
}
