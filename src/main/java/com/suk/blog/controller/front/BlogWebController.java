package com.suk.blog.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.ArticleCopy;
import com.suk.blog.entity.vo.ArticleConditionVo;
import com.suk.blog.entity.vo.PageVo;
import com.suk.blog.service.IArticleCopyService;
import com.suk.blog.service.IThemeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author suk
 * @version V1.0
 * @title: BlogWebController
 * @package: com.suk.blog.controller.front
 * @description: 网站前台
 * @date 2019/10/12 15:07
 */
@Controller
public class BlogWebController {
    private static final String THEME_PREFIX = "blog/front/theme/";
    private static final String BLOG_FRONT_PREFIX = "suk";
    @Autowired
    private IArticleCopyService articleCopyService;
    @Autowired
    private IThemeService bizThemeService;

    @GetMapping("/")
    public String index(Model model, ArticleConditionVo vo) {
        model.addAttribute("pageUrl", BLOG_FRONT_PREFIX + "/index");
        model.addAttribute("categoryId", "index");
        //轮播文章
        model.addAttribute("sliderList", articleCopyService.sliderList());
        loadMainPage(model, vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/index";
    }

    @RequestMapping(BLOG_FRONT_PREFIX + "/index/{pageNumber}")
    public String index(@PathVariable("pageNumber") Integer pageNumber, ArticleConditionVo vo, Model model) {
        vo.setPageNo(pageNumber);
        model.addAttribute("pageUrl", BLOG_FRONT_PREFIX + "/index");
        model.addAttribute("categoryId", "index");
        loadMainPage(model, vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/index";
    }

    /**
     * 分类列表
     *
     * @param categoryId
     * @param model
     * @return
     */
    @GetMapping(BLOG_FRONT_PREFIX + "/category/{categoryId}")
    public String category(@PathVariable("categoryId") Integer categoryId, Model model) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setCategoryId(categoryId);
        model.addAttribute("pageUrl", BLOG_FRONT_PREFIX + "/category/" + categoryId);
        model.addAttribute("categoryId", categoryId);
        loadMainPage(model, vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/index";
    }

    @GetMapping("/suk/category/{categoryId}/{pageNumber}")
    public String category(@PathVariable("categoryId") Integer categoryId, @PathVariable("pageNumber") Integer pageNumber, Model model) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setCategoryId(categoryId);
        vo.setPageNo(pageNumber);
        model.addAttribute("pageUrl", BLOG_FRONT_PREFIX + "/category/" + categoryId);
        model.addAttribute("categoryId", categoryId);
        loadMainPage(model, vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/index";
    }


    /**
     * 标签列表
     *
     * @param tagId
     * @param model
     * @return
     */
    @GetMapping(BLOG_FRONT_PREFIX + "/tag/{tagId}")
    public String tag(@PathVariable("tagId") Integer tagId, Model model) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setTagId(tagId);
        model.addAttribute("pageUrl", BLOG_FRONT_PREFIX + "/tag/" + tagId);
        loadMainPage(model, vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/index";
    }

    /**
     * 标签列表（分页）
     *
     * @param tagId
     * @param pageNumber
     * @param model
     * @return
     */
    @GetMapping(BLOG_FRONT_PREFIX + "/tag/{tagId}/{pageNumber}")
    public String tag(@PathVariable("tagId") Integer tagId, @PathVariable("pageNumber") Integer pageNumber, Model model) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setTagId(tagId);
        vo.setPageNo(pageNumber);
        model.addAttribute("pageUrl", BLOG_FRONT_PREFIX + "/tag/" + tagId);
        loadMainPage(model, vo);
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/index";
    }

    /**
     * 文章详情
     *
     * @param model
     * @param articleId
     * @return
     */
    @GetMapping(BLOG_FRONT_PREFIX + "/article/{articleId}")
    public String article(HttpServletRequest request, Model model, @PathVariable("articleId") Integer articleId) {
        ArticleCopy article = articleCopyService.selectById(articleId);
        if (article == null || Constants.STATUS_INVALID.equals(article.getStatus())) {
            throw new RuntimeException("article not found~");
        }
        model.addAttribute("article", article);
        model.addAttribute("categoryId", article.getCategoryId());
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/article";
    }

    /**
     * 文章详情
     *
     * @param model
     * @return
     */
    @GetMapping(BLOG_FRONT_PREFIX + "/comment")
    public String comment(Model model) {
        model.addAttribute("categoryId", "comment");
        return THEME_PREFIX + bizThemeService.selectCurrent().getName() + "/comment";
    }

    private void loadMainPage(Model model, ArticleConditionVo vo) {
        vo.setStatus(Constants.STATUS_VALID);
        PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        List<ArticleCopy> articleList = articleCopyService.findByCondition(vo);
        PageInfo<ArticleCopy> pageInfo = new PageInfo<>(articleList);
        PageVo pageVo = new PageVo();
        BeanUtils.copyProperties(pageInfo, pageVo);
        model.addAttribute("page", JSONObject.toJSON(pageVo));
        //文章列表
        model.addAttribute("articleList", articleList);
    }

}
