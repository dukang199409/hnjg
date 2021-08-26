package com.bsl.reportbean;

/**
 * 库存变动汇总对应表格类
 * @author 杜康
 *
 */
public class BslStockChangeSumReport {
	 
	private String prodType;
	private String transCode;
	private Float sumWeight;
	
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public Float getSumWeight() {
		return sumWeight;
	}
	public void setSumWeight(Float sumWeight) {
		this.sumWeight = sumWeight;
	}
	
}
