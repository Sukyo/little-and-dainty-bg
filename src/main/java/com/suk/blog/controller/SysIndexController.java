package com.suk.blog.controller;

import com.suk.blog.entity.SysMenu;
import com.suk.blog.entity.SysUser;
import com.suk.blog.service.ISysMenuService;
import com.suk.blog.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author suk
 * @version V1.0
 * @title: SysIndexController
 * @package: com.suk.share.controller
 * @description: 首页
 * @date 2019/7/4 17:13
 */
@Controller
public class SysIndexController extends BaseController {
    @Autowired
    private ISysMenuService menuService;

    @GetMapping("/index")
    public String index(ModelMap mmap) {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        return "index";
    }


    @GetMapping("/system/main")
    public String main() {
        return "main";
    }
}
