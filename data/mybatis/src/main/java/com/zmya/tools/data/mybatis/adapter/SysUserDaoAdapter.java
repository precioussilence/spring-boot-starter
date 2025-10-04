package com.zmya.tools.data.mybatis.adapter;

import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.dao.SysUserDao;
import com.zmya.tools.data.core.model.SysUser;
import com.zmya.tools.data.mybatis.entity.SysRoleEntity;
import com.zmya.tools.data.mybatis.entity.SysUserEntity;
import com.zmya.tools.data.mybatis.mapper.*;
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
public class SysUserDaoAdapter implements SysUserDao {

    private final SysUserMapper sysUserMapper;
    private final SysRoleMapper sysRoleMapper;

    @Override
    public List<String> findGrantedRoleCodes(String username) {
        if (!StringUtils.hasText(username)) {
            return List.of();
        }
        SelectStatementProvider provider = SqlBuilder
                .select(SysRoleEntityDynamicSqlSupport.sysRoleEntity.roleCode)
                .from(SysUserEntityDynamicSqlSupport.sysUserEntity)
                .join(SysUserRoleEntityDynamicSqlSupport.sysUserRoleEntity)
                .on(SysUserEntityDynamicSqlSupport.id, SqlBuilder.equalTo(SysUserRoleEntityDynamicSqlSupport.userId))
                .join(SysRoleEntityDynamicSqlSupport.sysRoleEntity)
                .on(SysUserRoleEntityDynamicSqlSupport.roleId, SqlBuilder.equalTo(SysRoleEntityDynamicSqlSupport.id))
                .where(SysUserEntityDynamicSqlSupport.username, SqlBuilder.isEqualToWhenPresent(username))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysRoleEntity> entities = sysRoleMapper.selectMany(provider);
        return entities.stream().map(SysRoleEntity::getRoleCode).toList();
    }

    @Override
    public void removeByIdIn(Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        ids.forEach(sysUserMapper::deleteByPrimaryKey);
    }

    @Override
    public List<SysUser> findByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return List.of();
        }
        SelectStatementProvider provider = SqlBuilder
                .select(SysUserEntityDynamicSqlSupport.sysUserEntity.allColumns())
                .from(SysUserEntityDynamicSqlSupport.sysUserEntity)
                .where(SysUserEntityDynamicSqlSupport.username, SqlBuilder.isEqualToWhenPresent(username))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysUserEntity> entities = sysUserMapper.selectMany(provider);
        return entities.stream().map(source -> {
            SysUser target = new SysUser();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    public Optional<SysUser> findById(Long id) {
        Optional<SysUserEntity> entity = sysUserMapper.selectByPrimaryKey(id);
        return entity.map(source -> {
            SysUser target = new SysUser();
            BeanUtils.copyProperties(source, target);
            return target;
        });
    }

    @Override
    public PageResult<SysUser> findAll(PageQuery query, String username, String nickname, String phone, String email) {
        boolean queryAll = !StringUtils.hasText(username)
                && !StringUtils.hasText(nickname)
                && !StringUtils.hasText(phone)
                && !StringUtils.hasText(email);
        long limit = Objects.isNull(query) ? 10L : query.getPageSize();
        long offset = Objects.isNull(query) ? 0L : query.getPageNumber() * limit;
        long count = sysUserMapper.count(completer -> queryAll ?
                completer
                :
                completer
                        .where(SysUserEntityDynamicSqlSupport.username, SqlBuilder.isEqualToWhenPresent(username))
                        .and(SysUserEntityDynamicSqlSupport.nickname, SqlBuilder.isEqualToWhenPresent(nickname))
                        .and(SysUserEntityDynamicSqlSupport.phone, SqlBuilder.isEqualToWhenPresent(phone))
                        .and(SysUserEntityDynamicSqlSupport.email, SqlBuilder.isEqualToWhenPresent(email))
        );
        if (count == 0L) {
            return PageResult.of(query, count, List.of());
        }
        List<SysUserEntity> entities = sysUserMapper.select(completer -> queryAll ?
                completer
                        .limit(limit)
                        .offset(offset)
                :
                completer
                        .where(SysUserEntityDynamicSqlSupport.username, SqlBuilder.isEqualToWhenPresent(username))
                        .and(SysUserEntityDynamicSqlSupport.nickname, SqlBuilder.isEqualToWhenPresent(nickname))
                        .and(SysUserEntityDynamicSqlSupport.phone, SqlBuilder.isEqualToWhenPresent(phone))
                        .and(SysUserEntityDynamicSqlSupport.email, SqlBuilder.isEqualToWhenPresent(email))
                        .limit(limit)
                        .offset(offset)
        );
        List<SysUser> users = entities.stream().map(source -> {
            SysUser target = new SysUser();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
        return PageResult.of(query, count, users);
    }

    @Override
    public SysUser save(SysUser sysUser) {
        SysUserEntity target = new SysUserEntity();
        BeanUtils.copyProperties(sysUser, target);
        sysUserMapper.insert(target);
        sysUser.setId(target.getId());
        return sysUser;
    }
}
