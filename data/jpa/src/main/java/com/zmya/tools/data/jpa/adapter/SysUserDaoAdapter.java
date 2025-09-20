package com.zmya.tools.data.jpa.adapter;

import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.dao.SysUserDao;
import com.zmya.tools.data.core.model.SysUser;
import com.zmya.tools.data.jpa.entity.SysUserEntity;
import com.zmya.tools.data.jpa.repository.SysUserRepository;
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

    @Override
    public Optional<SysUser> findById(Long id) {
        Optional<SysUserEntity> optional = sysUserRepository.findById(id);
        return optional.map(source -> {
            SysUser target = new SysUser();
            BeanUtils.copyProperties(source, target);
            return target;
        });
    }

    @Override
    public PageResult<SysUser> findAll(PageQuery pageQuery, String username, String nickname, String phone, String email) {
        Specification<SysUserEntity> spec = Specification.unrestricted();
        if (StringUtils.hasText(username)) {
            spec.and((root, query, builder) -> builder.equal(root.get("username"), username));
        }
        if (StringUtils.hasText(nickname)) {
            spec.and((root, query, builder) -> builder.equal(root.get("nickname"), nickname));
        }
        if (StringUtils.hasText(phone)) {
            spec.and((root, query, builder) -> builder.equal(root.get("phone"), phone));
        }
        if (StringUtils.hasText(email)) {
            spec.and((root, query, builder) -> builder.equal(root.get("email"), email));
        }
        PageRequest pageRequest = PageRequest.of(pageQuery.getPageNumber(), pageQuery.getPageSize());
        Page<SysUserEntity> page = sysUserRepository.findAll(spec, pageRequest);
        PageResult<SysUser> result = new PageResult<>();
        result.setPageNumber(page.getNumber());
        result.setPageSize(page.getSize());
        result.setTotalElements(page.getTotalElements());
        result.setTotalPages(page.getTotalPages());
        result.setContent(from(page.getContent()));
        return result;
    }

    @Override
    public SysUser save(SysUser sysUser) {
        SysUserEntity entity = new SysUserEntity();
        BeanUtils.copyProperties(sysUser, entity);
        SysUserEntity saved = sysUserRepository.save(entity);
        return from(saved);
    }

    private List<SysUser> from(List<SysUserEntity> entities) {
        return entities.stream().map(this::from).toList();
    }

    private SysUser from(SysUserEntity entity) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(entity, sysUser);
        return sysUser;
    }
}
