package com.lmgroup.groupbusiness.controller.business;

import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@Api(tags = "集团商城子业务菜单")
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
    @ApiOperation(value = "集团商城子业务菜单查询-分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "currentPage", value = "当前第几页", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void list(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        List<BusinessResVO> list = businessResService.list(pageSize, currentPage);
        int count = businessResService.selectCount(0);
        sendPageResult(resp, list, count);
    }

    /**
     * 新增集团商城子业务菜单
     *
     * @param
     * @return
     */
    @ApiOperation(value = "集团商城子业务菜单新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "集团子业务菜单名称", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pid", value = "父业务菜单id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "state", value = "状态,1启用,2未启用(默认)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户Id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
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
    @ApiOperation(value = "集团商城子业务菜单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "子业务菜单id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/data", method = RequestMethod.POST)
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
    @ApiOperation(value = "集团商城子业务菜单修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "子业务菜单Id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "state", value = "状态,1启用,2未启用(默认)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
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
    @ApiOperation(value = "根据父业务菜单id查询子业务菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "父业务菜单Id", dataType = "int", paramType = "query", required = true),
    })
    @RequestMapping(value = "/listByPid", method = RequestMethod.POST)
    public void listByPid(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int pid = Integer.parseInt(req.getParameter("pid"));
        if (pid < 1) {
            throw new ParamException("参数错误");
        }
        List<BusinessResVO> list = businessResService.queryByPid(pid);
     //   int count = businessResService.selectCount(pid);
        int count=list.size();
        sendPageResult(resp, list, count);
    }

}
