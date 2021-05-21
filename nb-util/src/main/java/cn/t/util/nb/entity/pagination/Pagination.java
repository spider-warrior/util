package cn.t.util.nb.entity.pagination;

public class Pagination {

    private Integer pageNo;
    private Integer pageSize;
    private Integer totalSize;

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

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return "Pagination{" +
            "pageNo=" + pageNo +
            ", pageSize=" + pageSize +
            ", totalSize=" + totalSize +
            '}';
    }
}
