<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="paramEditForm" class="itemForm" method="post">
	   <table>
	        <tr>
	        	<td width="120" align="right">参数编码:</td>
	            <td width="210" align="right">
	            	<input name="paramId" readonly="readonly" class="easyui-textbox" type="text" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">参数名称:</td>
	            <td width="210" align="right">
	            	<input name="paramName" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,100]'" style="width:200px;"></input>
	            </td>           
	        </tr>
	        <tr>
	            <td width="120" align="right">参数值:</td>
	            <td width="210" align="right">
	            	<input name="paramValue" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,256]'" style="width:200px;"></input>
	            </td> 
	            <td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-numberbox" type="text" style="width:200px;"></input>
	            </td>                
	        </tr>
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM0005EditForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM0005AddForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitM0005EditForm(){
		
		//有效性验证
		if(!$('#paramEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/param/editParam",$("#paramEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改参数信息成功!','info',function(){
					$("#paramEditWindow").window('close');
					searchM0005Form();
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function clearM0005AddForm(){
		$("#paramEditWindow").window('close');
	}
	
</script>
