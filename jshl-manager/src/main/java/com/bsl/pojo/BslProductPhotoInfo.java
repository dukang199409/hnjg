package com.bsl.pojo;

import java.util.Date;

public class BslProductPhotoInfo {
    private Date transDate;

    private String prodId;

    private String prodStatus;

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus == null ? null : prodStatus.trim();
    }
}