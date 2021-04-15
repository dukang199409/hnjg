package com.bsl.select;

import java.util.Date;
import java.util.List;

public class QueryExample {
	
	private String[] prodOutCarnos;
	private String[] prodIds;
	private String[] prodRucs;
	private List<String> prodIdsList;
	private String startDate;
	private String endDate;
	private String bsId;
	private String prodType;
	private String page;
	private String rows;
	private String loginUserId;
	private String userId;
	private String userName;
	private String userType;
	private String planId;
	private String planDetailId;
	private String prodBc;
	private String bsFlag;
	private String planDepartment;
	private String planStatus;
	private String norm;
	private String prodMaterial;
	private String prodOriId;
	private String prodStatus;
	private String prodPlanNo;
	private String prodNorm;
	private String prodFlag;
	private String prodId;
	private String prodInputuser;
	private boolean isFlag;
	private String className;
	private String methodName;
	private String fields;
	private String titles;
	private String excelName;
	private String sort;
	private String order;
	private String prodLuno;
	private String planLuno;
	private String planInfoDetailId;
	private String makeName;
	private String prodParentNo;
	private String isReAdd;	
	private String tradeType;
	private String prodOrder;
	private String prodUserType;
	private String prodOutCarno;
	private String prodSourceCompany;
	private String planFlag;
	private Date dateStart;
	private Date dateEnd;
	private String orderByClause;
	private String makeProdNorm;
	private String bsGettype;
	private String saleFlag;
	private String planJz;
	private String prodMakeJz;
	private String prodOrirawid;
	private String prodLength;

