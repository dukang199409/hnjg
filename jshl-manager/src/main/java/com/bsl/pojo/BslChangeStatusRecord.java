package com.bsl.pojo;

import java.util.Date;

public class BslChangeStatusRecord {
    private String changeSerno;

    private String changeType;

    private String changeProdId;

    private String beforeStatus;

    private String afterStatus;

    private String inputuser;

    private Date crtDate;

    private String remark;

    public String getChangeSerno() {
        return changeSerno;
    }

    public void setChangeSerno(String changeSerno) {
        this.changeSerno = changeSerno == null ? null : changeSerno.trim();
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType == null ? null : changeType.trim();
    }

    public String getChangeProdId() {
        return changeProdId;
    }

    public void setChangeProdId(String changeProdId) {
        this.changeProdId = changeProdId == null ? null : changeProdId.trim();
    }

    public String getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(String beforeStatus) {
        this.beforeStatus = beforeStatus == null ? null : beforeStatus.trim();
    }

    public String getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(String afterStatus) {
        this.afterStatus = afterStatus == null ? null : afterStatus.trim();
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