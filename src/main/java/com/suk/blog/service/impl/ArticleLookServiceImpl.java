package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.ArticleLook;
import com.suk.blog.mapper.ArticleLookMapper;
import com.suk.blog.service.IArticleLookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 文章浏览记录 服务层实现
 *
 * @author suk
 * @date 2019-09-27
 */
@Service
public class ArticleLookServiceImpl extends ServiceImpl<ArticleLookMapper, ArticleLook> implements IArticleLookService {
    @Autowired
    ArticleLookMapper articleLookMapper;

    /**
     * 分页查询
     *
     * @param iPage
     * @param articleLook
     * @return
     */
    @Override
    public IPage<ArticleLook> pageList(IPage<ArticleLook> iPage, ArticleLook articleLook) {
        LambdaQueryWrapper<ArticleLook> queryWrapper = new LambdaQueryWrapper<ArticleLook>();
        queryWrapper.ge(articleLook.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(articleLook.getParams().get(Constants.START_TIME).toString()), ArticleLook::getCreateTime, articleLook.getParams().get(Constants.START_TIME))
                .le(articleLook.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(articleLook.getParams().get(Constants.END_TIME).toString()), ArticleLook::getCreateTime, articleLook.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param articleLook
     * @return
     */
    @Override
    public List<ArticleLook> list(ArticleLook articleLook) {
        LambdaQueryWrapper<ArticleLook> queryWrapper = new LambdaQueryWrapper<ArticleLook>();
        queryWrapper.ge(articleLook.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(articleLook.getParams().get(Constants.START_TIME).toString()), ArticleLook::getCreateTime, articleLook.getParams().get(Constants.START_TIME))
                .le(articleLook.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(articleLook.getParams().get(Constants.END_TIME).toString()), ArticleLook::getCreateTime, articleLook.getParams().get(Constants.END_TIME));
        List<ArticleLook> list = list(queryWrapper);
        return list;
    }

    @Override
    public int checkArticleLook(Integer articleId, String userIp, Date lookTime) {
        LambdaQueryWrapper<ArticleLook> lambdaQueryWrapper = new LambdaQueryWrapper<ArticleLook>()
                .eq(ArticleLook::getArticleId, articleId)
                .eq(ArticleLook::getUserIp, userIp)
                .gt(ArticleLook::getLookTime, lookTime);
        return articleLookMapper.selectCount(lambdaQueryWrapper);
    }
}
