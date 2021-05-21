package cn.t.util.nb.entity;

import java.util.List;

public class ServiceProperties {

    private String propertyName;
    private String dataType;
    private Boolean required;
    private String min;
    private String max;
    private Double step;
    private Integer maxLength;
    private String method;
    private String unit;
    private List<String> enumList;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public Double getStep() {
        return step;
    }

    public void setStep(Double step) {
        this.step = step;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getEnumList() {
        return enumList;
    }

    public void setEnumList(List<String> enumList) {
        this.enumList = enumList;
    }

    @Override
    public String toString() {
        return "ServiceProperties{" +
            "propertyName='" + propertyName + '\'' +
            ", dataType='" + dataType + '\'' +
            ", required=" + required +
            ", min='" + min + '\'' +
            ", max='" + max + '\'' +
            ", step=" + step +
            ", maxLength=" + maxLength +
            ", method='" + method + '\'' +
            ", unit='" + unit + '\'' +
            ", enumList=" + enumList +
            '}';
    }
}
