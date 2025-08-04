package com.zmya.tools.auth.rbac.model.dto;

import lombok.Setter;

@Setter
public class PageQueryDTO {

    private Integer pageNumber;
    private Integer pageSize;

    public Integer getPageNumber() {
        if (this.pageNumber == null || this.pageNumber < 1) {
            return 0;
        }
        return this.pageNumber - 1;
    }

    public Integer getPageSize() {
        if (this.pageSize == null || this.pageSize < 1) {
            return 10;
        }
        return this.pageSize;
    }

}
