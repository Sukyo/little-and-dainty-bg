package com.suk.blog.common.exception.user;

/**
  * @author suk
  * @version V1.0
  * @title: RoleBlockedException
  * @package: com.suk.sys.exception.user
  * @description: 角色锁定异常类
  * @date 2019/7/4 11:42
  */
public class RoleBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public RoleBlockedException()
    {
        super("role.blocked", null);
    }
}
