package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.entity.SysResource;
import com.zmya.tools.auth.rbac.enums.ResourceStatusEnum;
import com.zmya.tools.auth.rbac.enums.ResourceTypeEnum;
import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.PageResultDTO;
import com.zmya.tools.auth.rbac.model.dto.ResourceDTO;
import com.zmya.tools.auth.rbac.model.request.ModifyResourceRequest;
import com.zmya.tools.auth.rbac.model.request.PageResourceRequest;
import com.zmya.tools.auth.rbac.model.request.SaveResourceRequest;
import com.zmya.tools.auth.rbac.repository.SysResourceRepository;
import com.zmya.tools.auth.rbac.utils.ModelConvertUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResourceService {

    private final SysResourceRepository sysResourceRepository;

    public SysResource save(SaveResourceRequest request) {
        SysResource sysResource = new SysResource();
        sysResource.setResourceName(request.getResourceName());
        sysResource.setResourceType(request.getResourceType());
        sysResource.setResourceCode(request.getResourceCode());
        sysResource.setParentId(request.getParentId());
        sysResource.setPath(request.getPath());
        sysResource.setIcon(request.getIcon());
        sysResource.setSort(request.getSort());
        sysResource.setVisible(request.getVisible());
        sysResource.setStatus(ResourceStatusEnum.NORMAL.getStatus());
        return sysResourceRepository.save(sysResource);
    }

    public boolean remove(List<Long> resourceIds) {
        sysResourceRepository.removeByIdIn((resourceIds));
        return true;
    }

    public SysResource modify(ModifyResourceRequest request) {
        Optional<SysResource> resourceOptional = sysResourceRepository.findById(request.getResourceId());
        if (resourceOptional.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.RESOURCE_NOT_FOUND);
        }
        SysResource sysResource = resourceOptional.get();
        if (StringUtils.hasText(request.getResourceName())) {
            sysResource.setResourceName(request.getResourceName());
        }
        if (StringUtils.hasText(request.getResourceCode())) {
            sysResource.setResourceCode(request.getResourceCode());
        }
        if (Objects.nonNull(ResourceTypeEnum.getByType(request.getResourceType()))) {
            sysResource.setResourceType(request.getResourceType());
        }
        if (Objects.nonNull(request.getParentId())) {
            sysResource.setParentId(request.getParentId());
        }
        if (StringUtils.hasText(request.getPath())) {
            sysResource.setPath(request.getPath());
        }
        if (StringUtils.hasText(request.getIcon())) {
            sysResource.setIcon(request.getIcon());
        }
        if (Objects.nonNull(request.getSort())) {
            sysResource.setSort(request.getSort());
        }
        if (Objects.nonNull(request.getVisible())) {
            sysResource.setVisible(request.getVisible());
        }
        return sysResourceRepository.save(sysResource);
    }

    public PageResultDTO<ResourceDTO> query(PageResourceRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<SysResource> page = sysResourceRepository.findAll(pageRequest);
        PageResultDTO<ResourceDTO> pageResultDTO = new PageResultDTO<>();
        pageResultDTO.setPageNumber(page.getNumber());
        pageResultDTO.setPageSize(page.getSize());
        pageResultDTO.setTotalElements(page.getTotalElements());
        pageResultDTO.setTotalPages(page.getTotalPages());
        pageResultDTO.setContent(ModelConvertUtils.fromResources(page.getContent()));
        return pageResultDTO;
    }

}
