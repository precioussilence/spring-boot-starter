package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.utils.HolderUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // todo: load user from db
        Map<String, String> user = HolderUtils.obtainUser(username);
        if (user == null) {
            return null;
        } else {
            return User.builder()
                    .username(username)
                    .password(user.get(username))
                    .build();
        }
    }

    public String loadCaptcha(String username) throws UsernameNotFoundException {
        return "123456";
    }

}
