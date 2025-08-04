package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.entity.SysUser;
import com.zmya.tools.auth.rbac.repository.SysApiRepository;
import com.zmya.tools.auth.rbac.repository.SysUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorityService {

    private final SysApiRepository sysApiRepository;
    private final SysUserRepository sysUserRepository;

    public List<String> getAuthorities(String path, String method) {
        return sysApiRepository.findRequiredRoleCodes(path, method);
    }

    public UserDetails getUserDetails(String username) {
        List<SysUser> users = sysUserRepository.findByUsername(username);
        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("username not found");
        }
        SysUser user = users.getFirst();
        List<String> grantedRoleCodes = sysUserRepository.findGrantedRoleCodes(username);
        return User.withUsername(username).password(user.getPassword()).authorities(grantedRoleCodes.toArray(String[]::new)).build();
    }

}
