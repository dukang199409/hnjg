package com.bsl.reportbean;

import java.util.Date;

/**
 * 库存变动对应表格类
 * @author 杜康
 *
 */
public class BslStockPhotoReport {
	
	private Date transDate;
	
	private String prodId;

    private String prodName;

    private String prodType;

    private String prodNorm;

    private String prodMaterial;
    
    private String prodUserType;

	private Float prodLength;

    private Integer prodNum;

    private Float prodPrintWeight;

    private String prodStatus;

    private String prodLuno;

    private String prodInputuser;

    private Date crtDate;

    private String remark;
    
    public String getProdUserType() {
		return prodUserType;
	}

	public void setProdUserType(String prodUserType) {
		this.prodUserType = prodUserType;
	}
    
    public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getProdNorm() {
		return prodNorm;
	}

	public void setProdNorm(String prodNorm) {
		this.prodNorm = prodNorm;
	}

	public String getProdMaterial() {
		return prodMaterial;
	}

	public void setProdMaterial(String prodMaterial) {
		this.prodMaterial = prodMaterial;
	}

	public Float getProdLength() {
		return prodLength;
	}

	public void setProdLength(Float prodLength) {
		this.prodLength = prodLength;
	}

	public Integer getProdNum() {
		return prodNum;
	}

	public void setProdNum(Integer prodNum) {
		this.prodNum = prodNum;
	}

	public Float getProdPrintWeight() {
		return prodPrintWeight;
	}

	public void setProdPrintWeight(Float prodPrintWeight) {
		this.prodPrintWeight = prodPrintWeight;
	}

	public String getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}

	public String getProdLuno() {
		return prodLuno;
	}

	public void setProdLuno(String prodLuno) {
		this.prodLuno = prodLuno;
	}

	public String getProdInputuser() {
		return prodInputuser;
	}

	public void setProdInputuser(String prodInputuser) {
		this.prodInputuser = prodInputuser;
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
		this.remark = remark;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	private String orderByClause;
	
}
