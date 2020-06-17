package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.Link;

import java.util.List;

/**
 * 友情链接 服务层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface ILinkService extends IService<Link> {

    /**
     * 分页查询
     *
     * @param iPage
     * @param link
     * @return
     */

    IPage<Link> pageList(IPage<Link> iPage, Link link);

    /**
     * 不分页查询
     *
     * @param link
     * @return
     */
    List<Link> list(Link link);
}
