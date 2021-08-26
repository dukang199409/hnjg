package com.bsl.common.pojo;

import java.util.Date;

public class BslMakePlanInfoDetailExtra {
	// 产品名称
	private String makeName;
	// 规格
	private String prodNorm;
	// 钢种
	private String prodMaterial;
	// 质量等级
	private String prodLevel;
	// 条数
	private String prodNum;
	// 单位
	private String unit;
	// 计划产出量
	private String planOutputVolume;
	// 实际重量
	private String prodWeight;
	// 计划完工日期
    private String planFinistDate;
    // 实收机组
    private String collectedUnits;
    // 成品规格
    private String makeProdNorm;
    private String prodOrirawid;
	
	public String getProdOrirawid() {
		return prodOrirawid;
	}
	public void setProdOrirawid(String prodOrirawid) {
		this.prodOrirawid = prodOrirawid;
	}
    // 备注
    private String remark;
    private String note;
    private String bak;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    
	public String getMakeName() {
		return makeName;
	}
	public String getProdNorm() {
		return prodNorm;
	}
	public String getProdMaterial() {
		return prodMaterial;
	}
	public String getProdLevel() {
		return prodLevel;
	}
	public String getProdNum() {
		return prodNum;
	}
	public String getUnit() {
		return unit;
	}
	public String getPlanOutputVolume() {
		return planOutputVolume;
	}
	public String getProdWeight() {
		return prodWeight;
	}
	public String getPlanFinistDate() {
		return planFinistDate;
	}
	public String getCollectedUnits() {
		return collectedUnits;
	}
	public String getMakeProdNorm() {
		return makeProdNorm;
	}
	public String getRemark() {
		return remark;
	}
	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}
	public void setProdNorm(String prodNorm) {
		this.prodNorm = prodNorm;
	}
	public void setProdMaterial(String prodMaterial) {
		this.prodMaterial = prodMaterial;
	}
	public void setProdLevel(String prodLevel) {
		this.prodLevel = prodLevel;
	}
	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setPlanOutputVolume(String planOutputVolume) {
		this.planOutputVolume = planOutputVolume;
	}
	public void setProdWeight(String prodWeight) {
		this.prodWeight = prodWeight;
	}
	public void setPlanFinistDate(String planFinistDate) {
		this.planFinistDate = planFinistDate;
	}
	public void setCollectedUnits(String collectedUnits) {
		this.collectedUnits = collectedUnits;
	}
	public void setMakeProdNorm(String makeProdNorm) {
		this.makeProdNorm = makeProdNorm;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getBak() {
		return bak;
	}
	public void setBak(String bak) {
		this.bak = bak;
	}
	
	public String getA() {
		return a;
	}
	public String getB() {
		return b;
	}
	public String getC() {
		return c;
	}
	public String getD() {
		return d;
	}
	public String getE() {
		return e;
	}
	public String getF() {
		return f;
	}
	public String getG() {
		return g;
	}
	public String getH() {
		return h;
	}
	public String getI() {
		return i;
	}
	public String getJ() {
		return j;
	}
	public void setA(String a) {
		this.a = a;
	}
	public void setB(String b) {
		this.b = b;
	}
	public void setC(String c) {
		this.c = c;
	}
	public void setD(String d) {
		this.d = d;
	}
	public void setE(String e) {
		this.e = e;
	}
	public void setF(String f) {
		this.f = f;
	}
	public void setG(String g) {
		this.g = g;
	}
	public void setH(String h) {
		this.h = h;
	}
	public void setI(String i) {
		this.i = i;
	}
	public void setJ(String j) {
		this.j = j;
	}
	/**
	 * 初始化
	 * @param makeName 产品名称
	 * @param prodNorm 规格
	 * @param prodMaterial 钢种
	 * @param prodLevel 质量等级
	 * @param prodNum 条数
	 * @param unit 单位
	 * @param planOutputVolume 计划产出量
	 * @param prodWeight 实际重量/销售订单号
	 * @param planFinistDate 计划完工日期
	 * @param collectedUnits 实收机组/短溢装
	 * @param makeProdNorm 成品规格
	 * @param remark 备注
	 */
	public BslMakePlanInfoDetailExtra(String makeName, String prodNorm, String prodMaterial, String prodLevel,
			String prodNum, String unit, String planOutputVolume, String prodWeight, String planFinistDate,
			String collectedUnits, String makeProdNorm, String remark) {
		super();
		this.makeName = makeName;
		this.prodNorm = prodNorm;
		this.prodMaterial = prodMaterial;
		this.prodLevel = prodLevel;
		this.prodNum = prodNum;
		this.unit = unit;
		this.planOutputVolume = planOutputVolume;
		this.prodWeight = prodWeight;
		this.planFinistDate = planFinistDate;
		this.collectedUnits = collectedUnits;
		this.makeProdNorm = makeProdNorm;
		this.remark = remark;
	}
	/**
	 * 原料入库通知单
	 * @param makeName
	 * @param prodNorm
	 * @param prodMaterial
	 * @param prodLevel
	 * @param prodNum
	 * @param unit
	 * @param planOutputVolume
	 * @param prodWeight
	 * @param planFinistDate
	 * @param collectedUnits
	 * @param makeProdNorm
	 * @param remark
	 * @param note
	 */
	public BslMakePlanInfoDetailExtra(String makeName, String prodNorm, String prodMaterial, String prodLevel,
			String prodNum, String unit, String planOutputVolume, String prodWeight, String planFinistDate,
			String collectedUnits, String makeProdNorm, String remark, String note) {
		super();
		this.makeName = makeName;
		this.prodNorm = prodNorm;
		this.prodMaterial = prodMaterial;
		this.prodLevel = prodLevel;
		this.prodNum = prodNum;
		this.unit = unit;
		this.planOutputVolume = planOutputVolume;
		this.prodWeight = prodWeight;
		this.planFinistDate = planFinistDate;
		this.collectedUnits = collectedUnits;
		this.makeProdNorm = makeProdNorm;
		this.remark = remark;
		this.note = note;
	}
	public BslMakePlanInfoDetailExtra(String makeName, String prodNorm, String prodMaterial, String prodLevel,
			String prodNum, String unit, String planOutputVolume, String prodWeight, String planFinistDate,
			String collectedUnits, String makeProdNorm, String remark, String note, String bak) {
		super();
		this.makeName = makeName;
		this.prodNorm = prodNorm;
		this.prodMaterial = prodMaterial;
		this.prodLevel = prodLevel;
		this.prodNum = prodNum;
		this.unit = unit;
		this.planOutputVolume = planOutputVolume;
		this.prodWeight = prodWeight;
		this.planFinistDate = planFinistDate;
		this.collectedUnits = collectedUnits;
		this.makeProdNorm = makeProdNorm;
		this.remark = remark;
		this.note = note;
		this.bak = bak;
	}
	
	/**
	 * 销售出库单
	 * @param makeName
	 * @param prodNorm
	 * @param prodOrirawid
	 * @param prodMaterial
	 * @param prodLevel
	 * @param prodNum
	 * @param unit
	 * @param planOutputVolume
	 * @param prodWeight
	 * @param planFinistDate
	 */
	public BslMakePlanInfoDetailExtra(String makeName,String prodNorm,String prodOrirawid, String prodMaterial, String prodLevel,
			String prodNum, String unit, String planOutputVolume, String prodWeight, String planFinistDate) {
		super();
		this.makeName = makeName;
		this.prodNorm = prodNorm;
		this.prodOrirawid = prodOrirawid;
		this.prodMaterial = prodMaterial;
		this.prodLevel = prodLevel;
		this.prodNum = prodNum;
		this.unit = unit;
		this.planOutputVolume = planOutputVolume;
		this.prodWeight = prodWeight;
		this.planFinistDate = planFinistDate;
	}
	
	/**
	 * 销售出库单
	 * @param makeName
	 * @param prodNorm
	 * @param prodMaterial
	 * @param prodLevel
	 * @param prodNum
	 * @param unit
	 * @param planOutputVolume
	 * @param prodWeight
	 * @param planFinistDate
	 */
	public BslMakePlanInfoDetailExtra(String makeName,String prodNorm, String prodMaterial, String prodLevel,
			String prodNum, String unit, String planOutputVolume, String prodWeight, String planFinistDate) {
		super();
		this.makeName = makeName;
		this.prodNorm = prodNorm;
		this.prodMaterial = prodMaterial;
		this.prodLevel = prodLevel;
		this.prodNum = prodNum;
		this.unit = unit;
		this.planOutputVolume = planOutputVolume;
		this.prodWeight = prodWeight;
		this.planFinistDate = planFinistDate;
	}
	
	public BslMakePlanInfoDetailExtra(String makeName, String prodNorm, String prodMaterial, String prodLevel,
			String prodNum, String unit, String planOutputVolume, String prodWeight, String planFinistDate,
			String collectedUnits, String makeProdNorm, String remark, String note, String bak, String a, String b,
			String c, String d, String e, String f, String g, String h, String i, String j) {
		super();
		this.makeName = makeName;
		this.prodNorm = prodNorm;
		this.prodMaterial = prodMaterial;
		this.prodLevel = prodLevel;
		this.prodNum = prodNum;
		this.unit = unit;
		this.planOutputVolume = planOutputVolume;
		this.prodWeight = prodWeight;
		this.planFinistDate = planFinistDate;
		this.collectedUnits = collectedUnits;
		this.makeProdNorm = makeProdNorm;
		this.remark = remark;
		this.note = note;
		this.bak = bak;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
		this.i = i;
		this.j = j;
	}
	
}
