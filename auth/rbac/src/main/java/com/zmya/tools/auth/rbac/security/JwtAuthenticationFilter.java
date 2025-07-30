package com.zmya.tools.auth.rbac.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final AuthenticationFailureHandler authenticationFailureHandler = new CustomAuthenticationFailureHandler();
    private final SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");
        if (!StringUtils.hasText(jwtToken)) {
            this.logger.trace("Did not process request since did not find bearer token");
            filterChain.doFilter(request, response);
            return;
        }
        JwtAuthenticationToken unauthenticated = JwtAuthenticationToken.unauthenticated(jwtToken);
        Map<String, String> details = new HashMap<>();
        WebAuthenticationDetails webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
        details.put("remoteAddress", webAuthenticationDetails.getRemoteAddress());
        details.put("sessionId", webAuthenticationDetails.getSessionId());
        unauthenticated.setDetails(details);

        try {
            Authentication authenticated = this.authenticationManager.authenticate(unauthenticated);
            SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authenticated);
            this.securityContextHolderStrategy.setContext(context);
            this.securityContextRepository.saveContext(context, request, response);

            filterChain.doFilter(request, response);
        } catch (AuthenticationException failed) {
            this.securityContextHolderStrategy.clearContext();
            this.logger.trace("Failed to process authentication request", failed);
            this.authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
        }
    }
}
