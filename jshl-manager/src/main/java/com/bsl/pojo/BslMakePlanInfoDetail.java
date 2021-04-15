package com.bsl.pojo;

import java.util.Date;

public class BslMakePlanInfoDetail {
    private String planInfoDetailId;

    private String planId;

    private String prodNorm;

    private String prodLevel;

    private Float prodLength;

    private Float prodWeight;

    private Integer prodNum;

    private String planOutputVolume;

    private Date planFinistDate;

    private String collectedUnits;

    private String planDyz;

    private String prodInputuser;

    private Date crtDate;

    private String remark;

    public String getPlanInfoDetailId() {
        return planInfoDetailId;
    }

    public void setPlanInfoDetailId(String planInfoDetailId) {
        this.planInfoDetailId = planInfoDetailId == null ? null : planInfoDetailId.trim();
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getProdNorm() {
        return prodNorm;
    }

    public void setProdNorm(String prodNorm) {
        this.prodNorm = prodNorm == null ? null : prodNorm.trim();
    }

    public String getProdLevel() {
        return prodLevel;
    }

    public void setProdLevel(String prodLevel) {
        this.prodLevel = prodLevel == null ? null : prodLevel.trim();
    }

    public Float getProdLength() {
        return prodLength;
    }

    public void setProdLength(Float prodLength) {
        this.prodLength = prodLength;
    }

    public Float getProdWeight() {
        return prodWeight;
    }

    public void setProdWeight(Float prodWeight) {
        this.prodWeight = prodWeight;
    }

    public Integer getProdNum() {
        return prodNum;
    }

    public void setProdNum(Integer prodNum) {
        this.prodNum = prodNum;
    }

    public String getPlanOutputVolume() {
        return planOutputVolume;
    }

    public void setPlanOutputVolume(String planOutputVolume) {
        this.planOutputVolume = planOutputVolume == null ? null : planOutputVolume.trim();
    }

    public Date getPlanFinistDate() {
        return planFinistDate;
    }

    public void setPlanFinistDate(Date planFinistDate) {
        this.planFinistDate = planFinistDate;
    }

    public String getCollectedUnits() {
        return collectedUnits;
    }

    public void setCollectedUnits(String collectedUnits) {
        this.collectedUnits = collectedUnits == null ? null : collectedUnits.trim();
    }

    public String getPlanDyz() {
        return planDyz;
    }

    public void setPlanDyz(String planDyz) {
        this.planDyz = planDyz == null ? null : planDyz.trim();
    }

    public String getProdInputuser() {
        return prodInputuser;
    }

    public void setProdInputuser(String prodInputuser) {
        this.prodInputuser = prodInputuser == null ? null : prodInputuser.trim();
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