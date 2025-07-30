package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, Long> {
}