package com.zmya.tools.auth.rbac.properties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
@Validated
public class JwtProperties {

    @NotEmpty
    private String secret;

    @NotNull
    private Integer expirationInSeconds;

    @NotEmpty
    private String signinUrl;

    @NotEmpty
    private String signupUrl;

    @NotEmpty
    private String usernameParameter = "username";

    @NotEmpty
    private String passwordParameter = "password";

}
