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
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.Category;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.ICategoryService;
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
 * 文章分类 信息操作处理
 *
 * @author suk
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/blog/category")
public class CategoryController extends BaseController {
    private String prefix = "blog/category";

    @Autowired
    private ICategoryService categoryService;

    @RequiresPermissions("blog:category:view")
    @GetMapping()
    public String category() {
        return prefix + "/category";
    }

    /**
     * 查询文章分类列表
     */
    @RequiresPermissions("blog:category:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Category category) {
        IPage<Category> page = getPage();
        categoryService.pageList(page, category);
        return getDataTable(page.getRecords(), page.getTotal());
    }

    @PostMapping("listAll")
    @ResponseBody
    public List<Category> loadCategory(boolean isFistLevel) {
        Category bizCategory = new Category();
        bizCategory.setStatus(Constants.STATUS_VALID);
        if (isFistLevel) {
            bizCategory.setPid(Constants.TOP_MENU_ID);
        }
        List<Category> categoryList = categoryService.selectCategories(bizCategory);
        return categoryList;
    }


    /**
     * 导出文章分类列表
     */
    @RequiresPermissions("blog:category:export")
    @GetMapping("/export")
    @ResponseBody
    public void export(Category category, HttpServletRequest request, HttpServletResponse response) {
        List<Category> list = categoryService.list(category);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "文章分类";
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, Category.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增文章分类
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存文章分类
     */
    @RequiresPermissions("blog:category:add")
    @Log(title = "文章分类", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Category category) {
        return toAjax(categoryService.save(category));
    }

    /**
     * 修改文章分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Category category = categoryService.selectById(id);
        mmap.put("category", category);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章分类
     */
    @RequiresPermissions("blog:category:edit")
    @Log(title = "文章分类", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Category category) {
        return toAjax(categoryService.updateById(category));
    }

    /**
     * 删除文章分类
     */
    @RequiresPermissions("blog:category:remove")
    @Log(title = "文章分类", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(categoryService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

}
