package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.SiteInfo;
import com.suk.blog.mapper.SiteInfoMapper;
import com.suk.blog.service.ISiteInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 网站配置 服务层实现
 *
 * @author suk
 * @date 2019-09-27
 */
@Service
public class SiteInfoServiceImpl extends ServiceImpl<SiteInfoMapper, SiteInfo> implements ISiteInfoService {
    /**
     * 分页查询
     *
     * @param iPage
     * @param siteInfo
     * @return
     */
    @Override
    public IPage<SiteInfo> pageList(IPage<SiteInfo> iPage, SiteInfo siteInfo) {
        LambdaQueryWrapper<SiteInfo> queryWrapper = new LambdaQueryWrapper<SiteInfo>();
        queryWrapper.ge(siteInfo.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(siteInfo.getParams().get(Constants.START_TIME).toString()), SiteInfo::getCreateTime, siteInfo.getParams().get(Constants.START_TIME))
                .le(siteInfo.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(siteInfo.getParams().get(Constants.END_TIME).toString()), SiteInfo::getCreateTime, siteInfo.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param siteInfo
     * @return
     */
    @Override
    public List<SiteInfo> list(SiteInfo siteInfo) {
        LambdaQueryWrapper<SiteInfo> queryWrapper = new LambdaQueryWrapper<SiteInfo>();
        queryWrapper.ge(siteInfo.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(siteInfo.getParams().get(Constants.START_TIME).toString()), SiteInfo::getCreateTime, siteInfo.getParams().get(Constants.START_TIME))
                .le(siteInfo.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(siteInfo.getParams().get(Constants.END_TIME).toString()), SiteInfo::getCreateTime, siteInfo.getParams().get(Constants.END_TIME));
        List<SiteInfo> list = list(queryWrapper);
        return list;
    }

    @Autowired
    private SiteInfoMapper siteInfoMapper;

    @Override
    public Map<String, Object> getSiteInfo() {
        Map<String, Object> map = siteInfoMapper.getSiteInfo();
        return map;
    }
}
