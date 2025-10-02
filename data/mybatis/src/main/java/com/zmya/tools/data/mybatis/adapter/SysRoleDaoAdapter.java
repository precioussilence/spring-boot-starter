package com.zmya.tools.data.mybatis.adapter;

import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.dao.SysRoleDao;
import com.zmya.tools.data.core.model.SysRole;
import com.zmya.tools.data.mybatis.entity.SysRoleEntity;
import com.zmya.tools.data.mybatis.mapper.SysRoleEntityDynamicSqlSupport;
import com.zmya.tools.data.mybatis.mapper.SysRoleMapper;
import lombok.AllArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "mybatis", matchIfMissing = true)
@AllArgsConstructor
public class SysRoleDaoAdapter implements SysRoleDao {

    private final SysRoleMapper sysRoleMapper;

    @Override
    public Optional<SysRole> findById(Long id) {
        Optional<SysRoleEntity> entity = sysRoleMapper.selectByPrimaryKey(id);
        return entity.map(source -> {
            SysRole target = new SysRole();
            BeanUtils.copyProperties(source, target);
            return target;
        });
    }

    @Override
    public List<SysRole> findByIdIn(Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return List.of();
        }
        SelectStatementProvider provider = SqlBuilder
                .select(SysRoleEntityDynamicSqlSupport.sysRoleEntity.allColumns())
                .from(SysRoleEntityDynamicSqlSupport.sysRoleEntity)
                .where(SysRoleEntityDynamicSqlSupport.id, SqlBuilder.isInWhenPresent(ids))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysRoleEntity> entities = sysRoleMapper.selectMany(provider);
        return entities.stream().map(source -> {
            SysRole target = new SysRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public void removeByIdIn(Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        ids.forEach(sysRoleMapper::deleteByPrimaryKey);
    }

    @Override
    public SysRole save(SysRole sysRole) {
        SysRoleEntity target = new SysRoleEntity();
        BeanUtils.copyProperties(sysRole, target);
        sysRoleMapper.insert(target);
        sysRole.setId(target.getId());
        return sysRole;
    }

    @Override
    public PageResult<SysRole> findAll(PageQuery query, String roleName) {
        if (Objects.isNull(query) && !StringUtils.hasText(roleName)) {
            return PageResult.empty();
        }
        long limit = Objects.isNull(query) ? 10L : query.getPageSize();
        long offset = Objects.isNull(query) ? 0L : query.getPageNumber() * limit;
        SelectStatementProvider provider = SqlBuilder
                .select(SysRoleEntityDynamicSqlSupport.sysRoleEntity.allColumns())
                .from(SysRoleEntityDynamicSqlSupport.sysRoleEntity)
                .where(SysRoleEntityDynamicSqlSupport.roleName, SqlBuilder.isEqualToWhenPresent(roleName))
                .limit(limit)
                .offset(offset)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        long count = sysRoleMapper.count(provider);
        if (count == 0L) {
            return PageResult.of(query, count, List.of());
        }
        List<SysRoleEntity> entities = sysRoleMapper.selectMany(provider);
        List<SysRole> resources = entities.stream().map(source -> {
            SysRole target = new SysRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
        return PageResult.of(query, count, resources);
    }

    @Override
    public List<SysRole> findAll() {
        SelectStatementProvider provider = SqlBuilder
                .select(SysRoleEntityDynamicSqlSupport.sysRoleEntity.allColumns())
                .from(SysRoleEntityDynamicSqlSupport.sysRoleEntity)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysRoleEntity> entities = sysRoleMapper.selectMany(provider);
        return entities.stream().map(source -> {
            SysRole target = new SysRole();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
