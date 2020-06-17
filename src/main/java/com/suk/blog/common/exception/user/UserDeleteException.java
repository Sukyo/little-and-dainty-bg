package com.suk.blog.common.exception.user;

/**
  * @author suk
  * @version V1.0
  * @title: UserDeleteException
  * @package: com.suk.sys.exception.user
  * @description: 用户账号已被删除
  * @date 2019/7/4 11:43
  */
public class UserDeleteException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserDeleteException()
    {
        super("user.password.delete", null);
    }
}
