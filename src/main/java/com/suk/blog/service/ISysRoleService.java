package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.SysRole;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 分页查询角色列表
     *
     * @param iPage
     * @param sysRole
     * @return
     */
    IPage<SysRole> pageList(IPage<SysRole> iPage, SysRole sysRole);

    /**
     * 不分页查询
     *
     * @param role
     * @return
     */
    List<SysRole> list(SysRole role);


    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户id
     * @return
     */
    Set<String> selectRoleKeys(Long userId);

    /***
     * 根据用户ID查询角色信息
     * @param userId 用户id
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 数据权限
     *
     * @param role
     * @return
     */
    int authDataScope(SysRole role);

    /**
     * 校验角色名称的唯一性
     *
     * @param role
     * @return
     */
    String checkRoleNameUnique(SysRole role);

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    String checkRoleKeyUnique(SysRole role);

    /**
     * 批量选择授权用户角色
     *
     * @param roleId  角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    boolean insertAuthUsers(Long roleId, String userIds);

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int insertRole(SysRole role);

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(SysRole role);

}
