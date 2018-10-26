package com.lmgroup.groupbusiness.domain.business;


import java.util.Date;
import java.util.List;

public class BusinessListVO {
    private int id;
    private String name;//业务名称
    private String englisName;//英文名称
    private List<BusinessResVO> businessResVO;

    public List<BusinessResVO> getBusinessResVO() {
        return businessResVO;
    }

    public void setBusinessResVO(List<BusinessResVO> businessResVO) {
        this.businessResVO = businessResVO;
    }

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

}
