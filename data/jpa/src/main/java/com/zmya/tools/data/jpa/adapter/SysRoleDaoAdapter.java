package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.dao.SysRoleDao;
import com.zmya.tools.data.core.model.SysRole;
import com.zmya.tools.data.jpa.entity.SysRoleEntity;
import com.zmya.tools.data.jpa.repository.SysRoleRepository;
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
public class SysRoleDaoAdapter implements SysRoleDao {

    private final SysRoleRepository sysRoleRepository;

    @Override
    public Optional<SysRole> findById(Long id) {
        Optional<SysRoleEntity> optional = sysRoleRepository.findById(id);
        return optional.map(opt -> {
            SysRole target = new SysRole();
            BeanUtils.copyProperties(opt, target);
            return target;
        });
    }

    @Override
    public List<SysRole> findByIdIn(Collection<Long> ids) {
        List<SysRoleEntity> list = sysRoleRepository.findByIdIn(ids);
        return list.stream().map(source -> {
            SysRole target = new SysRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public void removeByIdIn(Collection<Long> ids) {
        sysRoleRepository.removeByIdIn(ids);
    }

    @Override
    public SysRole save(SysRole sysRole) {
        SysRoleEntity entity = new SysRoleEntity();
        BeanUtils.copyProperties(sysRole, entity);
        SysRoleEntity saved = sysRoleRepository.save(entity);
        SysRole target = new SysRole();
        BeanUtils.copyProperties(saved, target);
        return target;
    }

    @Override
    public PageResult<SysRole> findAll(PageQuery pageQuery, String roleName) {
        Specification<SysRoleEntity> spec = Specification.unrestricted();
        if (StringUtils.hasText(roleName)) {
            spec.and((root, query, builder) -> builder.equal(root.get("roleName"), roleName));
        }
        PageRequest pageRequest = PageRequest.of(pageQuery.getPageNumber(), pageQuery.getPageSize());
        Page<SysRoleEntity> page = sysRoleRepository.findAll(spec, pageRequest);
        PageResult<SysRole> result = new PageResult<>();
        result.setPageNumber(page.getNumber());
        result.setPageSize(page.getSize());
        result.setTotalElements(page.getTotalElements());
        result.setTotalPages(page.getTotalPages());
        result.setContent(from(page.getContent()));
        return result;
    }

    @Override
    public List<SysRole> findAll() {
        List<SysRoleEntity> entities = sysRoleRepository.findAll();
        return entities.stream().map(this::from).toList();
    }

    private List<SysRole> from(List<SysRoleEntity> entities) {
        return entities.stream().map(this::from).toList();
    }

    private SysRole from(SysRoleEntity entity) {
        SysRole target = new SysRole();
        BeanUtils.copyProperties(entity, target);
        return target;
    }
}