	public String getProdLength() {
		return prodLength;
	}
	public void setProdLength(String prodLength) {
		this.prodLength = prodLength;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	
	public String getProdOrirawid() {
		return prodOrirawid;
	}
	public void setProdOrirawid(String prodOrirawid) {
		this.prodOrirawid = prodOrirawid;
	}
	
	public String getProdMakeJz() {
		return prodMakeJz;
	}
	public void setProdMakeJz(String prodMakeJz) {
		this.prodMakeJz = prodMakeJz;
	}
	public String getPlanJz() {
		return planJz;
	}
	public void setPlanJz(String planJz) {
		this.planJz = planJz;
	}
	public String getSaleFlag() {
		return saleFlag;
	}
	public void setSaleFlag(String saleFlag) {
		this.saleFlag = saleFlag;
	}
	public String getBsGettype() {
		return bsGettype;
	}
	public void setBsGettype(String bsGettype) {
		this.bsGettype = bsGettype;
	}
	public String getBsFlag() {
		return bsFlag;
	}
	public void setBsFlag(String bsFlag) {
		this.bsFlag = bsFlag;
	}
	public String getProdBc() {
		return prodBc;
	}
	public void setProdBc(String prodBc) {
		this.prodBc = prodBc;
	}
	public String getPlanDetailId() {
		return planDetailId;
	}
	public void setPlanDetailId(String planDetailId) {
		this.planDetailId = planDetailId;
	}
	public String[] getProdRucs() {
		return prodRucs;
	}
	public void setProdRucs(String[] prodRucs) {
		this.prodRucs = prodRucs;
	}
	public String getMakeProdNorm() {
		return makeProdNorm;
	}
	public void setMakeProdNorm(String makeProdNorm) {
		this.makeProdNorm = makeProdNorm;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getOrderByClause() {
		return orderByClause;
	}
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	public String getPlanFlag() {
		return planFlag;
	}
	public void setPlanFlag(String planFlag) {
		this.planFlag = planFlag;
	}
	private String wxFlag;
	
	public String getWxFlag() {
		return wxFlag;
	}
	public void setWxFlag(String wxFlag) {
		this.wxFlag = wxFlag;
	}
	public String getProdSourceCompany() {
		return prodSourceCompany;
	}
	public void setProdSourceCompany(String prodSourceCompany) {
		this.prodSourceCompany = prodSourceCompany;
	}
	public String getProdOutCarno() {
		return prodOutCarno;
	}
	public void setProdOutCarno(String prodOutCarno) {
		this.prodOutCarno = prodOutCarno;
	}
	public String getProdUserType() {
		return prodUserType;
	}
	public void setProdUserType(String prodUserType) {
		this.prodUserType = prodUserType;
	}
	public String getIsReAdd() {
		return isReAdd;
	}
	public void setIsReAdd(String isReAdd) {
		this.isReAdd = isReAdd;
	}
	public String getProdParentNo() {
		return prodParentNo;
	}
	public void setProdParentNo(String prodParentNo) {
		this.prodParentNo = prodParentNo;
	}
	private String prodOutPlan;
	public String getProdOutPlan() {
		return prodOutPlan;
	}
	public void setProdOutPlan(String prodOutPlan) {
		this.prodOutPlan = prodOutPlan;
	}
	
	public String getMakeName() {
		return makeName;
	}
	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}
	
	public String getPlanInfoDetailId() {
		return planInfoDetailId;
	}
	public void setPlanInfoDetailId(String planInfoDetailId) {
		this.planInfoDetailId = planInfoDetailId;
	}
	public String getPlanLuno() {
		return planLuno;
	}
	public void setPlanLuno(String planLuno) {
		this.planLuno = planLuno;
	}
	public String getProdLuno() {
		return prodLuno;
	}
	public void setProdLuno(String prodLuno) {
		this.prodLuno = prodLuno;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public String getBsId() {
		return bsId;
	}
	public String getPage() {
		return page;
	}
	public String getRows() {
		return rows;
	}
	public String getPlanId() {
		return planId;
	}
	public String getPlanDepartment() {
		return planDepartment;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public String getNorm() {
		return norm;
	}
	public String getProdMaterial() {
		return prodMaterial;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setBsId(String bsId) {
		this.bsId = bsId;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public void setPlanDepartment(String planDepartment) {
		this.planDepartment = planDepartment;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public void setNorm(String norm) {
		this.norm = norm;
	}
	public void setProdMaterial(String prodMaterial) {
		this.prodMaterial = prodMaterial;
	}
	public String getProdOriId() {
		return prodOriId;
	}
	public String getProdStatus() {
		return prodStatus;
	}
	public void setProdOriId(String prodOriId) {
		this.prodOriId = prodOriId;
	}
	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}
	public String getProdPlanNo() {
		return prodPlanNo;
	}
	public void setProdPlanNo(String prodPlanNo) {
		this.prodPlanNo = prodPlanNo;
	}
	public String getProdNorm() {
		return prodNorm;
	}
	public void setProdNorm(String prodNorm) {
		this.prodNorm = prodNorm;
	}
	public String getProdFlag() {
		return prodFlag;
	}
	public void setProdFlag(String prodFlag) {
		this.prodFlag = prodFlag;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdInputuser() {
		return prodInputuser;
	}
	public void setProdInputuser(String prodInputuser) {
		this.prodInputuser = prodInputuser;
	}
	public boolean isFlag() {
		return isFlag;
	}
	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}
	public String getClassName() {
		return className;
	}
	public String getMethodName() {
		return methodName;
	}
	public String getFields() {
		return fields;
	}
	public String getTitles() {
		return titles;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getProdOrder() {
		return prodOrder;
	}
	public void setProdOrder(String prodOrder) {
		this.prodOrder = prodOrder;
	}

	public String[] getProdOutCarnos() {
		return prodOutCarnos;
	}
	public void setProdOutCarnos(String[] prodOutCarnos) {
		this.prodOutCarnos = prodOutCarnos;
	}
	public String[] getProdIds() {
		return prodIds;
	}
	public void setProdIds(String[] prodIds) {
		this.prodIds = prodIds;
	}
	public List<String> getProdIdsList() {
		return prodIdsList;
	}
	public void setProdIdsList(List<String> prodIdsList) {
		this.prodIdsList = prodIdsList;
	}
	@Override
	public String toString() {
		return "QueryExample [startDate=" + startDate + ", endDate=" + endDate + ", bsId=" + bsId + ", page=" + page
				+ ", rows=" + rows + ", loginUserId=" + loginUserId + ", userId=" + userId + ", userName=" + userName
				+ ", userType=" + userType + ", planId=" + planId + ", planDepartment=" + planDepartment
				+ ", planStatus=" + planStatus + ", norm=" + norm + ", prodMaterial=" + prodMaterial + ", prodOriId="
				+ prodOriId + ", prodStatus=" + prodStatus + ", prodPlanNo=" + prodPlanNo + ", prodNorm=" + prodNorm
				+ ", prodFlag=" + prodFlag + ", prodId=" + prodId + ", prodInputuser=" + prodInputuser + ", isFlag="
				+ isFlag + ", className=" + className + ", methodName=" + methodName + ", fields=" + fields
				+ ", titles=" + titles + ", excelName=" + excelName + ", sort=" + sort + ", order=" + order
				+ ", prodLuno=" + prodLuno + ", planLuno=" + planLuno + ", planInfoDetailId=" + planInfoDetailId
				+ ", makeName=" + makeName + ", prodParentNo=" + prodParentNo + ", isReAdd=" + isReAdd + ", tradeType="
				+ tradeType + ", prodOrder=" + prodOrder + ", prodUserType=" + prodUserType + ", prodOutPlan="
				+ prodOutPlan + "]";
	}
	
}
