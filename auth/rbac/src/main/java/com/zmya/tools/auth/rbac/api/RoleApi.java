package com.zmya.tools.auth.rbac.api;

import com.zmya.tools.auth.rbac.entity.SysRole;
import com.zmya.tools.auth.rbac.model.dto.PageResultDTO;
import com.zmya.tools.auth.rbac.model.dto.ResourceDTO;
import com.zmya.tools.auth.rbac.model.dto.RoleDTO;
import com.zmya.tools.auth.rbac.model.request.ModifyRoleRequest;
import com.zmya.tools.auth.rbac.model.request.PageRoleRequest;
import com.zmya.tools.auth.rbac.model.request.SaveRoleRequest;
import com.zmya.tools.auth.rbac.model.request.SaveRoleResourceRequest;
import com.zmya.tools.auth.rbac.model.response.ApiResponse;
import com.zmya.tools.auth.rbac.service.RoleResourceService;
import com.zmya.tools.auth.rbac.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/sys/role")
@AllArgsConstructor
public class RoleApi {

    private final RoleService roleService;
    private final RoleResourceService roleResourceService;

    @PostMapping("/save")
    public ApiResponse<Boolean> save(@RequestBody SaveRoleRequest request) {
        SysRole save = roleService.save(request);
        return ApiResponse.success(Objects.nonNull(save), "success");
    }

    @PostMapping("/remove")
    public ApiResponse<Boolean> remove(@RequestParam List<Long> userIds) {
        boolean remove = roleService.remove(userIds);
        return ApiResponse.success(remove, "success");
    }

    @PostMapping("/modify")
    public ApiResponse<SysRole> modify(@RequestBody ModifyRoleRequest request) {
        SysRole modify = roleService.modify(request);
        return ApiResponse.success(modify, "success");
    }

    @GetMapping("/page")
    public ApiResponse<PageResultDTO<RoleDTO>> page(PageRoleRequest request) {
        PageResultDTO<RoleDTO> page = roleService.query(request);
        return ApiResponse.success(page, "success");
    }

    @PostMapping("/resource/save")
    public ApiResponse<Boolean> saveResource(@RequestBody SaveRoleResourceRequest request) {
        boolean save = roleResourceService.save(request);
        return ApiResponse.success(save, "success");
    }

    @GetMapping("/resource/list")
    public ApiResponse<List<ResourceDTO>> listResource(Long roleId) {
        List<ResourceDTO> page = roleResourceService.list(roleId);
        return ApiResponse.success(page, "success");
    }

}
