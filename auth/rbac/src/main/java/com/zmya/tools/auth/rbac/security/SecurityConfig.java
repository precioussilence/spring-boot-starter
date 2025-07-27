package com.zmya.tools.auth.rbac.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmya.tools.auth.rbac.properties.JwtProperties;
import com.zmya.tools.auth.rbac.response.ApiResponse;
import com.zmya.tools.auth.rbac.response.SigninResponse;
import com.zmya.tools.auth.rbac.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtProperties jwtProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
                authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),
                authenticationSuccessHandler(),
                authenticationFailureHandler());
        http.addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/signup", "/auth/signin")
                .permitAll()
                .anyRequest()
                .authenticated());
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.OK.value());

            String jwtToken = JwtUtils.generateJwt((String) authentication.getPrincipal(), jwtProperties.getExpirationInSeconds(), jwtProperties.getSecret());

            List<String> authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            SigninResponse signinResponse = new SigninResponse();
            signinResponse.setToken(jwtToken);
            signinResponse.setAuthorities(authorities);

            ApiResponse<SigninResponse> apiResponse = ApiResponse.success(signinResponse, "login success");

            new ObjectMapper().writeValue(response.getWriter(), apiResponse);
        };
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            ApiResponse<Void> apiResponse = ApiResponse.error(
                    HttpStatus.UNAUTHORIZED.value(),
                    exception.getMessage()
            );

            new ObjectMapper().writeValue(response.getWriter(), apiResponse);

        };
    }
}
