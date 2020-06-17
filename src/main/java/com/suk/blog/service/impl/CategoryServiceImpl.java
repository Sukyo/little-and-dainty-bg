package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.Category;
import com.suk.blog.mapper.CategoryMapper;
import com.suk.blog.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 文章分类 服务层实现
 *
 * @author suk
 * @date 2019-09-27
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    /**
     * 分页查询
     *
     * @param iPage
     * @param category
     * @return
     */
    @Override
    public IPage<Category> pageList(IPage<Category> iPage, Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<Category>();
        queryWrapper.like(StringUtils.isNoneBlank(category.getName()), Category::getName, category.getName())
                .ge(category.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(category.getParams().get(Constants.START_TIME).toString()), Category::getCreateTime, category.getParams().get(Constants.START_TIME))
                .le(category.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(category.getParams().get(Constants.END_TIME).toString()), Category::getCreateTime, category.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param category
     * @return
     */
    @Override
    public List<Category> list(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<Category>();
        queryWrapper.ge(category.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(category.getParams().get(Constants.START_TIME).toString()), Category::getCreateTime, category.getParams().get(Constants.START_TIME))
                .le(category.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(category.getParams().get(Constants.END_TIME).toString()), Category::getCreateTime, category.getParams().get(Constants.END_TIME));
        List<Category> list = list(queryWrapper);
        return list;
    }

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectCategories(Category bizCategory) {
        return categoryMapper.selectCategories(bizCategory);
    }

    @Override
    public Category selectById(Integer id) {
        return categoryMapper.selectById(id);
    }
}
