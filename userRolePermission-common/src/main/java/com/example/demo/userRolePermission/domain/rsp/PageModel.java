package com.example.demo.userRolePermission.domain.rsp;

import java.util.List;

public class PageModel<T> {
    private int pageNumber = 1;

    private int pageSize = 20;

    private int totalPages = 0;

    private int startRow = 0;

    private int endRow = 0;

    private int total = 0;

    private List<T> pageData;

    public PageModel(int total, int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.total = total;
        this.init();
    }

    private void init() {
        if ((this.total % this.pageSize) == 0) {
            this.totalPages = this.total / this.pageSize;
        } else {
            this.totalPages = this.total / this.pageSize + 1;
        }

        if (this.pageNumber * this.pageSize < this.total) {
            this.endRow = this.pageNumber * this.pageSize;
            this.startRow = this.endRow - this.pageSize;
        } else {
            this.endRow = this.total;
            this.startRow = this.totalPages - 1 <= 0 ? 0 : this.pageSize * (this.totalPages - 1);
        }
    }

    public boolean hasNextPage() {
        return pageNumber >= totalPages;
    }

    public boolean hasPreviousPage() {
        return pageNumber >= 2;
    }

    public void setTotal(int total) {
        this.total = total;
        this.init();
    }

    public boolean isFirstPage() {
        return pageNumber <= 1;
    }

    public boolean isLastPage() {
        return pageNumber >= totalPages;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotal() {
        return total;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}
