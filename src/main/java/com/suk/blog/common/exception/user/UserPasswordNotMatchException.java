package com.suk.blog.common.exception.user;

/**
 * @author suk
 * @version V1.0
 * @title: UserPasswordNotMatchException
 * @package: com.suk.sys.exception.user
 * @description: 用户密码不正确或不符合规范异常类
 * @date 2019/7/4 11:43
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
