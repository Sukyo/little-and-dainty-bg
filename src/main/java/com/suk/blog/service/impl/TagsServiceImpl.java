package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.Tags;
import com.suk.blog.mapper.TagsMapper;
import com.suk.blog.service.ITagsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 书签 服务层实现
 *
 * @author suk
 * @date 2019-09-27
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {
    /**
     * 分页查询
     *
     * @param iPage
     * @param tags
     * @return
     */
    @Override
    public IPage<Tags> pageList(IPage<Tags> iPage, Tags tags) {
        LambdaQueryWrapper<Tags> queryWrapper = new LambdaQueryWrapper<Tags>();
        queryWrapper.like(StringUtils.isNoneBlank(tags.getName()), Tags::getName, tags.getName())
                .ge(tags.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(tags.getParams().get(Constants.START_TIME).toString()), Tags::getCreateTime, tags.getParams().get(Constants.START_TIME))
                .le(tags.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(tags.getParams().get(Constants.END_TIME).toString()), Tags::getCreateTime, tags.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param tags
     * @return
     */
    @Override
    public List<Tags> list(Tags tags) {
        LambdaQueryWrapper<Tags> queryWrapper = new LambdaQueryWrapper<Tags>();
        queryWrapper.ge(tags.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(tags.getParams().get(Constants.START_TIME).toString()), Tags::getCreateTime, tags.getParams().get(Constants.START_TIME))
                .le(tags.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(tags.getParams().get(Constants.END_TIME).toString()), Tags::getCreateTime, tags.getParams().get(Constants.END_TIME));
        List<Tags> list = list(queryWrapper);
        return list;
    }
}
