package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.ArticleCopy;
import com.suk.blog.entity.vo.ArticleConditionVo;

import java.util.List;

/**
 * 文章 服务层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface IArticleCopyService extends IService<ArticleCopy> {


    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    IPage<ArticleCopy> newPageList(IPage<ArticleCopy> iPage, ArticleConditionVo vo);

    /**
     * 分页查询
     *
     * @param iPage
     * @param articleCopy
     * @return
     */

    IPage<ArticleCopy> pageList(IPage<ArticleCopy> iPage, ArticleCopy articleCopy);

    /**
     * 不分页查询
     *
     * @param articleCopy
     * @return
     */
    List<ArticleCopy> list(ArticleCopy articleCopy);

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<ArticleCopy> findByCondition(ArticleConditionVo vo);

    /**
     * 首页轮播
     *
     * @return
     */
    List<ArticleCopy> sliderList();

    /**
     * 站长推荐
     *
     * @param pageSize
     * @return
     */
    List<ArticleCopy> recommendedList(int pageSize);

    /**
     * 最近文章
     *
     * @param pageSize
     * @return
     */

    List<ArticleCopy> recentList(int pageSize);

    /**
     * 随机文章
     *
     * @param pageSize
     * @return
     */
    List<ArticleCopy> randomList(int pageSize);

    /**
     * 热门文章
     *
     * @param pageSize
     * @return
     */
    List<ArticleCopy> hotList(int pageSize);

    /**
     * 获取文章详情，文章标签、文章类型
     *
     * @param id
     * @return
     */
    ArticleCopy selectById(Integer id);

    /**
     * 新增返回主键对象
     *
     * @param articleCopy
     * @return
     */
    int saveAndGetId(ArticleCopy articleCopy);

    /**
     * 新增
     *
     * @param articleCopy
     * @param tagIds
     */
    void saveArticle(ArticleCopy articleCopy, String[] tagIds);

    /**
     * 更新
     *
     * @param articleCopy
     * @param tagIds
     */
    void updateArticle(ArticleCopy articleCopy, String[] tagIds);
}
