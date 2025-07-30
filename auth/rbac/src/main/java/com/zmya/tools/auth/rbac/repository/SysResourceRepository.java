package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysResourceRepository extends JpaRepository<SysResource, Long> {
}