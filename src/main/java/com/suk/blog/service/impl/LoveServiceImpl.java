package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.Love;
import com.suk.blog.mapper.LoveMapper;
import com.suk.blog.service.ILoveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * biz_love 服务层实现
 *
 * @author suk
 * @date 2019-09-27
 */
@Service
public class LoveServiceImpl extends ServiceImpl<LoveMapper, Love> implements ILoveService {
    @Autowired
    LoveMapper loveMapper;

    /**
     * 分页查询
     *
     * @param iPage
     * @param love
     * @return
     */
    @Override
    public IPage<Love> pageList(IPage<Love> iPage, Love love) {
        LambdaQueryWrapper<Love> queryWrapper = new LambdaQueryWrapper<Love>();
        queryWrapper.ge(love.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(love.getParams().get(Constants.START_TIME).toString()), Love::getCreateTime, love.getParams().get(Constants.START_TIME))
                .le(love.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(love.getParams().get(Constants.END_TIME).toString()), Love::getCreateTime, love.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param love
     * @return
     */
    @Override
    public List<Love> list(Love love) {
        LambdaQueryWrapper<Love> queryWrapper = new LambdaQueryWrapper<Love>();
        queryWrapper.ge(love.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(love.getParams().get(Constants.START_TIME).toString()), Love::getCreateTime, love.getParams().get(Constants.START_TIME))
                .le(love.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(love.getParams().get(Constants.END_TIME).toString()), Love::getCreateTime, love.getParams().get(Constants.END_TIME));
        List<Love> list = list(queryWrapper);
        return list;
    }

    @Override
    public Love checkLove(Integer bizId, String userIp) {
        LambdaQueryWrapper<Love> lambdaQueryWrapper = new LambdaQueryWrapper<Love>()
                .eq(Love::getBizId, bizId)
                .eq(Love::getUserIp, userIp);
        return loveMapper.selectOne(lambdaQueryWrapper);
    }
}
