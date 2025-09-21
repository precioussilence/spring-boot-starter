package com.zmya.tools.starter.auth.autoconfigure;

import com.zmya.tools.auth.rbac.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(UserService.class)
@EnableConfigurationProperties(AuthProperties.class)
@ComponentScan("com.zmya.tools.auth.rbac")
public class AuthAutoConfiguration {



}
