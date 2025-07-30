package com.zmya.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.zmya.demo", "com.zmya.tools.*"})
@EnableJpaRepositories(basePackages = {"com.zmya.tools.auth.rbac.repository"})
@EntityScan(basePackages = {"com.zmya.tools.auth.rbac.entity"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
