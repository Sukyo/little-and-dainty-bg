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
import com.suk.blog.entity.Link;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.ILinkService;
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
 * 友情链接 信息操作处理
 *
 * @author suk
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/blog/link")
public class LinkController extends BaseController {
    private String prefix = "blog/link" ;

    @Autowired
    private ILinkService linkService;

    @RequiresPermissions("blog:link:view")
    @GetMapping()
    public String link() {
        return prefix + "/link" ;
    }

    /**
     * 查询友情链接列表
     */
    @RequiresPermissions("blog:link:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Link link) {
        IPage<Link> page = getPage();
            linkService.pageList(page, link);
        return getDataTable(page.getRecords(), page.getTotal());
    }


    /**
     * 导出友情链接列表
     */
    @RequiresPermissions("blog:link:export")
    @GetMapping("/export")
    @ResponseBody
    public void export(Link link,HttpServletRequest request, HttpServletResponse response) {
        List<Link> list = linkService.list(link);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "友情链接" ;
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, Link.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增友情链接
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add" ;
    }

    /**
     * 新增保存友情链接
     */
    @RequiresPermissions("blog:link:add")
    @Log(title = "友情链接", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Link link) {
        return toAjax(linkService.save(link));
    }

    /**
     * 修改友情链接
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Link link =linkService.getById(id);
        mmap.put("link", link);
        return prefix + "/edit" ;
    }

    /**
     * 修改保存友情链接
     */
    @RequiresPermissions("blog:link:edit")
    @Log(title = "友情链接", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Link link) {
        return toAjax(linkService.updateById(link));
    }

    /**
     * 删除友情链接
     */
    @RequiresPermissions("blog:link:remove")
    @Log(title = "友情链接", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(linkService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
