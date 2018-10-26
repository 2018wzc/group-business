package com.lmgroup.groupbusiness.dao;

import org.apache.ibatis.annotations.Mapper;

import com.lmgroup.groupbusiness.domain.Hello;


@Mapper
public interface HelloDao {
    Hello selectHello(int id);
}
