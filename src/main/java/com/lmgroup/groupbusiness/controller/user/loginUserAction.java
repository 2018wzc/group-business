package com.lmgroup.groupbusiness.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lmgroup.groupbusiness.domain.user.LoginUserVO;
import com.lmgroup.groupbusiness.domain.user.SysLoginVO;
import com.lmgroup.groupbusiness.security.PermissionConstants;
import com.lmgroup.groupbusiness.security.RequiredPermission;
import com.lmgroup.groupbusiness.security.cipher.SHA256;
import com.lmgroup.groupbusiness.service.LoginUserService;
import com.lmgroup.groupbusiness.utils.ParamException;
import com.lmgroup.groupbusiness.utils.StringUtil;
import com.lmgroup.groupbusiness.utils.commonAction;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmgroup.groupbusiness.utils.Md5Utils;

import static com.lmgroup.groupbusiness.Redis.RedisUtil.closeJedis;
import static com.lmgroup.groupbusiness.Redis.RedisUtil.getJedis;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/login")
@Api(tags = "用户信息")
public class loginUserAction extends commonAction {
    private static Logger logger = Logger.getLogger(loginUserAction.class);

    @Autowired
    private LoginUserService loginUserService;

    @ApiOperation(value = "用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "账号", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "Password", value = "密码", dataType = "String", paramType = "query", required = true),

    })
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public void doLogin(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        String userAccount = req.getParameter("userAccount");
        String userPassword = req.getParameter("Password");
        if (StringUtil.isBlank(userAccount)) {
            throw new ParamException("用户名不能为空");
        }
        if (StringUtil.isBlank(userPassword)) {
            throw new ParamException("密码不能为空");
        }
        //获取用户密文与密码比较,不一致则登陆失败,一致则登陆成功
        LoginUserVO userVO = loginUserService.selectinfo(userAccount);
        if (userVO == null) {
            throw new ParamException("用户名或密码不对");
        }
        String md5Password = Md5Utils.stringMD5(userPassword);
        if (!md5Password.equals(userVO.getMdPssword())) {
            throw new ParamException("用户名或密码不对");
        }
        //生成tokeid的值
        UUID uuid = UUID.randomUUID();
        String tokenid = uuid.toString();
        tokenid = SHA256.encodeAsString(tokenid);
        int adminid = userVO.getAdminId();
        //存入数据到redis缓存中
        Jedis jedis = getJedis();
        //先清空缓存数据
        //  jedis.flushDB();
        // 设置key的有效期，并存储数据
        jedis.setex(adminid + "", 1800, tokenid);
        System.out.println(jedis.get(adminid + ""));
        closeJedis(jedis);
        SysLoginVO sysLogin = new SysLoginVO();
        sysLogin.setAdminId(adminid);
        sysLogin.setTokenId(tokenid);
        sendResult(resp, sysLogin);
    }

    @ApiOperation(value = "注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "账号", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "Password", value = "密码", dataType = "String", paramType = "query", required = true),

    })
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public void doRegister(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        String userAccount = req.getParameter("userAccount");
        String userPassword = req.getParameter("Password");
        if (StringUtil.isBlank(userAccount)) {
            throw new ParamException("用户名不能为空");
        }
        if (StringUtil.isBlank(userPassword)) {
            throw new ParamException("密码不能为空");
        }
        LoginUserVO loginUserVO = loginUserService.selectinfo(userAccount);
        if (loginUserVO != null) {
            throw new ParamException("该账户已经存在");
        }
        //用MD5创建密文
        String md5Password = Md5Utils.stringMD5(userPassword);
        int userVO = loginUserService.insertinfo(userAccount, userPassword, md5Password);
        if (userVO < 1) {
            throw new ParamException("保存失败");
        }
        sendResult(resp, "");
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "用户Id", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "adminId", value = "用户id", dataType = "Integer", paramType = "query", required = true),
            @ApiImplicitParam(name = "tokenId", value = "临时tokenId", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
    @RequiredPermission(PermissionConstants.NOLOGIN)
    public void doDelete(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        int adminid = Integer.parseInt(req.getParameter("adminId"));
        if (adminid < 1) {
            throw new ParamException("参数错误");
        }
        LoginUserVO loginUser = loginUserService.selectById(adminid);
        if (loginUser == null) {
            throw new ParamException("该用户不存在");
        }
        loginUserService.deleteUser(adminid);
        sendResult(resp, "");
    }
}
