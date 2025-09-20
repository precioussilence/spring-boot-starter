package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.data.core.dao.SysApiDao;
import com.zmya.tools.data.core.dao.SysUserDao;
import com.zmya.tools.data.core.model.SysUser;
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

    private final SysApiDao sysApiDao;
    private final SysUserDao sysUserDao;

    public List<String> getAuthorities(String path, String method) {
        return sysApiDao.findRequiredRoleCodes(path, method);
    }

    public UserDetails getUserDetails(String username) {
        List<SysUser> users = sysUserDao.findByUsername(username);
        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("username not found");
        }
        SysUser user = users.getFirst();
        List<String> grantedRoleCodes = sysUserDao.findGrantedRoleCodes(username);
        return User.withUsername(username).password(user.getPassword()).authorities(grantedRoleCodes.toArray(String[]::new)).build();
    }

}
