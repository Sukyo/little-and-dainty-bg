package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.SysOperLog;
import com.suk.blog.mapper.SysOperLogMapper;
import com.suk.blog.service.ISysOperLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {

    /**
     * 分页查询
     *
     * @param iPage
     * @param operLog
     * @return
     */
    @Override
    public IPage<SysOperLog> pageList(IPage<SysOperLog> iPage, SysOperLog operLog) {
        LambdaQueryWrapper<SysOperLog> queryWrapper = new LambdaQueryWrapper<SysOperLog>();
        queryWrapper.like(StringUtils.isNoneBlank(operLog.getTitle()), SysOperLog::getTitle, operLog.getTitle())
                .like(StringUtils.isNoneBlank(operLog.getOperName()), SysOperLog::getOperName, operLog.getOperName())
                .eq(operLog.getStatus() != null, SysOperLog::getStatus, operLog.getStatus())
                .eq(operLog.getBusinessType() != null, SysOperLog::getBusinessType, operLog.getBusinessType())
                .in(operLog.getBusinessTypes() != null, SysOperLog::getBusinessType, operLog.getBusinessTypes())
                .ge(operLog.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(operLog.getParams().get(Constants.START_TIME).toString()), SysOperLog::getOperTime, operLog.getParams().get(Constants.START_TIME))
                .le(operLog.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(operLog.getParams().get(Constants.END_TIME).toString()), SysOperLog::getOperTime, operLog.getParams().get(Constants.END_TIME));
        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param operLog
     * @return
     */
    @Override
    public List<SysOperLog> list(SysOperLog operLog) {
        LambdaQueryWrapper<SysOperLog> queryWrapper = new LambdaQueryWrapper<SysOperLog>();
        queryWrapper.like(StringUtils.isNoneBlank(operLog.getTitle()), SysOperLog::getTitle, operLog.getTitle())
                .like(StringUtils.isNoneBlank(operLog.getOperName()), SysOperLog::getOperName, operLog.getOperName())
                .eq(operLog.getStatus() != null, SysOperLog::getStatus, operLog.getStatus())
                .eq(operLog.getBusinessType() != null, SysOperLog::getBusinessType, operLog.getBusinessType())
                .in(operLog.getBusinessTypes() != null, SysOperLog::getBusinessType, operLog.getBusinessTypes())
                .ge(operLog.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(operLog.getParams().get(Constants.START_TIME).toString()), SysOperLog::getOperTime, operLog.getParams().get(Constants.START_TIME))
                .le(operLog.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(operLog.getParams().get(Constants.END_TIME).toString()), SysOperLog::getOperTime, operLog.getParams().get(Constants.END_TIME));
        List<SysOperLog> list = list(queryWrapper);
        return list;
    }
}
