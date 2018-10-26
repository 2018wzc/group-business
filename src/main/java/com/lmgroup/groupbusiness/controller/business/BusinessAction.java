package com.lmgroup.groupbusiness.controller.business;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmgroup.groupbusiness.domain.business.BusinessListVO;
import com.lmgroup.groupbusiness.domain.business.BussinessVO;
import com.lmgroup.groupbusiness.security.PermissionConstants;
import com.lmgroup.groupbusiness.security.RequiredPermission;
import com.lmgroup.groupbusiness.service.BussinessService;
import com.lmgroup.groupbusiness.utils.ParamException;
import com.lmgroup.groupbusiness.utils.commonAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessAction extends commonAction {
    private static Logger logger = Logger.getLogger(BusinessAction.class);

    @Autowired
    private BussinessService bussinessService;


    /**
     * 查询集团商城父业务菜单
     *
     * @param resp
     * @param req
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public void list(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        List<BusinessListVO> list = bussinessService.list();
        int count = list.size();
        sendPageResult(resp, list, count);
    }

    /**
     * 查询集团商城父业务菜单-后台分页查询
     *
     * @param resp
     * @param req
     * @throws Exception
     */
    @RequestMapping(value = "/listInfo")
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void listInfo(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        List<BussinessVO> list = bussinessService.listInfo(pageSize, currentPage);
        int count = list.size();
        sendPageResult(resp, list, count);
    }

    /**
     * 新增集团商城父业务菜单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/add")
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void add(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        String name = req.getParameter("name");
        String englishName = req.getParameter("englishName");
        int state = Integer.parseInt(req.getParameter("state"));
        int adminId = Integer.parseInt(req.getParameter("adminId"));
        bussinessService.add(name, englishName, state, adminId);
        sendResult(resp, null);
    }


    /**
     * 详情集团商城父业务菜单
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
        BussinessVO bussinessVO = bussinessService.data(id);
        sendResult(resp, bussinessVO);
    }

    /**
     * 修改集团商城父业务菜单
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
        BussinessVO bussinessVO = new BussinessVO();
        bussinessVO.setId(id);
        bussinessVO.setState(state);
        bussinessService.update(bussinessVO);
        sendResult(resp, null);
    }


}
