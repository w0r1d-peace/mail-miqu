package com.islet.domain.dto;

import lombok.Data;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liqigui
 * @date 2020-09-18 10:43
 */
@Data
@ToString
public class PageDTO extends BaseDTO {
    /**
     * currentPage 当前页
     */
    @Setter
    private Integer currentPage;

    /**
     * pageSize 每页显示的条数
     */
    @Setter
    private Integer pageSize;

    private static Integer DEFAULT_CURRENT_PAGE = 1;

    private static Integer DEFAULT_PAGE_SIZE = 10;

    private static Integer DEFAULT_MAX_PAGE_SIZE = 500;

    /**
     * 默认为1
     *
     * @return
     */
    public Integer getCurrentPage() {
        return this.currentPage == null || this.currentPage <= 0 ? DEFAULT_CURRENT_PAGE : this.currentPage;
    }

    /**
     * 默认为10，最大值为200，防止被外界乱调用
     *
     * @return
     */
    public Integer getPageSize() {
        return this.pageSize == null || this.pageSize <= 0 ? DEFAULT_PAGE_SIZE : Math.min(this.pageSize, DEFAULT_MAX_PAGE_SIZE);
    }

    public static PageDTO newBy(int currentPage) {
        return newBy(currentPage, 0);
    }

    public static PageDTO newBy(int currentPage, int pageSize) {
        PageDTO request = new PageDTO();
        request.setCurrentPage(currentPage);
        request.setPageSize(pageSize);
        return request;
    }

    public Integer getCurrentIndex() {
        return (getCurrentPage() - 1) * getPageSize();
    }
}
