package com.suk.blog.common.exception.user;

/**
 * @author suk
 * @version V1.0
 * @title: UserPasswordRetryLimitExceedException
 * @package: com.suk.sys.exception.user
 * @description: 用户错误最大次数异常类
 * @date 2019/7/4 11:43
 */
public class UserPasswordRetryLimitExceedException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitExceedException(int retryLimitCount) {
        super("user.password.retry.limit.exceed", new Object[]{retryLimitCount});
    }
}
