package com.suk.blog.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 网站主题表 biz_theme
 *
 * @author suk
 * @date 2019-09-27
 */
@Data
@TableName("biz_theme")
public class Theme extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 主题名（路径前缀）
     */
    @Excel(name = "主题名")
    private String name;
    /**
     * 主题描述
     */
    @Excel(name = "主题描述")
    private String description;
    /**
     * 主题预览图url
     */
    @Excel(name = "主题预览图url")
    private String img;
    /**
     * 0-未启用 1-启用
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

}
