package com.zmya.tools.auth.rbac.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResultDTO<T> {

    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private List<T> content;

    public Integer getPageNumber() {
        return this.pageNumber + 1;
    }

}
