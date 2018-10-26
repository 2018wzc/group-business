package com.lmgroup.groupbusiness.controller.business;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmgroup.groupbusiness.domain.business.BusinessResVO;
import com.lmgroup.groupbusiness.security.PermissionConstants;
import com.lmgroup.groupbusiness.security.RequiredPermission;
import com.lmgroup.groupbusiness.service.BusinessResService;
import com.lmgroup.groupbusiness.utils.ParamException;
import com.lmgroup.groupbusiness.utils.commonAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/businessRes")
public class BusinessResAction extends commonAction {
    private static Logger logger = Logger.getLogger(BusinessResAction.class);

    @Autowired
    private BusinessResService businessResService;
    /**
     * 查询集团商城子业务菜单列表-后台分页查询
     *
     * @param resp
     * @param req
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void list(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        List<BusinessResVO> list = businessResService.list(pageSize, currentPage);
        int count = list.size();
        sendPageResult(resp, list, count);
    }
    /**
     * 新增集团商城子业务菜单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/add")
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void add(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        String name = req.getParameter("name");
        int pid = Integer.parseInt(req.getParameter("pid"));
        int state = Integer.parseInt(req.getParameter("state"));
        int adminId = Integer.parseInt(req.getParameter("adminId"));
        businessResService.add(name, pid, state, adminId);
        sendResult(resp, null);
    }


    /**
     * 详情集团商城子业务菜单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/data")
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void data(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        if (id < 1) {
            throw new ParamException("参数错误");
        }
        BusinessResVO bussinessVO = businessResService.data(id);
        sendResult(resp, bussinessVO);
    }
    /**
     * 修改集团商城子业务菜单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/update")
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void update(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        int state = Integer.parseInt(req.getParameter("state"));
        if (id < 1 || state < 1) {
            throw new ParamException("参数错误");
        }
        BusinessResVO bussinessVO = new BusinessResVO();
        bussinessVO.setId(id);
        bussinessVO.setState(state);
        businessResService.update(bussinessVO);
        sendResult(resp, null);
    }

    /**
     * 根据父业务菜单id查询子业务菜单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listByPid")
    public void listByPid(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int pid = Integer.parseInt(req.getParameter("pid"));
        if (pid < 1) {
            throw new ParamException("参数错误");
        }
        List<BusinessResVO> list=businessResService.queryByPid(pid);
        int count = list.size();
        sendPageResult(resp, list, count);
    }

}
