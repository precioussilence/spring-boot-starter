package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysApi;
import com.zmya.tools.auth.rbac.entity.SysResource;
import com.zmya.tools.auth.rbac.entity.SysResourceApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysResourceApiRepository extends JpaRepository<SysResourceApi, Long> {
    List<SysResourceApi> findByApiIn(Collection<SysApi> apis);

    List<SysResourceApi> findByResourceAndApiIn(SysResource resource, Collection<SysApi> apis);
}