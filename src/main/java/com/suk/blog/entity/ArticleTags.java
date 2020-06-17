package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.suk.blog.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 文章标签表 biz_article_tags
 * 
 * @author suk
 * @date 2019-09-27
 */
@Data
@TableName("biz_article_tags")
public class ArticleTags extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/** 标签表主键 */
	private Integer tagId;
	/** 文章ID */
	private Integer articleId;

	private Date createTime;

	private Date updateTime;
}
