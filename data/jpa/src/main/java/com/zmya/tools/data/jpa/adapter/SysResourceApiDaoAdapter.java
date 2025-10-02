package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysResourceApiDao;
import com.zmya.tools.data.core.model.SysResourceApi;
import com.zmya.tools.data.jpa.entity.SysApiEntity;
import com.zmya.tools.data.jpa.entity.SysResourceApiEntity;
import com.zmya.tools.data.jpa.entity.SysResourceEntity;
import com.zmya.tools.data.jpa.repository.SysApiRepository;
import com.zmya.tools.data.jpa.repository.SysResourceApiRepository;
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
public class SysResourceApiDaoAdapter implements SysResourceApiDao {

    private final SysResourceApiRepository sysResourceApiRepository;
    private final SysResourceRepository sysResourceRepository;
    private final SysApiRepository sysApiRepository;

    @Override
    public List<SysResourceApi> findByResourceAndApiIn(Long resourceId, Collection<Long> apiIds) {
        List<SysApiEntity> apiList = apiIds.stream().map(id -> {
            SysApiEntity api = new SysApiEntity();
            api.setId(id);
            return api;
        }).toList();
        SysResourceEntity resource = new SysResourceEntity();
        resource.setId(resourceId);
        List<SysResourceApiEntity> list = sysResourceApiRepository.findByResourceAndApiIn(resource, apiList);
        return list.stream().map(source -> {
            SysResourceApi target = new SysResourceApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysResourceApi> saveAll(List<SysResourceApi> sysResourceApis) {
        List<SysResourceApiEntity> entities = sysResourceApis.stream().map(source -> {
            SysResourceApiEntity entity = new SysResourceApiEntity();
            entity.setResource(sysResourceRepository.getReferenceById(source.getResourceId()));
            entity.setApi(sysApiRepository.getReferenceById(source.getApiId()));
            return entity;
        }).toList();
        List<SysResourceApiEntity> saved = sysResourceApiRepository.saveAll(entities);
        return saved.stream().map(source -> {
            SysResourceApi target = new SysResourceApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
