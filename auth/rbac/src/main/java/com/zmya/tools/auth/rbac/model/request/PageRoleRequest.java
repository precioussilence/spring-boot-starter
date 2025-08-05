package com.zmya.tools.auth.rbac.model.request;

import com.zmya.tools.auth.rbac.model.dto.PageQueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageRoleRequest extends PageQueryDTO {

    private String roleName;

}
