package com.islet.domain.vo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageVO<T> {
    private static final PageVO<?> EMPTY_PAGE = new PageVO();
    private Long totalPage;
    private Long totalCount;
    private Long currentPage;
    private Long pageSize;
    private List<T> list;

    public <R> PageVO<R> map(Function<T, R> mapper) {
        PageVO<R> pageVO = new PageVO();
        pageVO.setCurrentPage(this.getCurrentPage());
        pageVO.setPageSize(this.getPageSize());
        pageVO.setTotalPage(this.getTotalPage());
        pageVO.setTotalCount(this.getTotalCount());
        pageVO.setList((List)this.getList().stream().map(mapper).collect(Collectors.toList()));
        return pageVO;
    }

    public void forEach(Consumer<T> action) {
        this.getList().forEach(action);
    }

    public PageVO<T> peek(Consumer<T> action) {
        this.forEach(action);
        return this;
    }

    public Long getTotalPage() {
        return (Long)Optional.ofNullable(this.totalPage).orElse(0L);
    }

    public Long getTotalCount() {
        return (Long)Optional.ofNullable(this.totalCount).orElse(0L);
    }

    public Long getCurrentPage() {
        return (Long)Optional.ofNullable(this.currentPage).orElse(1L);
    }

    public Long getPageSize() {
        return (Long)Optional.ofNullable(this.pageSize).orElse(10L);
    }

    public List<T> getList() {
        return (List)Optional.ofNullable(this.list).orElse(Collections.emptyList());
    }

    public PageVO() {
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String toString() {
        return "PageVO(totalPage=" + this.getTotalPage() + ", totalCount=" + this.getTotalCount() + ", list=" + this.getList() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageVO)) {
            return false;
        } else {
            PageVO<?> other = (PageVO)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                label49: {
                    Object this$totalPage = this.getTotalPage();
                    Object other$totalPage = other.getTotalPage();
                    if (this$totalPage == null) {
                        if (other$totalPage == null) {
                            break label49;
                        }
                    } else if (this$totalPage.equals(other$totalPage)) {
                        break label49;
                    }

                    return false;
                }

                Object this$totalCount = this.getTotalCount();
                Object other$totalCount = other.getTotalCount();
                if (this$totalCount == null) {
                    if (other$totalCount != null) {
                        return false;
                    }
                } else if (!this$totalCount.equals(other$totalCount)) {
                    return false;
                }

                Object this$list = this.getList();
                Object other$list = other.getList();
                if (this$list == null) {
                    if (other$list != null) {
                        return false;
                    }
                } else if (!this$list.equals(other$list)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageVO;
    }

    public int hashCode() {
        int result = super.hashCode();
        Object $totalPage = this.getTotalPage();
        result = result * 59 + ($totalPage == null ? 43 : $totalPage.hashCode());
        Object $totalCount = this.getTotalCount();
        result = result * 59 + ($totalCount == null ? 43 : $totalCount.hashCode());
        Object $list = this.getList();
        result = result * 59 + ($list == null ? 43 : $list.hashCode());
        return result;
    }
}
