package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.RoleDTO;
import com.zmya.tools.auth.rbac.model.request.SaveUserRoleRequest;
import com.zmya.tools.auth.rbac.utils.ModelConvertUtils;
import com.zmya.tools.data.core.dao.SysRoleDao;
import com.zmya.tools.data.core.dao.SysUserDao;
import com.zmya.tools.data.core.dao.SysUserRoleDao;
import com.zmya.tools.data.core.model.SysRole;
import com.zmya.tools.data.core.model.SysUser;
import com.zmya.tools.data.core.model.SysUserRole;
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

    private final SysUserDao sysUserDao;
    private final SysRoleDao sysRoleDao;
    private final SysUserRoleDao sysUserRoleDao;

    public boolean save(SaveUserRoleRequest request) {
        Optional<SysUser> userOptional = sysUserDao.findById(request.getUserId());
        if (userOptional.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_FOUND);
        }
        List<SysRole> roles = sysRoleDao.findByIdIn(request.getRoleIds());
        if (CollectionUtils.isEmpty(roles)) {
            throw new BusinessException(ErrorCodeEnum.ROLE_NOT_FOUND);
        }
        Set<Long> existRoleIds = roles.stream().map(SysRole::getId).collect(Collectors.toSet());
        if (!existRoleIds.containsAll(request.getRoleIds())) {
            throw new BusinessException(ErrorCodeEnum.ROLE_NOT_FOUND);
        }
        List<Long> needSaveRoleIds = new ArrayList<>();
        List<Long> roleIds = roles.stream().map(SysRole::getId).toList();
        List<SysUserRole> savedUserRoles = sysUserRoleDao.findByUserAndRoleIn(userOptional.get().getId(), roleIds);
        if (CollectionUtils.isEmpty(savedUserRoles)) {
            needSaveRoleIds.addAll(request.getRoleIds());
        } else {
            List<Long> savedUserRoleIds = savedUserRoles.stream().map(SysUserRole::getRoleId).toList();
            needSaveRoleIds.addAll(request.getRoleIds().stream().filter(roleId -> !savedUserRoleIds.contains(roleId)).toList());
        }
        if (needSaveRoleIds.isEmpty()) {
            return true;
        }
        List<SysUserRole> needSaveUserRoles = new ArrayList<>();
        needSaveRoleIds.forEach(roleId -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(request.getUserId());
            sysUserRole.setRoleId(roleId);
            needSaveUserRoles.add(sysUserRole);
        });
        List<SysUserRole> sysUserRoles = sysUserRoleDao.saveAll(needSaveUserRoles);
        return !CollectionUtils.isEmpty(sysUserRoles);
    }

    public List<RoleDTO> list(Long userId) {
        List<SysRole> allRoles = sysRoleDao.findAll();
        if (CollectionUtils.isEmpty(allRoles)) {
            return new ArrayList<>();
        }
        List<RoleDTO> roleDTOS = ModelConvertUtils.fromRoles(allRoles);
        List<SysUserRole> userRoles = sysUserRoleDao.findByUserId(userId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return roleDTOS;
        }
        List<Long> ownedRoleIds = userRoles.stream().map(SysUserRole::getRoleId).toList();
        roleDTOS.stream().filter(role -> ownedRoleIds.contains(role.getId())).forEach(roleDTO -> roleDTO.setOwned(true));
        return roleDTOS;
    }

}
