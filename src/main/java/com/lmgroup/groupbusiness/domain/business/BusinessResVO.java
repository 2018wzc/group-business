package com.lmgroup.groupbusiness.domain.business;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 集团商城子业务菜单类
 *
 * @author wangzichun
 */
@Data
public class BusinessResVO {

    private int id;

    private String name;//集团子业务名称

    private int pid;//父业务id

    private int state;//状态,1启用,0禁用

    private Timestamp creatTime;//创建时间

    private String creator;//创建人

    private int type;//1,功能按钮(默认),0层级菜单

    private String url;

}
