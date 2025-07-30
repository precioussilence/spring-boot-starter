package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysRoleResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleResourceRepository extends JpaRepository<SysRoleResource, Long> {
}