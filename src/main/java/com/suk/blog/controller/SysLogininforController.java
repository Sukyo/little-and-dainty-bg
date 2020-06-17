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
import com.suk.blog.entity.SysLogininfor;
import com.suk.blog.enums.LogType;
import com.suk.blog.enums.UserTypeEnum;
import com.suk.blog.service.ISysLogininforService;
import com.suk.blog.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统访问记录 前端控制器
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Controller
@RequestMapping("/system/logininfor")
public class SysLogininforController extends BaseController {
    private String prefix = "system/logininfor";

    @Autowired
    private ISysLogininforService logininforService;

    @RequiresPermissions("system:logininfor:view")
    @GetMapping()
    public String logininfor() {
        return prefix + "/logininfor";
    }

    @RequiresPermissions("system:logininfor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysLogininfor logininfor) {
        logininfor.setUserType(String.valueOf(UserTypeEnum.SYSTEM_USER.getValue()));
        IPage<SysLogininfor> page = getPage();
        logininforService.pageList(page, logininfor);
        return getDataTable(page.getRecords(), page.getTotal());
    }


    @Log(title = "登录日志导出", logType = LogType.EXPORT)
    @RequiresPermissions("system:user:export")
    @GetMapping("/export")
    public void export(SysLogininfor logininfor, HttpServletRequest request, HttpServletResponse response) {
        List<SysLogininfor> list = logininforService.list(logininfor);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "登录日志";
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, SysLogininfor.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    @RequiresPermissions("system:logininfor:remove")
    @Log(title = "登陆日志", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(logininforService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
