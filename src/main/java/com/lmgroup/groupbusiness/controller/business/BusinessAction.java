package com.lmgroup.groupbusiness.controller.business;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(tags = "集团商城父业务菜单")
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
    @ApiOperation(value = "集团商城父业务菜单查询")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
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
    @ApiOperation(value = "集团商城父业务菜单查询-分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "currentPage", value = "当前第几页", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/listInfo", method = RequestMethod.POST)
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
    @ApiOperation(value = "集团商城父业务菜单新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "父业务菜单名称", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "englishName", value = "父业务菜单英文名称(大写)", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "state", value = "状态,1启用,2未启用(默认)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户Id", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
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
    @ApiOperation(value = "集团商城父业务菜单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "父业务菜单Id", dataType = "int", paramType = "query", required = true),
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
        BussinessVO bussinessVO = bussinessService.data(id);
        sendResult(resp, bussinessVO);
    }

    /**
     * 修改集团商城父业务菜单
     *
     * @param
     * @return
     */
    @ApiOperation(value = "集团商城父业务菜单修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "父业务菜单Id", dataType = "int", paramType = "query", required = true),
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
        BussinessVO bussinessVO = new BussinessVO();
        bussinessVO.setId(id);
        bussinessVO.setState(state);
        bussinessService.update(bussinessVO);
        sendResult(resp, null);
    }


}
