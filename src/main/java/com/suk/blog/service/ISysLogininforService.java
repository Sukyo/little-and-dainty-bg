package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.SysLogininfor;

import java.util.List;

/**
 * <p>
 * 系统访问记录 服务类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
public interface ISysLogininforService extends IService<SysLogininfor> {
    /**
     * 分页查询
     *
     * @param iPage
     * @param logininfor
     * @return
     */
    IPage<SysLogininfor> pageList(IPage<SysLogininfor> iPage, SysLogininfor logininfor);

    /**
     * 不分页查询
     *
     * @param logininfor
     * @return
     */
    List<SysLogininfor> list(SysLogininfor logininfor);

}
