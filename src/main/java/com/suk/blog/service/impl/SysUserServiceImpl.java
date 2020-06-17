package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.constant.UserConstants;
import com.suk.blog.entity.SysRole;
import com.suk.blog.entity.SysUser;
import com.suk.blog.entity.SysUserRole;
import com.suk.blog.mapper.SysRoleMapper;
import com.suk.blog.mapper.SysUserMapper;
import com.suk.blog.mapper.SysUserRoleMapper;
import com.suk.blog.service.ISysUserRoleService;
import com.suk.blog.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author suk
 * @since 2019-07-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    ISysUserRoleService userRoleService;

    @Override
    public IPage<SysUser> pageList(IPage<SysUser> iPage, SysUser user) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>();
        queryWrapper
                .like(StringUtils.isNoneBlank(user.getUserName()), SysUser::getUserName, user.getUserName())
                .like(StringUtils.isNoneBlank(user.getTelephone()), SysUser::getTelephone, user.getTelephone())
                .ge(user.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(user.getParams().get(Constants.START_TIME).toString()), SysUser::getCreateTime, user.getParams().get(Constants.START_TIME))
                .le(user.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(user.getParams().get(Constants.END_TIME).toString()), SysUser::getCreateTime, user.getParams().get(Constants.END_TIME));
        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param user
     * @return
     */
    @Override
    public List<SysUser> list(SysUser user) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>();
        queryWrapper
                .like(StringUtils.isNoneBlank(user.getUserName()), SysUser::getUserName, user.getUserName())
                .like(StringUtils.isNoneBlank(user.getTelephone()), SysUser::getTelephone, user.getTelephone())
                .like(StringUtils.isNoneBlank(user.getSearchValue()), SysUser::getUserName, user.getSearchValue())
                .like(StringUtils.isNoneBlank(user.getSearchValue()), SysUser::getTelephone, user.getSearchValue())
                .ge(user.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(user.getParams().get(Constants.START_TIME).toString()), SysUser::getCreateTime, user.getParams().get(Constants.START_TIME))
                .le(user.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(user.getParams().get(Constants.END_TIME).toString()), SysUser::getCreateTime, user.getParams().get(Constants.END_TIME));
        List<SysUser> list = list(queryWrapper);
        return list;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    @Override
    public String checkLoginNameUnique(String loginName) {
        int count = count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, loginName));
        return count > 0 ? UserConstants.USER_NAME_NOT_UNIQUE : UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        Long userId = user.getId() == null ? -1L : user.getId();
        SysUser sysUser = getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getTelephone, user.getTelephone()));
        String s = sysUser != null && !userId.equals(sysUser.getId()) ? UserConstants.USER_NAME_NOT_UNIQUE : UserConstants.USER_NAME_UNIQUE;
        return s;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        Long userId = user.getId() == null ? -1L : user.getId();
        SysUser sysUser = getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getEmail, user.getEmail()));
        String s = sysUser != null && !userId.equals(sysUser.getId()) ? UserConstants.USER_NAME_NOT_UNIQUE : UserConstants.USER_NAME_UNIQUE;
        return s;
    }

    @Override
    public List<SysUser> selectAllocatedList(SysUser user) {
        return sysUserMapper.selectAllocatedList(user);
    }

    @Override
    public List<SysUser> selectUnallocatedList(SysUser user) {
        return sysUserMapper.selectUnallocatedList(user);
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(SysUser user) {
        // 新增用户信息
        int rows = sysUserMapper.insert(user);
        // 新增用户与角色管理
        insertUserRole(user);
        return rows;
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(SysUser user) {
        // 删除用户与角色关联
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, user.getId()));
        // 新增用户与角色管理
        insertUserRole(user);
        boolean b = updateById(user);
        return b ? 1 : 0;
    }

    /**
     * 查询用户所属角色组
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.selectRolesByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : sysRoles) {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(Long userId) {

        return "";
    }

    /**
     * 删除
     *
     * @param userId
     * @return
     */
    @Override
    public int deleteUser(Long userId) {
        //删除用户角色关联
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
        //删除用户信息
        boolean b = removeById(userId);
        return b ? 1 : 0;
    }

    /**
     * 批量删除
     *
     * @param userId
     * @return
     */
    @Override
    public int batchDeleteUser(Collection<? extends Serializable> userId) {
        //删除用户角色关联
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().in(SysUserRole::getUserId, userId));
        //删除用户
        boolean b = removeByIds(userId);
        return b ? 1 : 0;
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        Long[] roles = user.getRoleIds();
        if (roles != null) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getId());
                ur.setRoleId(roleId.intValue());
                list.add(ur);
            }
            if (list.size() > 0) {
                userRoleService.saveBatch(list);
            }
        }
    }

}
