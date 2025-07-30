package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
}