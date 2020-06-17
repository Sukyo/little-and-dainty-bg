package com.suk.blog.entity.vo;

import com.suk.blog.entity.Customer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Suk
 * @description: 打赏状态查询的vo
 * @date:Created in 2:40 2020/6/15
 * @modified By:
 */
@Data
public class RewardVo implements Serializable {
    private static final long serialVersionUID = 1L;
    //订单号
    private String orderNo;
    //打赏者
    private Customer customer;
}
