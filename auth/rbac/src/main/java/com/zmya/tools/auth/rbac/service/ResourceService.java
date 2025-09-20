package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.enums.ResourceStatusEnum;
import com.zmya.tools.auth.rbac.enums.ResourceTypeEnum;
import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.PageResultDTO;
import com.zmya.tools.auth.rbac.model.dto.ResourceDTO;
import com.zmya.tools.auth.rbac.model.request.ModifyResourceRequest;
import com.zmya.tools.auth.rbac.model.request.PageResourceRequest;
import com.zmya.tools.auth.rbac.model.request.SaveResourceRequest;
import com.zmya.tools.auth.rbac.utils.ModelConvertUtils;
import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.dao.SysResourceDao;
import com.zmya.tools.data.core.model.SysResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResourceService {

    private final SysResourceDao sysResourceDao;

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
        return sysResourceDao.save(sysResource);
    }

    public boolean remove(List<Long> resourceIds) {
        sysResourceDao.removeByIdIn((resourceIds));
        return true;
    }

    public SysResource modify(ModifyResourceRequest request) {
        Optional<SysResource> resourceOptional = sysResourceDao.findById(request.getResourceId());
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
        return sysResourceDao.save(sysResource);
    }

    public PageResultDTO<ResourceDTO> query(PageResourceRequest request) {
        PageQuery pageQuery = PageQuery.of(request.getPageNumber(), request.getPageSize());
        PageResult<SysResource> page = sysResourceDao.findAll(pageQuery, request.getResourceName());
        PageResultDTO<ResourceDTO> pageResultDTO = new PageResultDTO<>();
        pageResultDTO.setPageNumber(page.getPageNumber());
        pageResultDTO.setPageSize(page.getPageSize());
        pageResultDTO.setTotalElements(page.getTotalElements());
        pageResultDTO.setTotalPages(page.getTotalPages());
        pageResultDTO.setContent(ModelConvertUtils.fromResources(page.getContent()));
        return pageResultDTO;
    }

}
