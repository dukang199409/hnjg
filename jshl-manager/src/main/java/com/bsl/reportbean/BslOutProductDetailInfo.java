package com.bsl.reportbean;

import java.util.Date;

public class BslOutProductDetailInfo {
	
	private String bsCustomer;//销售客户
	private String bsOrderNo;//订单号
	private String bsInputuser;//制单人员
	private String prodRuc;//出库仓库
	private String prodOutPlan;//出库单号
	private String prodSaleSerno;//出库详细流水
	private Date outDate;//出库日期
	private String prodName;//物料名称
	private String prodNorm;//物料规格
	private String prodMaterial;//物料钢种
	private Float prodLength;//物料定尺
	private String prodUnitz;//主单位
	private String prodUnit;//单位
	private int sumProdNum;//出库数量
	private Float sumProdWeight;//出库重量
	private Float sumChaweight;//磅差重量
	private Float sumRetweight;//退货重量
	private Float salePrice;//出库单价
	private Float sumAmt;//出库金额
	private Float sumChaAmt;//磅差金额
	private Float sumRetAmt;//退货金额
	private String prodCurr;//币种
	private String prodOutCarno;//车号
	private String prodDclFlag;//外协厂标志
	private String bsFlag;//通知单类型
	private String bsGettype;//通知单类型
	private int printNum;//打印数量
	
	public int getPrintNum() {
		return printNum;
	}
	public void setPrintNum(int printNum) {
		this.printNum = printNum;
	}
	public String getBsGettype() {
		return bsGettype;
	}
	public void setBsGettype(String bsGettype) {
		this.bsGettype = bsGettype;
	}
	public String getProdDclFlag() {
		return prodDclFlag;
	}
	public void setProdDclFlag(String prodDclFlag) {
		this.prodDclFlag = prodDclFlag;
	}
	public String getBsFlag() {
		return bsFlag;
	}
	public void setBsFlag(String bsFlag) {
		this.bsFlag = bsFlag;
	}
	public String getProdOutCarno() {
		return prodOutCarno;
	}
	public void setProdOutCarno(String prodOutCarno) {
		this.prodOutCarno = prodOutCarno;
	}
	public String getBsCustomer() {
		return bsCustomer;
	}
	public void setBsCustomer(String bsCustomer) {
		this.bsCustomer = bsCustomer;
	}
	public String getBsOrderNo() {
		return bsOrderNo;
	}
	public void setBsOrderNo(String bsOrderNo) {
		this.bsOrderNo = bsOrderNo;
	}
	public String getBsInputuser() {
		return bsInputuser;
	}
	public void setBsInputuser(String bsInputuser) {
		this.bsInputuser = bsInputuser;
	}
	public String getProdRuc() {
		return prodRuc;
	}
	public void setProdRuc(String prodRuc) {
		this.prodRuc = prodRuc;
	}
	public String getProdOutPlan() {
		return prodOutPlan;
	}
	public void setProdOutPlan(String prodOutPlan) {
		this.prodOutPlan = prodOutPlan;
	}
	public String getProdSaleSerno() {
		return prodSaleSerno;
	}
	public void setProdSaleSerno(String prodSaleSerno) {
		this.prodSaleSerno = prodSaleSerno;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
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
	public String getProdUnitz() {
		return prodUnitz;
	}
	public void setProdUnitz(String prodUnitz) {
		this.prodUnitz = prodUnitz;
	}
	public String getProdUnit() {
		return prodUnit;
	}
	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}
	public int getSumProdNum() {
		return sumProdNum;
	}
	public void setSumProdNum(int sumProdNum) {
		this.sumProdNum = sumProdNum;
	}
	public Float getSumProdWeight() {
		return sumProdWeight;
	}
	public void setSumProdWeight(Float sumProdWeight) {
		this.sumProdWeight = sumProdWeight;
	}
	public Float getSumChaweight() {
		return sumChaweight;
	}
	public void setSumChaweight(Float sumChaweight) {
		this.sumChaweight = sumChaweight;
	}
	public Float getSumRetweight() {
		return sumRetweight;
	}
	public void setSumRetweight(Float sumRetweight) {
		this.sumRetweight = sumRetweight;
	}
	public Float getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}
	public Float getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(Float sumAmt) {
		this.sumAmt = sumAmt;
	}
	public Float getSumChaAmt() {
		return sumChaAmt;
	}
	public void setSumChaAmt(Float sumChaAmt) {
		this.sumChaAmt = sumChaAmt;
	}
	public Float getSumRetAmt() {
		return sumRetAmt;
	}
	public void setSumRetAmt(Float sumRetAmt) {
		this.sumRetAmt = sumRetAmt;
	}
	public String getProdCurr() {
		return prodCurr;
	}
	public void setProdCurr(String prodCurr) {
		this.prodCurr = prodCurr;
	}
	
}
