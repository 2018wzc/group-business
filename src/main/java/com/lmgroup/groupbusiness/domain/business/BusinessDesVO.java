package com.lmgroup.groupbusiness.domain.business;


import lombok.Data;

import java.sql.Timestamp;

/**
 * 集团商城业务类
 *
 * @author wangzichun
 */
@Data
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

}
