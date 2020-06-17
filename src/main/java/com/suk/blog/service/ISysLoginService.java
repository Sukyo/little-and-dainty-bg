package com.suk.blog.service;

import com.suk.blog.entity.SysUser;

/**
 * @author suk
 * @version V1.0
 * @title: ISysLoginService
 * @package: com.suk.sys.service
 * @description: TODO
 * @date 2019/7/4 10:38
 */
public interface ISysLoginService {
    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    SysUser login(String username, String password);
}
