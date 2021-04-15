<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="halfRawOutputSMForm" class="itemForm" method="post">
	    <table cellpadding="5">
	         <tr>
	        	<td width="120"  align="right">盘号:</td>
	            <td width="210"  align="right">
	            	<input name="prodId" class="easyui-textbox" type="text" readonly="true" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">物料名称:</td>
	            <td width="210" align="right">
	            	<input name="prodName" class="easyui-textbox" type="text" disabled="disabled" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>	           
	        </tr>
	        <tr>	
	        	<td width="120" align="right">纵剪带生产指令号:</td>
	            <td width="210" align="right">
	            	<input name="prodPlanNo" class="easyui-textbox" type="text" disabled="disabled" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>  
	            <td width="120" align="right">炉(批)号:</td>
	            <td width="210" align="right">
	            	<input name="prodLuno" class="easyui-textbox" type="text" disabled="disabled" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
	            </td>  
	        </tr>
	        <tr>
	        	<td width="120" align="right">钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" class="easyui-combobox" panelHeight="auto" disabled="disabled"  data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
				</td>
	            <td width="120" align="right">规格:</td>
	             <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>   
	        </tr>
	        <tr>
	            <td width="120" align="right">产品实际重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" class="easyui-numberbox" disabled="disabled" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">入库仓库/区:</td>
	            <td width="210" align="right">
	            	<select name="prodRuc" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
			           <option value="">请选择...</option>
			          <c:forEach items="${prodRucList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td> 
	        </tr>
	        <tr>
	        	<td width="120"  align="right">生产机组:</td>
	            <td width="210"  align="right">
	            	<select name="planJz" panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${prodUnitsSCList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" type="text" disabled="disabled"  data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>
	        </tr>
	         <tr hidden="true">
	        	<td width="120" align="right">录入人:</td>
	            <td width="210"  align="right">
	        		<input name="prodCheckuser" id="inputuserM3004SM" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormM3004SM()">确认出库</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeFormM3004SM()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitFormM3004SM(){
		var inputUser = $("#user_id").html();
		$("#inputuserM3004SM").textbox('setValue',inputUser);
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/halfRawOutput/output",$("#halfRawOutputSMForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','出库成功，编号为：'+data.data,'info',function(){
					$("#halfRawOutputSMWindow").window('close');
					$("#halfRawOutputList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeFormM3004SM(){
		$("#halfRawOutputSMWindow").window('close');
	}
</script>
