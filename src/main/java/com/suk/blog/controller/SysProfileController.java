package com.suk.blog.controller;

import com.suk.blog.aspectj.annotation.Log;
import com.suk.blog.common.response.AjaxResult;
import com.suk.blog.entity.SysUser;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.ISysUserService;
import com.suk.blog.service.impl.SysPasswordService;
import com.suk.blog.util.SftpProperties;
import com.suk.blog.util.SftpTool;
import com.suk.blog.util.ShiroUtils;
import com.suk.blog.util.UUIDUitl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 个人信息 业务处理
 */
@Controller
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {
    @Value("${sftp.client.host}")
    private String host;
    @Value("${sftp.client.port}")
    private Integer port;
    @Value("${sftp.client.username}")
    private String username;
    @Value("${sftp.client.password}")
    private String password;
    @Value("${sftp.client.root}")
    private String root;
    @Value("${sftp.client.domain}")
    private String domain;

    private static final Logger log = LoggerFactory.getLogger(SysProfileController.class);

    private String prefix = "system/user/profile";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    /**
     * 个人信息
     */
    @GetMapping()
    public String profile(ModelMap mmap) {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("user", user);
        mmap.put("roleGroup", userService.selectUserRoleGroup(user.getId().longValue()));
        mmap.put("postGroup", userService.selectUserPostGroup(user.getId().longValue()));
        return prefix + "/profile";
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        SysUser user = ShiroUtils.getSysUser();
        if (passwordService.matches(user, password)) {
            return true;
        }
        return false;
    }

    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap mmap) {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("user", userService.getById(user.getId()));
        return prefix + "/resetPwd";
    }

    @Log(title = "重置密码", logType = LogType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(String oldPassword, String newPassword) {
        SysUser user = ShiroUtils.getSysUser();
        if (StringUtils.isNotEmpty(newPassword) && passwordService.matches(user, oldPassword)) {
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getUserName(), newPassword, user.getSalt()));
            if (userService.updateById(user)) {
                ShiroUtils.setSysUser(userService.getById(user.getId()));
                return success();
            }
            return error();
        } else {
            return error("修改密码失败，旧密码错误");
        }
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit")
    public String edit(ModelMap mmap) {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("user", userService.getById(user.getId()));
        return prefix + "/edit";
    }

    /**
     * 修改头像
     */
    @GetMapping("/avatar")
    public String avatar(ModelMap mmap) {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("user", userService.getById(user.getId()));
        return prefix + "/avatar";
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", logType = LogType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(SysUser user) {
        SysUser currentUser = ShiroUtils.getSysUser();
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
        currentUser.setTelephone(user.getTelephone());
        currentUser.setSex(user.getSex());
        if (userService.updateById(currentUser)) {
            ShiroUtils.setSysUser(userService.getById(currentUser.getId()));
            return success();
        }
        return error();
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息", logType = LogType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file) {
        SysUser currentUser = ShiroUtils.getSysUser();
        try {
            if (!file.isEmpty()) {
                SftpProperties sftpProperties = new SftpProperties(host, port, username, password, root);
                String filename = file.getOriginalFilename();
                int lastIndexof = filename.lastIndexOf(".");
                String suffix = "";
                if (lastIndexof != -1) {
                    suffix = filename.substring(lastIndexof, filename.length());
                }
                String uuid = UUIDUitl.getUUID() + suffix;
                log.info("图片文件名为:"+uuid+"文件名后缀为:"+suffix+",lastindexof:"+lastIndexof+",原文件名:"+filename);
                SftpTool.uploadFile(sftpProperties, uuid, file.getInputStream());
                String url = domain + uuid;
                currentUser.setAvatar(url);
                if (userService.updateById(currentUser)) {
                    ShiroUtils.setSysUser(userService.getById(currentUser.getId()));
                    return success();
                }
            }
            return error();
        } catch (Exception e) {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }
}
