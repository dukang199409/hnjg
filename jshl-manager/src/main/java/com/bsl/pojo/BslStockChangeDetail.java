package com.bsl.pojo;

import java.util.Date;

public class BslStockChangeDetail {
    private String transSerno;

    private String prodId;

    private String planSerno;

    private String transCode;

    private String prodType;

    private String rubbishType;

    private String prodOriId;

    private Float rubbishWeight;

    private Float price;

    private String targetPlace;

    private String inputuser;

    private Date crtDate;

    private String remark;

    public String getTransSerno() {
        return transSerno;
    }

    public void setTransSerno(String transSerno) {
        this.transSerno = transSerno == null ? null : transSerno.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getPlanSerno() {
        return planSerno;
    }

    public void setPlanSerno(String planSerno) {
        this.planSerno = planSerno == null ? null : planSerno.trim();
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode == null ? null : transCode.trim();
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    public String getRubbishType() {
        return rubbishType;
    }

    public void setRubbishType(String rubbishType) {
        this.rubbishType = rubbishType == null ? null : rubbishType.trim();
    }

    public String getProdOriId() {
        return prodOriId;
    }

    public void setProdOriId(String prodOriId) {
        this.prodOriId = prodOriId == null ? null : prodOriId.trim();
    }

    public Float getRubbishWeight() {
        return rubbishWeight;
    }

    public void setRubbishWeight(Float rubbishWeight) {
        this.rubbishWeight = rubbishWeight;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getTargetPlace() {
        return targetPlace;
    }

    public void setTargetPlace(String targetPlace) {
        this.targetPlace = targetPlace == null ? null : targetPlace.trim();
    }

    public String getInputuser() {
        return inputuser;
    }

    public void setInputuser(String inputuser) {
        this.inputuser = inputuser == null ? null : inputuser.trim();
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