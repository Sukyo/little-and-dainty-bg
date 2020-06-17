package com.suk.blog.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String platform;

    private String title;

    private String author;

    private LocalDateTime releaseDate;

    private String content;

    private String tag;

    private String description;

    private String targetLink;


}
