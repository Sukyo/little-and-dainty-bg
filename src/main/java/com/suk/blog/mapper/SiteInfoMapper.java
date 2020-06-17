package com.suk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suk.blog.entity.SiteInfo;

import java.util.Map;

/**
 * 网站配置 数据层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface SiteInfoMapper extends BaseMapper<SiteInfo> {
    /**
     * 统计网站信息
     *
     * @return
     */
    Map<String, Object> getSiteInfo();
}