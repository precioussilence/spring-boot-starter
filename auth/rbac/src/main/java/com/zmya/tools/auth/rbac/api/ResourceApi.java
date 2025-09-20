package com.zmya.tools.auth.rbac.api;

import com.zmya.tools.auth.rbac.model.dto.ApiDTO;
import com.zmya.tools.auth.rbac.model.dto.PageResultDTO;
import com.zmya.tools.auth.rbac.model.dto.ResourceDTO;
import com.zmya.tools.auth.rbac.model.request.*;
import com.zmya.tools.auth.rbac.model.response.ApiResponse;
import com.zmya.tools.auth.rbac.service.ApiService;
import com.zmya.tools.auth.rbac.service.ResourceService;
import com.zmya.tools.data.core.model.SysResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/sys/resource")
@AllArgsConstructor
public class ResourceApi {

    private final ResourceService resourceService;
    private final ApiService apiService;

    @PostMapping("/save")
    public ApiResponse<Boolean> save(@RequestBody SaveResourceRequest request) {
        SysResource save = resourceService.save(request);
        return ApiResponse.success(Objects.nonNull(save), "success");
    }

    @PostMapping("/remove")
    public ApiResponse<Boolean> remove(@RequestParam List<Long> userIds) {
        boolean remove = resourceService.remove(userIds);
        return ApiResponse.success(remove, "success");
    }

    @PostMapping("/modify")
    public ApiResponse<SysResource> modify(@RequestBody ModifyResourceRequest request) {
        SysResource modify = resourceService.modify(request);
        return ApiResponse.success(modify, "success");
    }

    @GetMapping("/page")
    public ApiResponse<PageResultDTO<ResourceDTO>> page(PageResourceRequest request) {
        PageResultDTO<ResourceDTO> page = resourceService.query(request);
        return ApiResponse.success(page, "success");
    }

    @GetMapping("/api/list")
    public ApiResponse<List<ApiDTO>> list(ListApiRequest request) {
        List<ApiDTO> list = apiService.list(request);
        return ApiResponse.success(list, "success");
    }

    @PostMapping("/api/save")
    public ApiResponse<Boolean> saveApi(@RequestBody SaveResourceApiRequest request) {
        boolean save = apiService.save(request);
        return ApiResponse.success(save, "success");
    }

}
