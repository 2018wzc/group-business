package com.lmgroup.groupbusiness.service.impl;

import org.springframework.stereotype.Service;

import com.lmgroup.groupbusiness.dao.user.LoginUserDao;
import com.lmgroup.groupbusiness.domain.user.LoginUserVO;
import com.lmgroup.groupbusiness.service.LoginUserService;

import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

import static com.lmgroup.groupbusiness.Redis.RedisUtil.closeJedis;
import static com.lmgroup.groupbusiness.Redis.RedisUtil.getJedis;
import static com.lmgroup.groupbusiness.utils.FormatDate.DateFormatStamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Resource
    private LoginUserDao loginUserDao;

    @Override
    public int selectUserId(String userAccount) {
        return loginUserDao.selectUserid(userAccount);
    }

    @Override
    public LoginUserVO selectinfo(String userAccount) {
        return loginUserDao.selectinfo(userAccount);
    }

    @Override
    public int insertinfo(String userAccount, String userPassword, String md5Password) throws Exception{
        LoginUserVO userVO=new LoginUserVO();
        userVO.setMdPssword(md5Password);
        userVO.setUserAccount(userAccount);
        userVO.setUserPassword(userPassword);
        userVO.setCreatTime(DateFormatStamp(new Date()));
        return loginUserDao.insertinfo(userVO);
    }

    @Override
    public LoginUserVO selectById(int adminId) {
        return loginUserDao.selectById(adminId);
    }

    @Override
    public void deleteUser(int adminId) {
        loginUserDao.deleteUser(adminId);
    }

    public Set<String> getPermission(int adminId, String tokenId) throws Exception {
        Set<String> permissionSet = new HashSet<>();
        permissionSet.add(adminId + "");
        permissionSet.add(tokenId);
        Jedis jedis = getJedis();
        if (jedis.exists(adminId + "") && tokenId.equals(jedis.get(adminId + ""))) {
            permissionSet.add("nologin");
        }
        closeJedis(jedis);
        return permissionSet;
    }
}
