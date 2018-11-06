package com.lmgroup.groupbusiness.service;

import com.lmgroup.groupbusiness.domain.business.BusinessImgVO;

import java.util.List;

public interface BusinessImgService {

    /*
      查询首页轮播图片
     */
    List<BusinessImgVO> list() throws Exception;

    /**
     * 查询首页轮播图片-分页
     *
     * @param pageSize
     * @param currentPage
     * @return
     * @throws Exception
     */
    List<BusinessImgVO> listInfo(int pageSize, int currentPage) throws Exception;

    /**
     * 新增首页轮播图片
     *
     * @param businessImgVO
     * @throws Exception
     */
    void add(BusinessImgVO businessImgVO) throws Exception;

    /*
    详情
     */
    BusinessImgVO date(int id) throws Exception;

    /*
     删除首页轮播图片
     */
    void delete(int id) throws Exception;

    /*
    查询轮播图片总数
     */
    int queryCount();

    /*
    更新是否启用状态
     */
    void update(BusinessImgVO imgVO) throws Exception;
}
