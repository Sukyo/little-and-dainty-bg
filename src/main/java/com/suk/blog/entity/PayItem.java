package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author: Suk
 * @description: 打赏金额和描述
 * @date:Created in 15:02 2020/6/14
 * @modified By:
 */
@Data
@TableName("pay_items")
public class PayItem extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @TableId(value = "pid",type = IdType.AUTO)
    private Integer pid;
    //打赏金额数
    private Long payMoney;
    //打赏金额描述
    private String payDescription;
}
