package com.zmya.tools.auth.rbac.repository;

import com.zmya.tools.auth.rbac.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    @Query("select r.roleCode " +
            "from SysUser u " +
            "join u.sysUserRoles ur " +
            "join ur.role r " +
            "where u.username=:username " +
            "and u.status=1 " +
            "and r.status=1")
    List<String> findGrantedRoleCodes(@Param("username") String username);

}