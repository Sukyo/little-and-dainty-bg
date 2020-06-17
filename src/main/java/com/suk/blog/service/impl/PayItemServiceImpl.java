package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.entity.PayItem;
import com.suk.blog.mapper.PayItemMapper;
import com.suk.blog.service.IPayItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Suk
 * @description: 打赏人的实现类
 * @date:Created in 15:20 2020/6/14
 * @modified By:
 */
@Service
public class PayItemServiceImpl extends ServiceImpl<PayItemMapper, PayItem> implements IPayItemService {
    private final Logger logger = LoggerFactory.getLogger(PayItemServiceImpl.class);

    @Override
    public List<PayItem> getPayItems() {
        return list();
    }
}
