package com.lmgroup.groupbusiness.domain.user;

/**
 * 登陆系统公共参数
 */
public class SysLoginVO {
    private int adminId;//登陆账号id,用户唯一标识
    private String tokenId;//登录token,30分钟失效

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
