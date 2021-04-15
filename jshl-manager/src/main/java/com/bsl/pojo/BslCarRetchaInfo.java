package com.bsl.pojo;

public class BslCarRetchaInfo {
    private String carNo;

    private Float retWeight;

    private Integer printNum;

    private Float chaWeight;

    private String bsGettype;

    private String wxFlag;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Float getRetWeight() {
        return retWeight;
    }

    public void setRetWeight(Float retWeight) {
        this.retWeight = retWeight;
    }

    public Integer getPrintNum() {
        return printNum;
    }

    public void setPrintNum(Integer printNum) {
        this.printNum = printNum;
    }

    public Float getChaWeight() {
        return chaWeight;
    }

    public void setChaWeight(Float chaWeight) {
        this.chaWeight = chaWeight;
    }

    public String getBsGettype() {
        return bsGettype;
    }

    public void setBsGettype(String bsGettype) {
        this.bsGettype = bsGettype == null ? null : bsGettype.trim();
    }

    public String getWxFlag() {
        return wxFlag;
    }

    public void setWxFlag(String wxFlag) {
        this.wxFlag = wxFlag == null ? null : wxFlag.trim();
    }
}