package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysApiRepository extends JpaRepository<SysApi, Long> {
}