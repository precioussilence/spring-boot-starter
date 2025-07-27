package com.zmya.tools.auth.rbac.api;

import com.zmya.tools.auth.rbac.request.SignupRequest;
import com.zmya.tools.auth.rbac.utils.HolderUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthApi {

    private final PasswordEncoder encoder;

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest request) {
        if (Objects.nonNull(HolderUtils.obtainUser(request.getUsername()))) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        HolderUtils.saveUser(request.getUsername(), encoder.encode(request.getPassword()));
        return ResponseEntity.ok().body(HolderUtils.obtainUser(request.getUsername()));
    }

}
