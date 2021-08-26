package com.bsl.pojo;

import java.util.Date;

public class BslWasteWeightInfo {
    private String wasteType;

    private Float wasteWeight;

    private Float wasteInWeight;

    private Float wasteOutWeight;

    private Date crtDate;

    private String remark;

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType == null ? null : wasteType.trim();
    }

    public Float getWasteWeight() {
        return wasteWeight;
    }

    public void setWasteWeight(Float wasteWeight) {
        this.wasteWeight = wasteWeight;
    }

    public Float getWasteInWeight() {
        return wasteInWeight;
    }

    public void setWasteInWeight(Float wasteInWeight) {
        this.wasteInWeight = wasteInWeight;
    }

    public Float getWasteOutWeight() {
        return wasteOutWeight;
    }

    public void setWasteOutWeight(Float wasteOutWeight) {
        this.wasteOutWeight = wasteOutWeight;
    }

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