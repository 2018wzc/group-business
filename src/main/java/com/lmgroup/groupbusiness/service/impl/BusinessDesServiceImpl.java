package com.lmgroup.groupbusiness.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lmgroup.groupbusiness.dao.businessDao.BusinessDesDao;
import com.lmgroup.groupbusiness.dao.user.LoginUserDao;
import com.lmgroup.groupbusiness.domain.business.BusinessDesVO;
import com.lmgroup.groupbusiness.domain.user.LoginUserVO;
import com.lmgroup.groupbusiness.service.BusinessDesService;
import com.lmgroup.groupbusiness.utils.ParamException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BusinessDesServiceImpl implements BusinessDesService {

    @Resource
    private BusinessDesDao businessDesDao;
    @Resource
    private LoginUserDao loginUserDao;

    public List<BusinessDesVO> list(int pageSize, int currentPage) throws Exception {
        if (pageSize < 0) {
            pageSize = 10;
        }
        if (currentPage < 0) {
            throw new ParamException("参数错误");
        }
        HashMap hashMap=new HashMap();
        hashMap.put("pageSize",pageSize);
        hashMap.put("currentPage",currentPage);
        return businessDesDao.listInfo(hashMap);
    }

    public void add(BusinessDesVO businessDes) throws Exception {
         businessDesDao.addBusiness(businessDes);
    }

    public BusinessDesVO data(int id) throws Exception {
        return businessDesDao.dataInfo(id);
    }


    public void update(BusinessDesVO businessDes) throws Exception {
         businessDesDao.updateInfo(businessDes);
    }


    public List<BusinessDesVO> queryByPid(int pid) throws Exception {
        return businessDesDao.queryByPid(pid);
    }

    public List<BusinessDesVO> queryByType(int typeId) throws Exception {
        return businessDesDao.queryByType(typeId);
    }

}
