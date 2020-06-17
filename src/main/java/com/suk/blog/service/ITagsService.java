package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.Tags;

import java.util.List;

/**
 * 书签 服务层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface ITagsService extends IService<Tags> {

    /**
     * 分页查询
     *
     * @param iPage
     * @param tags
     * @return
     */

    IPage<Tags> pageList(IPage<Tags> iPage, Tags tags);

    /**
     * 不分页查询
     *
     * @param tags
     * @return
     */
    List<Tags> list(Tags tags);
}
