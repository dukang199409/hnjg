package com.bsl.pojo;

import java.util.Date;

public class BslSaleInfoDetail {
    private String saleSerno;

    private String salePlanId;

    private String saleFlag;

    private Integer saleNum;

    private Float saleWeight;

    private String prodId;

    private String prodMaterial;

    private String prodNorm;

    private Float prodLength;

    private Integer prodSumnum;

    private Float prodSumweight;

    private Float salePrice;

    private String saleStatus;

    private String inputuser;

    private Date crtDate;

    private Date updDate;

    private String remark;

    private Integer prodJsnum;

    private Float prodJsweight;

    public String getSaleSerno() {
        return saleSerno;
    }

    public void setSaleSerno(String saleSerno) {
        this.saleSerno = saleSerno == null ? null : saleSerno.trim();
    }

    public String getSalePlanId() {
        return salePlanId;
    }

    public void setSalePlanId(String salePlanId) {
        this.salePlanId = salePlanId == null ? null : salePlanId.trim();
    }

    public String getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(String saleFlag) {
        this.saleFlag = saleFlag == null ? null : saleFlag.trim();
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Float getSaleWeight() {
        return saleWeight;
    }

    public void setSaleWeight(Float saleWeight) {
        this.saleWeight = saleWeight;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getProdMaterial() {
        return prodMaterial;
    }

    public void setProdMaterial(String prodMaterial) {
        this.prodMaterial = prodMaterial == null ? null : prodMaterial.trim();
    }

    public String getProdNorm() {
        return prodNorm;
    }

    public void setProdNorm(String prodNorm) {
        this.prodNorm = prodNorm == null ? null : prodNorm.trim();
    }

    public Float getProdLength() {
        return prodLength;
    }

    public void setProdLength(Float prodLength) {
        this.prodLength = prodLength;
    }

    public Integer getProdSumnum() {
        return prodSumnum;
    }

    public void setProdSumnum(Integer prodSumnum) {
        this.prodSumnum = prodSumnum;
    }

    public Float getProdSumweight() {
        return prodSumweight;
    }

    public void setProdSumweight(Float prodSumweight) {
        this.prodSumweight = prodSumweight;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus == null ? null : saleStatus.trim();
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

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getProdJsnum() {
        return prodJsnum;
    }

    public void setProdJsnum(Integer prodJsnum) {
        this.prodJsnum = prodJsnum;
    }

    public Float getProdJsweight() {
        return prodJsweight;
    }

    public void setProdJsweight(Float prodJsweight) {
        this.prodJsweight = prodJsweight;
    }
}