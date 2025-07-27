package com.zmya.tools.auth.rbac.security;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager,
                                      AuthenticationSuccessHandler authenticationSuccessHandler,
                                      AuthenticationFailureHandler authenticationFailureHandler) {
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
        setAuthenticationFailureHandler(authenticationFailureHandler);
        setRequiresAuthenticationRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/auth/signin"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        ContentCachingRequestWrapper wrappedRequest = wrapRequest(request);
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

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(wrappedRequest);
        Map<String, Object> details = new HashMap<>();
        details.put("webAuthenticationDetails", webAuthenticationDetails);
        json.forEach((key, value) -> {
            if (!key.equals(getUsernameParameter()) && !key.equals(getPasswordParameter())) {
                details.put(key, value);
            }
        });

        authRequest.setDetails(details);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        if (!wrappedRequest.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + wrappedRequest.getMethod());
        }
        if (null == wrappedRequest.getContentType() || !wrappedRequest.getContentType().equals("application/json")) {
            throw new AuthenticationServiceException("Authentication content type not supported: " + wrappedRequest.getContentType());
        }
        return wrappedRequest;
    }

}
