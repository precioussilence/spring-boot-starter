package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysResource;
import com.zmya.tools.auth.rbac.entity.SysRoleResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysRoleResourceRepository extends JpaRepository<SysRoleResource, Long> {
    List<SysRoleResource> findByResourceIn(Collection<SysResource> resources);
}