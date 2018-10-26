package com.lmgroup.groupbusiness.security;

import java.lang.annotation.*;

/**
 * @author wangzichun
 * @description 与拦截器结合使用 验证权限
 * @date 2018/10/18
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequiredPermission {
    String value();
}
