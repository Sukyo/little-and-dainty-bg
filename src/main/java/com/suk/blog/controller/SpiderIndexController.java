package com.suk.blog.controller;

import com.suk.blog.enums.ExitWayEnum;
import com.suk.blog.enums.Platform;
import com.suk.blog.service.ICategoryService;
import com.suk.blog.service.IConfigService;
import com.suk.blog.util.HunterConfigTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author suk
 * @version V1.0
 * @title: SpiderCrawlController
 * @package: com.suk.blog.controller
 * @description: 文章抓取主页
 * @date 2019/8/29 11:34
 */
@Controller
@RequestMapping(value = "/spider/crawl")
public class SpiderIndexController extends BaseController {

    @Autowired
    IConfigService configService;
    @Autowired
    ICategoryService categoryService;

    @GetMapping("/index")
    public ModelAndView remover(Model model) {
        model.addAttribute("exitWayList", ExitWayEnum.values());
        model.addAttribute("spiderConfig", HunterConfigTemplate.configTemplate.toJSONString());
        model.addAttribute("platforms", Platform.values());
        model.addAttribute("categorys", categoryService.list());
        return new ModelAndView("crawl");
    }


    @GetMapping("/system/main")
    public String main() {
        return "main";
    }
}
