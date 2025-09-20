package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.dao.SysResourceDao;
import com.zmya.tools.data.core.model.SysResource;
import com.zmya.tools.data.jpa.entity.SysResourceEntity;
import com.zmya.tools.data.jpa.repository.SysResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "jpa", matchIfMissing = true)
@AllArgsConstructor
public class SysResourceDaoAdapter implements SysResourceDao {

    private final SysResourceRepository sysResourceRepository;

    @Override
    public List<SysResource> findByIdIn(Collection<Long> ids) {
        List<SysResourceEntity> list = sysResourceRepository.findByIdIn(ids);
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

    @Override
    public Optional<SysResource> findById(Long id) {
        Optional<SysResourceEntity> optional = sysResourceRepository.findById(id);
        return optional.map(source -> {
            SysResource target = new SysResource();
            BeanUtils.copyProperties(source, target);
            return target;
        });
    }

    @Override
    public PageResult<SysResource> findAll(PageQuery pageQuery, String resourceName) {
        Specification<SysResourceEntity> spec = Specification.unrestricted();
        if (StringUtils.hasText(resourceName)) {
            spec.and((root, query, builder) -> builder.equal(root.get("resourceName"), resourceName));
        }
        PageRequest pageRequest = PageRequest.of(pageQuery.getPageNumber(), pageQuery.getPageSize());
        Page<SysResourceEntity> page = sysResourceRepository.findAll(spec, pageRequest);
        PageResult<SysResource> result = new PageResult<>();
        result.setPageNumber(page.getNumber());
        result.setPageSize(page.getSize());
        result.setTotalElements(page.getTotalElements());
        result.setTotalPages(page.getTotalPages());
        result.setContent(from(page.getContent()));
        return result;
    }

    @Override
    public List<SysResource> findAll() {
        List<SysResourceEntity> entities = sysResourceRepository.findAll();
        return entities.stream().map(this::from).toList();
    }

    @Override
    public SysResource save(SysResource sysResource) {
        SysResourceEntity entity = new SysResourceEntity();
        BeanUtils.copyProperties(sysResource, entity);
        SysResourceEntity saved = sysResourceRepository.save(entity);
        SysResource target = new SysResource();
        BeanUtils.copyProperties(saved, target);
        return target;
    }

    private List<SysResource> from(Collection<SysResourceEntity> entities) {
        return entities.stream().map(this::from).toList();
    }

    private SysResource from(SysResourceEntity entity) {
        SysResource target = new SysResource();
        BeanUtils.copyProperties(entity, target);
        return target;
    }
}
