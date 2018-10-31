package com.lmgroup.groupbusiness.dao.businessDao;

import org.apache.ibatis.annotations.Mapper;

import com.lmgroup.groupbusiness.domain.business.BusinessResVO;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BusinessResDao {

    List<BusinessResVO> listInfo(HashMap hashMap);

    void addBusiness(BusinessResVO bussiness);

    BusinessResVO dataInfo(int id);

    void updateInfo(BusinessResVO bussinessVO);

    List<BusinessResVO> queryByPid(int pid);

    int selectCount(HashMap hashMap);
}
