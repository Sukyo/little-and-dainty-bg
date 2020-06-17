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
import com.suk.blog.entity.Love;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.ILoveService;
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
 * biz_love 信息操作处理
 *
 * @author suk
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/blog/love")
public class LoveController extends BaseController {
    private String prefix = "blog/love" ;

    @Autowired
    private ILoveService loveService;

    @RequiresPermissions("blog:love:view")
    @GetMapping()
    public String love() {
        return prefix + "/love" ;
    }

    /**
     * 查询biz_love列表
     */
    @RequiresPermissions("blog:love:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Love love) {
        IPage<Love> page = getPage();
            loveService.pageList(page, love);
        return getDataTable(page.getRecords(), page.getTotal());
    }


    /**
     * 导出biz_love列表
     */
    @RequiresPermissions("blog:love:export")
    @GetMapping("/export")
    @ResponseBody
    public void export(Love love,HttpServletRequest request, HttpServletResponse response) {
        List<Love> list = loveService.list(love);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "biz_love" ;
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, Love.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增biz_love
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add" ;
    }

    /**
     * 新增保存biz_love
     */
    @RequiresPermissions("blog:love:add")
    @Log(title = "biz_love", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Love love) {
        return toAjax(loveService.save(love));
    }

    /**
     * 修改biz_love
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Love love =loveService.getById(id);
        mmap.put("love", love);
        return prefix + "/edit" ;
    }

    /**
     * 修改保存biz_love
     */
    @RequiresPermissions("blog:love:edit")
    @Log(title = "biz_love", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Love love) {
        return toAjax(loveService.updateById(love));
    }

    /**
     * 删除biz_love
     */
    @RequiresPermissions("blog:love:remove")
    @Log(title = "biz_love", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(loveService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
