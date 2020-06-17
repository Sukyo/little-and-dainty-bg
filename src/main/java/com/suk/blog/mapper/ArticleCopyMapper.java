package com.suk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suk.blog.entity.ArticleCopy;
import com.suk.blog.entity.vo.ArticleConditionVo;

import java.util.List;

/**
 * 文章 数据层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface ArticleCopyMapper extends BaseMapper<ArticleCopy> {
    /**
     * 分页查询，关联查询文章标签、文章类型
     *
     * @param vo
     * @return
     */
    List<ArticleCopy> newList(ArticleConditionVo vo);

    long count(ArticleConditionVo vo);

    /**
     * 统计指定文章的标签集合
     *
     * @param list
     * @return
     */
    List<ArticleCopy> listTagsByArticleId(List<Integer> list);

    /**
     * 热门文章
     *
     * @return
     */
    List<ArticleCopy> hotList();

    /**
     * 获取文章详情，文章标签、文章类型
     *
     * @param id
     * @return
     */
    ArticleCopy selectById(Integer id);

    /**
     * 批量删除文章
     *
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);
}