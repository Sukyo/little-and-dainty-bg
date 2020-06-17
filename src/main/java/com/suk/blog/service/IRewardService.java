package com.suk.blog.service;

import com.alipay.api.AlipayApiException;
import com.suk.blog.entity.Customer;

/**
 * @author: Suk
 * @description: 打赏的service
 * @date:Created in 1:35 2020/6/15
 * @modified By:
 */
public interface IRewardService {
    /**
     * 查询订单支付状态并更新
     *
     * @param orderNo    订单号
     * @param appId
     * @param privateKey
     * @param publicKey
     * @return
     */
    boolean getPayState(String orderNo, String appId, String privateKey, String publicKey, Customer customer);
}
