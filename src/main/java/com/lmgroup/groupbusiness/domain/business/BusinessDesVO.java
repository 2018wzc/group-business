package com.lmgroup.groupbusiness.domain.business;


import java.sql.Timestamp;

/**
 * 集团商城业务类
 *
 * @author wangzichun
 */
public class BusinessDesVO {

    private int id;

    private int pid;//子业务id

    private String image;//图片,多个图片用";"隔开

    private String describ;//描述

    private String content;//内容

    private int typeId;//业务类型id,1集团要闻,2业务动态,3媒体资讯,4其他

    private String title;//业务名称

    private Timestamp creatTime;//创建时间

    private String creator;//创建人

    private int state;//状态,1启用,0禁用


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
