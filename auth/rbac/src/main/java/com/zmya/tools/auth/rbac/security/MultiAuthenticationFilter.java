package com.zmya.tools.auth.rbac.security;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class MultiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public MultiAuthenticationFilter(AuthenticationManager authenticationManager,
                                     AuthenticationSuccessHandler authenticationSuccessHandler,
                                     AuthenticationFailureHandler authenticationFailureHandler) {
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
        setAuthenticationFailureHandler(authenticationFailureHandler);
        setRequiresAuthenticationRequestMatcher(new NegatedRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/auth/signup")));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        Authentication authentication;
        if (isJwt(request)) {
            authentication = jwtAuthentication(request);
        } else if (isSignin(request)) {
            authentication = signinAuthentication(request);
        } else {
            throw new AuthenticationServiceException("Authentication type not supported");
        }

        return this.getAuthenticationManager().authenticate(authentication);
    }

    private boolean isJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        return StringUtils.hasText(headerAuth);
    }

    private boolean isSignin(HttpServletRequest request) {
        return HttpMethod.POST.name().equals(request.getMethod()) && MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType());
    }

    private Map<String, String> extractDetails(HttpServletRequest request) {
        Map<String, String> details = new HashMap<>();
        WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
        details.put("remoteAddress", webAuthenticationDetails.getRemoteAddress());
        details.put("sessionId", webAuthenticationDetails.getSessionId());
        return details;
    }

    private Authentication jwtAuthentication(HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");
        JwtAuthenticationToken unauthenticated = JwtAuthenticationToken.unauthenticated(jwtToken);
        Map<String, String> details = extractDetails(request);
        unauthenticated.setDetails(details);
        return unauthenticated;
    }

    private Authentication signinAuthentication(HttpServletRequest request) {
        Map<String, String> json;
        try {
            String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(MapperFeature.REQUIRE_HANDLERS_FOR_JAVA8_TIMES, false);
            json = objectMapper.readValue(body, Map.class);
        } catch (Exception e) {
            throw new AuthenticationServiceException("Authentication body parse failed");
        }
        String username = json.getOrDefault(getUsernameParameter(), "").trim();
        String password = json.getOrDefault(getPasswordParameter(), "");

        SigninAuthenticationToken unauthenticated = SigninAuthenticationToken.unauthenticated(username, password);
        Map<String, String> details = extractDetails(request);
        json.forEach((key, value) -> {
            if (!key.equals(getPasswordParameter())) {
                details.put(key, value);
            }
        });
        unauthenticated.setDetails(details);

        return unauthenticated;
    }

}
