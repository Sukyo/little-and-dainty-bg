package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * biz_love表 biz_love
 *
 * @author suk
 * @date 2019-09-27
 */
@Data
@TableName("biz_love")
public class Love extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 业务ID
     */
    private Integer bizId;
    /**
     * 业务类型：1.文章，2.评论
     */
    private Integer bizType;
    /**
     * 已登录用户ID
     */
    private String userId;
    /**
     * 用户IP
     */
    private String userIp;
    /**
     * 状态
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;
}
