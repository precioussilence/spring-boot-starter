package com.zmya.tools.auth.rbac.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authorityService.getUserDetails(username);
    }

    public String loadCaptcha(String username) throws UsernameNotFoundException {
        return "123456";
    }

}
