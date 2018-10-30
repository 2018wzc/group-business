package com.lmgroup.groupbusiness.dao.businessDao;

import org.apache.ibatis.annotations.Mapper;

import com.lmgroup.groupbusiness.domain.business.BusinessDesVO;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BusinessDesDao {

    List<BusinessDesVO> listInfo(HashMap hashMap);

    void addBusiness(BusinessDesVO businessDes);

    BusinessDesVO dataInfo(int id);

    void updateInfo(BusinessDesVO businessDes);

    List<BusinessDesVO> queryByPid(int pid);

    List<BusinessDesVO> queryByType(HashMap hashMap);
}
