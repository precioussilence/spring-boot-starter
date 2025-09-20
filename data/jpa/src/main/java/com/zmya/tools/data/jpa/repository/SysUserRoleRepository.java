package com.zmya.tools.data.jpa.repository;

import com.zmya.tools.data.jpa.entity.SysRoleEntity;
import com.zmya.tools.data.jpa.entity.SysUserEntity;
import com.zmya.tools.data.jpa.entity.SysUserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysUserRoleRepository extends JpaRepository<SysUserRoleEntity, Long> {
    List<SysUserRoleEntity> findByUserAndRoleIn(SysUserEntity user, Collection<SysRoleEntity> roles);

    List<SysUserRoleEntity> findByUser_Id(Long userId);
}