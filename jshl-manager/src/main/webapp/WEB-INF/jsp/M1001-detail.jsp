<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="receiptDetailForm" class="itemForm" method="post">
	   <table>
	        <tr>
	        	<td width="120" align="right">原料入库通知单号:</td>
	            <td width="210" align="right">
	            	<input name="bsId" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,20]'" readonly="readonly" style="width:200px;"></input>
	            </td>           
	            <td width="120" align="right">通知单类别: </td>
	            <td width="210" align="right">
	            	<select name="bsFlag" class="easyui-combobox" panelHeight="auto" readonly="readonly" data-options="editable:true" style="width:200px;">
			          <c:forEach items="${bsFlagSList}" var="a">
			          	   <option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>	 
	            <td width="120" align="right">供应商:</td>
	            <td width="210" align="right">
	            	<input name="bsCompany" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">含质保书: </td>
	            <td width="210" align="right">
	            	<select name="bsHasguarantee" class="easyui-combobox" readonly="readonly" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <c:forEach items="${nyFlagList}" var="a">
			          	   <option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">客户:</td>
	            <td width="210" align="right">
	            	<input name="bsCustomer" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">卷数:</td>
	            <td width="210" align="right">
	            	<input name="bsAmt" class="easyui-numberbox" readonly="readonly" type="text" data-options="required:true,min:1,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">原料来料总重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="bsWeight" class="easyui-numberbox" readonly="readonly" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">运输单位:</td>
	            <td width="210" align="right">
	            	<input name="bsTransport" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	        </tr>
	         <tr>
	            <td width="120" align="right">预计来料规格:</td>
	            <td width="210" align="right">
	            	<input name="bsNorm" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">预计来料时间:</td>
	            <td width="210" align="right">
	            	<input name="arrDate" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">运输车号:</td>
	            <td width="210" align="right">
	            	<input name="bsCarno" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">提货方式:</td>
	            <td width="210" align="right">
		            <select name="bsGettype" class="easyui-combobox" readonly="readonly" panelHeight="auto" data-options="editable:true,required:false" style="width:200px;">
				       <option value="">请选择...</option>
				        <c:forEach items="${bsGettypeList}" var="a">
			          	   <option value="${a.enumKey}">${a.enumValue}</option>
			           </c:forEach>
					</select>
				</td>
	        </tr>
	        <tr>
	            <td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>
	        </tr>
	    </table>
	    <table class="easyui-datagrid" id="receiptDetailInfoList" title="通知单详细卷板信息"  style="height:300px"
	       data-options="singleSelect:true,rownumbers:true,fitColumns:true,collapsible:true,method:'get'">
	    <thead>
	        <tr>
	        	<th data-options="field:'prodId',width:150,sortable:true">原料物料编码</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">物料名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:70,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	        	<th data-options="field:'prodLength',width:70,sortable:true">定尺/米</th>
	            <th data-options="field:'prodLevel',width:90,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodRecordWeight',width:125,sortable:true">原料来料重量/吨</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	        </tr>
	    </thead>
	</table>
	</form>
</div>
<script type="text/javascript">

</script>
