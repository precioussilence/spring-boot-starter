package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
    List<SysRole> findByIdIn(Collection<Long> ids);
}