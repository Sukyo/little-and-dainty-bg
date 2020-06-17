package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.SysDictData;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
public interface ISysDictDataService extends IService<SysDictData> {
    /**
     * 分页查询
     *
     * @param iPage
     * @param dictData
     * @return
     */
    IPage<SysDictData> pageList(IPage<SysDictData> iPage, SysDictData dictData);

    /**
     * 不分页查询
     *
     * @param dictData
     * @return
     */
    List<SysDictData> list(SysDictData dictData);
}
