package com.lmgroup.groupbusiness.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lmgroup.groupbusiness.dao.businessDao.BusinessResDao;
import com.lmgroup.groupbusiness.dao.businessDao.BussinessDao;
import com.lmgroup.groupbusiness.dao.user.LoginUserDao;
import com.lmgroup.groupbusiness.domain.business.BusinessListVO;
import com.lmgroup.groupbusiness.domain.business.BusinessResVO;
import com.lmgroup.groupbusiness.domain.business.BussinessVO;
import com.lmgroup.groupbusiness.domain.user.LoginUserVO;
import com.lmgroup.groupbusiness.service.BussinessService;
import com.lmgroup.groupbusiness.utils.ParamException;

import javax.annotation.Resource;

import static com.lmgroup.groupbusiness.utils.FormatDate.DateFormatStamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class BussinessServiceImpl implements BussinessService {
    @Resource
    private BussinessDao bussinessDao;
    @Resource
    private LoginUserDao loginUserDao;
    @Resource
    private BusinessResDao businessResDao;

    /* @Cacheable(value = "thisredis", key = "'yyt_'+#id")*/
    public List<BussinessVO> listInfo(int pageSize, int currentPage) throws Exception {
        if (pageSize < 0) {
            pageSize = 10;
        }
        if (currentPage < 0) {
            throw new ParamException("参数错误");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("pageSize", pageSize);
        hashMap.put("currentPage", currentPage);
        return bussinessDao.listInfo(hashMap);
    }

    public List<BusinessListVO> list() throws Exception {
        List<BusinessListVO> businessListVOS = new ArrayList<BusinessListVO>();
        List<BussinessVO> list = bussinessDao.list();
        for (int i = 0; i < list.size(); i++) {
            BusinessListVO listVO = new BusinessListVO();
            BussinessVO bussiness = list.get(i);
            int id = bussiness.getId();
            List<BusinessResVO> reslist = businessResDao.queryByPid(id);
            if (reslist != null) {
                listVO.setBusinessResVO(reslist);
            }
            listVO.setId(id);
            listVO.setName(bussiness.getName());
            listVO.setEnglisName(bussiness.getEnglisName());
            listVO.setReorder(bussiness.getReorder());
            listVO.setPath(bussiness.getPath());
            businessListVOS.add(listVO);
        }
        return businessListVOS;
    }

    public void add(BussinessVO bussiness) throws Exception {
        bussinessDao.addBusiness(bussiness);
    }

    public BussinessVO data(int id) throws Exception {
        return bussinessDao.dataInfo(id);
    }

    public void update(BussinessVO bussiness) throws Exception {
        bussinessDao.updateBusiness(bussiness);
    }

    public int selectCount() throws Exception {
        return bussinessDao.selectCount();
    }
}
