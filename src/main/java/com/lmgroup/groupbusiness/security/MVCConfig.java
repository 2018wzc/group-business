package com.lmgroup.groupbusiness.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {
    @Bean
    public SecurityInterceptor securityInterceptor(){
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry reqistry){
        reqistry.addInterceptor(securityInterceptor()).excludePathPatterns("/static/*").excludePathPatterns("/error").addPathPatterns("/**");
    }
}
