package com.zmya.tools.data.jpa.repository;

import com.zmya.tools.data.jpa.entity.SysResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

public interface SysResourceRepository extends JpaRepository<SysResourceEntity, Long>, JpaSpecificationExecutor<SysResourceEntity> {
    List<SysResourceEntity> findByIdIn(Collection<Long> ids);

    void removeByIdIn(Collection<Long> ids);
}