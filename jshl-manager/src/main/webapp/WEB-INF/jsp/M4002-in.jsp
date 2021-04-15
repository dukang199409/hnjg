<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="wasteWxInForm" class="itemForm" method="post">
	   <table>
	        <tr>
	            <td width="120"  align="right">废品类型:</td>
	            <td width="210"  align="right">
	            	<select name="wasteType" id="wasteTypeM4002In" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${wasteTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>    
	            <td width="120" align="right">入库重量:</td>
	            <td width="210" align="right">
	            	<input name="wasteWeight" id="wasteWeightM4002In" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            	 <!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM1003()">称重</a> -->
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightM4002In()" style="width:70px;">称重</a>
	        	</td>	  
	        </tr>
	        <tr>	
	            <td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>          
	        </tr>
	         <tr hidden="true">
	        	<td width="120" align="right">录入人:</td>
	            <td width="210"  align="right">
	        		<input name="inputUser" id="inputuserM4002In" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        </tr>
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM4002InForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeM4002InForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	
	//联动称重机获取实际重量
	function getWeightM4002In(){
		var params = {};
		$.post("/rxtx/weighingByD",params, function(data){
			if(data.status == 200){
				$("#wasteWeightM4002In").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//提交表单
	function submitM4002InForm(){
		
		//有效性验证
		if(!$('#wasteWxInForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		if($("#wasteTypeM4002In").combobox("getValue") == ""){
			$.messager.alert('提示','废品类型必输!');
			return ;
		}
		var inputUser = $("#user_id").html();
		$("#inputuserM4002In").textbox('setValue',inputUser);
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/wasteWx/in",$("#wasteWxInForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','废品入库成功!累计重量为：'+data.data,'info',function(){
					$("#wasteWxInWindow").window('close');
					searchM4002Form();
					//$("#receiptList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeM4002InForm(){
		$('#wasteWxInForm').form('reset');
	}
	
</script>
