package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.SiteInfo;

import java.util.List;
import java.util.Map;

/**
 * 网站配置 服务层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface ISiteInfoService extends IService<SiteInfo> {

    /**
     * 分页查询
     *
     * @param iPage
     * @param siteInfo
     * @return
     */

    IPage<SiteInfo> pageList(IPage<SiteInfo> iPage, SiteInfo siteInfo);

    /**
     * 不分页查询
     *
     * @param siteInfo
     * @return
     */
    List<SiteInfo> list(SiteInfo siteInfo);

    Map<String, Object> getSiteInfo();
}
