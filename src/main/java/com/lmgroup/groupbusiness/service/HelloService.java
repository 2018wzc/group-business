package com.lmgroup.groupbusiness.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lmgroup.groupbusiness.dao.HelloDao;
import com.lmgroup.groupbusiness.domain.Hello;

import javax.annotation.Resource;

@Service
public class HelloService {
    @Resource
    private HelloDao helloDao;

    @Cacheable(value="thisredis", key="'yyt_'+#id")
    public Hello selectHello(int id){
        return helloDao.selectHello(id);
    }


}
