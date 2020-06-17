package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.ArticleCopy;
import com.suk.blog.entity.vo.ArticleConditionVo;
import com.suk.blog.mapper.ArticleCopyMapper;
import com.suk.blog.service.IArticleCopyService;
import com.suk.blog.service.IArticleTagsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 文章 服务层实现
 *
 * @author suk
 * @date 2019-09-27
 */
@Service
public class ArticleCopyServiceImpl extends ServiceImpl<ArticleCopyMapper, ArticleCopy> implements IArticleCopyService {

    @Autowired
    ArticleCopyMapper articleCopyMapper;
    @Autowired
    IArticleTagsService articleTagsService;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public IPage<ArticleCopy> newPageList(IPage<ArticleCopy> iPage, ArticleConditionVo vo) {
        long count = articleCopyMapper.count(vo);
        List<ArticleCopy> list = articleCopyMapper.newList(vo);
        if (!CollectionUtils.isEmpty(list)) {
            List<Integer> ids = new ArrayList<>();
            for (ArticleCopy bizArticle : list) {
                ids.add(bizArticle.getId());
            }
            List<ArticleCopy> listTag = articleCopyMapper.listTagsByArticleId(ids);
            // listTag, 重新组装数据为{id: Article}
            Map<Integer, ArticleCopy> tagMap = new LinkedHashMap<>(listTag.size());
            for (ArticleCopy bizArticle : listTag) {
                tagMap.put(bizArticle.getId(), bizArticle);
            }

            for (ArticleCopy bizArticle : list) {
                ArticleCopy tagArticle = tagMap.get(bizArticle.getId());
                bizArticle.setTags(tagArticle.getTags());
            }
        }
        iPage.setRecords(list);
        iPage.setTotal(count);
        return iPage;
    }

    /**
     * 分页查询
     *
     * @param iPage
     * @param articleCopy
     * @return
     */
    @Override
    public IPage<ArticleCopy> pageList(IPage<ArticleCopy> iPage, ArticleCopy articleCopy) {
        LambdaQueryWrapper<ArticleCopy> queryWrapper = new LambdaQueryWrapper<ArticleCopy>();
        queryWrapper.ge(articleCopy.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(articleCopy.getParams().get(Constants.START_TIME).toString()), ArticleCopy::getCreateTime, articleCopy.getParams().get(Constants.START_TIME))
                .le(articleCopy.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(articleCopy.getParams().get(Constants.END_TIME).toString()), ArticleCopy::getCreateTime, articleCopy.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param articleCopy
     * @return
     */
    @Override
    public List<ArticleCopy> list(ArticleCopy articleCopy) {
        LambdaQueryWrapper<ArticleCopy> queryWrapper = new LambdaQueryWrapper<ArticleCopy>();
        queryWrapper.ge(articleCopy.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(articleCopy.getParams().get(Constants.START_TIME).toString()), ArticleCopy::getCreateTime, articleCopy.getParams().get(Constants.START_TIME))
                .le(articleCopy.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(articleCopy.getParams().get(Constants.END_TIME).toString()), ArticleCopy::getCreateTime, articleCopy.getParams().get(Constants.END_TIME));
        List<ArticleCopy> list = list(queryWrapper);
        return list;
    }

    @Override
    public List<ArticleCopy> findByCondition(ArticleConditionVo vo) {
        List<ArticleCopy> list = articleCopyMapper.newList(vo);
        if (!CollectionUtils.isEmpty(list)) {
            List<Integer> ids = new ArrayList<>();
            for (ArticleCopy bizArticle : list) {
                ids.add(bizArticle.getId());
            }
            List<ArticleCopy> listTag = articleCopyMapper.listTagsByArticleId(ids);
            // listTag, 重新组装数据为{id: Article}
            Map<Integer, ArticleCopy> tagMap = new LinkedHashMap<>(listTag.size());
            for (ArticleCopy bizArticle : listTag) {
                tagMap.put(bizArticle.getId(), bizArticle);
            }

            for (ArticleCopy bizArticle : list) {
                ArticleCopy tagArticle = tagMap.get(bizArticle.getId());
                bizArticle.setTags(tagArticle.getTags());
            }
        }
        return list;
    }

    @Override
    public List<ArticleCopy> sliderList() {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setSlider(true);
        vo.setStatus(Constants.STATUS_VALID);
        return this.findByCondition(vo);
    }

    @Override
    public List<ArticleCopy> recommendedList(int pageSize) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setRecommended(true);
        vo.setStatus(Constants.STATUS_VALID);
        vo.setPageSize(pageSize);
        PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        return this.findByCondition(vo);
    }

    @Override
    public List<ArticleCopy> recentList(int pageSize) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setPageSize(pageSize);
        vo.setStatus(Constants.STATUS_VALID);
        vo.setRecentFlag(true);
        PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        return this.findByCondition(vo);
    }

    @Override
    public List<ArticleCopy> randomList(int pageSize) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setRandom(true);
        vo.setStatus(Constants.STATUS_VALID);
        vo.setPageSize(pageSize);
        PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        return this.findByCondition(vo);
    }

    @Override
    public List<ArticleCopy> hotList(int pageSize) {
        PageHelper.startPage(1, pageSize);
        return articleCopyMapper.hotList();
    }

    /**
     * 获取文章详情，文章标签、文章类型
     *
     * @param id
     * @return
     */
    @Override
    public ArticleCopy selectById(Integer id) {
        return articleCopyMapper.selectById(id);
    }

    /**
     * 新增返回主键对象
     *
     * @param articleCopy
     * @return
     */
    @Override
    public int saveAndGetId(ArticleCopy articleCopy) {
        try {
            return articleCopyMapper.insert(articleCopy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 新增
     *
     * @param articleCopy
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveArticle(ArticleCopy articleCopy, String[] tagIds) {
        articleCopyMapper.insert(articleCopy);
        articleTagsService.batchInsert(tagIds, articleCopy.getId());
    }

    /**
     * 更新
     *
     * @param articleCopy
     * @param tagIds
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateArticle(ArticleCopy articleCopy, String[] tagIds) {
        articleCopyMapper.updateById(articleCopy);
        articleTagsService.removeByArticleId(articleCopy.getId());
        articleTagsService.batchInsert(tagIds, articleCopy.getId());
    }
}
