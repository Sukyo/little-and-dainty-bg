package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.ArticleLook;

import java.util.Date;
import java.util.List;

/**
 * 文章浏览记录 服务层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface IArticleLookService extends IService<ArticleLook> {

    /**
     * 分页查询
     *
     * @param iPage
     * @param articleLook
     * @return
     */

    IPage<ArticleLook> pageList(IPage<ArticleLook> iPage, ArticleLook articleLook);

    /**
     * 不分页查询
     *
     * @param articleLook
     * @return
     */
    List<ArticleLook> list(ArticleLook articleLook);

    int checkArticleLook(Integer articleId, String userIp, Date lookTime);
}
