package com.zmya.tools.auth.rbac.security;

import com.zmya.tools.auth.rbac.properties.JwtProperties;
import com.zmya.tools.auth.rbac.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final JwtProperties jwtProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(JwtAuthenticationProvider jwtAuthenticationProvider,
                                                       SigninAuthenticationProvider signinAuthenticationProvider) {
        return new ProviderManager(List.of(jwtAuthenticationProvider, signinAuthenticationProvider));
    }

    @Bean
    public SigninAuthenticationProvider signinAuthenticationProvider(CustomUserDetailsService userDetailsService,
                                                                     PasswordEncoder passwordEncoder) {
        return new SigninAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider(CustomUserDetailsService userDetailsService,
                                                               JwtProperties jwtProperties) {
        return new JwtAuthenticationProvider(userDetailsService, jwtProperties);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   SigninAuthenticationFilter signinAuthenticationFilter,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter,
                                                   CustomAuthorizationManager customAuthorizationManager) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(signinAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(jwtProperties.getSignupUrl(), "/sys/user/query")
                        .permitAll()
                        .anyRequest()
                        .access(customAuthorizationManager))
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

}
