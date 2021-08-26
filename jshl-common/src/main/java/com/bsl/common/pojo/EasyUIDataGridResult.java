package com.bsl.common.pojo;

import java.util.List;

public class EasyUIDataGridResult {
	
	private long total;
	private List<?> rows;
	//类名
	private String className;
	//方法名
	private String methodName;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public String getClassName() {
		return className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}	
}
