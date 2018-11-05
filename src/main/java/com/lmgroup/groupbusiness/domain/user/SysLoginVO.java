package com.lmgroup.groupbusiness.domain.user;

import lombok.Data;

/**
 * 登陆系统公共参数
 */
@Data
public class SysLoginVO {

    private int adminId;//登陆账号id,用户唯一标识

    private String tokenId;//登录token,30分钟失效

}
