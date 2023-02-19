package com.example.demo.userRolePermission.domain.req;

import java.util.Date;

public class QueryBaseReq {
    private Integer pageNumber = 1;

    private Integer pageSize = 20;

    private String sortBy;

    private boolean onlyCount = false;

    private Date createdDateBegin;

    private Date createdDateEnd;

    private Date lastUpdatedDateBegin;

    private Date lastUpdatedDateEnd;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public boolean isOnlyCount() {
        return onlyCount;
    }

    public void setOnlyCount(boolean onlyCount) {
        this.onlyCount = onlyCount;
    }

    public Date getCreatedDateBegin() {
        return createdDateBegin;
    }

    public void setCreatedDateBegin(Date createdDateBegin) {
        this.createdDateBegin = createdDateBegin;
    }

    public Date getCreatedDateEnd() {
        return createdDateEnd;
    }

    public void setCreatedDateEnd(Date createdDateEnd) {
        this.createdDateEnd = createdDateEnd;
    }

    public Date getLastUpdatedDateBegin() {
        return lastUpdatedDateBegin;
    }

    public void setLastUpdatedDateBegin(Date lastUpdatedDateBegin) {
        this.lastUpdatedDateBegin = lastUpdatedDateBegin;
    }

    public Date getLastUpdatedDateEnd() {
        return lastUpdatedDateEnd;
    }

    public void setLastUpdatedDateEnd(Date lastUpdatedDateEnd) {
        this.lastUpdatedDateEnd = lastUpdatedDateEnd;
    }
}
