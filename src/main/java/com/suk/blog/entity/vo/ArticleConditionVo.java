package com.suk.blog.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleConditionVo extends BaseConditionVo {
    private Integer categoryId;
    private Integer tagId;
    private Integer status;
    private Boolean top;
    private Boolean recommended;
    private Boolean slider;
    private Boolean original;
    private Boolean random;
    private Boolean recentFlag;
    private Boolean sliderFlag;
    private List<Long> tagIds;
    private String keywords;

}

