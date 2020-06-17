package com.suk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suk.blog.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户id
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);
}
