<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="enumAddForm" class="itemForm" method="post">
	   <table>
	        <tr>
	        	<td width="120" align="right">数据字典英文名称:</td>
	            <td width="210" align="right">
	            	<input name="enumEnglishName" readonly="readonly" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,50]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">数据字典中文名称:</td>
	            <td width="210" align="right">
	            	<input name="enumChineseName" readonly="readonly" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,100]'" style="width:200px;"></input>
	            </td>           
	        </tr>
	        <tr>
	        	<td width="120" align="right">数据字典key值:</td>
	            <td width="210" align="right">
	            	<input name="enumKey" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">数据字典value值:</td>
	            <td width="210" align="right">
	            	<input name="enumValue" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,120]'" style="width:200px;"></input>
	            </td>           
	        </tr>
	        <tr>
	        	<td width="120" align="right">数据字典排序值:</td>
	            <td width="210" align="right">
	            	<input name="enumOrder" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>        
	        </tr>
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM0004AddForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM0004AddForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitM0004AddForm(){
		
		//有效性验证
		if(!$('#enumAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/enum/add",$("#enumAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增数据字典项成功!','info',function(){
					$("#enumAddWindow").window('close');
					searchM0004Form();
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function clearM0004AddForm(){
		$("#enumAddWindow").window('close');
	}
	
</script>
