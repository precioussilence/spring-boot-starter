package com.zmya.tools.auth.rbac.utils;

import com.zmya.tools.auth.rbac.entity.SysRole;
import com.zmya.tools.auth.rbac.model.dto.RoleDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ModelConvertUtils {

    public static List<RoleDTO> from(List<SysRole> roles) {
        List<RoleDTO> list = new ArrayList<>();
        for (SysRole role : roles) {
            RoleDTO roleDTO = new RoleDTO();
            BeanUtils.copyProperties(role, roleDTO);
            list.add(roleDTO);
        }
        return list;
    }

    private ModelConvertUtils() {
        super();
    }

}
