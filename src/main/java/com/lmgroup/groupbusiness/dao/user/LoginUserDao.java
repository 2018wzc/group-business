package com.lmgroup.groupbusiness.dao.user;

import org.apache.ibatis.annotations.Mapper;

import com.lmgroup.groupbusiness.domain.user.LoginUserVO;

import java.util.Date;

@Mapper
public interface LoginUserDao {
    int selectUserid(String userAccount);

    LoginUserVO selectinfo(String userAccount);

    int insertinfo(LoginUserVO userVO);

    LoginUserVO selectById(int adminId);

    void deleteUser(int adminId);
}
