package com.lmgroup.groupbusiness.service;

import java.util.List;

import com.lmgroup.groupbusiness.domain.business.BusinessListVO;
import com.lmgroup.groupbusiness.domain.business.BussinessVO;

public interface BussinessService {
    /**
     * 查询集团商城父业务菜单
     *
     * @return
     */
    List<BussinessVO> listInfo(int pageSize,int currentPage) throws Exception;

    List<BusinessListVO> list()throws Exception;
    /**
     * 新增集团商城父业务菜单
     *
     * @param bussiness
     * @return
     */
    void add(BussinessVO bussiness) throws Exception;

    /**
     * 集团商城父业务菜单详情
     *
     * @param id
     * @return
     */
    BussinessVO data(int id) throws Exception;

    /**
     * 修改集团商城父业务菜单
     *
     * @param bussiness
     * @return
     */
    void update(BussinessVO bussiness) throws Exception;

    /**
     * 查询总条数
     * @return
     * @throws Exception
     */
    public int selectCount() throws Exception;
}
