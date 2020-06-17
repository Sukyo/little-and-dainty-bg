package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 文章分类表 biz_category
 *
 * @author suk
 * @date 2019-09-27
 */
@Data
@TableName("biz_category")
public class Category extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**  */
    private Integer pid;
    /**
     * 文章类型名
     */
    private String name;
    /**
     * 类型介绍
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否可用
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private Category parent;
    @TableField(exist = false)
    private List<Category> nodes;
}
