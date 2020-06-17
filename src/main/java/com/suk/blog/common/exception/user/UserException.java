package com.suk.blog.common.exception.user;

import com.suk.blog.common.exception.base.BaseException;

/**
  * @author suk
  * @version V1.0
  * @title: UserException
  * @package: com.suk.sys.exception.user
  * @description: 用户信息异常类
  * @date 2019/7/4 11:43
  */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
