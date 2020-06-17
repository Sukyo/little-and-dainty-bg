package com.suk.blog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suk
 * @version V1.0
 * @title: MybatisPlusConfig
 * @package: com.suk.sys.config
 * @description: mybatis plus 分页插件配置(切记一定要配置分页插件，不然没法分页)
 * @date 2019/7/8 17:08
 */
@Configuration
/*@MapperScan("com.suk.mapper")*/ /*  XiaofengShiroApplication在启动文件上写了，这里不加*/
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}