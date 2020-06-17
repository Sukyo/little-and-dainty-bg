package com.suk.blog.common.exception.user;

/**
  * @author suk
  * @version V1.0
  * @title: CaptchaException
  * @package: com.suk.sys.exception.user
  * @description: 验证码错误异常类
  * @date 2019/7/4 11:42
  */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
