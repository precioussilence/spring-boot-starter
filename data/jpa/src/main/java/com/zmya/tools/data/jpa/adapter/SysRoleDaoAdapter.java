package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysRoleDao;
import com.zmya.tools.data.core.model.SysRole;
import com.zmya.tools.data.jpa.entity.SysRoleEntity;
import com.zmya.tools.data.jpa.repository.SysRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "jpa", matchIfMissing = true)
@AllArgsConstructor
public class SysRoleDaoAdapter implements SysRoleDao {

    private final SysRoleRepository sysRoleRepository;

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
}
