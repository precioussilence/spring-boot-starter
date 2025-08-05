package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface SysApiRepository extends JpaRepository<SysApi, Long>, JpaSpecificationExecutor<SysApi> {

    @Query("select r.roleCode " +
            "from SysApi a " +
            "join a.sysResourceApis ra " +
            "join ra.resource rs " +
            "join rs.sysRoleResources rrs " +
            "join rrs.role r " +
            "where a.url=:url " +
            "and a.method=:method " +
            "and a.status=1 " +
            "and rs.status=1 " +
            "and r.status=1")
    List<String> findRequiredRoleCodes(@Param("url") String url, @Param("method") String method);

    List<SysApi> findByIdIn(Collection<Long> ids);

    List<SysApi> findByUrlAndMethod(String url, String method);
}