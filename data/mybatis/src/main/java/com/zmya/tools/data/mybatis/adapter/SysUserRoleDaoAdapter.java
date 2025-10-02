package com.zmya.tools.data.mybatis.adapter;

import com.zmya.tools.data.core.dao.SysUserRoleDao;
import com.zmya.tools.data.core.model.SysUserRole;
import com.zmya.tools.data.mybatis.entity.SysUserRoleEntity;
import com.zmya.tools.data.mybatis.mapper.SysUserRoleEntityDynamicSqlSupport;
import com.zmya.tools.data.mybatis.mapper.SysUserRoleMapper;
import lombok.AllArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
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
public class SysUserRoleDaoAdapter implements SysUserRoleDao {

    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> findByUserAndRoleIn(Long userId, Collection<Long> roleIds) {
        if (Objects.isNull(userId) && CollectionUtils.isEmpty(roleIds)) {
            return List.of();
        }
        SelectStatementProvider provider = SqlBuilder
                .select(SysUserRoleEntityDynamicSqlSupport.sysUserRoleEntity.allColumns())
                .from(SysUserRoleEntityDynamicSqlSupport.sysUserRoleEntity)
                .where(SysUserRoleEntityDynamicSqlSupport.userId, SqlBuilder.isEqualToWhenPresent(userId))
                .and(SysUserRoleEntityDynamicSqlSupport.roleId, SqlBuilder.isInWhenPresent(roleIds))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysUserRoleEntity> entities = sysUserRoleMapper.selectMany(provider);
        return entities.stream().map(source -> {
            SysUserRole target = new SysUserRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysUserRole> findByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return List.of();
        }
        SelectStatementProvider provider = SqlBuilder
                .select(SysUserRoleEntityDynamicSqlSupport.sysUserRoleEntity.allColumns())
                .from(SysUserRoleEntityDynamicSqlSupport.sysUserRoleEntity)
                .where(SysUserRoleEntityDynamicSqlSupport.userId, SqlBuilder.isEqualToWhenPresent(userId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysUserRoleEntity> entities = sysUserRoleMapper.selectMany(provider);
        return entities.stream().map(source -> {
            SysUserRole target = new SysUserRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    @Transactional
    public List<SysUserRole> saveAll(List<SysUserRole> sysUserRoles) {
        if (CollectionUtils.isEmpty(sysUserRoles)) {
            return List.of();
        }
        sysUserRoles.forEach(source -> {
            SysUserRoleEntity entity = new SysUserRoleEntity();
            BeanUtils.copyProperties(source, entity);
            sysUserRoleMapper.insert(entity);
            source.setId(entity.getId());
        });
        return sysUserRoles;
    }
}
