package com.zmya.tools.data.core.common.result;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private List<T> content;

    public Integer getPageNumber() {
        return this.pageNumber + 1;
    }

}
