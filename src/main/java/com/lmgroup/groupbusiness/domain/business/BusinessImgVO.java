package com.lmgroup.groupbusiness.domain.business;

/**
 * 首页轮播图片
 *
 * @author wangzichun 时间:2018/10/31 14:09
 */
public class BusinessImgVO {
    private int id;
    private String title;//图片标题
    private String image;//图片
    private int state;//状态,1启用,2未启用(默认)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
