<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="userTypeAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td width="120"  align="right">员工编号:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" type="text" name="userId" readonly="readonly" data-options="required:true" style="width: 200px;"></input>
	            </td>
	            <td width="120"  align="right">员工名称:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" type="text" name="userName" readonly="readonly" data-options="required:true" style="width: 200px;"></input>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">角色类型: </td>
	        	<td width="210"  align="right">
	            	<select name="userType"  panelHeight="auto" class="easyui-combobox" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <!-- <option value="00">00-管理员</option> -->
					  <c:forEach items="${userTypeList}" var="a">
			          	   <option value="${a.enumKey}">${a.enumKey}-${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>	
	           <td width="120" align="right">备注:</td>
	           <td width="210"  align="right">
	           		<input class="easyui-textbox" name="remark" data-options="required:false" style="text; width: 200px;"></input>
	           	</td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormM0002Add()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearFormM0002Add()">重置</a>
	</div>
</div>
<script type="text/javascript">

	//提交表单
	function submitFormM0002Add(){
		//有效性验证
		if(!$('#userTypeAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/userType/add",$("#userTypeAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','员工角色配置成功!','info',function(){
					$("#userTypeAddWindow").window('close');
					searchFormM0002();
				});
			} else {
				$.messager.alert('提示', data.msg);
			}
		});
	}
	
	function clearFormM0002Add(){
		$('#userTypeAddForm').form('reset');
	}
</script>
