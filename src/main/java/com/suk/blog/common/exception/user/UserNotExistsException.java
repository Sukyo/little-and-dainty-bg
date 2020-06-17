package com.suk.blog.common.exception.user;

/**
  * @author suk
  * @version V1.0
  * @title: UserNotExistsException
  * @package: com.suk.sys.exception.user
  * @description: 用户不存在异常类
  * @date 2019/7/4 11:43
  */
public class UserNotExistsException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException()
    {
        super("user.not.exists", null);
    }
}
