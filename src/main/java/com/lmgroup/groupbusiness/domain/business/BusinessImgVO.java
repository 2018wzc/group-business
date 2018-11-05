package com.lmgroup.groupbusiness.domain.business;

import lombok.Data;

/**
 * 首页轮播图片
 *
 * @author wangzichun 时间:2018/10/31 14:09
 */
@Data
public class BusinessImgVO {

    private int id;

    private String title;//图片标题

    private String image;//图片

    private int state;//状态,1启用,2未启用(默认)

}
