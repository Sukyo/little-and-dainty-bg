package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 文章评论表 biz_comment
 *
 * @author suk
 * @date 2019-09-27
 */
@Data
@TableName("biz_comment")
public class Comment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 被评论的文章或者页面的ID(-1:留言板)
     */
    private Integer sid;
    /**
     * 评论人的ID
     */
    private String userId;
    /**
     * 父级评论的id
     */
    private Integer pid;
    /**
     * 评论人的QQ（未登录用户）
     */
    private String qq;
    /**
     * 评论人的昵称（未登录用户）
     */
    private String nickname;
    /**
     * 评论人的头像地址
     */
    private String avatar;
    /**
     * 评论人的邮箱地址（未登录用户）
     */
    private String email;
    /**
     * 评论人的网站地址（未登录用户）
     */
    private String url;
    /**
     * 评论的状态
     */
    private Integer status;
    /**
     * 评论时的ip
     */
    private String ip;
    /**
     * 经度
     */
    private String lng;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 评论时的地址
     */
    private String address;
    /**
     * 评论时的系统类型
     */
    private String os;
    /**
     * 评论时的系统的简称
     */
    private String osShortName;
    /**
     * 评论时的浏览器类型
     */
    private String browser;
    /**
     * 评论时的浏览器的简称
     */
    private String browserShortName;
    /**
     * 评论的内容
     */
    private String content;

    private String remark;
    /**
     * 支持（赞）
     */
    private Integer support;
    /**
     * 反对（踩）
     */
    private Integer oppose;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    Comment parent;
    @TableField(exist = false)
    ArticleCopy article;
    @TableField(exist = false)
    private Integer loveCount;
}
