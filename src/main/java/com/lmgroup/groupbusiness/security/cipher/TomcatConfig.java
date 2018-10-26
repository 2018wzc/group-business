package com.lmgroup.groupbusiness.security.cipher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.MultipartConfigElement;

@Configuration
public class TomcatConfig {
    @Value("${spring.http.multipart.max-file-size}")
    private String MaxFileSize;
    @Value("${spring.http.multipart.max-request-size}")
    private String MaxRequestSize;

    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}
