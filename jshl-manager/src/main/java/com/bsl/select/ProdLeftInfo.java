package com.bsl.select;

import java.util.List;

public class ProdLeftInfo {

	private List<String> prodNorms;
	private List<String> prodMaterials;
	private List<Float> prodLengths;
	private List<String> prodLunos;
	private int prodCount;
	private Float sumWeight;
	public List<String> getProdNorms() {
		return prodNorms;
	}
	public void setProdNorms(List<String> prodNorms) {
		this.prodNorms = prodNorms;
	}
	public List<String> getProdMaterials() {
		return prodMaterials;
	}
	public void setProdMaterials(List<String> prodMaterials) {
		this.prodMaterials = prodMaterials;
	}
	public List<Float> getProdLengths() {
		return prodLengths;
	}
	public void setProdLengths(List<Float> prodLengths) {
		this.prodLengths = prodLengths;
	}
	public List<String> getProdLunos() {
		return prodLunos;
	}
	public void setProdLunos(List<String> prodLunos) {
		this.prodLunos = prodLunos;
	}
	public int getProdCount() {
		return prodCount;
	}
	public void setProdCount(int prodCount) {
		this.prodCount = prodCount;
	}
	public Float getSumWeight() {
		return sumWeight;
	}
	public void setSumWeight(Float sumWeight) {
		this.sumWeight = sumWeight;
	}
	
}
