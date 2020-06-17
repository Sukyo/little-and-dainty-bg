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
import com.suk.blog.entity.SysOperLog;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.ISysOperLogService;
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
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Controller
@RequestMapping("/system/operlog")
public class SysOperLogController extends BaseController<SysOperLog> {
    private String prefix = "system/operlog";

    @Autowired
    private ISysOperLogService operLogService;

    @RequiresPermissions("system:operlog:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/operlog";
    }

    @RequiresPermissions("system:operlog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysOperLog operLog) {
        IPage<SysOperLog> page = getPage();
        operLogService.pageList(page, operLog);
        return getDataTable(page.getRecords(), page.getTotal());
    }

    @Log(title = "操作日志导出", logType = LogType.EXPORT)
    @RequiresPermissions("system:operlog:export")
    @GetMapping("/export")
    public void export(SysOperLog operLog, HttpServletRequest request, HttpServletResponse response) {
        List<SysOperLog> list = operLogService.list(operLog);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "操作日志";
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, SysOperLog.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    @RequiresPermissions("system:operlog:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(operLogService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

    @RequiresPermissions("system:operlog:detail")
    @GetMapping("/detail/{operId}")
    public String detail(@PathVariable("operId") Long operId, ModelMap mmap) {
        mmap.put("operLog", operLogService.getById(operId));
        return prefix + "/detail";
    }

//    @Log(title = "操作日志", logType = LogType.CLEAN)
//    @RequiresPermissions("system:operlog:remove")
//    @PostMapping("/clean")
//    @ResponseBody
//    public AjaxResult clean() {
//        operLogService.cleanOperLog();
//        return success();
//    }
}
