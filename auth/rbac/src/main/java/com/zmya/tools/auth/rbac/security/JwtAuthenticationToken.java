package com.zmya.tools.auth.rbac.security;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String jwtToken;
    private final String username;

    private JwtAuthenticationToken(String jwtToken) {
        super(Collections.emptyList());
        this.jwtToken = jwtToken;
        this.username = null;
    }

    private JwtAuthenticationToken(String username, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.jwtToken = null;
        this.username = username;
    }

    public static JwtAuthenticationToken unauthenticated(String jwtToken) {
        return new JwtAuthenticationToken(jwtToken);
    }

    public static JwtAuthenticationToken authenticated(String username,
                                                       Collection<? extends GrantedAuthority> authorities) {
        return new JwtAuthenticationToken(username, authorities);
    }

    @Override
    public Object getCredentials() {
        return this.jwtToken;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }
}
