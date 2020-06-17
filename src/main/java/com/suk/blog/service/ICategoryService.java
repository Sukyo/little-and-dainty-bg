package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.Category;

import java.util.List;

/**
 * 文章分类 服务层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 分页查询
     *
     * @param iPage
     * @param category
     * @return
     */

    IPage<Category> pageList(IPage<Category> iPage, Category category);

    /**
     * 不分页查询
     *
     * @param category
     * @return
     */
    List<Category> list(Category category);

    List<Category> selectCategories(Category category);

    Category selectById(Integer id);
}
