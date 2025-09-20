package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.enums.RoleStatusEnum;
import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.PageResultDTO;
import com.zmya.tools.auth.rbac.model.dto.RoleDTO;
import com.zmya.tools.auth.rbac.model.request.ModifyRoleRequest;
import com.zmya.tools.auth.rbac.model.request.PageRoleRequest;
import com.zmya.tools.auth.rbac.model.request.SaveRoleRequest;
import com.zmya.tools.auth.rbac.utils.ModelConvertUtils;
import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.dao.SysRoleDao;
import com.zmya.tools.data.core.model.SysRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final SysRoleDao sysRoleDao;

    public SysRole save(SaveRoleRequest request) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(request.getRoleName());
        sysRole.setRoleCode(request.getRoleCode());
        sysRole.setDescription(request.getDescription());
        sysRole.setStatus(RoleStatusEnum.NORMAL.getStatus());
        return sysRoleDao.save(sysRole);
    }

    public boolean remove(List<Long> roleIds) {
        sysRoleDao.removeByIdIn((roleIds));
        return true;
    }

    public SysRole modify(ModifyRoleRequest request) {
        Optional<SysRole> roleOptional = sysRoleDao.findById(request.getRoleId());
        if (roleOptional.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.ROLE_NOT_FOUND);
        }
        SysRole sysRole = roleOptional.get();
        if (StringUtils.hasText(request.getRoleName())) {
            sysRole.setRoleName(request.getRoleName());
        }
        if (StringUtils.hasText(request.getRoleCode())) {
            sysRole.setRoleCode(request.getRoleCode());
        }
        if (StringUtils.hasText(request.getDescription())) {
            sysRole.setDescription(request.getDescription());
        }
        RoleStatusEnum status = RoleStatusEnum.getByStatus(request.getStatus());
        if (Objects.nonNull(status)) {
            sysRole.setStatus(status.getStatus());
        }
        return sysRoleDao.save(sysRole);
    }

    public PageResultDTO<RoleDTO> query(PageRoleRequest request) {
        PageQuery query = PageQuery.of(request.getPageNumber(), request.getPageSize());
        PageResult<SysRole> page = sysRoleDao.findAll(query, request.getRoleName());
        PageResultDTO<RoleDTO> pageResultDTO = new PageResultDTO<>();
        pageResultDTO.setPageNumber(page.getPageNumber());
        pageResultDTO.setPageSize(page.getPageSize());
        pageResultDTO.setTotalElements(page.getTotalElements());
        pageResultDTO.setTotalPages(page.getTotalPages());
        pageResultDTO.setContent(ModelConvertUtils.fromRoles(page.getContent()));
        return pageResultDTO;
    }

}
