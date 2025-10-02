package com.zmya.tools.data.core.common.result;

import com.zmya.tools.data.core.common.query.PageQuery;
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

    public static <T> PageResult<T> empty() {
        return new PageResult<>();
    }

    public static <T> PageResult<T> of(PageQuery query, Long totalElements, List<T> content) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageNumber(query.getPageNumber());
        pageResult.setPageSize(query.getPageSize());
        pageResult.setTotalElements(totalElements);
        pageResult.setTotalPages((int) Math.ceil((double) totalElements / query.getPageSize()));
        pageResult.setContent(content);
        return pageResult;
    }

}
