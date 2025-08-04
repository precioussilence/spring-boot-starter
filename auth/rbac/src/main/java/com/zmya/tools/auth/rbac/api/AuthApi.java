package com.zmya.tools.auth.rbac.api;

import com.zmya.tools.auth.rbac.model.request.SignupRequest;
import com.zmya.tools.auth.rbac.model.response.ApiResponse;
import com.zmya.tools.auth.rbac.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthApi {

    private final UserService userService;

    @PostMapping("signup")
    public ApiResponse<Boolean> signup(@RequestBody SignupRequest request) {
        boolean signup = userService.signup(request);
        return ApiResponse.success(signup, "signup success");
    }

}
