package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysApiDao;
import com.zmya.tools.data.core.model.SysApi;
import com.zmya.tools.data.jpa.entity.SysApiEntity;
import com.zmya.tools.data.jpa.repository.SysApiRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
        List<SysApiEntity> list = sysApiRepository.findByIdIn(ids);
        return list.stream().map(source -> {
            SysApi target = new SysApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysApi> findByUrlAndMethod(String url, String method) {
        List<SysApiEntity> list = sysApiRepository.findByUrlAndMethod(url, method);
        return list.stream().map(source -> {
            SysApi target = new SysApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysApi> findByApiNameAndUrl(String apiName, String url) {
        Specification<SysApiEntity> spec = Specification.unrestricted();
        if (StringUtils.hasText(apiName)) {
            spec.and((root, query, builder) -> builder.equal(root.get("apiName"), apiName));
        }
        if (StringUtils.hasText(url)) {
            spec.and((root, query, builder) -> builder.equal(root.get("url"), url));
        }
        List<SysApiEntity> all = sysApiRepository.findAll(spec);
        return all.stream().map(source -> {
            SysApi target = new SysApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysApi> saveAll(Collection<SysApi> sysApis) {
        List<SysApiEntity> entities = sysApis.stream().map(source -> {
            SysApiEntity target = new SysApiEntity();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
        List<SysApiEntity> saved = sysApiRepository.saveAll(entities);
        return saved.stream().map(source -> {
            SysApi target = new SysApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
