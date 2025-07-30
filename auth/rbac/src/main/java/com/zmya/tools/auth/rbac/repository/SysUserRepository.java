package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
}