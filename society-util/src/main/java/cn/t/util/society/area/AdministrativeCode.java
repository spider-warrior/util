package cn.t.util.society.area;

public abstract class AdministrativeCode {

    protected String name;
    protected String value;
    protected AdministrativeCode parentCode;

    public AdministrativeCode(String name, String value, AdministrativeCode parentCode) {
        this.name = name;
        this.value = value;
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AdministrativeCode getParentCode() {
        return parentCode;
    }

    public void setParentCode(AdministrativeCode parentCode) {
        this.parentCode = parentCode;
    }
}
