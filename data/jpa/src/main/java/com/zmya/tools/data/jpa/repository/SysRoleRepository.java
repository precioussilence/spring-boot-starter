package com.zmya.tools.data.jpa.repository;

import com.zmya.tools.data.jpa.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
    List<SysRole> findByIdIn(Collection<Long> ids);

    void removeByIdIn(Collection<Long> ids);
}