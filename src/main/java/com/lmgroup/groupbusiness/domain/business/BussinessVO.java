package com.lmgroup.groupbusiness.domain.business;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 集团商城业务菜单类
 *
 * @author wangzichun
 */
@Data
public class BussinessVO {

    private int id;

    private String name;//业务名称

    private String englisName;//英文名称

    private Timestamp creatTime;//创建时间

    private String creator;//创建人

    private int state;//状态,1启用,0禁用(默认)

    private int reorder;//菜单显示优先级

    private String path;//页面路径指向

    private int type;//1,功能按钮,0层级菜单(默认)

    private String url;

}
