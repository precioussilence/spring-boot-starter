package com.zmya.tools.data.jpa.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ConditionalOnProperty(prefix = "tools.auth", name = "orm-type", havingValue = "jpa")
@EnableJpaRepositories(basePackages = {"com.zmya.tools.data.jpa.repository"})
@EntityScan(basePackages = {"com.zmya.tools.data.jpa.entity"})
@ComponentScan(basePackages = {"com.zmya.tools.data.jpa.adapter"})
public class JpaDataConfiguration {
}
