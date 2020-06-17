package com.suk.blog.controller;

import java.util.List;


import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.suk.blog.aspectj.annotation.Log;
import com.suk.blog.common.response.AjaxResult;
import com.suk.blog.common.response.TableDataInfo;
import com.suk.blog.entity.ArticleTags;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.IArticleTagsService;
import com.suk.blog.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * 文章标签 信息操作处理
 *
 * @author suk
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/blog/articleTags")
public class ArticleTagsController extends BaseController {
    private String prefix = "blog/articleTags" ;

    @Autowired
    private IArticleTagsService articleTagsService;

    @RequiresPermissions("blog:articleTags:view")
    @GetMapping()
    public String articleTags() {
        return prefix + "/articleTags" ;
    }

    /**
     * 查询文章标签列表
     */
    @RequiresPermissions("blog:articleTags:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(ArticleTags articleTags) {
        IPage<ArticleTags> page = getPage();
            articleTagsService.pageList(page, articleTags);
        return getDataTable(page.getRecords(), page.getTotal());
    }


    /**
     * 导出文章标签列表
     */
    @RequiresPermissions("blog:articleTags:export")
    @PostMapping("/export")
    @ResponseBody
    public void export(ArticleTags articleTags,HttpServletRequest request, HttpServletResponse response) {
        List<ArticleTags> list = articleTagsService.list(articleTags);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "文章标签" ;
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, ArticleTags.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增文章标签
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add" ;
    }

    /**
     * 新增保存文章标签
     */
    @RequiresPermissions("blog:articleTags:add")
    @Log(title = "文章标签", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ArticleTags articleTags) {
        return toAjax(articleTagsService.save(articleTags));
    }

    /**
     * 修改文章标签
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ArticleTags articleTags =articleTagsService.getById(id);
        mmap.put("articleTags", articleTags);
        return prefix + "/edit" ;
    }

    /**
     * 修改保存文章标签
     */
    @RequiresPermissions("blog:articleTags:edit")
    @Log(title = "文章标签", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ArticleTags articleTags) {
        return toAjax(articleTagsService.updateById(articleTags));
    }

    /**
     * 删除文章标签
     */
    @RequiresPermissions("blog:articleTags:remove")
    @Log(title = "文章标签", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(articleTagsService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
