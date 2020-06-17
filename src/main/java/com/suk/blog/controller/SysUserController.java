package com.suk.blog.controller;


import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.suk.blog.aspectj.annotation.Log;
import com.suk.blog.common.response.AjaxResult;
import com.suk.blog.common.response.TableDataInfo;
import com.suk.blog.constant.UserConstants;
import com.suk.blog.entity.SysRole;
import com.suk.blog.entity.SysUser;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.ISysRoleService;
import com.suk.blog.service.ISysUserService;
import com.suk.blog.service.impl.SysPasswordService;
import com.suk.blog.util.DateUtil;
import com.suk.blog.util.ShiroUtils;
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
 * 用户信息表 前端控制器
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user";
    }

    @Log(title = "用户查询", logType = LogType.SELECT)
    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser user) {
        IPage<SysUser> page = getPage();
        userService.pageList(page, user);
        return getDataTable(page.getRecords(), page.getSize());
    }

    @Log(title = "用户管理", logType = LogType.EXPORT)
    @RequiresPermissions("system:user:export")
    @GetMapping("/export")
    public void export(SysUser user, HttpServletRequest request, HttpServletResponse response) {
        List<SysUser> list = userService.list(user);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "用户信息";
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, SysUser.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        List<SysRole> roleList = roleService.list(new LambdaQueryWrapper<SysRole>().eq(SysRole::getDelFlag, 0));
        mmap.put("roles", roleList);
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysUser user) {
        if (user.getId() != null && SysUser.isAdmin(ShiroUtils.getSysUser().getUserType())) {
            return error("不允许修改超级管理员用户");
        }
        String nameUnique = userService.checkLoginNameUnique(user.getUserName());
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(nameUnique)) {
            return error("保存用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getUserName(), user.getPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        user.setCreateTime(DateUtil.getNowTimestamp());
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.getById(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysUser user) {
        if (user.getId() != null && SysUser.isAdmin(ShiroUtils.getSysUser().getUserType())) {
            return error("不允许修改超级管理员用户");
        }
        user.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(userService.updateUser(user));
    }


    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", logType = LogType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("user", userService.getById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", logType = LogType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user) {
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getUserName(), user.getPassword(), user.getSalt()));
        return toAjax(userService.updateById(user));
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            if (StringUtils.isNoneBlank(ids)) {
                String[] split = ids.split(",");
                return toAjax(userService.batchDeleteUser(Arrays.asList(split)));
            }
        } catch (Exception e) {
            return error(e.getMessage());
        }
        return error();
    }


    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user) {
        String nameUnique = userService.checkLoginNameUnique(user.getUserName());
        return nameUnique;
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user) {
        String phoneUnique = userService.checkPhoneUnique(user);
        return phoneUnique;
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user) {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", logType = LogType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysUser user) {
        if (SysUser.isAdmin(ShiroUtils.getSysUser().getUserType())) {
            return error("不允许修改超级管理员用户");
        }
        return toAjax(userService.updateUser(user));
    }
}
