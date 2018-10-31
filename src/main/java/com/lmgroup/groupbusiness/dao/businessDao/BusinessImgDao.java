package com.lmgroup.groupbusiness.dao.businessDao;

import com.lmgroup.groupbusiness.domain.business.BusinessImgVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BusinessImgDao {
    List<BusinessImgVO> list();

    List<BusinessImgVO> listInfo(HashMap hashMap);

    void add(BusinessImgVO businessImgVO);

    BusinessImgVO date(int id);

    void delete(int id);

    int queryCount();

    void update(BusinessImgVO businessImgVO);
}
