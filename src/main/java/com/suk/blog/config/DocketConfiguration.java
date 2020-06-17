package com.suk.blog.config;


import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author suk
 * @version V1.0
 * @title: DocketConfiguration
 * @package: com.suk.sys.config
 * @description: swagger2配置
 * @date 2019/7/3 16:47
 */
@Configuration
@EnableSwagger2
public class DocketConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public Docket startApiDocket() {
        if (Lists.newArrayList(env.getActiveProfiles()).contains("prod")) {
            return new Docket(DocumentationType.SWAGGER_2).groupName("test").apiInfo(apiInfo()).select().paths(PathSelectors.none()).build();
        } else {
            return new Docket(DocumentationType.SWAGGER_2).groupName("test").apiInfo(apiInfo()).select().build();
        }
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("SpringBoot Shiro-API").description("请求接口需要对参数进行签名以确定安全，具体签名算法见外部文档").termsOfServiceUrl("http://www.test.com")
                .version("2.0").build();
    }

}
