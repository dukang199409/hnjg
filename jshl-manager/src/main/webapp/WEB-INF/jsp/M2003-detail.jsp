<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="planDoDetailFormM2003" class="itemForm" method="post">
	    <table cellpadding="5">
	    	<tr>
	    		<td width="120" align="right">纵剪带生产指令号:</td>
	            <td width="210" align="right">
	            	<input name="planId" class="easyui-textbox" panelHeight="auto"  type="text" data-options="required:false" style="width:200px;"  readonly="readonly"></input>
	            </td>
	       		<td width="120" align="right">用料规格:</td>
	            <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	             <td width="120" align="right">钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" class="easyui-combobox" readonly="readonly" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	            <td width="120"  align="right">用料炉号:</td>
	            <td width="210"  align="right">
	            	<input name="planLuno" class="easyui-textbox" panelHeight="auto"  type="text" data-options="required:false" style="width:200px;"  readonly="readonly"></input>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120"  align="right">用料重量/吨:</td>
	            <td width="210"  align="right">
	            	<input name="prodWeight" class="easyui-numberbox" type="text" readonly="readonly"  data-options="required:false,min:0,precision:3,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	        	<td width="120"  align="right">用料数量/卷:</td>
	            <td width="210"  align="right">
	            	<input name="prodNum" class="easyui-numberbox" type="text" readonly="readonly"  data-options="required:true,min:0,precision:0,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120"  align="right">生产部门:</td>
	            <td width="210"  align="right">
	            	<select name="planDepartment" panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true" style="width:200px;" readonly="readonly">
			          <option value="">请选择...</option>
			         <c:forEach items="${planDepartmentList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        	<td width="120"  align="right">制造产品名称:</td>
	            <td width="210"  align="right">
	            	<input name="makeName" class="easyui-textbox" readonly="readonly" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">生产订单号:</td>
	            <td width="210"  align="right">
	            	<input name="prodOrder" class="easyui-textbox" readonly="readonly" data-options="required:false,validType:'length[0,20]'" style="text; width: 200px;"></input>
	            </td>
	        	<td width="120" align="right">工厂:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" name="company" readonly="readonly" data-options="required:false,validType:'length[0,20]'" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">来料客户:</td>
	            <td width="210" align="right">
	            	<select name="customer" class="easyui-combobox" panelHeight="auto" readonly="readonly" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${customerList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">备注:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" name="remark" readonly="readonly" data-options="required:false,validType:'length[0,120]'" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	        	<td width="120" align="right"></td>
	            <td width="210"  align="right">
	            </td>
	            <td width="120" align="right"></td>
	            <td width="210"  align="right">
	            </td>
	        </tr>
	    </table>
	     <table class="easyui-datagrid" id="planDoDetailListM2003" title="纵剪带生产指令调度计划信息"  style="height:320px"
	       data-options="rownumbers:true,fitColumns:true,collapsible:true">
		    <thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
		        	<th data-options="field:'planInfoDetailId',width:140">调度计划编号</th>
		            <th data-options="field:'prodNorm',width:100">制造规格</th>
		            <th data-options="field:'prodLevel',width:120,formatter:BSL.formatProdLevel">质量等级</th>
		            <th data-options="field:'prodWeight',width:80">用料重量/吨</th>
		            <th data-options="field:'prodNum',width:80">条数</th>
		            <th data-options="field:'planOutputVolume',width:100">计划产出量/吨</th>
		            <th data-options="field:'planFinistDate',width:110,formatter:BSL.formatDateTime">计划完工日期</th>
		            <th data-options="field:'collectedUnits',width:80,formatter:BSL.formatMakeDept">实收机组</th>
		            <th data-options="field:'crtDate',formatter:BSL.formatDateTime,width:100">创建日期</th>
		            <th data-options="field:'prodInputuser',width:100">产品录入人</th>
		            <th data-options="field:'remark',width:100">备注</th>
		        </tr>
		    </thead>
		</table>
	</form>
</div>
<script type="text/javascript">
	
</script>
