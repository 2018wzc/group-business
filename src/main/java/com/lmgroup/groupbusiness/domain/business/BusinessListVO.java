package com.lmgroup.groupbusiness.domain.business;


import lombok.Data;

import java.util.List;

/**
 * 集团商城父业务菜单及子业务数据
 *
 * @author wangzichun
 */
@Data
public class BusinessListVO {

    private int id;

    private String name;//业务名称

    private String englisName;//英文名称

    private int reorder;//菜单显示优先级

    private String path;//页面路径指向

    private int type;//1,功能按钮,0层级菜单(默认)

    private String url;

    private List<BusinessResVO> businessResVO;


}
