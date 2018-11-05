package com.lmgroup.groupbusiness.domain.user;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户表
 *
 * @author wangzichun 时间:2018/10/18 14:21
 */
@Data
public class LoginUserVO {

    private int adminId;//用户id

    private String userAccount;//用户名

    private String userPassword;//密码

    private String mdPssword;

    private Timestamp creatTime;

}
