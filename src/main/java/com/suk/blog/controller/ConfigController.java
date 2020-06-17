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
import com.suk.blog.entity.Config;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.IConfigService;
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
 * <p>
 * 参数配置表 前端控制器
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Controller
@RequestMapping("/spider/config")
public class ConfigController extends BaseController<Config> {
    private String prefix = "spider/config";

    @Autowired
    private IConfigService configService;

    @RequiresPermissions("spider:config:view")
    @GetMapping()
    public String config() {
        return prefix + "/config";
    }

    /**
     * 查询配置列表
     */
    @RequiresPermissions("spider:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Config config) {
        IPage<Config> page = getPage();
        configService.pageList(page, config);
        return getDataTable(page.getRecords(), page.getTotal());
    }

    @Log(title = "配置管理", logType = LogType.EXPORT)
    @RequiresPermissions("spider:config:export")
    @GetMapping("/export")
    public void export(Config config, HttpServletRequest request, HttpServletResponse response) {
        List<Config> list = configService.list(config);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "配置管理";
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, Config.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增参数配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存参数配置
     */
    @RequiresPermissions("spider:config:add")
    @Log(title = "配置管理", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Config config) {
        return toAjax(configService.save(config));
    }

    /**
     * 修改参数配置
     */
    @GetMapping("/edit/{configId}")
    public String edit(@PathVariable("configId") Long configId, ModelMap mmap) {
        mmap.put("config", configService.getById(configId));
        return prefix + "/edit";
    }

    /**
     * 修改保存参数配置
     */
    @RequiresPermissions("spider:config:edit")
    @Log(title = "配置管理", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Config config) {
        return toAjax(configService.updateById(config));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("spider:config:remove")
    @Log(title = "配置管理", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(configService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
