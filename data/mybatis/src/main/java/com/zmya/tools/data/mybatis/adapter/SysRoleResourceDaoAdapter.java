package com.zmya.tools.data.mybatis.adapter;

import com.zmya.tools.data.core.dao.SysRoleResourceDao;
import com.zmya.tools.data.core.model.SysRoleResource;
import com.zmya.tools.data.mybatis.entity.SysRoleResourceEntity;
import com.zmya.tools.data.mybatis.mapper.SysRoleResourceEntityDynamicSqlSupport;
import com.zmya.tools.data.mybatis.mapper.SysRoleResourceMapper;
import lombok.AllArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "mybatis", matchIfMissing = true)
@AllArgsConstructor
public class SysRoleResourceDaoAdapter implements SysRoleResourceDao {

    private final SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public List<SysRoleResource> findByResourceIn(Collection<Long> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return List.of();
        }
        List<SysRoleResourceEntity> entities = sysRoleResourceMapper.select(completer -> completer
                .where(SysRoleResourceEntityDynamicSqlSupport.resourceId, SqlBuilder.isInWhenPresent(resourceIds))
        );
        return entities.stream().map(source -> {
            SysRoleResource target = new SysRoleResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysRoleResource> findByRoleAndResourceIn(Long roleId, Collection<Long> resourceIds) {
        if (Objects.isNull(roleId) && CollectionUtils.isEmpty(resourceIds)) {
            return List.of();
        }
        List<SysRoleResourceEntity> entities = sysRoleResourceMapper.select(completer -> completer
                .where(SysRoleResourceEntityDynamicSqlSupport.roleId, SqlBuilder.isEqualToWhenPresent(roleId))
                .and(SysRoleResourceEntityDynamicSqlSupport.resourceId, SqlBuilder.isInWhenPresent(resourceIds))
        );
        return entities.stream().map(source -> {
            SysRoleResource target = new SysRoleResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysRoleResource> findByRoleId(Long roleId) {
        if (Objects.isNull(roleId)) {
            return List.of();
        }
        List<SysRoleResourceEntity> entities = sysRoleResourceMapper.select(completer -> completer
                .where(SysRoleResourceEntityDynamicSqlSupport.roleId, SqlBuilder.isEqualToWhenPresent(roleId))
        );
        return entities.stream().map(source -> {
            SysRoleResource target = new SysRoleResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    @Transactional
    public List<SysRoleResource> saveAll(List<SysRoleResource> sysRoleResourceList) {
        if (CollectionUtils.isEmpty(sysRoleResourceList)) {
            return List.of();
        }
        sysRoleResourceList.forEach(source -> {
            SysRoleResourceEntity target = new SysRoleResourceEntity();
            BeanUtils.copyProperties(source, target);
            sysRoleResourceMapper.insert(target);
            source.setId(target.getId());
        });
        return sysRoleResourceList;
    }
}
