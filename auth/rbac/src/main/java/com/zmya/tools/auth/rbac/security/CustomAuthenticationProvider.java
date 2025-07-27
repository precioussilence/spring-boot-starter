package com.zmya.tools.auth.rbac.security;

import com.zmya.tools.auth.rbac.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        Map<String, String> details = (Map<String, String>) authentication.getDetails();
        if (!validateCaptcha(username, details.get("captcha"))) {
            throw new BadCredentialsException("captcha is invalid or expired");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails) {
            throw new BadCredentialsException("user not found");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("wrong username or password");
        }

        return new UsernamePasswordAuthenticationToken(
                username,
                password,
                userDetails.getAuthorities()
        );
    }

    private boolean validateCaptcha(String username, String captcha) {
        return userDetailsService.loadCaptcha(username).equals(captcha);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
