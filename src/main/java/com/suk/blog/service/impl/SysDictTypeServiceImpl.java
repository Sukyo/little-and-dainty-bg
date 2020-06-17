package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.constant.UserConstants;
import com.suk.blog.entity.SysDictData;
import com.suk.blog.entity.SysDictType;
import com.suk.blog.mapper.SysDictDataMapper;
import com.suk.blog.mapper.SysDictTypeMapper;
import com.suk.blog.service.ISysDictTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements ISysDictTypeService {

    @Autowired
    SysDictDataMapper dictDataMapper;

    /**
     * 分页查询
     *
     * @param iPage
     * @param dictType
     * @return
     */
    @Override
    public IPage<SysDictType> pageList(IPage<SysDictType> iPage, SysDictType dictType) {
        LambdaQueryWrapper<SysDictType> queryWrapper = new LambdaQueryWrapper<SysDictType>();
        queryWrapper.eq(StringUtils.isNoneBlank(dictType.getStatus()), SysDictType::getStatus, dictType.getStatus())
                .like(StringUtils.isNoneBlank(dictType.getDictName()), SysDictType::getDictName, dictType.getDictName())
                .like(StringUtils.isNoneBlank(dictType.getDictType()), SysDictType::getDictType, dictType.getDictType())
                .ge(dictType.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(dictType.getParams().get(Constants.START_TIME).toString()), SysDictType::getCreateTime, dictType.getParams().get(Constants.START_TIME))
                .le(dictType.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(dictType.getParams().get(Constants.END_TIME).toString()), SysDictType::getCreateTime, dictType.getParams().get(Constants.END_TIME));
        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询字典类型
     *
     * @param dictType
     * @return
     */
    @Override
    public List<SysDictType> list(SysDictType dictType) {
        LambdaQueryWrapper<SysDictType> queryWrapper = new LambdaQueryWrapper<SysDictType>();
        queryWrapper.eq(StringUtils.isNoneBlank(dictType.getStatus()), SysDictType::getStatus, dictType.getStatus())
                .like(StringUtils.isNoneBlank(dictType.getDictName()), SysDictType::getDictName, dictType.getDictName())
                .like(StringUtils.isNoneBlank(dictType.getDictType()), SysDictType::getDictType, dictType.getDictType())
                .ge(dictType.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(dictType.getParams().get(Constants.START_TIME).toString()), SysDictType::getCreateTime, dictType.getParams().get(Constants.START_TIME))
                .le(dictType.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(dictType.getParams().get(Constants.END_TIME).toString()), SysDictType::getCreateTime, dictType.getParams().get(Constants.END_TIME));
        List<SysDictType> list = list(queryWrapper);
        return list;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int updateDictType(SysDictType dictType) {
        SysDictType oldDict = getById(dictType.getId());
        SysDictData dictData = new SysDictData();
        dictData.setDictType(dictType.getDictType());
        dictDataMapper.update(dictData, new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getDictType, oldDict.getDictType()));
        boolean b = updateById(dictType);
        return b ? 1 : 0;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict) {
        Long dictId = dict.getId() == null ? -1L : dict.getId();
        SysDictType info = getOne(new LambdaQueryWrapper<SysDictType>().eq(SysDictType::getDictType, dict.getDictType()));
        if (info != null && info.getId().longValue() != dictId.longValue()) {
            return UserConstants.DICT_TYPE_NOT_UNIQUE;
        }
        return UserConstants.DICT_TYPE_UNIQUE;
    }
}
