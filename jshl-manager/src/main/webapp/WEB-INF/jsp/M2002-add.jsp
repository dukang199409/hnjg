<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="planDetailAddForm" class="itemForm" method="post">
	    <table>
	        <tr>
	        	<td width="120" align="right">纵剪带生产指令号:</td>
	            <td width="210" align="right">
	            	<input name="planId" id="planIdM2002Add" class="easyui-textbox" readonly="readonly" type="text" data-options="required:true,validType:'length[0,32]'" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">制造规格: </td>
	            <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>         
	        </tr>
	        <tr>
	        	<td width="120" align="right">产品质量等级: </td>
	            <td width="210" align="right">
	            	<select name="prodLevel" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			           <option value="">请选择...</option>
			           <c:forEach items="${prodLevelList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        	<td width="120" align="right">条数: </td>
	            <td width="210" align="right">
	            	<input name="prodNum" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>	           
	        </tr>
	        <tr>
	        	<td width="120" align="right">用料重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodWeight" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">计划产出量/吨:</td>
	            <td width="210" align="right">
	            	<input name="planOutputVolume" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">计划完工日期:</td>
	            <td width="210" align="right">
	            	<input id="planFinistDateM2002" name="planFinistDateM2002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
	            </td>
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
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="M2002Add" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">

	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#planDetailAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var inputUser = $("#user_id").html();
		$("#prod_inputuser_M2002").textbox('setValue',inputUser);
		$('#M2002Add').linkbutton('disable');
		$.post("/planDetail/add?planFinistDateM2002="+$('#planFinistDateM2002').datebox("getValue"), $("#planDetailAddForm").serialize(), function(data) {
			if (data.status == 200) {
				$.messager.alert('提示','纵剪调单详细新增成功!计划编号为：'+data.data,'info',function(){
					$("#planDetailAddWindow").window('close');
					serachM2001Detail();
				});
			} else {
				$.messager.alert('提示', data.msg);
			}
			$('#M2002Add').linkbutton('enable');
		});
	}

	function clearForm() {
		var planId = $("#planIdM2002Add").textbox('getValue');
		$('#planDetailAddForm').form('reset');
		$("#planIdM2002Add").textbox('setValue',planId);
	}
</script>
