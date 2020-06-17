package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author: Suk
 * @description: 打赏人
 * @date:Created in 14:50 2020/6/14
 * @modified By:
 */
@Data
@TableName("pay_customer")
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;
    //打赏人名字
    private String customerName;
    //打赏人岗位
    private String customerJobType;
    //打赏金额 单位:分
    private Long customerPayMoney;
    //打赏人qq头像
    private String customerFacePic;
    //打赏日期
    private Date customerRewardDate;
}
