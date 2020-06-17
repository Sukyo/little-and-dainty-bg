package com.suk.blog.controller;

import com.suk.blog.common.response.AjaxResult;
import com.suk.blog.service.RemoverService;
import com.suk.blog.util.DateUtil;
import me.zhyd.hunter.config.HunterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author suk
 * @version V1.0
 * @title: CrawlController
 * @package: com.suk.spider.controller
 * @description: 文章抓取控制器
 * @date 2019/9/4 10:56
 */
@RestController
@RequestMapping("/remover")
public class CrawlController extends BaseController {

    @Autowired
    private RemoverService removerService;

    @PostMapping("/run")
    @ResponseBody
    public void run(Integer categoryId, HunterConfig config, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        removerService.run(categoryId, config, writer);
    }


    @PostMapping("/stop")
    @ResponseBody
    public AjaxResult stop() {
        try {
            removerService.stop();
        } catch (Exception e) {
            return error(e.getMessage());
        }
        return success("程序已停止运行，当前时间 " + DateUtil.date2string(new Date(), DateUtil.DATE_TIME_FORMAT));
    }

    @PostMapping("/single")
    @ResponseBody
    public void single(Integer categoryId, String[] url, boolean convertImg, HttpServletResponse response) throws IOException {
        removerService.crawlSingle(categoryId, url, convertImg, response.getWriter());
    }

}
