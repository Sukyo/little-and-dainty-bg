package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.constant.UserConstants;
import com.suk.blog.entity.SysConfig;
import com.suk.blog.mapper.SysConfigMapper;
import com.suk.blog.service.ISysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

    /**
     * 分页查询
     *
     * @param iPage
     * @param config
     * @return
     */
    @Override
    public IPage<SysConfig> pageList(IPage<SysConfig> iPage, SysConfig config) {
        LambdaQueryWrapper<SysConfig> queryWrapper = new LambdaQueryWrapper<SysConfig>();
        queryWrapper.eq(StringUtils.isNoneBlank(config.getConfigType()), SysConfig::getConfigType, config.getConfigType())
                .like(StringUtils.isNoneBlank(config.getConfigName()), SysConfig::getConfigName, config.getConfigName())
                .like(StringUtils.isNoneBlank(config.getConfigKey()), SysConfig::getConfigKey, config.getConfigKey())
                .ge(config.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(config.getParams().get(Constants.START_TIME).toString()), SysConfig::getCreateTime, config.getParams().get(Constants.START_TIME))
                .le(config.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(config.getParams().get(Constants.END_TIME).toString()), SysConfig::getCreateTime, config.getParams().get(Constants.END_TIME));
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
    public List<SysConfig> list(SysConfig config) {
        LambdaQueryWrapper<SysConfig> queryWrapper = new LambdaQueryWrapper<SysConfig>();
        queryWrapper.eq(StringUtils.isNoneBlank(config.getConfigType()), SysConfig::getConfigType, config.getConfigType())
                .like(StringUtils.isNoneBlank(config.getConfigName()), SysConfig::getConfigName, config.getConfigName())
                .like(StringUtils.isNoneBlank(config.getConfigKey()), SysConfig::getConfigKey, config.getConfigKey())
                .ge(config.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(config.getParams().get(Constants.START_TIME).toString()), SysConfig::getCreateTime, config.getParams().get(Constants.START_TIME))
                .le(config.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(config.getParams().get(Constants.END_TIME).toString()), SysConfig::getCreateTime, config.getParams().get(Constants.END_TIME));
        List<SysConfig> list = list(queryWrapper);
        return list;
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        Long configId = config.getId() == null ? -1L : config.getId();
        SysConfig info = getOne(new LambdaQueryWrapper<SysConfig>().eq(SysConfig::getConfigKey, config.getConfigKey()));
        if (info != null && info.getId().longValue() != configId.longValue()) {
            return UserConstants.CONFIG_KEY_NOT_UNIQUE;
        }
        return UserConstants.CONFIG_KEY_UNIQUE;
    }
}
