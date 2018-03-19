package com.herman.common.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库对象基类-包含每个表必备字段
 * Created by herman on 2017/12/29.
 */
public class CommonEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id; // 主键
    private Boolean isDeleted; // 是否删除（0：否，1：是）
    private Date gmtCreate; // 创建时间
    private Date gmtModified; // 更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "CommonEntity{" +
                "id=" + id +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
