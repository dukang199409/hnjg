package com.bsl.pojo;

public class BslCodeTableKey {
    private String menuId;

    private String enumKey;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getEnumKey() {
        return enumKey;
    }

    public void setEnumKey(String enumKey) {
        this.enumKey = enumKey == null ? null : enumKey.trim();
    }
}