package cn.t.util.nb.entity;

public class ConnectionInfo {
    private String cellId;

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    @Override
    public String toString() {
        return "ConnectionInfo{" +
            "cellId='" + cellId + '\'' +
            '}';
    }
}
