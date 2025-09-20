package com.zmya.tools.data.jpa.repository;

import com.zmya.tools.data.jpa.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysRoleRepository extends JpaRepository<SysRoleEntity, Long> {
    List<SysRoleEntity> findByIdIn(Collection<Long> ids);

    void removeByIdIn(Collection<Long> ids);
}