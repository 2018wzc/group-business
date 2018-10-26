package com.lmgroup.groupbusiness;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {
	
	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.lmgroup.groupbusiness.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("集团官网-后台接口")
                .description("更多信息请访问：http://www.lmchaoshi.com")
                .termsOfServiceUrl("http://www.lmchaoshi.com")
                .contact("vpclub")
                .version("1.0")
                .build();
    }


}
