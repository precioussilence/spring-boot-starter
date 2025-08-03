package com.zmya.tools.auth.rbac.security;

import com.zmya.tools.auth.rbac.service.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Component
@AllArgsConstructor
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final AuthorityService authorityService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        Authentication auth = authentication.get();
        if (Objects.isNull(auth) || !auth.isAuthenticated() || CollectionUtils.isEmpty(auth.getAuthorities())) {
            return new AuthorizationDecision(false);
        }
        List<String> grantedAuthorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        String path = context.getRequest().getServletPath();
        String method = context.getRequest().getMethod();
        List<String> requiredAuthorities = authorityService.getAuthorities(path, method);
        boolean hasAuthority = requiredAuthorities.stream().anyMatch(grantedAuthorities::contains);
        return new AuthorizationDecision(hasAuthority);
    }
}
