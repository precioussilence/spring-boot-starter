package com.zmya.tools.auth.rbac.utils;

import com.zmya.tools.auth.rbac.model.dto.ResourceDTO;
import com.zmya.tools.auth.rbac.model.dto.RoleDTO;
import com.zmya.tools.data.core.model.SysResource;
import com.zmya.tools.data.core.model.SysRole;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ModelConvertUtils {

    public static List<RoleDTO> fromRoles(List<SysRole> roles) {
        List<RoleDTO> list = new ArrayList<>();
        for (SysRole role : roles) {
            RoleDTO roleDTO = new RoleDTO();
            BeanUtils.copyProperties(role, roleDTO);
            list.add(roleDTO);
        }
        return list;
    }

    public static List<ResourceDTO> fromResources(List<SysResource> resources) {
        List<ResourceDTO> list = new ArrayList<>();
        for (SysResource resource : resources) {
            ResourceDTO resourceDTO = new ResourceDTO();
            BeanUtils.copyProperties(resource, resourceDTO);
            list.add(resourceDTO);
        }
        return list;
    }

    private ModelConvertUtils() {
        super();
    }

}
