package com.bsl.reportbean;

public class BslProductInfoCollect {
	private String prodPlanNo;
	private String prodLuno;
	private String prodName;
	private String prodNorm;
	private String prodMaterial;
	private Float prodLength;
	private Integer prodNumCount;
	private Float prodWeightTotal;
	private String prodLevel;
	private String prodOrirawid;
	
	public String getProdOrirawid() {
		return prodOrirawid;
	}
	public void setProdOrirawid(String prodOrirawid) {
		this.prodOrirawid = prodOrirawid;
	}
	public String getProdPlanNo() {
		return prodPlanNo;
	}
	public String getProdLuno() {
		return prodLuno;
	}
	public String getProdName() {
		return prodName;
	}
	public String getProdNorm() {
		return prodNorm;
	}
	public String getProdMaterial() {
		return prodMaterial;
	}
	public Float getProdLength() {
		return prodLength;
	}
	public Integer getProdNumCount() {
		return prodNumCount;
	}
	public Float getProdWeightTotal() {
		return prodWeightTotal;
	}
	public String getProdLevel() {
		return prodLevel;
	}
	public void setProdPlanNo(String prodPlanNo) {
		this.prodPlanNo = prodPlanNo;
	}
	public void setProdLuno(String prodLuno) {
		this.prodLuno = prodLuno;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public void setProdNorm(String prodNorm) {
		this.prodNorm = prodNorm;
	}
	public void setProdMaterial(String prodMaterial) {
		this.prodMaterial = prodMaterial;
	}
	public void setProdLength(Float prodLength) {
		this.prodLength = prodLength;
	}
	public void setProdNumCount(Integer prodNumCount) {
		this.prodNumCount = prodNumCount;
	}
	public void setProdWeightTotal(Float prodWeightTotal) {
		this.prodWeightTotal = prodWeightTotal;
	}
	public void setProdLevel(String prodLevel) {
		this.prodLevel = prodLevel;
	}
	@Override
	public String toString() {
		return "BslProductInfoCollect [prodPlanNo=" + prodPlanNo + ", prodLuno=" + prodLuno + ", prodName=" + prodName
				+ ", prodNorm=" + prodNorm + ", prodMaterial=" + prodMaterial + ", prodLength=" + prodLength
				+ ", prodNumCount=" + prodNumCount + ", prodWeightTotal=" + prodWeightTotal + ", prodLevel=" + prodLevel
				+ "]";
	}
}
