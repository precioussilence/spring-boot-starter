package com.zmya.tools.auth.rbac.security;

import com.zmya.tools.auth.rbac.properties.JwtProperties;
import com.zmya.tools.auth.rbac.service.CustomUserDetailsService;
import com.zmya.tools.auth.rbac.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

@AllArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService userDetailsService;
    private final JwtProperties properties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        String token = authenticationToken.getJwtToken();
        try {
            String username = JwtUtils.parseJwt(token, properties.getSecret());
            if (Objects.isNull(username)) {
                throw new BadCredentialsException("token expired");
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (Objects.isNull(userDetails)) {
                throw new BadCredentialsException("user not found");
            }
            return UsernamePasswordAuthenticationToken.authenticated(username, null, userDetails.getAuthorities());
        } catch (AuthenticationException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AuthenticationServiceException("Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
