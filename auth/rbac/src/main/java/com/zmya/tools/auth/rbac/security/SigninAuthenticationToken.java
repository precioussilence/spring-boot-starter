package com.zmya.tools.auth.rbac.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SigninAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private SigninAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    private SigninAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static SigninAuthenticationToken unauthenticated(Object principal, Object credentials) {
        return new SigninAuthenticationToken(principal, credentials);
    }

    public static SigninAuthenticationToken authenticated(Object principal, Object credentials,
                                                          Collection<? extends GrantedAuthority> authorities) {
        return new SigninAuthenticationToken(principal, credentials, authorities);
    }
}
