package com.suk.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.SysMenu;
import com.suk.blog.entity.SysRole;
import com.suk.blog.entity.SysUser;
import com.suk.blog.entity.vo.Ztree;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return
     */
    Set<String> selectPermssionByUserId(Long userId);

    /**
     * 根据用户ID查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenusByUser(SysUser user);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    List<Ztree> roleMenuTreeData(SysRole role, Long userId);

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    List<SysMenu> selectMenuAll(Long userId);

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    List<SysMenu> selectMenuList(SysMenu menu, Long userId);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuById(Long menuId);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    String checkMenuNameUnique(SysMenu menu);

    /**
     * 查询所有菜单信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<Ztree> menuTreeData(Long userId);
}
