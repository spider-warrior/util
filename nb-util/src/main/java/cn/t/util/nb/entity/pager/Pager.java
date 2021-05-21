package cn.t.util.nb.entity.pager;

public abstract class Pager {
    private Integer totalCount;
    private Integer pageNo;
    private Integer pageSize;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Pager{" +
            "totalCount=" + totalCount +
            ", pageNo=" + pageNo +
            ", pageSize=" + pageSize +
            '}';
    }
}
