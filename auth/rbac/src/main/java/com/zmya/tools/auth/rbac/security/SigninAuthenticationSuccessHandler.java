package com.zmya.tools.auth.rbac.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmya.tools.auth.rbac.properties.JwtProperties;
import com.zmya.tools.auth.rbac.response.ApiResponse;
import com.zmya.tools.auth.rbac.response.SigninResponse;
import com.zmya.tools.auth.rbac.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@AllArgsConstructor
public class SigninAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProperties jwtProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.OK.value());
        String username = authentication.getPrincipal().toString();
        String jwtToken = JwtUtils.generateJwt(username, jwtProperties.getExpirationInSeconds(), jwtProperties.getSecret());
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        SigninResponse signinResponse = new SigninResponse();
        signinResponse.setToken(jwtToken);
        signinResponse.setAuthorities(authorities);

        ApiResponse<SigninResponse> apiResponse = ApiResponse.success(signinResponse, "login success");

        new ObjectMapper().writeValue(response.getWriter(), apiResponse);
    }
}
