package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.Theme;

import java.util.List;

/**
 * 网站主题 服务层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface IThemeService extends IService<Theme> {

    /**
     * 分页查询
     *
     * @param iPage
     * @param theme
     * @return
     */

    IPage<Theme> pageList(IPage<Theme> iPage, Theme theme);

    /**
     * 不分页查询
     *
     * @param theme
     * @return
     */
    List<Theme> list(Theme theme);

    int useTheme(Integer id);

    Theme selectCurrent();
}
