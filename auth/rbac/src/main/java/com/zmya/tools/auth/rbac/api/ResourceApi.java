package com.zmya.tools.auth.rbac.api;

import com.zmya.tools.auth.rbac.entity.SysResource;
import com.zmya.tools.auth.rbac.model.dto.PageResultDTO;
import com.zmya.tools.auth.rbac.model.dto.ResourceDTO;
import com.zmya.tools.auth.rbac.model.request.ModifyResourceRequest;
import com.zmya.tools.auth.rbac.model.request.QueryResourceRequest;
import com.zmya.tools.auth.rbac.model.request.SaveResourceRequest;
import com.zmya.tools.auth.rbac.model.response.ApiResponse;
import com.zmya.tools.auth.rbac.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/sys/resource")
@AllArgsConstructor
public class ResourceApi {

    private final ResourceService resourceService;

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

    @GetMapping("/query")
    public ApiResponse<PageResultDTO<ResourceDTO>> query(QueryResourceRequest request) {
        PageResultDTO<ResourceDTO> page = resourceService.query(request);
        return ApiResponse.success(page, "success");
    }

}
