package com.lmgroup.groupbusiness.domain.business;

import java.sql.Timestamp;

/**
 * 集团商城子业务菜单类
 * @author wangzichun
 */
public class BusinessResVO {
    private int id;
    private String name;//集团子业务名称
    private int pid;//父业务id
    private int state;//状态,1启用,0禁用
    private Timestamp creatTime;//创建时间
    private String creator;//创建人

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
