package com.suk.blog.common.exception.user;

/**
  * @author suk
  * @version V1.0
  * @title: UserBlockedException
  * @package: com.suk.sys.exception.user
  * @description: 用户锁定异常类
  * @date 2019/7/4 11:42
  */
public class UserBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserBlockedException()
    {
        super("user.blocked", null);
    }
}
