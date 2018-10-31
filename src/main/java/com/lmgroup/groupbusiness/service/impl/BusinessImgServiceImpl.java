package com.lmgroup.groupbusiness.service.impl;

import com.lmgroup.groupbusiness.dao.businessDao.BusinessImgDao;
import com.lmgroup.groupbusiness.domain.business.BusinessImgVO;
import com.lmgroup.groupbusiness.service.BusinessImgService;
import com.lmgroup.groupbusiness.utils.ParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

import static com.lmgroup.groupbusiness.security.cipher.UpLoadImg.delImage;

@Service
public class BusinessImgServiceImpl implements BusinessImgService {

    @Resource
    private BusinessImgDao businessImgDao;

    @Override
    public List<BusinessImgVO> list() throws Exception {
        return businessImgDao.list();
    }

    @Override
    public List<BusinessImgVO> listInfo(int pageSize, int currentPage) throws Exception {
        if (pageSize < 0) {
            pageSize = 10;
        }
        if (currentPage < 0) {
            throw new ParamException("参数错误");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("pageSize", pageSize);
        hashMap.put("currentPage", currentPage);
        return businessImgDao.listInfo(hashMap);
    }

    @Override
    public void add(BusinessImgVO businessImgVO) throws Exception {
        businessImgDao.add(businessImgVO);
    }

    @Override
    public BusinessImgVO date(int id) throws Exception {
        return businessImgDao.date(id);
    }

    @Override
    public void delete(int id) throws Exception {
        BusinessImgVO businessImgVO=businessImgDao.date(id);
        String imgName=businessImgVO.getImage();
        int state=businessImgVO.getState();
        if(state==1){
            throw new ParamException("该图片是启用状态不能删除");
        }
        if(StringUtils.isNotBlank(imgName)){
            String name=imgName.substring(imgName.lastIndexOf("/")+1);
            delImage(name);
        }
        businessImgDao.delete(id);
    }

    @Override
    public int queryCount() {
        return businessImgDao.queryCount();
    }

    @Override
    public void update(int id,int state) throws Exception{
        if (id < 1 || state < 1) {
            throw new ParamException("参数错误");
        }
        BusinessImgVO businessImgVO=new BusinessImgVO();
        businessImgVO.setId(id);
        businessImgVO.setState(state);
         businessImgDao.update(businessImgVO);
    }
}
