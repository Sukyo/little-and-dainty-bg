package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.SysDictData;
import com.suk.blog.mapper.SysDictDataMapper;
import com.suk.blog.service.ISysDictDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements ISysDictDataService {

    /**
     * 分页查询
     *
     * @param iPage
     * @param dictData
     * @return
     */
    @Override
    public IPage<SysDictData> pageList(IPage<SysDictData> iPage, SysDictData dictData) {
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<SysDictData>();
        queryWrapper.eq(StringUtils.isNoneBlank(dictData.getDictType()), SysDictData::getDictType, dictData.getDictType())
                .eq(StringUtils.isNoneBlank(dictData.getStatus()), SysDictData::getStatus, dictData.getStatus())
                .like(StringUtils.isNoneBlank(dictData.getDictLabel()), SysDictData::getDictLabel, dictData.getDictType())
                .ge(dictData.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(dictData.getParams().get(Constants.START_TIME).toString()), SysDictData::getCreateTime, dictData.getParams().get(Constants.START_TIME))
                .le(dictData.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(dictData.getParams().get(Constants.END_TIME).toString()), SysDictData::getCreateTime, dictData.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param dictData
     * @return
     */
    @Override
    public List<SysDictData> list(SysDictData dictData) {
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<SysDictData>();
        queryWrapper.eq(StringUtils.isNoneBlank(dictData.getDictType()), SysDictData::getDictType, dictData.getDictType())
                .eq(StringUtils.isNoneBlank(dictData.getStatus()), SysDictData::getStatus, dictData.getStatus())
                .like(StringUtils.isNoneBlank(dictData.getDictLabel()), SysDictData::getDictLabel, dictData.getDictType())
                .ge(dictData.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(dictData.getParams().get(Constants.START_TIME).toString()), SysDictData::getCreateTime, dictData.getParams().get(Constants.START_TIME))
                .le(dictData.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(dictData.getParams().get(Constants.END_TIME).toString()), SysDictData::getCreateTime, dictData.getParams().get(Constants.END_TIME));
        List<SysDictData> list = list(queryWrapper);
        return list;
    }
}
