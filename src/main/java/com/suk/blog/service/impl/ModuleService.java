package com.suk.blog.service.impl;

import com.suk.blog.constant.Constants;
import com.suk.blog.entity.Category;
import com.suk.blog.entity.Link;
import com.suk.blog.entity.Tags;
import com.suk.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * js调用 thymeleaf
 *
 * @author
 * @version V1.0
 * @date
 */
@Component("module")
public class ModuleService {
    @Autowired
    private ICategoryService bizCategoryService;
    @Autowired
    private IArticleCopyService bizArticleService;
    @Autowired
    private ITagsService bizTagsService;
    @Autowired
    private ILinkService bizLinkService;
    @Autowired
    private ISiteInfoService siteInfoService;
    @Autowired
    private ISiteConfigService siteConfigService;

    public Object get(String moduleName) {
        switch (moduleName) {
            case "categoryList"://分类
                Category bizCategory = new Category();
                bizCategory.setStatus(Constants.STATUS_VALID);
                return bizCategoryService.selectCategories(bizCategory);
            case "tagList":             //标签
                return bizTagsService.list(new Tags());
            case "sliderList":          //轮播文章
                return bizArticleService.sliderList();
            case "recentList":          //最近文章
                return bizArticleService.recentList(10);
            case "recommendedList":    //推荐文章
                return bizArticleService.recommendedList(10);
            case "hotList":             //热门文章
                return bizArticleService.hotList(10);
            case "randomList":          //随机文章
                return bizArticleService.randomList(10);
            case "linkList":            //友链
                Link bizLink = new Link();
                bizLink.setStatus(Constants.STATUS_VALID);
                return bizLinkService.list(bizLink);
            case "siteInfo":            //网站信息统计
                return siteInfoService.getSiteInfo();
            case "sysConfig":           //网站基本信息配置
                return siteConfigService.selectAllToMap();
            default:
                return null;
        }
    }
}
