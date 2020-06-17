package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.Config;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author suk
 * @since 2019-09-04
 */
public interface IConfigService extends IService<Config> {
    /**
     * 分页查询
     *
     * @param iPage
     * @param config
     * @return
     */
    IPage<Config> pageList(IPage<Config> iPage, Config config);

    /**
     * 不分页查询
     *
     * @param sysConfig
     * @return
     */
    List<Config> list(Config sysConfig);
}
