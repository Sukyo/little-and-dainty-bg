package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.SysConfig;

import java.util.List;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
public interface ISysConfigService extends IService<SysConfig> {
    /**
     * 分页查询
     *
     * @param iPage
     * @param config
     * @return
     */
    IPage<SysConfig> pageList(IPage<SysConfig> iPage, SysConfig config);

    /**
     * 不分页查询
     *
     * @param sysConfig
     * @return
     */
    List<SysConfig> list(SysConfig sysConfig);

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    String checkConfigKeyUnique(SysConfig config);
}
