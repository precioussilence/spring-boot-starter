package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysRole;
import com.zmya.tools.auth.rbac.entity.SysUser;
import com.zmya.tools.auth.rbac.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, Long> {
    List<SysUserRole> findByUserAndRoleIn(SysUser user, Collection<SysRole> roles);

    List<SysUserRole> findByUser_Id(Long userId);
}