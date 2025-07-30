package com.zmya.tools.auth.rbac.api;

import com.zmya.tools.auth.rbac.entity.SysUser;
import com.zmya.tools.auth.rbac.request.SaveUserRequest;
import com.zmya.tools.auth.rbac.response.ApiResponse;
import com.zmya.tools.auth.rbac.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/sys/user")
@AllArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping("save")
    public ApiResponse<Boolean> save(@RequestBody SaveUserRequest request) {
        SysUser save = userService.save(request);
        return ApiResponse.success(Objects.nonNull(save), "success");
    }

}
