<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="M5010EditForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	        	<td width="120" align="right">车次流水:</td>
	            <td width="210" align="right">
	            	<input name="carNo" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" readonly="readonly" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">退货重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="retWeight" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td> 
	        </tr>
	        <tr>
	        	<td width="120" align="right">磅差重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="chaWeight" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td> 
	            <td width="120" align="right">运送方式:</td>
	             <td width="210" align="right">
	            	<select name="bsGettype" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			           <c:forEach items="${bsGettypeList}" var="a">
		          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
		         		</c:forEach>
					</select>
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormM5010Edit()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeFormM5010Edit()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitFormM5010Edit(){
		//有效性验证
		if(!$('#prodPlanDetailEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var user = $("#user_id").html(); 
    	if(user != '000000'){
    		$.messager.alert('提示','只有超级管理员才允许修改车次信息!');
    		return ;
    	}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/qualityPrint/edit",$("#M5010EditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改成功','info',function(){
					$("#M5010EditWindow").window('close');
					searchM5010Form();
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeFormM5010Edit(){
		$("#M5010EditWindow").window('close');
	}
</script>
