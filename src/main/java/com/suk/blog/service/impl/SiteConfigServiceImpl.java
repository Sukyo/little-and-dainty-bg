package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.entity.SiteConfig;
import com.suk.blog.mapper.SiteConfigMapper;
import com.suk.blog.service.ISiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统配置 服务层实现
 *
 * @author suk
 * @date 2019-09-28
 */
@Service
public class SiteConfigServiceImpl extends ServiceImpl<SiteConfigMapper, SiteConfig> implements ISiteConfigService {

    @Autowired
    SiteConfigMapper siteConfigMapper;

    @Override
    public Map<String, String> selectAllToMap() {
        List<SiteConfig> sysConfigs = siteConfigMapper.selectList(new LambdaQueryWrapper<>());
        Map<String, String> map = new HashMap<String, String>(sysConfigs.size());
        for (SiteConfig config : sysConfigs) {
            map.put(config.getSysKey(), config.getSysValue());
        }
        return map;
    }
}
