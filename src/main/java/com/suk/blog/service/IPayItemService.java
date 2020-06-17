package com.suk.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.PayItem;

import java.util.List;

/**
 * @author: Suk
 * @description: 打赏service
 * @date:Created in 14:48 2020/6/14
 * @modified By:
 */
public interface IPayItemService extends IService<PayItem> {
    /**
     * 获取所有打赏项
     * @return List<PayItem>
     */
    List<PayItem> getPayItems();
}
