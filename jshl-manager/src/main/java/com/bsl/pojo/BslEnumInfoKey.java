package com.bsl.pojo;

public class BslEnumInfoKey {
    private String enumEnglishName;

    private String enumKey;

    public String getEnumEnglishName() {
        return enumEnglishName;
    }

    public void setEnumEnglishName(String enumEnglishName) {
        this.enumEnglishName = enumEnglishName == null ? null : enumEnglishName.trim();
    }

    public String getEnumKey() {
        return enumKey;
    }

    public void setEnumKey(String enumKey) {
        this.enumKey = enumKey == null ? null : enumKey.trim();
    }
}