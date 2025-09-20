package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysUserRoleDao;
import com.zmya.tools.data.core.model.SysUserRole;
import com.zmya.tools.data.jpa.entity.SysRoleEntity;
import com.zmya.tools.data.jpa.entity.SysUserEntity;
import com.zmya.tools.data.jpa.entity.SysUserRoleEntity;
import com.zmya.tools.data.jpa.repository.SysRoleRepository;
import com.zmya.tools.data.jpa.repository.SysUserRepository;
import com.zmya.tools.data.jpa.repository.SysUserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "jpa", matchIfMissing = true)
@AllArgsConstructor
public class SysUserRoleDaoAdapter implements SysUserRoleDao {

    private final SysUserRoleRepository sysUserRoleRepository;
    private final SysUserRepository sysUserRepository;
    private final SysRoleRepository sysRoleRepository;

    @Override
    public List<SysUserRole> findByUserAndRoleIn(Long userId, Collection<Long> roleIds) {
        SysUserEntity sysUser = new SysUserEntity();
        sysUser.setId(userId);
        List<SysRoleEntity> roleList = roleIds.stream().map(id -> {
            SysRoleEntity role = new SysRoleEntity();
            role.setId(id);
            return role;
        }).toList();
        List<SysUserRoleEntity> list = sysUserRoleRepository.findByUserAndRoleIn(sysUser, roleList);
        return list.stream().map(source -> {
            SysUserRole target = new SysUserRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysUserRole> findByUserId(Long userId) {
        List<SysUserRoleEntity> list = sysUserRoleRepository.findByUser_Id(userId);
        return list.stream().map(source -> {
            SysUserRole target = new SysUserRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysUserRole> saveAll(Collection<SysUserRole> sysUserRoles) {
        List<SysUserRoleEntity> entities = new ArrayList<>();
        sysUserRoles.forEach(source -> {
            SysUserRoleEntity sysUserRole = new SysUserRoleEntity();
            sysUserRole.setUser(sysUserRepository.getReferenceById(source.getUserId()));
            sysUserRole.setRole(sysRoleRepository.getReferenceById(source.getRoleId()));
            entities.add(sysUserRole);
        });
        List<SysUserRoleEntity> saved = sysUserRoleRepository.saveAll(entities);
        return saved.stream().map(source -> {
            SysUserRole target = new SysUserRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
