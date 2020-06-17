package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.entity.Config;
import com.suk.blog.mapper.ConfigMapper;
import com.suk.blog.service.IConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suk
 * @since 2019-09-04
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {
    /**
     * 分页查询
     *
     * @param iPage
     * @param config
     * @return
     */
    @Override
    public IPage<Config> pageList(IPage<Config> iPage, Config config) {
        LambdaQueryWrapper<Config> queryWrapper = new LambdaQueryWrapper<Config>();
        page(iPage, queryWrapper);
        return iPage;

    }

    /**
     * 不分页查询
     *
     * @param config
     * @return
     */
    @Override
    public List<Config> list(Config config) {
        LambdaQueryWrapper<Config> queryWrapper = new LambdaQueryWrapper<Config>();
        List<Config> list = list(queryWrapper);
        return list;
    }
}
