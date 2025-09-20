package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysUserRoleDao;
import com.zmya.tools.data.core.model.SysUserRole;
import com.zmya.tools.data.jpa.entity.SysRole;
import com.zmya.tools.data.jpa.entity.SysUser;
import com.zmya.tools.data.jpa.repository.SysUserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "jpa", matchIfMissing = true)
@AllArgsConstructor
public class SysUserRoleDaoAdapter implements SysUserRoleDao {

    private final SysUserRoleRepository sysUserRoleRepository;

    @Override
    public List<SysUserRole> findByUserAndRoleIn(Long userId, Collection<Long> roleIds) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        List<SysRole> roleList = roleIds.stream().map(id -> {
            SysRole role = new SysRole();
            role.setId(id);
            return role;
        }).toList();
        List<com.zmya.tools.data.jpa.entity.SysUserRole> list = sysUserRoleRepository.findByUserAndRoleIn(sysUser, roleList);
        return list.stream().map(source -> {
            SysUserRole target = new SysUserRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysUserRole> findByUserId(Long userId) {
        List<com.zmya.tools.data.jpa.entity.SysUserRole> list = sysUserRoleRepository.findByUser_Id(userId);
        return list.stream().map(source -> {
            SysUserRole target = new SysUserRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
