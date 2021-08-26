package com.bsl.pojo;

public class BslEnumInfo extends BslEnumInfoKey {
    private String enumChineseName;

    private String enumValue;

    private Integer enumOrder;

    public String getEnumChineseName() {
        return enumChineseName;
    }

    public void setEnumChineseName(String enumChineseName) {
        this.enumChineseName = enumChineseName == null ? null : enumChineseName.trim();
    }

    public String getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(String enumValue) {
        this.enumValue = enumValue == null ? null : enumValue.trim();
    }

    public Integer getEnumOrder() {
        return enumOrder;
    }

    public void setEnumOrder(Integer enumOrder) {
        this.enumOrder = enumOrder;
    }
}