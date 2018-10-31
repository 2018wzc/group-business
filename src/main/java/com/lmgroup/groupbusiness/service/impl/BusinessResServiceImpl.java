package com.lmgroup.groupbusiness.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lmgroup.groupbusiness.dao.businessDao.BusinessResDao;
import com.lmgroup.groupbusiness.dao.user.LoginUserDao;
import com.lmgroup.groupbusiness.domain.business.BusinessResVO;
import com.lmgroup.groupbusiness.domain.user.LoginUserVO;
import com.lmgroup.groupbusiness.service.BusinessResService;
import com.lmgroup.groupbusiness.utils.ParamException;

import javax.annotation.Resource;

import static com.lmgroup.groupbusiness.utils.FormatDate.DateFormatStamp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BusinessResServiceImpl implements BusinessResService {
    @Resource
    private BusinessResDao businessResDao;
    @Resource
    private LoginUserDao loginUserDao;

    public List<BusinessResVO> list(int pageSize, int currentPage) throws Exception {
        if (pageSize < 0) {
            pageSize = 10;
        }
        if (currentPage < 0) {
            throw new ParamException("参数错误");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("pageSize", pageSize);
        hashMap.put("currentPage", currentPage);
        return businessResDao.listInfo(hashMap);
    }


    public void add(String name, int pid, int state, int adminId) throws Exception {
        if (StringUtils.isBlank(name)) {
            throw new ParamException("参数错误");
        }
        if (state < 1 || adminId < 1 || pid < 1) {
            throw new ParamException("参数错误");
        }
        BusinessResVO businessRes = new BusinessResVO();
        businessRes.setName(name);
        businessRes.setPid(pid);
        LoginUserVO userVO = loginUserDao.selectById(adminId);
        if (userVO == null) {
            throw new ParamException("操作人账号不存在");
        }
        businessRes.setCreator(userVO.getUserAccount());
        businessRes.setCreatTime(DateFormatStamp(new Date()));
        businessRes.setState(state);
        businessResDao.addBusiness(businessRes);
    }


    public BusinessResVO data(int id) throws Exception {
        return businessResDao.dataInfo(id);
    }


    public void update(BusinessResVO businessRes) throws Exception {
        businessResDao.updateInfo(businessRes);
    }


    public List<BusinessResVO> queryByPid(int pid) throws Exception {
        return businessResDao.queryByPid(pid);
    }

    public int selectCount(int pid) throws Exception {
        HashMap hashMap = new HashMap();
        if(pid>0){
            hashMap.put("pid", pid);
        }
        return businessResDao.selectCount(hashMap);
    }
}
