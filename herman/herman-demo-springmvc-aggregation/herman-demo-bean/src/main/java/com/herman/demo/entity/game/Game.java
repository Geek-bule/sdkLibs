package com.herman.demo.entity.game;

import com.herman.common.bean.CommonEntity;


/**
 * 游戏信息表
 */
public class Game extends CommonEntity {

    private static final long serialVersionUID = -178090212219953550L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 游戏代码
     */
    private String code;

    /**
     * 游戏名称
     */
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
