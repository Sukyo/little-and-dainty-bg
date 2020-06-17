package com.suk.blog.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.suk.blog.aspectj.annotation.Log;
import com.suk.blog.common.response.AjaxResult;
import com.suk.blog.common.response.TableDataInfo;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.ArticleCopy;
import com.suk.blog.entity.Category;
import com.suk.blog.entity.SysUser;
import com.suk.blog.entity.Tags;
import com.suk.blog.entity.vo.ArticleConditionVo;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.IArticleCopyService;
import com.suk.blog.service.ICategoryService;
import com.suk.blog.service.ITagsService;
import com.suk.blog.util.DateUtil;
import com.suk.blog.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 文章 信息操作处理
 *
 * @author suk
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/blog/articleCopy")
public class ArticleCopyController extends BaseController {
    private String prefix = "blog/articleCopy";

    @Autowired
    private IArticleCopyService articleCopyService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ITagsService tagsService;


    @RequiresPermissions("blog:articleCopy:view")
    @GetMapping()
    public String articleCopy(ModelMap modelMap) {
        modelMap.put("categories", categoryService.list());
        return prefix + "/articleCopy";
    }

    /**
     * 查询文章列表
     */
    @RequiresPermissions("blog:articleCopy:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ArticleConditionVo articleConditionVo) {
        IPage<ArticleCopy> page = getPage();
        articleConditionVo.setSliderFlag(true);
        articleConditionVo.setPageNo((int) page.getCurrent());
        articleConditionVo.setPageSize((int) page.getSize());
        articleCopyService.newPageList(page, articleConditionVo);
        return getDataTable(page.getRecords(), page.getTotal());
    }


    /**
     * 导出文章列表
     */
    @RequiresPermissions("blog:articleCopy:export")
    @GetMapping("/export")
    @ResponseBody
    public void export(ArticleCopy articleCopy, HttpServletRequest request, HttpServletResponse response) {
        List<ArticleCopy> list = articleCopyService.list(articleCopy);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "文章";
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, ArticleCopy.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增文章
     */
    @GetMapping("/add")
    public String add(Model model) {
        Category bizCategory = new Category();
        bizCategory.setStatus(Constants.STATUS_VALID);
        List<Category> bizCategories = categoryService.selectCategories(bizCategory);
        List<Tags> tags = tagsService.list(new Tags());
        model.addAttribute("categories", bizCategories);
        model.addAttribute("tags", tags);
        return prefix + "/add";
    }

    /**
     * 新增保存文章
     */
    @RequiresPermissions("blog:articleCopy:add")
    @Log(title = "文章", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ArticleCopy articleCopy, String tag) {
        SysUser user = ShiroUtils.getSysUser();
        articleCopy.setUserId(user.getId().toString());
        articleCopy.setAuthor(user.getUserName());
        articleCopy.setCreateTime(DateUtil.getNowTimestamp());
        articleCopyService.saveArticle(articleCopy, StringUtils.isBlank(tag) ? new String[0] : tag.split(","));
        return success();
    }

    /**
     * 修改文章
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap model) {
        ArticleCopy bizArticle = articleCopyService.selectById(id);
        Category bizCategory = new Category();
        bizCategory.setStatus(Constants.STATUS_VALID);
        List<Category> bizCategories = categoryService.selectCategories(bizCategory);
        model.addAttribute("categories", bizCategories);
        List<Tags> sysTags = tagsService.list(new Tags());
        /*方便前端处理回显*/
        List<Tags> aTags = new ArrayList<>();
        List<Tags> sTags = new ArrayList<>();
        boolean flag;
        for (Tags sysTag : sysTags) {
            flag = false;
            for (Tags articleTag : bizArticle.getTags()) {
                if (articleTag.getId().equals(sysTag.getId())) {
                    Tags tempTags = new Tags();
                    tempTags.setId(sysTag.getId());
                    tempTags.setName(sysTag.getName());
                    aTags.add(tempTags);
                    sTags.add(tempTags);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                sTags.add(sysTag);
            }
        }
        bizArticle.setTags(aTags);
        model.addAttribute("tags", sTags);
        model.put("article", bizArticle);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章
     */
    @RequiresPermissions("blog:articleCopy:edit")
    @Log(title = "文章", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ArticleCopy articleCopy, String tag) {
        articleCopyService.updateArticle(articleCopy, StringUtils.isBlank(tag) ? new String[0] : tag.split(","));
        return success();
    }

    /**
     * 删除文章
     */
    @RequiresPermissions("blog:articleCopy:remove")
    @Log(title = "文章", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(articleCopyService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
