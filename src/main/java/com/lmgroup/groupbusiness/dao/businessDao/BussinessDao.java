package com.lmgroup.groupbusiness.dao.businessDao;

import org.apache.ibatis.annotations.Mapper;

import com.lmgroup.groupbusiness.domain.business.BussinessVO;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface BussinessDao {

    List<BussinessVO> listInfo(HashMap hashMap);

    List<BussinessVO> list();

    void addBusiness(BussinessVO bussiness);

    BussinessVO dataInfo(int id);

    void updateBusiness(BussinessVO bussiness);

    int selectCount();
}
