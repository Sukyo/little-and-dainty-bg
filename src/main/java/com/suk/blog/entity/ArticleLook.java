package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 文章浏览记录表 biz_article_look
 *
 * @author suk
 * @date 2019-09-27
 */
@Data
@TableName("biz_article_look")
public class ArticleLook extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 文章ID
     */
    private Integer articleId;
    /**
     * 已登录用户ID
     */
    private String userId;
    /**
     * 用户IP
     */
    private String userIp;
    /**
     * 浏览时间
     */
    private Date lookTime;

    private Date createTime;

    private Date updateTime;
}
