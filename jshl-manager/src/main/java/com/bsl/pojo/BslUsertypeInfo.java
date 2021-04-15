package com.bsl.pojo;

import java.util.Date;

public class BslUsertypeInfo extends BslUsertypeInfoKey {
    private Date crtDate;

    private String remark;

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}