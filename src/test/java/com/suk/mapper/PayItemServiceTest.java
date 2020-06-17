package com.suk.mapper;

import com.suk.blog.SukBlogApplication;
import com.suk.blog.entity.PayItem;
import com.suk.blog.service.IPayItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: Suk
 * @description: todo
 * @date:Created in 15:48 2020/6/14
 * @modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SukBlogApplication.class)
public class PayItemServiceTest {
    @Autowired
    private IPayItemService iPayItemService;

    @Test
    public void findAll() {
        List<PayItem> list = iPayItemService.list();
        list.stream().forEach(payitem -> {
            System.out.println(payitem);
        });
    }
}
