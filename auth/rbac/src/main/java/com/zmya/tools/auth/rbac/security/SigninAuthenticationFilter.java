package com.zmya.tools.auth.rbac.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zmya.tools.auth.rbac.properties.JwtProperties;
import com.zmya.tools.auth.rbac.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class SigninAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final JwtProperties jwtProperties;

    public SigninAuthenticationFilter(AuthenticationManager authenticationManager,
                                      JwtProperties jwtProperties) {
        super(jwtProperties.getSigninUrl(), authenticationManager);
        this.jwtProperties = jwtProperties;
        setAuthenticationSuccessHandler(new SigninAuthenticationSuccessHandler(jwtProperties));
        setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        Authentication authentication;
        if (postJson(request)) {
            authentication = signinAuthentication(request);
        } else {
            throw new AuthenticationServiceException("Authentication type not supported");
        }

        return this.getAuthenticationManager().authenticate(authentication);
    }

    private boolean postJson(HttpServletRequest request) {
        return HttpMethod.POST.name().equals(request.getMethod())
                && MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType());
    }

    private Map<String, String> extractDetails(HttpServletRequest request) {
        Map<String, String> details = new HashMap<>();
        WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
        details.put("remoteAddress", webAuthenticationDetails.getRemoteAddress());
        details.put("sessionId", webAuthenticationDetails.getSessionId());
        return details;
    }

    private Authentication signinAuthentication(HttpServletRequest request) {
        Map<String, String> json;
        try {
            String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            json = JsonUtils.fromJson(body, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new AuthenticationServiceException("Authentication body parse failed");
        }
        String username = json.getOrDefault(jwtProperties.getUsernameParameter(), "").trim();
        String password = json.getOrDefault(jwtProperties.getPasswordParameter(), "").trim();

        SigninAuthenticationToken unauthenticated = SigninAuthenticationToken.unauthenticated(username, password);
        Map<String, String> details = extractDetails(request);
        json.forEach((key, value) -> {
            if (!key.equals(jwtProperties.getPasswordParameter())) {
                details.put(key, value);
            }
        });
        unauthenticated.setDetails(details);

        return unauthenticated;
    }

}
