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

import static com.lmgroup.groupbusiness.security.cipher.UpLoadImg.delImage;

@Service
public class BusinessDesServiceImpl implements BusinessDesService {

    @Resource
    private BusinessDesDao businessDesDao;
    @Resource
    private LoginUserDao loginUserDao;

    @Override
    public List<BusinessDesVO> list(int pageSize, int currentPage) throws Exception {
        if (pageSize < 0) {
            pageSize = 10;
        }
        if (currentPage < 0) {
            throw new ParamException("参数错误");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("pageSize", pageSize);
        hashMap.put("currentPage", currentPage);
        return businessDesDao.listInfo(hashMap);
    }

    @Override
    public void add(BusinessDesVO businessDes) throws Exception {
        businessDesDao.addBusiness(businessDes);
    }

    @Override
    public BusinessDesVO data(int id) throws Exception {
        return businessDesDao.dataInfo(id);
    }

    @Override
    public void update(BusinessDesVO businessDes) throws Exception {
        businessDesDao.updateInfo(businessDes);
    }

    @Override
    public List<BusinessDesVO> queryByPid(int pid) throws Exception {
        return businessDesDao.queryByPid(pid);
    }

    @Override
    public List<BusinessDesVO> queryByType(int pageSize, int currentPage, int typeId) throws Exception {
        if (typeId < 1) {
            throw new ParamException("参数错误");
        }
        if (pageSize < 0) {
            pageSize = 10;
        }
        if (currentPage < 0) {
            throw new ParamException("参数错误");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("pageSize", pageSize);
        hashMap.put("currentPage", currentPage);
        hashMap.put("typeId", typeId);
        return businessDesDao.queryByType(hashMap);
    }

    @Override
    public int selectCount(int typeId) throws Exception {
        HashMap hashMap = new HashMap();
        if (typeId > 0) {
            hashMap.put("typeId", typeId);
        }
        return businessDesDao.selectCount(hashMap);
    }

    @Override
    public void delete(int id) throws Exception {
        BusinessDesVO businessDesVO = businessDesDao.dataInfo(id);
        String imgName = businessDesVO.getImage();
        int state = businessDesVO.getState();
        if (state != 2) {
            throw new ParamException("该图片非禁用状态不能删除");
        }
        if (StringUtils.isNotBlank(imgName)) {
            String name = imgName.substring(imgName.lastIndexOf("/") + 1);
            delImage(name);
        }
        businessDesDao.delete(id);
    }

}
