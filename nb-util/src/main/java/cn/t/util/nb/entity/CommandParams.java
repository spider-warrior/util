package cn.t.util.nb.entity;

import java.util.List;

public class CommandParams {
    private String paraName;
    private String dataType;
    private Boolean required;
    private String min;
    private String max;
    private Double step;
    private Integer maxLength;
    private String unit;
    private List<String> enumList;

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
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
        return "CommandParams{" +
            "paraName='" + paraName + '\'' +
            ", dataType='" + dataType + '\'' +
            ", required=" + required +
            ", min='" + min + '\'' +
            ", max='" + max + '\'' +
            ", step=" + step +
            ", maxLength=" + maxLength +
            ", unit='" + unit + '\'' +
            ", enumList=" + enumList +
            '}';
    }
}
