package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 书签表 biz_tags
 *
 * @author suk
 * @date 2019-09-27
 */
@Data
@TableName("biz_tags")
public class Tags extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 书签名
     */
    private String name;
    /**
     * 描述
     */
    private String description;

    private Date createTime;

    private Date updateTime;
}
