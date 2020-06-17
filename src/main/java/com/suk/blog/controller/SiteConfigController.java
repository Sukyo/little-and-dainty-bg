package com.suk.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.suk.blog.aspectj.annotation.Log;
import com.suk.blog.common.response.AjaxResult;
import com.suk.blog.entity.SiteConfig;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.ISiteConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置 信息操作处理
 *
 * @author suk
 * @date 2019-09-28
 */
@Controller
@RequestMapping("/blog/siteConfig")
public class SiteConfigController extends BaseController {
    private String prefix = "blog/siteConfig";

    @Autowired
    private ISiteConfigService siteConfigService;

    @RequiresPermissions("blog:siteConfig:view")
    @GetMapping()
    public String siteConfig(SiteConfig siteConfig, Model model) {
        List<SiteConfig> list = siteConfigService.list();
        Map<String, String> map = new HashMap<String, String>(list.size());
        for (SiteConfig config : list) {
            map.put(config.getSysKey(), config.getSysValue());
        }
        model.addAttribute("siteinfo", map);
        return prefix + "/edit";
    }

    /**
     * 修改保存系统配置
     */
    @RequiresPermissions("blog:siteConfig:edit")
    @Log(title = "系统配置", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestParam Map<String, String> map) {
        for (String key : map.keySet()) {
            SiteConfig siteConfig = new SiteConfig();
            siteConfig.setSysKey(key);
            siteConfig.setSysValue(map.get(key));
            siteConfigService.update(siteConfig, new LambdaQueryWrapper<SiteConfig>().eq(SiteConfig::getSysKey, key));
        }
        return success();
    }

}
