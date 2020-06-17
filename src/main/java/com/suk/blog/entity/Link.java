package com.suk.blog.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 友情链接表 biz_link
 *
 * @author suk
 * @date 2019-09-27
 */
@Data
@TableName("biz_link")
public class Link extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 链接名
     */
    @Excel(name = "链接名")
    private String name;
    /**
     * 链接地址
     */
    @Excel(name = "链接地址")
    private String url;
    /**
     * 链接介绍
     */
    @Excel(name = "链接介绍")
    private String description;
    /**
     * 友链图片地址
     */
    private String img;
    /**
     * 友链站长邮箱
     */
    @Excel(name = "友链站长邮箱")
    private String email;
    /**
     * 友链站长qq
     */
    @Excel(name = "友链站长qq")
    private String qq;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 1-管理员添加 2-自助申请
     */
    private Integer origin;

    /**
     * 备注
     */
    private String remark;

    private Date createTime;

    private Date updateTime;
}
