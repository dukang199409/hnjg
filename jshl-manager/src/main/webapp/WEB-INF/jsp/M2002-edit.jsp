<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="planDetailEditForm" class="itemForm" method="post">
	    <table cellpadding="5">
	    	<tr>
	    		<td width="120" align="right">调度计划编号: </td>
	            <td width="210" align="right">
	            	<input name="planInfoDetailId" class="easyui-textbox" type="text" data-options="required:false" readonly="readonly" style="width:200px;"></input>
	            </td>
	    		<td width="120" align="right">纵剪带生产指令号:</td>
	            <td width="210" align="right">
	            	<input name="planId" class="easyui-textbox" readonly="readonly" type="text" data-options="required:true,validType:'length[0,32]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">制造规格: </td>
	            <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>  	
	            <td width="120" align="right">产品质量等级: </td>
	            <td width="210" align="right">
	            	<select name="prodLevel" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			         <c:forEach items="${prodLevelList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>  
	        </tr>
	        <tr>
	        	<td width="120" align="right">条数: </td>
	            <td width="210" align="right">
	            	<input name="prodNum" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">用料重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodWeight" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>	           
	        </tr>
	        <tr>
	        	<td width="120" align="right">计划产出量/吨:</td>
	            <td width="210" align="right">
	            	<input name="planOutputVolume" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">计划完工日期:</td>
	            <td width="210" align="right">
	            	<input id="planFinishDateM2002" name="planFinishDateM2002" class="easyui-datebox" type="text" panelHeight="225px" data-options="required:false" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">实收机组:</td>
	            <td width="210" align="right">
	            	<select name="collectedUnits" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			         <c:forEach items="${planDepartmentList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">备注:</td>
	        	 <td width="210"  align="right">
	            	<input class="easyui-textbox" name="remark" data-options="required:false" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	        	<td width="120" align="right">录入人:</td>
	            <td width="210"  align="right">
	        		<input name="prodInputuser" id="prod_inputuser_M2002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        	<td width="120" align="right">计划完成日期:</td>
	            <td width="210"  align="right">
	        		<input name="planFinistDateM2002" id="planFinistDateM2002Text" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">

	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#planDetailEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var inputUser = $("#user_id").html();
		$("#prod_inputuser_M2002").textbox('setValue',inputUser);
		$("#planFinistDateM2002Text").textbox('setValue',$('#planFinishDateM2002').datebox("getValue"));
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/planDetail/update",$("#planDetailEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','纵剪调单详细修改成功！','info',function(){
					$("#planDetailEditWindow").window('close');
					serachM2001Detail();
				});
			} else {
				$.messager.alert('提示', data.msg);
			}
		});
	}
	
	function closeForm(){
		$("#planDetailEditWindow").window('close');
	}
	
</script>
