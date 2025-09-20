package com.zmya.tools.data.jpa.repository;

import com.zmya.tools.data.jpa.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUserEntity, Long> {

    @Query("select r.roleCode " +
            "from SysUserEntity u " +
            "join u.sysUserRoles ur " +
            "join ur.role r " +
            "where u.username=:username " +
            "and u.status=1 " +
            "and r.status=1")
    List<String> findGrantedRoleCodes(@Param("username") String username);

    void removeByIdIn(Collection<Long> ids);

    List<SysUserEntity> findByUsername(String username);
}