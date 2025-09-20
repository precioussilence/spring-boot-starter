package com.zmya.tools.data.jpa.repository;

import com.zmya.tools.data.jpa.entity.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SysResourceRepository extends JpaRepository<SysResource, Long> {
    List<SysResource> findByIdIn(Collection<Long> ids);

    void removeByIdIn(Collection<Long> ids);
}