package com.lmgroup.groupbusiness.controller.business;

import com.lmgroup.groupbusiness.domain.business.BusinessImgVO;
import com.lmgroup.groupbusiness.security.PermissionConstants;
import com.lmgroup.groupbusiness.security.RequiredPermission;
import com.lmgroup.groupbusiness.service.BusinessImgService;
import com.lmgroup.groupbusiness.utils.ParamException;
import com.lmgroup.groupbusiness.utils.commonAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.lmgroup.groupbusiness.security.cipher.UpLoadImg.upLoadFile;

@RestController
@RequestMapping("/businessImg")
@Api(tags = "首页轮播图片")
public class BusinessImgAction extends commonAction {
    private static Logger logger = Logger.getLogger(BusinessAction.class);

    @Autowired
    private BusinessImgService businessImgService;

    @ApiOperation(value = "首页轮播图片查询")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void list(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        List<BusinessImgVO> list = businessImgService.list();
        sendResult(resp, list);
    }

    @ApiOperation(value = "首页轮播图片查询-分页查询")
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
        List<BusinessImgVO> list = businessImgService.listInfo(pageSize, currentPage);
        int count = businessImgService.queryCount();
        sendPageResult(resp, list, count);
    }


    @ApiOperation(value = "首页轮播图片详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void date(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        if (id < 1) {
            throw new ParamException("参数错误");
        }
        BusinessImgVO businessImgVO = businessImgService.date(id);
        sendResult(resp, businessImgVO);
    }

    @ApiOperation(value = "首页轮播图片新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "图片标题", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "image", value = "单个图片文件", dataType = "files", paramType = "multipart/form-data", required = true),
            @ApiImplicitParam(name = "state", value = "状态,1启用,2未启用(默认)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户Id", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void add(HttpServletResponse resp, HttpServletRequest req, @RequestParam("image") MultipartFile file) throws Exception {
        String title = req.getParameter("title");
        int state = Integer.parseInt(req.getParameter("state"));
        if (state < 1) {
            throw new ParamException("参数错误");
        }
        BusinessImgVO businessImgVO = new BusinessImgVO();
        if (!file.isEmpty()) {
            String image = upLoadFile(file);
            businessImgVO.setImage(image);
        }
        businessImgVO.setTitle(title);
        businessImgVO.setState(state);
        businessImgService.add(businessImgVO);
        sendResult(resp, null);
    }

    @ApiOperation(value = "更新状态是否启用首页轮播图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "state", value = "状态,1启用,2未启用(默认)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户id", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void update(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        int state = Integer.parseInt(req.getParameter("state"));
        businessImgService.update(id, state);
        sendResult(resp, null);
    }

    @ApiOperation(value = "首页轮播图片删除")
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
        businessImgService.delete(id);
        sendResult(resp, null);
    }
}
