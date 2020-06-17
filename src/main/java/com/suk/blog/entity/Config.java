package com.suk.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author suk
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spider_config")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String platform;

    private String domain;

    private String titleRegex;

    private String authorRegex;

    private String releaseDateRegex;

    private String contentRegex;

    private String tagRegex;

    private String descriptionRegex;

    private String targetLinksRegex;

    private String header;

    private String entryUrls;


}
