package com.lmgroup.groupbusiness.service;

import java.util.List;

import com.lmgroup.groupbusiness.domain.business.BusinessResVO;

public interface BusinessResService {
    /**
     * 查询集团商城子业务菜单列表
     *
     * @return
     */
    List<BusinessResVO> list(int pageSize, int currentPage) throws Exception;

    /**
     * 新增集团商城子业务菜单
     *
     * @param
     * @return
     */
    void add(String name,int pid,int state,int adminId) throws Exception;

    /**
     * 详情集团商城子业务菜单
     *
     * @param id
     * @return
     */
    BusinessResVO data(int id) throws Exception;

    /**
     * 修改集团商城子业务菜单
     *
     * @param bussinessVO
     * @return
     */
    void update(BusinessResVO bussinessVO) throws Exception;

    /**
     * 根据父业务id查询子业务菜单
     * @param pid
     * @return
     */
    List<BusinessResVO> queryByPid(int pid) throws Exception;
}
