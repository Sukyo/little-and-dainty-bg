package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.Link;
import com.suk.blog.mapper.LinkMapper;
import com.suk.blog.service.ILinkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 友情链接 服务层实现
 *
 * @author suk
 * @date 2019-09-27
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {
    /**
     * 分页查询
     *
     * @param iPage
     * @param link
     * @return
     */
    @Override
    public IPage<Link> pageList(IPage<Link> iPage, Link link) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<Link>();
        queryWrapper.like(StringUtils.isNoneBlank(link.getName()), Link::getName, link.getName())
                .ge(link.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(link.getParams().get(Constants.START_TIME).toString()), Link::getCreateTime, link.getParams().get(Constants.START_TIME))
                .le(link.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(link.getParams().get(Constants.END_TIME).toString()), Link::getCreateTime, link.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param link
     * @return
     */
    @Override
    public List<Link> list(Link link) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<Link>();
        queryWrapper.like(StringUtils.isNoneBlank(link.getName()), Link::getName, link.getName())
                .eq(link.getStatus() != null, Link::getStatus, link.getStatus())
                .ge(link.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(link.getParams().get(Constants.START_TIME).toString()), Link::getCreateTime, link.getParams().get(Constants.START_TIME))
                .le(link.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(link.getParams().get(Constants.END_TIME).toString()), Link::getCreateTime, link.getParams().get(Constants.END_TIME));
        List<Link> list = list(queryWrapper);
        return list;
    }
}
