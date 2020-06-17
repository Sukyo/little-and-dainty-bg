package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.suk.blog.entity.base.BaseEntity;

/**
 * 系统配置表 biz_site_config
 * 
 * @author suk
 * @date 2019-09-28
 */
@Data
@TableName("biz_site_config")
public class SiteConfig extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/** key */
	private String sysKey;
	/** value */
	private String sysValue;
	/** 状态   0：隐藏   1：显示 */
	private Integer status;

}
