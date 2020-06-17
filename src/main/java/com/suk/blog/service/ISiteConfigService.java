package com.suk.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.SiteConfig;

import java.util.Map;

/**
 * 系统配置 服务层
 *
 * @author suk
 * @date 2019-09-28
 */
public interface ISiteConfigService extends IService<SiteConfig> {

    Map<String, String> selectAllToMap();

}
