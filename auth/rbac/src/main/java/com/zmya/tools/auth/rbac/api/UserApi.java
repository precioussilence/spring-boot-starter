package com.zmya.tools.auth.rbac.api;

import com.zmya.tools.auth.rbac.entity.SysUser;
import com.zmya.tools.auth.rbac.model.dto.PageResultDTO;
import com.zmya.tools.auth.rbac.model.dto.UserDTO;
import com.zmya.tools.auth.rbac.model.request.ModifyUserRequest;
import com.zmya.tools.auth.rbac.model.request.QueryUserRequest;
import com.zmya.tools.auth.rbac.model.request.SaveUserRequest;
import com.zmya.tools.auth.rbac.model.response.ApiResponse;
import com.zmya.tools.auth.rbac.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sys/user")
@AllArgsConstructor
@Validated
public class UserApi {

    private final UserService userService;

    @PostMapping("/save")
    public ApiResponse<Boolean> save(@RequestBody SaveUserRequest request) {
        SysUser save = userService.save(request);
        return ApiResponse.success(Objects.nonNull(save), "success");
    }

    @PostMapping("/remove")
    public ApiResponse<Boolean> remove(@RequestParam List<Long> userIds) {
        boolean remove = userService.remove(userIds);
        return ApiResponse.success(remove, "success");
    }

    @PostMapping("/modify")
    public ApiResponse<SysUser> modify(@RequestBody ModifyUserRequest request) {
        SysUser modify = userService.modify(request);
        return ApiResponse.success(modify, "success");
    }

    @GetMapping("/query")
    public ApiResponse<PageResultDTO<UserDTO>> query(QueryUserRequest request) {
        PageResultDTO<UserDTO> page = userService.query(request);
        return ApiResponse.success(page, "success");
    }

}
