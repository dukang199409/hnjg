package com.bsl.pojo;

import java.util.Date;

public class BslLuStockInfoKey {
    private Date transDate;

    private String prodLuno;

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getProdLuno() {
        return prodLuno;
    }

    public void setProdLuno(String prodLuno) {
        this.prodLuno = prodLuno == null ? null : prodLuno.trim();
    }
}