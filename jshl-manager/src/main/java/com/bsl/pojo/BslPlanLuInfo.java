package com.bsl.pojo;

public class BslPlanLuInfo {
    private Integer no;

    private String planId;

    private String planLuno;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    public String getPlanLuno() {
        return planLuno;
    }

    public void setPlanLuno(String planLuno) {
        this.planLuno = planLuno == null ? null : planLuno.trim();
    }
}