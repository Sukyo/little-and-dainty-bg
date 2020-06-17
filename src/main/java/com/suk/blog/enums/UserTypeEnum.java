package com.suk.blog.enums;

public enum UserTypeEnum {
    /**
     * 网站用户
     */
    WEBSITE_USER(0),
    /**
     * 系统用户
     */
    SYSTEM_USER(1);

    private Integer value;

    UserTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
