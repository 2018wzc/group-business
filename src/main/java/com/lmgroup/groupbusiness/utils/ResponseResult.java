package com.lmgroup.groupbusiness.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult implements Serializable {

    final static long serialVersionUID = 1l;

    private String msg;

    private int code;

    private Object data;

    private int count;//总条数

    private int pageCount;//当前页总条数

    private int pageSize;//页面大小

    private int currentPage;//当前第几页

    private boolean success;

}

