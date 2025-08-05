package com.zmya.tools.auth.rbac.model.request;

import lombok.Data;

@Data
public class ListApiRequest {

    private String apiName;
    private String url;

}
