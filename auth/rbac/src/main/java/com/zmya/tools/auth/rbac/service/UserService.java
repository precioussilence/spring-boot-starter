package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.entity.SysUser;
import com.zmya.tools.auth.rbac.repository.SysUserRepository;
import com.zmya.tools.auth.rbac.request.SaveUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final SysUserRepository sysUserRepository;

    public SysUser save(SaveUserRequest request) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(request.getUsername());
        sysUser.setPassword(request.getPassword());
        sysUser.setNickname(request.getNickname());
        sysUser.setEmail(request.getEmail());
        sysUser.setPhone(request.getPhone());
        sysUser.setAvatar(request.getAvatar());
        return sysUserRepository.save(sysUser);
    }

}
