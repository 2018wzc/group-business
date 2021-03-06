package com.lmgroup.groupbusiness.controller.business;

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

import com.lmgroup.groupbusiness.domain.business.BusinessDesVO;
import com.lmgroup.groupbusiness.domain.user.LoginUserVO;
import com.lmgroup.groupbusiness.security.PermissionConstants;
import com.lmgroup.groupbusiness.security.RequiredPermission;
import com.lmgroup.groupbusiness.service.BusinessDesService;
import com.lmgroup.groupbusiness.service.LoginUserService;
import com.lmgroup.groupbusiness.utils.ParamException;
import com.lmgroup.groupbusiness.utils.commonAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.lmgroup.groupbusiness.utils.FormatDate.DateFormatStamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/businessDes")
@Api(tags = "集团商城业务")
public class BusinessDesAction extends commonAction {

    private static Logger logger = Logger.getLogger(BusinessDesAction.class);
    @Autowired
    private BusinessDesService businessDesService;
    @Autowired
    private LoginUserService loginUserService;

    /**
     * 查询集团商城业务列表
     *
     * @param resp
     * @param req
     * @throws Exception
     */
    @ApiOperation(value = "集团商城业务列表-分页查询")
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
        List<BusinessDesVO> list = businessDesService.list(pageSize, currentPage);
        int count = businessDesService.selectCount(0);
        ResponseResult rs = new ResponseResult();
        double f1 = new BigDecimal((float)count/pageSize).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        rs.setPageCount((int)Math.ceil(f1));
        rs.setData(list);
        rs.setCount(count);
        rs.setPageSize(pageSize);
        rs.setCurrentPage(currentPage);
        sendPageResult(resp, rs);
    }

    /**
     * 新增集团商城业务
     *
     * @param
     * @return
     */
    @ApiOperation(value = "集团商城业务新增(swargger不支持多文件数据类型,导致该接口此处无法测试,请在postman测试该接口)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "图片名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pid", value = "子业务菜单id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "describ", value = "描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "内容", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "业务名称", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "state", value = "状态,1启用,2未启用(默认)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户Id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),

    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void add(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int pid = Integer.parseInt(req.getParameter("pid"));
        String describ = req.getParameter("describ");
        String content = req.getParameter("content");
        String title = req.getParameter("title");
        String image = req.getParameter("image");
        int state = Integer.parseInt(req.getParameter("state"));
        int adminId = Integer.parseInt(req.getParameter("adminId"));
        boolean flag = pid < 1 || state < 1 || adminId < 1 || StringUtils.isBlank(title);
        if (flag) {
            throw new ParamException("参数错误");
        }
        BusinessDesVO businessDesVO = new BusinessDesVO();
        if (StringUtils.isNotBlank(describ)) {
            businessDesVO.setDescrib(describ);
        }
        if (StringUtils.isNotBlank(content)) {
            businessDesVO.setContent(content);
        }
        if (StringUtils.isNotBlank(image)) {
            businessDesVO.setImage(image);
        }
        LoginUserVO userVO = loginUserService.selectById(adminId);
        if (userVO == null) {
            throw new ParamException("操作用户不存在");
        }
        businessDesVO.setPid(pid);
        businessDesVO.setState(state);
        businessDesVO.setTitle(title);
        businessDesVO.setCreator(userVO.getUserAccount());
        businessDesVO.setCreatTime(DateFormatStamp(new Date()));

        businessDesService.add(businessDesVO);
        sendResult(resp, null);

    }


    /**
     * 详情集团商城业务
     *
     * @param
     * @return
     */
    @ApiOperation(value = "集团商城业务详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "业务Id", dataType = "int", paramType = "query", required = true),
    })
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    //@RequiredPermission(PermissionConstants.NOLOGIN)
    public void data(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        if (id < 1) {
            throw new ParamException("参数错误");
        }
        BusinessDesVO businessDesVO = businessDesService.data(id);
        sendResult(resp, businessDesVO);
    }

    /**
     * 修改集团商城业务
     *
     * @param
     * @return
     */
    @ApiOperation(value = "集团商城业务修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "业务Id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "state", value = "状态,1启用,2未启用(默认)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "image", value = "图片名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pid", value = "子业务菜单id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "describ", value = "描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "内容", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "业务名称", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void update(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        int state = Integer.parseInt(req.getParameter("state"));
        int pid = Integer.parseInt(req.getParameter("pid"));
        String title = req.getParameter("title");
        String image = req.getParameter("image");
        boolean flag = id < 1 || pid < 1 || state < 1 || StringUtils.isBlank(title);
        if (flag) {
            throw new ParamException("参数错误");
        }
        String describ = req.getParameter("describ");
        String content = req.getParameter("content");
        BusinessDesVO bussinessVO = new BusinessDesVO();
        if (StringUtils.isNotBlank(describ)) {
            bussinessVO.setDescrib(describ);
        }
        if (StringUtils.isNotBlank(content)) {
            bussinessVO.setContent(content);
        }
        if (StringUtils.isNotBlank(image)) {
            bussinessVO.setImage(image);
        }
        bussinessVO.setId(id);
        bussinessVO.setState(state);
        bussinessVO.setPid(pid);
        bussinessVO.setTitle(title);
        businessDesService.update(bussinessVO);
        sendResult(resp, null);
    }

    /**
     * 根据业务类型查询业务列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "根据业务类型查询集团商城业务-分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "业务类型Id(对应子业务菜单Id)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "currentPage", value = "当前第几页", dataType = "int", paramType = "query", required = true),
    })
    @RequestMapping(value = "/listByType", method = RequestMethod.POST)
    public void listByType(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int typeId = Integer.parseInt(req.getParameter("typeId"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        List<BusinessDesVO> list = businessDesService.queryByType(pageSize, currentPage, typeId);
        int count = businessDesService.selectCount(typeId);
        ResponseResult rs = new ResponseResult();
        double f1 = new BigDecimal((float)count/pageSize).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        rs.setPageCount((int)Math.ceil(f1));
        rs.setData(list);
        rs.setCount(count);
        sendPageResult(resp, rs);
    }

    /**
     * 根据子业务id查询该业务下所有业务列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "根据子业务菜单id查询集团商城业务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "子业务菜单Id", dataType = "int", paramType = "query", required = true),
    })
    @RequestMapping(value = "/queryByPid", method = RequestMethod.POST)
    public void queryByPid(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int pid = Integer.parseInt(req.getParameter("pid"));
        if (pid < 1) {
            throw new ParamException("参数错误");
        }
        List<BusinessDesVO> list = businessDesService.queryByPid(pid);
        ResponseResult rs = new ResponseResult();
        rs.setData(list);
        rs.setPageCount(list.size());
        sendPageResult(resp, rs);
    }

    @ApiOperation(value = "删除集团业务(只有被禁用的业务才可被删除)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void delete(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        if (id < 1) {
            throw new ParamException("参数错误");
        }
        businessDesService.delete(id);
        sendResult(resp, null);
    }

}