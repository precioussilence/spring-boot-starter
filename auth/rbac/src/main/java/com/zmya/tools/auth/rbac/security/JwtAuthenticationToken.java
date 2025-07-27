package com.zmya.tools.auth.rbac.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String jwtToken;

    private JwtAuthenticationToken(Object principal, Object credentials, String jwtToken) {
        super(principal, credentials);
        this.jwtToken = jwtToken;
    }

    private JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String jwtToken) {
        super(principal, credentials, authorities);
        this.jwtToken = jwtToken;
    }

    public static JwtAuthenticationToken unauthenticated(String jwtToken) {
        return new JwtAuthenticationToken(null, null, jwtToken);
    }

    public static JwtAuthenticationToken authenticated(Object principal, Object credentials,
                                                       Collection<? extends GrantedAuthority> authorities) {
        return new JwtAuthenticationToken(principal, credentials, authorities, null);
    }
}
