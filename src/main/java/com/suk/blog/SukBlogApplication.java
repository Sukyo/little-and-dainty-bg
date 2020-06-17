package com.suk.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author suk
 * @version V1.0
 * @title: SukSpiderApplication
 * @package: com.suk.blog
 * @description: suk 博客系统
 * @date 2019/9/27 16:32
 */
@EnableTransactionManagement
@MapperScan("com.suk.blog.mapper")
@SpringBootApplication
public class SukBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SukBlogApplication.class, args);
    }

}
