package com.bsl.reportbean;

/**
 * 产品质量保证书实现类
 * @author 杜康
 *
 */
public class BslProductQualityInfo {
	
	private String prodOutCarno;//发货车号信息
	private String wxFlag;//发货车号外协厂信息
	private String prodPlanNo;//生产批号
	private String prodLuno;//炉号
	private String prodName;//名称
	private String prodNorm;//规格
	private String prodMaterial;//物料钢种
	private Float prodLength;//物料定尺
	private int sumProdNum;//支数
	private Float sumProdWeight;//出库重量
	private String prodLevel;//质量标准
	private String prodRuc;//发货仓库
	private Integer chemicalC;//化学成分
	private Integer chemicalMn;
    private Integer chemicalSi;
    private Integer chemicalS;
    private Integer chemicalP;
    private Integer chemicalTi;
    private Integer chemicalNi;
    private Integer chemicalCr;
    private Integer chemicalCu;
    private Integer chemicalNb;

    private Integer mechanicalS;//力学性能
    private Integer mechanicalB;
    private Integer mechanicalL;

    private Integer impactT;//V型冲击力
    private String impactN1;
    private String impactN2;
	private String impactN3;
    private String bendWC;
    private String bendYB;

	public String getWxFlag() {
		return wxFlag;
	}
	public void setWxFlag(String wxFlag) {
		this.wxFlag = wxFlag;
	}
    public String getProdRuc() {
		return prodRuc;
	}
	public void setProdRuc(String prodRuc) {
		this.prodRuc = prodRuc;
	}
	public String getImpactN2() {
		return impactN2;
	}
	public void setImpactN2(String impactN2) {
		this.impactN2 = impactN2;
	}
	public String getImpactN3() {
		return impactN3;
	}
	public void setImpactN3(String impactN3) {
		this.impactN3 = impactN3;
	}
	public String getBendWC() {
		return bendWC;
	}
	public void setBendWC(String bendWC) {
		this.bendWC = bendWC;
	}
	public String getBendYB() {
		return bendYB;
	}
	public void setBendYB(String bendYB) {
		this.bendYB = bendYB;
	}
	public String getProdOutCarno() {
		return prodOutCarno;
	}
	public void setProdOutCarno(String prodOutCarno) {
		this.prodOutCarno = prodOutCarno;
	}
	public String getProdPlanNo() {
		return prodPlanNo;
	}
	public void setProdPlanNo(String prodPlanNo) {
		this.prodPlanNo = prodPlanNo;
	}
	public String getProdLuno() {
		return prodLuno;
	}
	public void setProdLuno(String prodLuno) {
		this.prodLuno = prodLuno;
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
	public String getProdLevel() {
		return prodLevel;
	}
	public void setProdLevel(String prodLevel) {
		this.prodLevel = prodLevel;
	}

   
	public void setChemicalNb(int chemicalNb) {
		this.chemicalNb = chemicalNb;
	}
	public Integer getMechanicalS() {
		return mechanicalS;
	}
	public void setMechanicalS(Integer mechanicalS) {
		this.mechanicalS = mechanicalS;
	}
	public Integer getMechanicalB() {
		return mechanicalB;
	}
	public void setMechanicalB(Integer mechanicalB) {
		this.mechanicalB = mechanicalB;
	}
	public Integer getMechanicalL() {
		return mechanicalL;
	}
	public void setMechanicalL(Integer mechanicalL) {
		this.mechanicalL = mechanicalL;
	}
	public Integer getImpactT() {
		return impactT;
	}
	public void setImpactT(Integer impactT) {
		this.impactT = impactT;
	}
	public String getImpactN1() {
		return impactN1;
	}
	public void setImpactN1(String impactN1) {
		this.impactN1 = impactN1;
	}

	public Integer getChemicalC() {
		return chemicalC;
	}
	public void setChemicalC(Integer chemicalC) {
		this.chemicalC = chemicalC;
	}
	public Integer getChemicalMn() {
		return chemicalMn;
	}
	public void setChemicalMn(Integer chemicalMn) {
		this.chemicalMn = chemicalMn;
	}
	public Integer getChemicalSi() {
		return chemicalSi;
	}
	public void setChemicalSi(Integer chemicalSi) {
		this.chemicalSi = chemicalSi;
	}
	public Integer getChemicalS() {
		return chemicalS;
	}
	public void setChemicalS(Integer chemicalS) {
		this.chemicalS = chemicalS;
	}
	public Integer getChemicalP() {
		return chemicalP;
	}
	public void setChemicalP(Integer chemicalP) {
		this.chemicalP = chemicalP;
	}
	public Integer getChemicalTi() {
		return chemicalTi;
	}
	public void setChemicalTi(Integer chemicalTi) {
		this.chemicalTi = chemicalTi;
	}
	public Integer getChemicalNi() {
		return chemicalNi;
	}
	public void setChemicalNi(Integer chemicalNi) {
		this.chemicalNi = chemicalNi;
	}
	public Integer getChemicalCr() {
		return chemicalCr;
	}
	public void setChemicalCr(Integer chemicalCr) {
		this.chemicalCr = chemicalCr;
	}
	public Integer getChemicalCu() {
		return chemicalCu;
	}
	public void setChemicalCu(Integer chemicalCu) {
		this.chemicalCu = chemicalCu;
	}
	public Integer getChemicalNb() {
		return chemicalNb;
	}
	public void setChemicalNb(Integer chemicalNb) {
		this.chemicalNb = chemicalNb;
	}
	
	
	
}
