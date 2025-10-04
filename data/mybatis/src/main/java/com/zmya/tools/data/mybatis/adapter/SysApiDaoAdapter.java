package com.zmya.tools.data.mybatis.adapter;

import com.zmya.tools.data.core.dao.SysApiDao;
import com.zmya.tools.data.core.model.SysApi;
import com.zmya.tools.data.mybatis.entity.SysApiEntity;
import com.zmya.tools.data.mybatis.entity.SysRoleEntity;
import com.zmya.tools.data.mybatis.mapper.*;
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

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "mybatis", matchIfMissing = true)
@AllArgsConstructor
public class SysApiDaoAdapter implements SysApiDao {

    private final SysApiMapper sysApiMapper;
    private final SysRoleMapper sysRoleMapper;

    @Override
    public List<String> findRequiredRoleCodes(String url, String method) {
        if (!StringUtils.hasText(url) && !StringUtils.hasText(method)) {
            return List.of();
        }
        SelectStatementProvider provider = SqlBuilder
                .select(SysRoleEntityDynamicSqlSupport.roleCode)
                .from(SysApiEntityDynamicSqlSupport.sysApiEntity)
                .join(SysResourceApiEntityDynamicSqlSupport.sysResourceApiEntity)
                .on(SysApiEntityDynamicSqlSupport.id, SqlBuilder.equalTo(SysResourceApiEntityDynamicSqlSupport.apiId))
                .join(SysResourceEntityDynamicSqlSupport.sysResourceEntity)
                .on(SysResourceApiEntityDynamicSqlSupport.resourceId, SqlBuilder.equalTo(SysResourceEntityDynamicSqlSupport.id))
                .join(SysRoleResourceEntityDynamicSqlSupport.sysRoleResourceEntity)
                .on(SysResourceEntityDynamicSqlSupport.id, SqlBuilder.equalTo(SysRoleResourceEntityDynamicSqlSupport.resourceId))
                .join(SysRoleEntityDynamicSqlSupport.sysRoleEntity)
                .on(SysRoleResourceEntityDynamicSqlSupport.roleId, SqlBuilder.equalTo(SysRoleEntityDynamicSqlSupport.id))
                .where(SysApiEntityDynamicSqlSupport.url, SqlBuilder.isEqualToWhenPresent(url))
                .and(SysApiEntityDynamicSqlSupport.method, SqlBuilder.isEqualToWhenPresent(method))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysRoleEntity> entities = sysRoleMapper.selectMany(provider);
        return entities.stream().map(SysRoleEntity::getRoleCode).toList();
    }

    @Override
    public List<SysApi> findByIdIn(Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return List.of();
        }
        List<SysApiEntity> entities = sysApiMapper.select(completer -> completer
                .where(SysApiEntityDynamicSqlSupport.id, SqlBuilder.isIn(ids))
        );
        return entities.stream().map(source -> {
            SysApi target = new SysApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysApi> findByUrlAndMethod(String url, String method) {
        if (!StringUtils.hasText(url) && !StringUtils.hasText(method)) {
            return List.of();
        }
        List<SysApiEntity> entities = sysApiMapper.select(completer -> completer
                .where(SysApiEntityDynamicSqlSupport.url, SqlBuilder.isEqualToWhenPresent(url))
                .and(SysApiEntityDynamicSqlSupport.method, SqlBuilder.isEqualToWhenPresent(method))
        );
        return entities.stream().map(source -> {
            SysApi target = new SysApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public List<SysApi> findByApiNameAndUrl(String apiName, String url) {
        if (!StringUtils.hasText(apiName) && !StringUtils.hasText(url)) {
            return List.of();
        }
        List<SysApiEntity> entities = sysApiMapper.select(completer -> completer
                .where(SysApiEntityDynamicSqlSupport.apiName, SqlBuilder.isEqualToWhenPresent(apiName))
                .and(SysApiEntityDynamicSqlSupport.url, SqlBuilder.isEqualToWhenPresent(url))
        );
        return entities.stream().map(source -> {
            SysApi target = new SysApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    @Transactional
    public List<SysApi> saveAll(Collection<SysApi> sysApis) {
        List<SysApiEntity> entities = sysApis.stream().map(source -> {
            SysApiEntity target = new SysApiEntity();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
        entities.forEach(entity -> {
            if (Objects.nonNull(entity.getId())) {
                sysApiMapper.updateByPrimaryKey(entity);
            } else {
                sysApiMapper.insert(entity);
            }
        });
        return entities.stream().map(source -> {
            SysApi target = new SysApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
