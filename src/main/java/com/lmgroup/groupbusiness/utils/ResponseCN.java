package com.lmgroup.groupbusiness.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public class ResponseCN {

    //解决中文乱码
    public static HttpHeaders header() {
        HttpHeaders _headers = new HttpHeaders();
        MediaType _mediaType = new MediaType("text", "html", Charset.forName("GBK"));//解决中文乱码
        _headers.setContentType(_mediaType);
        return _headers;
    }
}
