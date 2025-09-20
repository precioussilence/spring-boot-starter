package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysResourceApiDao;
import com.zmya.tools.data.core.model.SysResourceApi;
import com.zmya.tools.data.jpa.repository.SysResourceApiRepository;
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

    @Override
    public List<SysResourceApi> findByApiIn(Collection<Long> apiIds) {
        List<com.zmya.tools.data.jpa.entity.SysApi> apiList = apiIds.stream().map(id -> {
            com.zmya.tools.data.jpa.entity.SysApi api = new com.zmya.tools.data.jpa.entity.SysApi();
            api.setId(id);
            return api;
        }).toList();
        List<com.zmya.tools.data.jpa.entity.SysResourceApi> list = sysResourceApiRepository.findByApiIn(apiList);
        return list.stream().map(source -> {
            SysResourceApi target = new SysResourceApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysResourceApi> findByResourceAndApiIn(Long resourceId, Collection<Long> apiIds) {
        List<com.zmya.tools.data.jpa.entity.SysApi> apiList = apiIds.stream().map(id -> {
            com.zmya.tools.data.jpa.entity.SysApi api = new com.zmya.tools.data.jpa.entity.SysApi();
            api.setId(id);
            return api;
        }).toList();
        com.zmya.tools.data.jpa.entity.SysResource resource = new com.zmya.tools.data.jpa.entity.SysResource();
        resource.setId(resourceId);
        List<com.zmya.tools.data.jpa.entity.SysResourceApi> list = sysResourceApiRepository.findByResourceAndApiIn(resource, apiList);
        return list.stream().map(source -> {
            SysResourceApi target = new SysResourceApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
