package com.lmgroup.groupbusiness.service;

import java.util.List;

import com.lmgroup.groupbusiness.domain.business.BusinessDesVO;

public interface BusinessDesService {
    /**
     * 查询集团商城业务列表
     *
     * @return
     */
    List<BusinessDesVO> list(int pageSize, int currentPage) throws Exception;

    /**
     * 新增集团商城业务
     *
     * @param businessDes
     * @return
     */
    void add(BusinessDesVO businessDes) throws Exception;

    /**
     * 详情集团商城业务
     *
     * @param id
     * @return
     */
    BusinessDesVO data(int id) throws Exception;

    /**
     * 修改集团商城业务
     *
     * @param businessDes
     * @return
     */
    void update(BusinessDesVO businessDes) throws Exception;

    /**
     * 根据子业务id查询该业务下所有业务列表
     *
     * @param pid
     * @return
     */
    List<BusinessDesVO> queryByPid(int pid) throws Exception;

    /**
     * 根据业务类型查询该类型下所有业务列表
     *
     * @param
     * @return
     */
    List<BusinessDesVO> queryByType(int pageSize,int currentPage,int typeId) throws Exception;
}
