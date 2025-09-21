package com.zmya.tools.starter.auth.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zmya.tools.auth")
@Data
public class AuthProperties {

    private boolean enabled = true;
    private String tokenSecret;
    private long tokenExpiration = 86400000;

}
