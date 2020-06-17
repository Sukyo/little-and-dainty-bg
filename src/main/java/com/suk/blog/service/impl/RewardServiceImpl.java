package com.suk.blog.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.suk.blog.entity.Customer;
import com.suk.blog.mapper.RewardCustomerMapper;
import com.suk.blog.service.IRewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: Suk
 * @description: 打赏的service实现类
 * @date:Created in 1:37 2020/6/15
 * @modified By:
 */
@Service
public class RewardServiceImpl implements IRewardService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final String PAY_SUCCESS = "TRADE_SUCCESS";

    @Autowired
    private RewardCustomerMapper rewardCustomerMapper;

    @Override
    public boolean getPayState(String orderNo, String appId, String privateKey, String publicKey, Customer customer) {
        logger.info(">>>>>>>>>>查询订单支付状态,orderNo=[" + orderNo + "]<<<<<<<<<<<");
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",
                appId,
                privateKey,
                "json",
                "GBK",
                publicKey,
                "RSA2"
        );
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + orderNo + "\"" +
                "  }");
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response != null && response.isSuccess() && PAY_SUCCESS.equals(response.getTradeStatus())) {
            //打赏成功后将打赏人入库
            //传进来的金额单位是分
            customer.setCustomerPayMoney(customer.getCustomerPayMoney());
            //设置头像为qq头像
            customer.setCustomerFacePic("http://q1.qlogo.cn/g?b=qq&nk=" + customer.getCustomerFacePic() + "&s=640");
            customer.setCustomerRewardDate(new Date());
            int result = rewardCustomerMapper.insert(customer);
            logger.error(String.valueOf(result));
            //此处还可发送邮件通知用户支付成功
            return true;
        } else {
            return false;
        }
    }
}
