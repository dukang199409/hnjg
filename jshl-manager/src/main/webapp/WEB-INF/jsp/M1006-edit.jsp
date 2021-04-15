<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="pEditing:10px 10px 10px 10px">
	<form id="sendFlagEditForm" class="itemForm" method="post">
	   <table>
	       <tr>
	        	<td width="120" align="right">炉(批)号:</td>
	            <td width="210" align="right">
	            	<input name="luId" class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,32]'" style="width:200px;"></input>
	            </td>
	       		<td width="120" align="right">送检标志:</td>
	       		<td width="210" align="right">
	            	<select name="sendFlag"  class="easyui-combobox" panelHeight="auto" data-options="editable:true,required:true" style="width:200px;">
			            <option value="">请选择...</option>
			            <c:forEach items="${nyFlagList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			            </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr> 
	        	<td width="120" align="right">送检结果:</td>
	            <td width="210" align="right">
	            	<input name="sendResult" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,256]'" style="width:200px;" maxLength="10"></input>
	            </td>
	            <td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,256]'" style="width:200px;" maxLength="10"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	            <td width="120" align="right">录入人:</td>
	            <td width="210" align="right">
	            	<input name="inputuser" id="inputUserM1006Edit" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">界面标志:</td>
	            <td width="210" align="right">
	            	<input name="flag" id="flagM1006Edit" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	        </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM1006Edit()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetM1006Edit()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitM1006Edit(){
		//记录复核人员信息
		var checkUser = $("#user_id").html(); 
		$("#inputUserM1006Edit").textbox('setValue',checkUser);
		
		//有效性验证
		if(!$('#sendFlagEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//ajax的post方式提交表单
		//$("#itemEditForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/sendFlag/edit",$("#sendFlagEditForm").serialize(), function(data){
			if(data.status == 200){
				var luId = data.data;
				$.messager.alert('提示','修改成功!炉号为：'+luId,'info',function(){
					var flag = $("#flagM1006Edit").textbox('getValue');
					if(flag == "M2004"){
						$("#sendFlagEditWindowM2004").window('close');
					}else if(flag == "M2004SM"){
						$("#sendFlagEditWindowM2004SM").window('close');
					}else{
						$("#sendFlagEditWindow").window('close');
						searchM1006Form();
					}
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function resetM1006Edit(){
		var flag = $("#flagM1006Edit").textbox('getValue');
		if(flag == "M2004"){
			$("#sendFlagEditWindowM2004").window('close');
		}else if(flag == "M2004SM"){
			$("#sendFlagEditWindowM2004SM").window('close');
		}else{
			$("#sendFlagEditWindow").window('close');
		}
	}
</script>
