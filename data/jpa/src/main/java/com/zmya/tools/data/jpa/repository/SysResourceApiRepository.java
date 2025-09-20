package com.zmya.tools.data.jpa.repository;

import com.zmya.tools.data.jpa.entity.SysApiEntity;
import com.zmya.tools.data.jpa.entity.SysResourceApiEntity;
import com.zmya.tools.data.jpa.entity.SysResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysResourceApiRepository extends JpaRepository<SysResourceApiEntity, Long> {
    List<SysResourceApiEntity> findByApiIn(Collection<SysApiEntity> apis);

    List<SysResourceApiEntity> findByResourceAndApiIn(SysResourceEntity resource, Collection<SysApiEntity> apis);
}