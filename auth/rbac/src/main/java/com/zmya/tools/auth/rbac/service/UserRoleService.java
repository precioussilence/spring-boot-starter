package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.entity.SysRole;
import com.zmya.tools.auth.rbac.entity.SysUser;
import com.zmya.tools.auth.rbac.entity.SysUserRole;
import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.RoleDTO;
import com.zmya.tools.auth.rbac.model.request.SaveUserRoleRequest;
import com.zmya.tools.auth.rbac.repository.SysRoleRepository;
import com.zmya.tools.auth.rbac.repository.SysUserRepository;
import com.zmya.tools.auth.rbac.repository.SysUserRoleRepository;
import com.zmya.tools.auth.rbac.utils.ModelConvertUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserRoleService {

    private final SysUserRepository sysUserRepository;
    private final SysRoleRepository sysRoleRepository;
    private final SysUserRoleRepository sysUserRoleRepository;

    public boolean save(SaveUserRoleRequest request) {
        Optional<SysUser> userOptional = sysUserRepository.findById(request.getUserId());
        if (userOptional.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_FOUND);
        }
        List<SysRole> roles = sysRoleRepository.findByIdIn(request.getRoleIds());
        if (CollectionUtils.isEmpty(roles)) {
            throw new BusinessException(ErrorCodeEnum.ROLE_NOT_FOUND);
        }
        Set<Long> existRoleIds = roles.stream().map(SysRole::getId).collect(Collectors.toSet());
        if (!existRoleIds.containsAll(request.getRoleIds())) {
            throw new BusinessException(ErrorCodeEnum.ROLE_NOT_FOUND);
        }
        List<Long> needSaveRoleIds = new ArrayList<>();
        List<SysUserRole> savedUserRoles = sysUserRoleRepository.findByUserAndRoleIn(userOptional.get(), roles);
        if (CollectionUtils.isEmpty(savedUserRoles)) {
            needSaveRoleIds.addAll(request.getRoleIds());
        } else {
            List<Long> savedUserRoleIds = savedUserRoles.stream().map(SysUserRole::getRole).map(SysRole::getId).toList();
            needSaveRoleIds.addAll(request.getRoleIds().stream().filter(roleId -> !savedUserRoleIds.contains(roleId)).toList());
        }
        if (needSaveRoleIds.isEmpty()) {
            return true;
        }
        List<SysUserRole> needSaveUserRoles = new ArrayList<>();
        needSaveRoleIds.forEach(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUser(sysUserRepository.getReferenceById(request.getUserId()));
            sysUserRole.setRole(sysRoleRepository.getReferenceById(roleId));
            needSaveUserRoles.add(sysUserRole);
        });
        List<SysUserRole> sysUserRoles = sysUserRoleRepository.saveAll(needSaveUserRoles);
        return !CollectionUtils.isEmpty(sysUserRoles);
    }

    public List<RoleDTO> list(Long userId) {
        List<SysRole> allRoles = sysRoleRepository.findAll();
        if (CollectionUtils.isEmpty(allRoles)) {
            return new ArrayList<>();
        }
        List<RoleDTO> roleDTOS = ModelConvertUtils.fromRoles(allRoles);
        List<SysUserRole> userRoles = sysUserRoleRepository.findByUser_Id(userId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return roleDTOS;
        }
        List<Long> ownedRoleIds = userRoles.stream().map(SysUserRole::getRole).map(SysRole::getId).toList();
        roleDTOS.stream().filter(role -> ownedRoleIds.contains(role.getId())).forEach(roleDTO -> roleDTO.setOwned(true));
        return roleDTOS;
    }

}
