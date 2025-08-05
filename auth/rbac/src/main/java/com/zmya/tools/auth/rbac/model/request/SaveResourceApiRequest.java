package com.zmya.tools.auth.rbac.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SaveResourceApiRequest {

    @NotNull(message = "resourceId cannot be null")
    private Long resourceId;

    @NotEmpty(message = "apiIds cannot be null")
    private List<Long> apiIds;

}
