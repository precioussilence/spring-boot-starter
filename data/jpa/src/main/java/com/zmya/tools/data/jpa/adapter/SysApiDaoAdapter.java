package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysApiDao;
import com.zmya.tools.data.core.model.SysApi;
import com.zmya.tools.data.jpa.repository.SysApiRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "jpa", matchIfMissing = true)
@AllArgsConstructor
public class SysApiDaoAdapter implements SysApiDao {

    private final SysApiRepository sysApiRepository;

    @Override
    public List<String> findRequiredRoleCodes(String url, String method) {
        return sysApiRepository.findRequiredRoleCodes(url, method);
    }

    @Override
    public List<SysApi> findByIdIn(Collection<Long> ids) {
        List<com.zmya.tools.data.jpa.entity.SysApi> list = sysApiRepository.findByIdIn(ids);
        return list.stream().map(source->{
            SysApi target = new SysApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysApi> findByUrlAndMethod(String url, String method) {
        List<com.zmya.tools.data.jpa.entity.SysApi> list = sysApiRepository.findByUrlAndMethod(url, method);
        return list.stream().map(source->{
            SysApi target = new SysApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
