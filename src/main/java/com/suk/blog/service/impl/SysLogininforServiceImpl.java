package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.SysLogininfor;
import com.suk.blog.mapper.SysLogininforMapper;
import com.suk.blog.service.ISysLogininforService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements ISysLogininforService {

    /**
     * 分页查询
     *
     * @param iPage
     * @param logininfor
     * @return
     */
    @Override
    public IPage<SysLogininfor> pageList(IPage<SysLogininfor> iPage, SysLogininfor logininfor) {
        LambdaQueryWrapper<SysLogininfor> queryWrapper = new LambdaQueryWrapper<SysLogininfor>();
        queryWrapper.like(StringUtils.isNoneBlank(logininfor.getIpaddr()), SysLogininfor::getIpaddr, logininfor.getIpaddr())
                .like(StringUtils.isNoneBlank(logininfor.getLoginName()), SysLogininfor::getLoginName, logininfor.getLoginName())
                .eq(StringUtils.isNoneBlank(logininfor.getUserType()), SysLogininfor::getUserType, logininfor.getUserType())
                .eq(StringUtils.isNoneBlank(logininfor.getStatus()), SysLogininfor::getStatus, logininfor.getStatus())
                .ge(logininfor.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(logininfor.getParams().get(Constants.START_TIME).toString()), SysLogininfor::getLoginTime, logininfor.getParams().get(Constants.START_TIME))
                .le(logininfor.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(logininfor.getParams().get(Constants.END_TIME).toString()), SysLogininfor::getLoginTime, logininfor.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param logininfor
     * @return
     */
    @Override
    public List<SysLogininfor> list(SysLogininfor logininfor) {
        LambdaQueryWrapper<SysLogininfor> queryWrapper = new LambdaQueryWrapper<SysLogininfor>();
        queryWrapper.like(StringUtils.isNoneBlank(logininfor.getIpaddr()), SysLogininfor::getIpaddr, logininfor.getIpaddr())
                .like(StringUtils.isNoneBlank(logininfor.getLoginName()), SysLogininfor::getLoginName, logininfor.getLoginName())
                .eq(StringUtils.isNoneBlank(logininfor.getUserType()), SysLogininfor::getUserType, logininfor.getUserType())
                .eq(StringUtils.isNoneBlank(logininfor.getStatus()), SysLogininfor::getStatus, logininfor.getStatus())
                .ge(logininfor.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(logininfor.getParams().get(Constants.START_TIME).toString()), SysLogininfor::getLoginTime, logininfor.getParams().get(Constants.START_TIME))
                .le(logininfor.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(logininfor.getParams().get(Constants.END_TIME).toString()), SysLogininfor::getLoginTime, logininfor.getParams().get(Constants.END_TIME));
        List<SysLogininfor> list = list(queryWrapper);
        return list;
    }
}
