package com.lmgroup.groupbusiness.service;

import java.util.Date;
import java.util.Set;

import com.lmgroup.groupbusiness.domain.user.LoginUserVO;

public interface LoginUserService {
    /**
     * 根据用户名获取用户id
     *
     * @param userAccount
     * @return
     */
    int selectUserId(String userAccount);

    /**
     * 根据用户名获取用户信息
     *
     * @param userAccount
     * @return
     */
    LoginUserVO selectinfo(String userAccount);

    /**
     * 新增用户
     *
     * @param userAccount
     * @param userPassword
     * @param md5Password
     * @return
     */
    int insertinfo(String userAccount, String userPassword, String md5Password)throws Exception;

    /**
     * 根据用户id查询用户信息
     *
     * @param adminId
     * @return
     */
    LoginUserVO selectById(int adminId);

    /**
     * 根据用户id删除用户
     *
     * @param adminId
     * @return
     */
    void deleteUser(int adminId);

    /**
     * 添加拦截器验证参数
     *
     * @param adminId
     * @param tokenId
     * @return
     * @throws Exception
     */
    Set<String> getPermission(int adminId, String tokenId) throws Exception;
}
