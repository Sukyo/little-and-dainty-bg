package com.suk.blog.controller;

import com.suk.blog.entity.PayItem;
import com.suk.blog.service.IPayItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: Suk
 * @description: 打赏的前端控制器
 * @date:Created in 1:11 2020/6/14
 * @modified By:
 */
@Controller
public class PayItemController {

    private final String PREFIX = "/blog/front";

    @Autowired
    private IPayItemService iPayItemService;

    /**
     * 返回打赏项弹窗
     * @return
     */
    @RequestMapping(PREFIX + "/reward")
    public ModelAndView toReward() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("payItems", iPayItemService.getPayItems());
        mv.setViewName("blog/front/theme/zblog/reward");
        mv.setStatus(HttpStatus.OK);
        return mv;
    }
}
