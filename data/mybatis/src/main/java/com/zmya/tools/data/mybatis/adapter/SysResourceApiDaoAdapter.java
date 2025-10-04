package com.zmya.tools.data.mybatis.adapter;

import com.zmya.tools.data.core.dao.SysResourceApiDao;
import com.zmya.tools.data.core.model.SysResourceApi;
import com.zmya.tools.data.mybatis.entity.SysResourceApiEntity;
import com.zmya.tools.data.mybatis.mapper.SysResourceApiEntityDynamicSqlSupport;
import com.zmya.tools.data.mybatis.mapper.SysResourceApiMapper;
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
public class SysResourceApiDaoAdapter implements SysResourceApiDao {

    private final SysResourceApiMapper sysResourceApiMapper;

    @Override
    public List<SysResourceApi> findByResourceAndApiIn(Long resourceId, Collection<Long> apiIds) {
        if (Objects.isNull(resourceId) && CollectionUtils.isEmpty(apiIds)) {
            return List.of();
        }
        List<SysResourceApiEntity> entities = sysResourceApiMapper.select(completer -> completer
                .where(SysResourceApiEntityDynamicSqlSupport.resourceId, SqlBuilder.isEqualToWhenPresent(resourceId))
                .and(SysResourceApiEntityDynamicSqlSupport.apiId, SqlBuilder.isInWhenPresent(apiIds))
        );
        return entities.stream().map(source -> {
            SysResourceApi target = new SysResourceApi();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }

    @Override
    @Transactional
    public List<SysResourceApi> saveAll(List<SysResourceApi> sysResourceApis) {
        if (CollectionUtils.isEmpty(sysResourceApis)) {
            return List.of();
        }
        sysResourceApis.forEach(source -> {
            SysResourceApiEntity target = new SysResourceApiEntity();
            BeanUtils.copyProperties(source, target);
            sysResourceApiMapper.insert(target);
            source.setId(target.getId());
        });
        return sysResourceApis;
    }
}
