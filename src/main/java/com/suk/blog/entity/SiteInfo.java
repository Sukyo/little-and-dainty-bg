package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 网站配置表 biz_site_info
 *
 * @author suk
 * @date 2019-09-27
 */
@Data
@TableName("biz_site_info")
public class SiteInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**  */
    private String siteName;
    /**  */
    private String siteDesc;
    /**  */
    private String sitePic;

    private Date createTime;

    private Date updateTime;

}
