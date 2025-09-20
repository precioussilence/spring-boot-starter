package com.zmya.tools.data.core.common.query;

import lombok.Setter;

@Setter
public class PageQuery {

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

    public static PageQuery of(Integer pageNumber, Integer pageSize) {
        PageQuery query = new PageQuery();
        query.setPageNumber(pageNumber);
        query.setPageSize(pageSize);
        return query;
    }

}
