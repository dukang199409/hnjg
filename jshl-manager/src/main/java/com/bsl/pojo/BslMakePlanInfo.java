package com.bsl.pojo;

import java.util.Date;

public class BslMakePlanInfo {
    private String planId;

    private String planFlag;

    private String company;

    private String planDepartment;

    private String planJz;

    private String planLuno;

    private String planStatus;

    private String prodNorm;

    private Float prodWeight;

    private Integer prodNum;

    private String prodMaterial;

    private String prodType;

    private String makeType;

    private String prodOrder;

    private String makeName;

    private String makeProdNorm;

    private String customer;

    private Integer alreadyNum;

    private Float alreadySum;

    private String inputuser;

    private String checkuser;

    private Date crtDate;

    private String remark;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getPlanFlag() {
        return planFlag;
    }

    public void setPlanFlag(String planFlag) {
        this.planFlag = planFlag == null ? null : planFlag.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getPlanDepartment() {
        return planDepartment;
    }

    public void setPlanDepartment(String planDepartment) {
        this.planDepartment = planDepartment == null ? null : planDepartment.trim();
    }

    public String getPlanJz() {
        return planJz;
    }

    public void setPlanJz(String planJz) {
        this.planJz = planJz == null ? null : planJz.trim();
    }

    public String getPlanLuno() {
        return planLuno;
    }

    public void setPlanLuno(String planLuno) {
        this.planLuno = planLuno == null ? null : planLuno.trim();
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus == null ? null : planStatus.trim();
    }

    public String getProdNorm() {
        return prodNorm;
    }

    public void setProdNorm(String prodNorm) {
        this.prodNorm = prodNorm == null ? null : prodNorm.trim();
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

    public String getProdMaterial() {
        return prodMaterial;
    }

    public void setProdMaterial(String prodMaterial) {
        this.prodMaterial = prodMaterial == null ? null : prodMaterial.trim();
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    public String getMakeType() {
        return makeType;
    }

    public void setMakeType(String makeType) {
        this.makeType = makeType == null ? null : makeType.trim();
    }

    public String getProdOrder() {
        return prodOrder;
    }

    public void setProdOrder(String prodOrder) {
        this.prodOrder = prodOrder == null ? null : prodOrder.trim();
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName == null ? null : makeName.trim();
    }

    public String getMakeProdNorm() {
        return makeProdNorm;
    }

    public void setMakeProdNorm(String makeProdNorm) {
        this.makeProdNorm = makeProdNorm == null ? null : makeProdNorm.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public Integer getAlreadyNum() {
        return alreadyNum;
    }

    public void setAlreadyNum(Integer alreadyNum) {
        this.alreadyNum = alreadyNum;
    }

    public Float getAlreadySum() {
        return alreadySum;
    }

    public void setAlreadySum(Float alreadySum) {
        this.alreadySum = alreadySum;
    }

    public String getInputuser() {
        return inputuser;
    }

    public void setInputuser(String inputuser) {
        this.inputuser = inputuser == null ? null : inputuser.trim();
    }

    public String getCheckuser() {
        return checkuser;
    }

    public void setCheckuser(String checkuser) {
        this.checkuser = checkuser == null ? null : checkuser.trim();
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
