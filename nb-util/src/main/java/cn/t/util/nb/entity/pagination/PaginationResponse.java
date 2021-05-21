package cn.t.util.nb.entity.pagination;

import java.util.List;

public class PaginationResponse<T> {

    private Pagination pagination;
    private List<T> data;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PaginationResponse{" +
            "pagination=" + pagination +
            ", data=" + data +
            '}';
    }
}
