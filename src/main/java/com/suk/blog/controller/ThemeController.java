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
import com.suk.blog.entity.Theme;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.IThemeService;
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
import java.util.List;
import java.util.Map;

/**
 * 网站主题 信息操作处理
 *
 * @author suk
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/blog/theme")
public class ThemeController extends BaseController {
    private String prefix = "blog/theme" ;

    @Autowired
    private IThemeService themeService;

    @RequiresPermissions("blog:theme:view")
    @GetMapping()
    public String theme() {
        return prefix + "/theme" ;
    }

    /**
     * 查询网站主题列表
     */
    @RequiresPermissions("blog:theme:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Theme theme) {
        IPage<Theme> page = getPage();
        themeService.pageList(page, theme);
        return getDataTable(page.getRecords(), page.getTotal());
    }


    /**
     * 导出网站主题列表
     */
    @RequiresPermissions("blog:theme:export")
    @GetMapping("/export")
    @ResponseBody
    public void export(Theme theme, HttpServletRequest request, HttpServletResponse response) {
        List<Theme> list = themeService.list(theme);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "网站主题" ;
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, Theme.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增网站主题
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add" ;
    }

    /**
     * 新增保存网站主题
     */
    @RequiresPermissions("blog:theme:add")
    @Log(title = "网站主题", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Theme theme) {
        theme.setCreateTime(DateUtil.getNowTimestamp());
        return toAjax(themeService.save(theme));
    }

    /**
     * 修改网站主题
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Theme theme = themeService.getById(id);
        mmap.put("theme", theme);
        return prefix + "/edit" ;
    }

    /**
     * 修改保存网站主题
     */
    @RequiresPermissions("blog:theme:edit")
    @Log(title = "网站主题", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Theme theme) {
        return toAjax(themeService.updateById(theme));
    }

    /**
     * 删除网站主题
     */
    @RequiresPermissions("blog:theme:remove")
    @Log(title = "网站主题", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(themeService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
