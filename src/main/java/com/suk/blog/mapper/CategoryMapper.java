package com.suk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suk.blog.entity.Category;

import java.util.List;

/**
 * 文章分类 数据层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> selectCategories(Category category);

    Category selectById(Integer id);

}