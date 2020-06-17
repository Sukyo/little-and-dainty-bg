package com.suk.blog.common.exception.user;

/**
 * @author suk
 * @version V1.0
 * @title: UserPasswordRetryLimitCountException
 * @package: com.suk.sys.exception.user
 * @description: 用户错误记数异常类
 * @date 2019/7/4 11:43
 */
public class UserPasswordRetryLimitCountException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitCountException(int retryLimitCount) {
        super("user.password.retry.limit.count", new Object[]{retryLimitCount});
    }
}
