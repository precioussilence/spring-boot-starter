package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysResourceDao;
import com.zmya.tools.data.core.model.SysResource;
import com.zmya.tools.data.jpa.repository.SysResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "jpa", matchIfMissing = true)
@AllArgsConstructor
public class SysResourceDaoAdapter implements SysResourceDao {

    private final SysResourceRepository sysResourceRepository;

    @Override
    public List<SysResource> findByIdIn(Collection<Long> ids) {
        List<com.zmya.tools.data.jpa.entity.SysResource> list = sysResourceRepository.findByIdIn(ids);
        return list.stream().map(source -> {
            SysResource target = new SysResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public void removeByIdIn(Collection<Long> ids) {
        sysResourceRepository.removeByIdIn(ids);
    }
}
