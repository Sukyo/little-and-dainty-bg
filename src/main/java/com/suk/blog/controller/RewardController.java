package com.suk.blog.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.suk.blog.common.response.AjaxResult;
import com.suk.blog.entity.Customer;
import com.suk.blog.entity.vo.RewardVo;
import com.suk.blog.service.IRewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: Suk
 * @description: 打赏的前端控制器
 * @date:Created in 18:08 2020/6/14
 * @modified By:
 */
@RestController
@RequestMapping("/alipay")
public class RewardController {
    private static final Logger logger = LoggerFactory.getLogger(RewardController.class);

    //    @Autowired
//    IZyRechargeRecordService rechargeRecordService;
    @Autowired
    IRewardService iRewardService;

    /**
     * 你的私钥
     */
    @Value("${alipay.dmf.privateKey}")
    private String appPrivateKey;

    /**
     * 你的公钥
     */
    @Value("${alipay.dmf.publicKey}")
    private String appPublicKey;

    /**
     * 支付宝公钥
     */
    @Value("${alipay.zfb.publicKey}")
    private String zfbPublicKey;
    /**
     * 你的应用ID
     */
    @Value("${alipay.dmf.appId}")
    private String appId;

    /**
     * 获取支付宝二维码
     * @param customerPayMoney
     * @return
     */
    @PostMapping("/precreate/{customerPayMoney}")
    public AjaxResult getPayQrcode(@PathVariable("customerPayMoney") String customerPayMoney) {
        //生成订单号
        String orderNo = UUID.randomUUID().toString();
        //订单入库
        //---------------------------------------------
//        rechargeRecordService.save(rechargeRecord);
        //AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        //创建支付请求服务器,封装参数
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",//阿里网关
                appId,//appId
                appPrivateKey,//app私钥
                "json",//传输格式
                "GBK",//传输编码
                zfbPublicKey,//支付宝公钥
                "RSA2"//加密方式
        );
        //创建订单请求
        AlipayTradePrecreateRequest atpr = new AlipayTradePrecreateRequest();
        //分转换成元
        double customerPayMoney_yuan = Double.parseDouble(customerPayMoney) / 100;
        //封装订单详情
        atpr.setBizContent("{" +
                "\"out_trade_no\":\"" + orderNo + "\"," +
                "\"total_amount\":" + customerPayMoney_yuan + "," +
                "\"subject\":\"阿硕的博客|Suk 打赏UP\"" +
                "  }");
        //创建支付响应接收对象
        AlipayTradePrecreateResponse response = null;
        try {
            //执行支付
            response = alipayClient.execute(atpr);
        } catch (AlipayApiException e) {
            logger.error("请求二维码遇到未知错误");
            e.printStackTrace();
        }
        //判断是否请求成功
        if (!response.isSuccess()) {
            //如果失败则返回错误信息
            logger.error("生成二维码失败");
            return AjaxResult.error("生成二维码失败");
        }
        //如果请求成功就返回订单号和二维码
        Map<String, Object> result = new HashMap<>(16);
        result.put("id", orderNo);
        result.put("qrCode", response.getQrCode());
        return AjaxResult.success(result);
    }

    /**
     * 查询订单状态并记录打赏信息
     * @param rewardVo
     * @return
     */
    @PostMapping(value = "/query")
    public AjaxResult queryPayState(@RequestBody RewardVo rewardVo) {
        boolean payState = iRewardService.getPayState(rewardVo.getOrderNo(), appId, appPrivateKey, zfbPublicKey, rewardVo.getCustomer());
        return payState ? AjaxResult.success(1) : AjaxResult.error();
    }
}
