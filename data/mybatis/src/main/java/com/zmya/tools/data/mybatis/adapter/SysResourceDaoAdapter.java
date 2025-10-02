package com.zmya.tools.data.mybatis.adapter;

import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.dao.SysResourceDao;
import com.zmya.tools.data.core.model.SysResource;
import com.zmya.tools.data.mybatis.entity.SysResourceEntity;
import com.zmya.tools.data.mybatis.mapper.SysResourceEntityDynamicSqlSupport;
import com.zmya.tools.data.mybatis.mapper.SysResourceMapper;
import lombok.AllArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "mybatis", matchIfMissing = true)
@AllArgsConstructor
public class SysResourceDaoAdapter implements SysResourceDao {

    private final SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> findByIdIn(Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return List.of();
        }
        SelectStatementProvider provider = SqlBuilder
                .select(SysResourceEntityDynamicSqlSupport.sysResourceEntity.allColumns())
                .from(SysResourceEntityDynamicSqlSupport.sysResourceEntity)
                .where(SysResourceEntityDynamicSqlSupport.id, SqlBuilder.isInWhenPresent(ids))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysResourceEntity> entities = sysResourceMapper.selectMany(provider);
        return entities.stream().map(source -> {
            SysResource target = new SysResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    @Transactional
    public void removeByIdIn(Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        ids.forEach(sysResourceMapper::deleteByPrimaryKey);
    }

    @Override
    public Optional<SysResource> findById(Long id) {
        Optional<SysResourceEntity> entity = sysResourceMapper.selectByPrimaryKey(id);
        return entity.map(source -> {
            SysResource target = new SysResource();
            BeanUtils.copyProperties(source, target);
            return target;
        });
    }

    @Override
    public PageResult<SysResource> findAll(PageQuery query, String resourceName) {
        if (Objects.isNull(query) && !StringUtils.hasText(resourceName)) {
            return PageResult.empty();
        }
        long limit = Objects.isNull(query) ? 10L : query.getPageSize();
        long offset = Objects.isNull(query) ? 0L : query.getPageNumber() * limit;
        SelectStatementProvider provider = SqlBuilder
                .select(SysResourceEntityDynamicSqlSupport.sysResourceEntity.allColumns())
                .from(SysResourceEntityDynamicSqlSupport.sysResourceEntity)
                .where(SysResourceEntityDynamicSqlSupport.resourceName, SqlBuilder.isEqualToWhenPresent(resourceName))
                .limit(limit)
                .offset(offset)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        long count = sysResourceMapper.count(provider);
        if (count == 0L) {
            return PageResult.of(query, count, List.of());
        }
        List<SysResourceEntity> entities = sysResourceMapper.selectMany(provider);
        List<SysResource> resources = entities.stream().map(source -> {
            SysResource target = new SysResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
        return PageResult.of(query, count, resources);
    }

    @Override
    public List<SysResource> findAll() {
        SelectStatementProvider provider = SqlBuilder
                .select(SysResourceEntityDynamicSqlSupport.sysResourceEntity.allColumns())
                .from(SysResourceEntityDynamicSqlSupport.sysResourceEntity)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysResourceEntity> entities = sysResourceMapper.selectMany(provider);
        return entities.stream().map(source -> {
            SysResource target = new SysResource();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public SysResource save(SysResource sysResource) {
        SysResourceEntity target = new SysResourceEntity();
        BeanUtils.copyProperties(sysResource, target);
        sysResourceMapper.insert(target);
        sysResource.setId(target.getId());
        return sysResource;
    }
}
