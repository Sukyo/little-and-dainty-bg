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
 * 文章表 biz_article_copy
 *
 * @author suk
 * @date 2019-09-27
 */
@Data
@TableName("biz_article")
public class ArticleCopy extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 作者
     */
    private String author;
    /**
     * 文章封面图片
     */
    private String coverImage;
    /**
     * 文章专属二维码地址
     */
    private String qrcodePath;
    /**  */
    private Integer isMarkdown;
    /**
     * 文章内容
     */
    private String content;
    /**
     * markdown版的文章内容
     */
    private String contentMd;
    /**
     * 是否置顶
     */
    private Integer top;
    /**
     * 类型
     */
    private Integer categoryId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 是否推荐
     */
    private Integer recommended;
    /**
     * 是否轮播
     */
    private Integer slider;
    /**
     * 轮播图地址
     */
    private String sliderImg;
    /**
     * 是否原创
     */
    private Integer original;
    /**
     * 文章简介，最多200字
     */
    private String description;
    /**
     * 文章关键字，优化搜索
     */
    private String keywords;
    /**
     * 是否开启评论
     */
    private Integer comment;

    private Date createTime;

    private Date updateTime;
    @TableField(exist = false)
    private Integer lookCount = 0;
    @TableField(exist = false)
    private Integer commentCount = 0;
    @TableField(exist = false)
    private Integer loveCount = 0;
    @TableField(exist = false)
    private Integer newFlag = 0;
    @TableField(exist = false)
    List<Tags> tags;
    @TableField(exist = false)
    Category category;
}


