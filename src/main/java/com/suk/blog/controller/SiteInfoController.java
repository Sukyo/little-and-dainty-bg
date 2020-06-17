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
import com.suk.blog.entity.SiteInfo;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.ISiteInfoService;
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
 * 网站配置 信息操作处理
 *
 * @author suk
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/blog/siteInfo")
public class SiteInfoController extends BaseController {
    private String prefix = "blog/siteInfo" ;

    @Autowired
    private ISiteInfoService siteInfoService;

    @RequiresPermissions("blog:siteInfo:view")
    @GetMapping()
    public String siteInfo() {
        return prefix + "/siteInfo" ;
    }

    /**
     * 查询网站配置列表
     */
    @RequiresPermissions("blog:siteInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SiteInfo siteInfo) {
        IPage<SiteInfo> page = getPage();
            siteInfoService.pageList(page, siteInfo);
        return getDataTable(page.getRecords(), page.getTotal());
    }


    /**
     * 导出网站配置列表
     */
    @RequiresPermissions("blog:siteInfo:export")
    @GetMapping("/export")
    @ResponseBody
    public void export(SiteInfo siteInfo,HttpServletRequest request, HttpServletResponse response) {
        List<SiteInfo> list = siteInfoService.list(siteInfo);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "网站配置" ;
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, SiteInfo.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增网站配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add" ;
    }

    /**
     * 新增保存网站配置
     */
    @RequiresPermissions("blog:siteInfo:add")
    @Log(title = "网站配置", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SiteInfo siteInfo) {
        return toAjax(siteInfoService.save(siteInfo));
    }

    /**
     * 修改网站配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        SiteInfo siteInfo =siteInfoService.getById(id);
        mmap.put("siteInfo", siteInfo);
        return prefix + "/edit" ;
    }

    /**
     * 修改保存网站配置
     */
    @RequiresPermissions("blog:siteInfo:edit")
    @Log(title = "网站配置", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SiteInfo siteInfo) {
        return toAjax(siteInfoService.updateById(siteInfo));
    }

    /**
     * 删除网站配置
     */
    @RequiresPermissions("blog:siteInfo:remove")
    @Log(title = "网站配置", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(siteInfoService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
