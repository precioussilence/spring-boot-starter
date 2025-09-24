package com.zmya.tools.data.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "tools.auth", name = "orm-type", havingValue = "mybatis")
@MapperScan(basePackages = {"com.zmya.tools.data.mybatis.mapper"})
@ComponentScan(basePackages = {"com.zmya.tools.data.mybatis.adapter"})
public class MybatisDataConfiguration {
}
