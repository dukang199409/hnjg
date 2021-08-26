package com.bsl.pojo;

import java.util.Date;

public class BslSendCheckInfo {
    private String luId;

    private String sendFlag;

    private String sendResult;

    private Date sendDate;

    private Date updDate;

    private String remark;

    private String inputuser;

    public String getLuId() {
        return luId;
    }

    public void setLuId(String luId) {
        this.luId = luId == null ? null : luId.trim();
    }

    public String getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(String sendFlag) {
        this.sendFlag = sendFlag == null ? null : sendFlag.trim();
    }

    public String getSendResult() {
        return sendResult;
    }

    public void setSendResult(String sendResult) {
        this.sendResult = sendResult == null ? null : sendResult.trim();
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
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

    public String getInputuser() {
        return inputuser;
    }

    public void setInputuser(String inputuser) {
        this.inputuser = inputuser == null ? null : inputuser.trim();
    }
}