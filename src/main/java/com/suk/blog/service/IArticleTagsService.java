package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.ArticleTags;

import java.util.List;

/**
 * 文章标签 服务层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface IArticleTagsService extends IService<ArticleTags> {

    /**
     * 分页查询
     *
     * @param iPage
     * @param articleTags
     * @return
     */

    IPage<ArticleTags> pageList(IPage<ArticleTags> iPage, ArticleTags articleTags);

    /**
     * 不分页查询
     *
     * @param articleTags
     * @return
     */
    List<ArticleTags> list(ArticleTags articleTags);

    /**
     * 批量插入
     *
     * @param tagIds    tag标签集合
     * @param articleId 文章id
     */
    void batchInsert(String[] tagIds, Integer articleId);

    /**
     * 根据文章id删除标签
     *
     * @param articleId
     * @return
     */
    int removeByArticleId(Integer articleId);
}
