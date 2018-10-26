package com.lmgroup.groupbusiness.domain.user;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户表
 *
 * @author wangzichun 时间:2018/10/18 14:21
 */
public class LoginUserVO {
      private int adminId;//用户id
      private String userAccount;//用户名
      private String userPassword;//密码
      private String mdPssword;
      private Timestamp creatTime;
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getMdPssword() {
        return mdPssword;
    }

    public void setMdPssword(String mdPssword) {
        this.mdPssword = mdPssword;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }
}
