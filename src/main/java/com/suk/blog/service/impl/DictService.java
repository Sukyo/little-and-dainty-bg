package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.entity.SysDictData;
import com.suk.blog.mapper.SysDictDataMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * html调用 thymeleaf 实现字典读取
 */
@Service("dict")
public class DictService extends ServiceImpl<SysDictDataMapper, SysDictData> {

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<SysDictData> getType(String dictType) {
        LambdaQueryWrapper<SysDictData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDictData::getStatus, 0).eq(SysDictData::getDictType, dictType).orderByAsc(SysDictData::getDictSort);
        List<SysDictData> list = list(queryWrapper);
        return list;
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue) {
        SysDictData dictData = getOne(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getDictType, dictType).eq(SysDictData::getDictValue, dictValue));
        return dictData.getDictLabel();
    }
}
