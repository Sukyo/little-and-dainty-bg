package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.suk.blog.constant.Constants;
import com.suk.blog.constant.UserConstants;
import com.suk.blog.entity.SysRole;
import com.suk.blog.entity.SysRoleMenu;
import com.suk.blog.entity.SysUserRole;
import com.suk.blog.mapper.SysRoleMapper;
import com.suk.blog.mapper.SysRoleMenuMapper;
import com.suk.blog.service.ISysRoleMenuService;
import com.suk.blog.service.ISysRoleService;
import com.suk.blog.service.ISysUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    ISysUserRoleService userRoleService;
    @Autowired
    ISysRoleMenuService roleMenuService;

    @Override
    public IPage<SysRole> pageList(IPage<SysRole> iPage, SysRole role) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<SysRole>();
        queryWrapper.like(StringUtils.isNoneBlank(role.getRoleName()), SysRole::getRoleName, role.getRoleName())
                .like(StringUtils.isNoneBlank(role.getRoleKey()), SysRole::getRoleKey, role.getRoleKey())
                .eq(StringUtils.isNoneBlank(role.getStatus()), SysRole::getStatus, role.getStatus())
                .ge(role.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(role.getParams().get(Constants.START_TIME).toString()), SysRole::getCreateTime, role.getParams().get(Constants.START_TIME))
                .le(role.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(role.getParams().get(Constants.END_TIME).toString()), SysRole::getCreateTime, role.getParams().get(Constants.END_TIME));
        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param role
     * @return
     */
    @Override
    public List<SysRole> list(SysRole role) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<SysRole>();
        queryWrapper.like(StringUtils.isNoneBlank(role.getRoleName()), SysRole::getRoleName, role.getRoleName())
                .like(StringUtils.isNoneBlank(role.getRoleKey()), SysRole::getRoleKey, role.getRoleKey())
                .eq(StringUtils.isNoneBlank(role.getStatus()), SysRole::getStatus, role.getStatus())
                .ge(role.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(role.getParams().get(Constants.START_TIME).toString()), SysRole::getCreateTime, role.getParams().get(Constants.START_TIME))
                .le(role.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(role.getParams().get(Constants.END_TIME).toString()), SysRole::getCreateTime, role.getParams().get(Constants.END_TIME));
        List<SysRole> list = list(queryWrapper);
        return list;
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public Set<String> selectRoleKeys(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectRolesByUserId(userId);
        Set<String> stringSet = Sets.newHashSet();
        if (CollectionUtils.isEmpty(sysRoles)) {
            return stringSet;
        }
        for (SysRole perm : sysRoles) {
            if (perm != null) {
                stringSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return stringSet;
    }

    /***
     * 根据用户ID查询角色信息
     * @param userId 用户id
     * @return
     */
    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        List<SysRole> userRoles = sysRoleMapper.selectRolesByUserId(userId);
        List<SysRole> roles = list();
        for (SysRole role : roles) {
            for (SysRole userRole : userRoles) {
                if (role.getId().longValue() == userRole.getId().longValue()) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    @Override
    public int authDataScope(SysRole role) {
        // 修改角色信息
        boolean b = updateById(role);
        return b ? 1 : 0;
    }

    @Override
    public String checkRoleNameUnique(SysRole role) {
        Long roleId = role.getId() == null ? -1L : role.getId();
        SysRole sysRole = getOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleName, role.getRoleName()));
        if (sysRole != null && sysRole.getId().longValue() != roleId.longValue()) {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    @Override
    public String checkRoleKeyUnique(SysRole role) {
        Long roleId = role.getId() == null ? -1L : role.getId();
        SysRole sysRole = getOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleKey, role.getRoleKey()));
        if (sysRole != null && sysRole.getId().longValue() != roleId.longValue()) {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }


    /**
     * 批量选择授权用户角色
     *
     * @param roleId  角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    @Override
    public boolean insertAuthUsers(Long roleId, String userIds) {
        String[] userIdList = userIds.split(",");
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        for (String userId : userIdList) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(Integer.parseInt(userId));
            ur.setRoleId(roleId.intValue());
            list.add(ur);
        }
        boolean b = userRoleService.saveBatch(list, list.size());
        return b;
    }

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int insertRole(SysRole role) {
        // 新增角色信息
        sysRoleMapper.insert(role);
        boolean b = insertRoleMenu(role);
        return b ? 1 : 0;
    }

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRole(SysRole role) {
        // 修改角色信息
        sysRoleMapper.updateById(role);
        // 删除角色与菜单关联
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, role.getId()));
        //插入新的角色菜单关联
        boolean b = insertRoleMenu(role);
        return b ? 1 : 0;
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public boolean insertRoleMenu(SysRole role) {
        boolean b = true;
        // 新增用户与角色管理
        List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
        for (Long menuId : role.getMenuIds()) {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getId().intValue());
            rm.setMenuId(menuId.intValue());
            list.add(rm);
        }
        if (list.size() > 0) {
            b = roleMenuService.saveBatch(list);
        }
        return b;
    }
}
