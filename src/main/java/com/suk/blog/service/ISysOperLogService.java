package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.SysOperLog;

import java.util.List;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
public interface ISysOperLogService extends IService<SysOperLog> {
    /**
     * 分页查询
     *
     * @param iPage
     * @param operLog
     * @return
     */
    IPage<SysOperLog> pageList(IPage<SysOperLog> iPage, SysOperLog operLog);

    /**
     * 不分页查询
     *
     * @param operLog
     * @return
     */
    List<SysOperLog> list(SysOperLog operLog);
}
