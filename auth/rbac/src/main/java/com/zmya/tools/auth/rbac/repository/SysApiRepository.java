package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysApiRepository extends JpaRepository<SysApi, Long> {
    List<SysApi> findByUrlAndMethod(String url, String method);
}