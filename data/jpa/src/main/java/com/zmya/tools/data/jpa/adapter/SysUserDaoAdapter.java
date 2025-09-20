package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.dao.SysUserDao;
import com.zmya.tools.data.core.model.SysUser;
import com.zmya.tools.data.jpa.entity.SysUserEntity;
import com.zmya.tools.data.jpa.repository.SysUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@ConditionalOnProperty(name = "zmya.data.impl", havingValue = "jpa", matchIfMissing = true)
@AllArgsConstructor
public class SysUserDaoAdapter implements SysUserDao {

    private final SysUserRepository sysUserRepository;

    @Override
    public List<String> findGrantedRoleCodes(String username) {
        return sysUserRepository.findGrantedRoleCodes(username);
    }

    @Override
    public void removeByIdIn(Collection<Long> ids) {
        sysUserRepository.removeByIdIn(ids);
    }

    @Override
    public List<SysUser> findByUsername(String username) {
        List<SysUserEntity> list = sysUserRepository.findByUsername(username);
        return list.stream().map(source -> {
            SysUser target = new SysUser();
            BeanUtils.copyProperties(source, target);
            return target;
        }).toList();
    }
}
