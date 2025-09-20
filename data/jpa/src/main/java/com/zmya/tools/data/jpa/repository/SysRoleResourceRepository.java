package com.zmya.tools.data.jpa.repository;

import com.zmya.tools.data.jpa.entity.SysResourceEntity;
import com.zmya.tools.data.jpa.entity.SysRoleEntity;
import com.zmya.tools.data.jpa.entity.SysRoleResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysRoleResourceRepository extends JpaRepository<SysRoleResourceEntity, Long> {
    List<SysRoleResourceEntity> findByResourceIn(Collection<SysResourceEntity> resources);

    List<SysRoleResourceEntity> findByRoleAndResourceIn(SysRoleEntity role, Collection<SysResourceEntity> resources);

    List<SysRoleResourceEntity> findByRole_Id(Long roleId);
}