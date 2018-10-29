package com.lmgroup.groupbusiness.domain.business;

import java.sql.Timestamp;

/**
 * 集团商城业务菜单类
 *
 * @author wangzichun
 */
public class BussinessVO {

    private int id;

    private String name;//业务名称

    private String englisName;//英文名称

    private Timestamp creatTime;//创建时间

    private String creator;//创建人

    private int state;//状态,1启用,0禁用(默认)

    private int reorder;//菜单显示优先级

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

    public String getEnglisName() {
        return englisName;
    }

    public void setEnglisName(String englisName) {
        this.englisName = englisName;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getReorder() {
        return reorder;
    }

    public void setReorder(int reorder) {
        this.reorder = reorder;
    }
}
