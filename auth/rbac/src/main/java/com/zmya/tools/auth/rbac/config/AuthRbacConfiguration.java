package com.zmya.tools.auth.rbac.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "tools.auth", name = "enable", havingValue = "true")
@ComponentScan(basePackages = {"com.zmya.tools.auth.rbac"})
public class AuthRbacConfiguration {
}
