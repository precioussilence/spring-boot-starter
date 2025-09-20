package com.zmya.tools.data.jpa.repository;

import com.zmya.tools.data.jpa.entity.SysRole;
import com.zmya.tools.data.jpa.entity.SysUser;
import com.zmya.tools.data.jpa.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, Long> {
    List<SysUserRole> findByUserAndRoleIn(SysUser user, Collection<SysRole> roles);

    List<SysUserRole> findByUser_Id(Long userId);
}