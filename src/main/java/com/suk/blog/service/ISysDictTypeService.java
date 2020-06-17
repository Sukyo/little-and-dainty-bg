package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.SysDictType;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
public interface ISysDictTypeService extends IService<SysDictType> {

    /**
     * 分页查询
     *
     * @param iPage
     * @param dictType
     * @return
     */
    IPage<SysDictType> pageList(IPage<SysDictType> iPage, SysDictType dictType);

    /**
     * 不分页查询字典类型
     *
     * @param dictType
     * @return
     */
    List<SysDictType> list(SysDictType dictType);

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int updateDictType(SysDictType dictType);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    String checkDictTypeUnique(SysDictType dict);

}
