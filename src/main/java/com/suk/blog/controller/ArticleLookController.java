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
import com.suk.blog.entity.ArticleLook;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.IArticleLookService;
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
 * 文章浏览记录 信息操作处理
 *
 * @author suk
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/blog/articleLook")
public class ArticleLookController extends BaseController {
    private String prefix = "blog/articleLook" ;

    @Autowired
    private IArticleLookService articleLookService;

    @RequiresPermissions("blog:articleLook:view")
    @GetMapping()
    public String articleLook() {
        return prefix + "/articleLook" ;
    }

    /**
     * 查询文章浏览记录列表
     */
    @RequiresPermissions("blog:articleLook:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ArticleLook articleLook) {
        IPage<ArticleLook> page = getPage();
            articleLookService.pageList(page, articleLook);
        return getDataTable(page.getRecords(), page.getTotal());
    }


    /**
     * 导出文章浏览记录列表
     */
    @RequiresPermissions("blog:articleLook:export")
    @GetMapping("/export")
    @ResponseBody
    public void export(ArticleLook articleLook,HttpServletRequest request, HttpServletResponse response) {
        List<ArticleLook> list = articleLookService.list(articleLook);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "文章浏览记录" ;
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, ArticleLook.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增文章浏览记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add" ;
    }

    /**
     * 新增保存文章浏览记录
     */
    @RequiresPermissions("blog:articleLook:add")
    @Log(title = "文章浏览记录", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ArticleLook articleLook) {
        return toAjax(articleLookService.save(articleLook));
    }

    /**
     * 修改文章浏览记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ArticleLook articleLook =articleLookService.getById(id);
        mmap.put("articleLook", articleLook);
        return prefix + "/edit" ;
    }

    /**
     * 修改保存文章浏览记录
     */
    @RequiresPermissions("blog:articleLook:edit")
    @Log(title = "文章浏览记录", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ArticleLook articleLook) {
        return toAjax(articleLookService.updateById(articleLook));
    }

    /**
     * 删除文章浏览记录
     */
    @RequiresPermissions("blog:articleLook:remove")
    @Log(title = "文章浏览记录", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(articleLookService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
