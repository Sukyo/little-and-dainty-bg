package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.entity.SysConfig;
import com.suk.blog.mapper.SysConfigMapper;
import org.springframework.stereotype.Service;

/**
 * html调用 thymeleaf 实现参数管理
 */
@Service("config")
public class ConfigService extends ServiceImpl<SysConfigMapper, SysConfig> {

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数名称
     * @return 参数键值
     */
    public String getKey(String configKey) {
        SysConfig sysConfig = getOne(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getConfigKey, configKey));
        return sysConfig != null ? sysConfig.getConfigValue() : "";
    }
}
