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
import com.suk.blog.entity.Tags;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.ITagsService;
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
 * 书签 信息操作处理
 *
 * @author suk
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/blog/tags")
public class TagsController extends BaseController {
    private String prefix = "blog/tags";

    @Autowired
    private ITagsService tagsService;

    @RequiresPermissions("blog:tags:view")
    @GetMapping()
    public String tags() {
        return prefix + "/tags";
    }

    /**
     * 查询书签列表
     */
    @RequiresPermissions("blog:tags:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Tags tags) {
        IPage<Tags> page = getPage();
        tagsService.pageList(page, tags);
        return getDataTable(page.getRecords(), page.getTotal());
    }


    /**
     * 导出书签列表
     */
    @RequiresPermissions("blog:tags:export")
    @GetMapping("/export")
    @ResponseBody
    public void export(Tags tags, HttpServletRequest request, HttpServletResponse response) {
        List<Tags> list = tagsService.list(tags);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "书签";
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, Tags.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增书签
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存书签
     */
    @RequiresPermissions("blog:tags:add")
    @Log(title = "书签", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Tags tags) {
        return toAjax(tagsService.save(tags));
    }

    /**
     * 修改书签
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Tags tags = tagsService.getById(id);
        mmap.put("tags", tags);
        return prefix + "/edit";
    }

    /**
     * 修改保存书签
     */
    @RequiresPermissions("blog:tags:edit")
    @Log(title = "书签", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Tags tags) {
        return toAjax(tagsService.updateById(tags));
    }

    /**
     * 删除书签
     */
    @RequiresPermissions("blog:tags:remove")
    @Log(title = "书签", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(tagsService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
