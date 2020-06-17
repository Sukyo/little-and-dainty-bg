package com.suk.blog.enums;

/**
 * @author suk
 * @version V1.0
 * @title: UserStatus
 * @package: com.suk.sys.common.enums
 * @description: 用户状态
 * @date 2019/7/4 11:50
 */
public enum UserStatus {
    OK("0", "正常"), DISABLE("1", "停用"), DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
