package com.bsl.reportbean;

/**
 * 车次流水信息实现类
 * @author 杜康
 *
 */
public class BslSaleCarInfo {
	
	private String prodOutCarno;//发货车次流水信息
	private int sumProdNum;//支数
	private Float sumProdWeight;//出库重量
	private String prodCarno;//发货车号信息
	private String carDate;//发货日期信息
	private int printNum;
	private String wxFlag;
	private String bsCustomer;
	private String bsGettype;
	private Float retWeight;
	private Float chaWeight;
	
	public Float getRetWeight() {
		return retWeight;
	}
	public void setRetWeight(Float retWeight) {
		this.retWeight = retWeight;
	}
	public Float getChaWeight() {
		return chaWeight;
	}
	public void setChaWeight(Float chaWeight) {
		this.chaWeight = chaWeight;
	}
	public String getBsCustomer() {
		return bsCustomer;
	}
	public void setBsCustomer(String bsCustomer) {
		this.bsCustomer = bsCustomer;
	}
	public String getBsGettype() {
		return bsGettype;
	}
	public void setBsGettype(String bsGettype) {
		this.bsGettype = bsGettype;
	}
	public String getWxFlag() {
		return wxFlag;
	}
	public void setWxFlag(String wxFlag) {
		this.wxFlag = wxFlag;
	}
	public int getPrintNum() {
		return printNum;
	}
	public void setPrintNum(int printNum) {
		this.printNum = printNum;
	}
	public String getProdOutCarno() {
		return prodOutCarno;
	}
	public void setProdOutCarno(String prodOutCarno) {
		this.prodOutCarno = prodOutCarno;
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
	public String getProdCarno() {
		return prodCarno;
	}
	public void setProdCarno(String prodCarno) {
		this.prodCarno = prodCarno;
	}
	public String getCarDate() {
		return carDate;
	}
	public void setCarDate(String carDate) {
		this.carDate = carDate;
	}
	
}
