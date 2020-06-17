package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.Theme;
import com.suk.blog.mapper.ThemeMapper;
import com.suk.blog.service.IThemeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 网站主题 服务层实现
 *
 * @author suk
 * @date 2019-09-27
 */
@Service
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements IThemeService {
    /**
     * 分页查询
     *
     * @param iPage
     * @param theme
     * @return
     */
    @Override
    public IPage<Theme> pageList(IPage<Theme> iPage, Theme theme) {
        LambdaQueryWrapper<Theme> queryWrapper = new LambdaQueryWrapper<Theme>();
        queryWrapper.like(StringUtils.isNoneBlank(theme.getName()), Theme::getName, theme.getName())
                .ge(theme.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(theme.getParams().get(Constants.START_TIME).toString()), Theme::getCreateTime, theme.getParams().get(Constants.START_TIME))
                .le(theme.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(theme.getParams().get(Constants.END_TIME).toString()), Theme::getCreateTime, theme.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param theme
     * @return
     */
    @Override
    public List<Theme> list(Theme theme) {
        LambdaQueryWrapper<Theme> queryWrapper = new LambdaQueryWrapper<Theme>();
        queryWrapper.like(StringUtils.isNoneBlank(theme.getName()), Theme::getName, theme.getName())
                .ge(theme.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(theme.getParams().get(Constants.START_TIME).toString()), Theme::getCreateTime, theme.getParams().get(Constants.START_TIME))
                .le(theme.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(theme.getParams().get(Constants.END_TIME).toString()), Theme::getCreateTime, theme.getParams().get(Constants.END_TIME));
        List<Theme> list = list(queryWrapper);
        return list;
    }

    @Autowired
    private ThemeMapper themeMapper;

    @Override
    public int useTheme(Integer id) {
        Theme theme = new Theme();
        theme.setStatus(Constants.STATUS_INVALID);
        themeMapper.update(theme, new LambdaQueryWrapper<Theme>().eq(Theme::getStatus, Constants.STATUS_VALID));
        theme.setId(id);
        theme.setStatus(Constants.STATUS_VALID);
        return themeMapper.updateById(theme);
    }

    @Override
    public Theme selectCurrent() {
        return themeMapper.selectOne(new LambdaQueryWrapper<Theme>().eq(Theme::getStatus, Constants.STATUS_VALID));
    }
}
