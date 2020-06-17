package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.ArticleTags;
import com.suk.blog.mapper.ArticleTagsMapper;
import com.suk.blog.service.IArticleTagsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 文章标签 服务层实现
 *
 * @author suk
 * @date 2019-09-27
 */
@Service
public class ArticleTagsServiceImpl extends ServiceImpl<ArticleTagsMapper, ArticleTags> implements IArticleTagsService {
    @Autowired
    ArticleTagsMapper articleTagsMapper;

    /**
     * 分页查询
     *
     * @param iPage
     * @param articleTags
     * @return
     */
    @Override
    public IPage<ArticleTags> pageList(IPage<ArticleTags> iPage, ArticleTags articleTags) {
        LambdaQueryWrapper<ArticleTags> queryWrapper = new LambdaQueryWrapper<ArticleTags>();
        queryWrapper.ge(articleTags.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(articleTags.getParams().get(Constants.START_TIME).toString()), ArticleTags::getCreateTime, articleTags.getParams().get(Constants.START_TIME))
                .le(articleTags.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(articleTags.getParams().get(Constants.END_TIME).toString()), ArticleTags::getCreateTime, articleTags.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param articleTags
     * @return
     */
    @Override
    public List<ArticleTags> list(ArticleTags articleTags) {
        LambdaQueryWrapper<ArticleTags> queryWrapper = new LambdaQueryWrapper<ArticleTags>();
        queryWrapper.ge(articleTags.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(articleTags.getParams().get(Constants.START_TIME).toString()), ArticleTags::getCreateTime, articleTags.getParams().get(Constants.START_TIME))
                .le(articleTags.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(articleTags.getParams().get(Constants.END_TIME).toString()), ArticleTags::getCreateTime, articleTags.getParams().get(Constants.END_TIME));
        List<ArticleTags> list = list(queryWrapper);
        return list;
    }

    /**
     * 批量插入
     *
     * @param tagIds    tag标签集合
     * @param articleId 文章id
     */
    @Override
    public void batchInsert(String[] tagIds, Integer articleId) {
        Date date = new Date();
        for (String tagId : tagIds) {
            ArticleTags articleTags = new ArticleTags();
            articleTags.setTagId(Integer.parseInt(tagId));
            articleTags.setArticleId(articleId);
            articleTags.setCreateTime(date);
            articleTags.setUpdateTime(date);
            articleTagsMapper.insert(articleTags);
        }
    }

    /**
     * 根据文章id删除标签
     *
     * @param articleId
     * @return
     */
    @Override
    public int removeByArticleId(Integer articleId) {
        int delete = articleTagsMapper.delete(new LambdaQueryWrapper<ArticleTags>().eq(ArticleTags::getArticleId, articleId));
        return delete;
    }

}

