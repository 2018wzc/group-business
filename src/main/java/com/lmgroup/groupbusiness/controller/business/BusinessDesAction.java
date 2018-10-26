package com.lmgroup.groupbusiness.controller.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

import static com.lmgroup.groupbusiness.security.cipher.UpLoadImg.upLoadFile;
import static com.lmgroup.groupbusiness.utils.FormatDate.DateFormatStamp;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/businessDes")
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
    @RequestMapping(value = "/list")
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void list(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        List<BusinessDesVO> list = businessDesService.list(pageSize, currentPage);
        int count = list.size();
        sendPageResult(resp, list, count);
    }

    /**
     * 新增集团商城业务
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void add(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        List<MultipartFile> files =((MultipartHttpServletRequest)req).getFiles("image");
        int pid = Integer.parseInt(req.getParameter("pid"));
        String image = "";
        String describ = req.getParameter("describ");
        String content = req.getParameter("content");
        int typeId = Integer.parseInt(req.getParameter("typeId"));
        String title = req.getParameter("title");
        int state = Integer.parseInt(req.getParameter("state"));
        int adminId = Integer.parseInt(req.getParameter("adminId"));
        if (pid < 1 || typeId < 1 || state < 1 || adminId < 1) {
            throw new ParamException("参数错误");
        }
        Boolean flag = StringUtils.isBlank(describ) || StringUtils.isBlank(content) || StringUtils.isBlank(title);
        if (flag) {
            throw new ParamException("参数错误");
        }
        LoginUserVO userVO = loginUserService.selectById(adminId);
        if (userVO == null) {
            throw new ParamException("操作用户不存在");
        }
       if(files==null||files.size()<0){
            throw new ParamException("参数错误");
        }
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            image+= upLoadFile(file)+";";
        }
        BusinessDesVO businessDesVO = new BusinessDesVO();
        businessDesVO.setPid(pid);
        businessDesVO.setState(state);
        businessDesVO.setContent(content);
        businessDesVO.setImage(image);
        businessDesVO.setTitle(title);
        businessDesVO.setDescrib(describ);
        businessDesVO.setTypeId(typeId);
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
    @RequestMapping(value = "/data")
    @RequiredPermission(PermissionConstants.NOLOGIN)
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
    @RequestMapping(value = "/update")
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void update(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        int state = Integer.parseInt(req.getParameter("state"));
        if (id < 1 || state < 1) {
            throw new ParamException("参数错误");
        }
        BusinessDesVO bussinessVO = new BusinessDesVO();
        bussinessVO.setId(id);
        bussinessVO.setState(state);
        businessDesService.update(bussinessVO);
        sendResult(resp, null);
    }

    /**
     * 根据业务类型查询业务列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listByType")
    public void listByType(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int typeId = Integer.parseInt(req.getParameter("typeId"));
        if (typeId < 1) {
            throw new ParamException("参数错误");
        }
        List<BusinessDesVO> list = businessDesService.queryByPid(typeId);
        int count = list.size();
        sendPageResult(resp, list, count);
    }

    /**
     * 根据子业务id查询该业务下所有业务列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/queryByPid")
    public void queryByPid(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int pid = Integer.parseInt(req.getParameter("pid"));
        if (pid < 1) {
            throw new ParamException("参数错误");
        }
        List<BusinessDesVO> list = businessDesService.queryByPid(pid);
        int count = list.size();
        sendPageResult(resp, list, count);
    }

}