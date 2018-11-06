package com.lmgroup.groupbusiness.controller.business;

import com.lmgroup.groupbusiness.domain.user.LoginUserVO;
import com.lmgroup.groupbusiness.service.LoginUserService;
import com.lmgroup.groupbusiness.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Date;
import java.util.List;

import static com.lmgroup.groupbusiness.utils.FormatDate.DateFormatStamp;

@RestController
@RequestMapping("/business")
@Api(tags = "集团商城父业务菜单")
public class BusinessAction extends commonAction {
    private static Logger logger = Logger.getLogger(BusinessAction.class);

    @Autowired
    private BussinessService bussinessService;
    @Autowired
    private LoginUserService userService;

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
        ResponseResult rs = new ResponseResult();
        rs.setCount(count);
        rs.setCurrentPage(list.size());
        rs.setData(list);
        sendPageResult(resp, rs);
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
        int count = bussinessService.selectCount();
        ResponseResult rs = new ResponseResult();
        rs.setPageCount(list.size());
        rs.setCount(count);
        rs.setData(list);
        rs.setPageSize(pageSize);
        rs.setCurrentPage(currentPage);
        sendPageResult(resp, rs);
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
            @ApiImplicitParam(name = "englisName", value = "父业务菜单英文名称(大写)", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "state", value = "状态,1启用,2未启用(默认)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "reorder", value = "优先级", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "type", value = "1,功能按钮,0层级菜单(默认)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "path", value = "页面路径指向('/'+小写英文名称+'/')", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "url", value = "管理系统路径", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户Id", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void add(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        String name = req.getParameter("name");
        String englishName = req.getParameter("englisName");
        int state = Integer.parseInt(req.getParameter("state"));
        int type = Integer.parseInt(req.getParameter("type"));
        int reorder = Integer.parseInt(req.getParameter("reorder"));
        int adminId = Integer.parseInt(req.getParameter("adminId"));
        String path = req.getParameter("path");
        String url = req.getParameter("url");
        if (StringUtils.isBlank(name) || StringUtils.isBlank(englishName) || StringUtils.isBlank(path)|| StringUtils.isBlank(url)) {
            throw new ParamException("参数错误");
        }
        if (state < 1 || adminId < 1 || reorder < 1) {
            throw new ParamException("参数错误");
        }
        LoginUserVO userVO = userService.selectById(adminId);
        if (userVO == null) {
            throw new ParamException("操作人账号不存在");
        }
        BussinessVO bussiness = new BussinessVO();
        bussiness.setType(type);
        bussiness.setName(name);
        bussiness.setEnglisName(englishName);
        bussiness.setPath(path);
        bussiness.setUrl(url);
        bussiness.setReorder(reorder);
        bussiness.setCreator(userVO.getUserAccount());
        bussiness.setCreatTime(DateFormatStamp(new Date()));
        bussiness.setState(state);
        bussinessService.add(bussiness);
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
            @ApiImplicitParam(name = "name", value = "父业务菜单名称", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "englisName", value = "父业务菜单英文名称(大写)", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "reorder", value = "优先级", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "type", value = "1,功能按钮,0层级菜单(默认)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "path", value = "页面路径指向('/'+小写英文名称+'/')", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "url", value = "管理系统路径", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void update(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        int state = Integer.parseInt(req.getParameter("state"));
        String name = req.getParameter("name");
        String englishName = req.getParameter("englisName");
        int reorder = Integer.parseInt(req.getParameter("reorder"));
        int type = Integer.parseInt(req.getParameter("type"));
        String path = req.getParameter("path");
        String url = req.getParameter("url");
        if (id < 1 || state < 1) {
            throw new ParamException("参数错误");
        }
        BussinessVO bussinessVO = new BussinessVO();
        bussinessVO.setReorder(reorder);
        bussinessVO.setName(name);
        bussinessVO.setEnglisName(englishName);
        bussinessVO.setPath(path);
        bussinessVO.setUrl(url);
        bussinessVO.setType(type);
        bussinessVO.setId(id);
        bussinessVO.setState(state);
        bussinessService.update(bussinessVO);
        sendResult(resp, null);
    }


}
