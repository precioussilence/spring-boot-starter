package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysResource;
import com.zmya.tools.auth.rbac.entity.SysRole;
import com.zmya.tools.auth.rbac.entity.SysRoleResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysRoleResourceRepository extends JpaRepository<SysRoleResource, Long> {
    List<SysRoleResource> findByResourceIn(Collection<SysResource> resources);

    List<SysRoleResource> findByRoleAndResourceIn(SysRole role, Collection<SysResource> resources);

    List<SysRoleResource> findByRole_Id(Long roleId);
}