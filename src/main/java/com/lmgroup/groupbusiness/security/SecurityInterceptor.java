package com.lmgroup.groupbusiness.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lmgroup.groupbusiness.service.LoginUserService;
import com.lmgroup.groupbusiness.utils.ParamException;
import com.lmgroup.groupbusiness.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author wangzichun
 * @description 权限拦截器
 * @date 2018/10/18
 */
public class SecurityInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginUserService loginUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证权限
        if (this.hasPermission(handler,request)) {
            return true;
        }
        //null==request.getHeader("x-requested-with")TODO 暂时用这个来判断是否为ajax请求
        //如果没有权限,则抛出403异常,springboot会处理,跳转到/error/403页面
        response.sendError(HttpStatus.FORBIDDEN.value(), "登录超时,请重新登录");
        return false;
    }

    /**
     * 是否有权限
     *
     * @param handler
     * @return
     */
    private boolean hasPermission(Object handler,HttpServletRequest request) throws Exception{
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取方法上的注解
            RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
            //如果方法上的注解为空,则获取类的注解
            if (requiredPermission == null) {
                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);

            }
            //如果标记了注解,则判断权限
            if (requiredPermission != null && StringUtils.isNotBlank(requiredPermission.value())) {
                //redis或者数据库中获取该用户的权限信息,并判断是否有权限
                int  adminId=Integer.parseInt(request.getParameter("adminId"));
                String tokenId=request.getParameter("tokenId");
                if(adminId<1){
                    throw new ParamException("用户id不能为空");
                }
                if(StringUtil.isBlank(tokenId)){
                    throw new ParamException("tokenId不能为空");
                }
                Set<String> permissionSet = loginUserService.getPermission(adminId,tokenId);
                if (CollectionUtils.isEmpty(permissionSet)) {
                    return false;
                }
                return permissionSet.contains(requiredPermission.value());
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
