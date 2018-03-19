package com.herman.gameserver.entity.mobile;

import java.io.Serializable;


/**
 * 
 * 用户游戏表
 * 
 */
public class MobileGame implements Serializable {

	private static final long serialVersionUID = 1L;


	/**
	 * 物理ID
	 */
	private Long id;

	/**
	 * 游戏id
	 */
	private Long gameId;

	/**
	 * 本平台生成的设备唯一标识
	 */
	private String dgUdid;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 创建时间
	 */
	private java.util.Date createtime;

	/**
	 * 更新时间
	 */
	private java.util.Date updatetime;

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

	public void setDgUdid(String dgUdid){
		this.dgUdid = dgUdid;
	}

	public String getDgUdid(){
		return this.dgUdid;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return this.version;
	}

	public void setCreatetime(java.util.Date createtime){
		this.createtime = createtime;
	}

	public java.util.Date getCreatetime(){
		return this.createtime;
	}

	public void setUpdatetime(java.util.Date updatetime){
		this.updatetime = updatetime;
	}

	public java.util.Date getUpdatetime(){
		return this.updatetime;
	}

	public void setIsDeleted(Long isDeleted){
		this.isDeleted = isDeleted;
	}

	public Long getIsDeleted(){
		return this.isDeleted;
	}

	public String toString (){
		return "物理ID:"+(id == null ? "空" : id)+"，游戏id:"+(gameId == null ? "空" : gameId)+"，本平台生成的设备唯一标识:"+(dgUdid == null ? "空" : dgUdid)+"，版本号:"+(version == null ? "空" : version)+"，创建时间:"+(createtime == null ? "空" : createtime)+"，更新时间:"+(updatetime == null ? "空" : updatetime)+"，是否删除（0：否，1：是）:"+(isDeleted == null ? "空" : isDeleted);
	}
}
