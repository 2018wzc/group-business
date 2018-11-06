package com.lmgroup.groupbusiness.utils;


import com.google.gson.Gson;
import com.lmgroup.groupbusiness.service.LoginUserService;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class commonAction {
    private Logger logger = Logger.getLogger(commonAction.class.getName());
    private Gson gson = null;
    private ResponseResult rs = null;

    @Resource
    private LoginUserService loginUserService;
    /**
     * 接口响应
     *
     * @param resp
     * @param data
     * @throws IOException
     */
    protected void sendResult(HttpServletResponse resp, Object data) throws IOException {

        rs = new ResponseResult();
        String result = "";
        try {
            gson = new Gson();
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Content-type", "text/html;charset=UTF-8");
            rs.setSuccess(true);
            rs.setCode(200);
            if (data != null) {
                rs.setData(data);
            }
            logger.info(result);

        } catch (Exception e) {
            rs.setSuccess(false);
            rs.setMsg(e.getMessage());
            throw e;
        }
        result = gson.toJson(rs);
        logger.info(result);
        resp.getWriter().write(result);
    }

   /**
     * 分页
     *
     * @param resp
     * @param rspResult
     * @throws IOException
     */
    protected void sendPageResult(HttpServletResponse resp, ResponseResult rspResult) throws IOException {
        gson = new Gson();
        ResponseResult rs=rspResult;
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        rs.setCode(200);
        rs.setSuccess(true);
        String result = gson.toJson(rs);
        logger.info(result);
        resp.getWriter().write(result);
    }

    /**
     * 处理异常
     *
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler
    protected String doExp(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        gson = new Gson();
        ex.printStackTrace();
        rs = new ResponseResult();
        rs.setSuccess(false);
        if (ex instanceof ParamException) {
            rs.setMsg(ex.getMessage());
        } else {
            rs.setMsg("操作失败" + ex.getMessage());
        }
        try {
            String result = gson.toJson(rs);
            logger.error(result);
            response.getWriter().print(result);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获取用户id
     * @param request
     * @return
     */
    protected int getUserId(HttpServletRequest request)throws Exception{
        String userAccount=request.getParameter("userAccount");
        if(StringUtil.isBlank(userAccount)){
            throw new ParamException("用户名不能为空");
        }
        return loginUserService.selectUserId(userAccount);
    }
}
