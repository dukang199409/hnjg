<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="prodPlanDetailEditForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	        	<td width="120" align="right">调度计划编号:</td>
	            <td width="210" align="right">
	            	<input name="planInfoDetailId" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" readonly="readonly" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">产品生产指令号:</td>
	            <td width="210" align="right">
	            	<input name="planId" class="easyui-textbox" readonly="readonly" type="text" data-options="required:true,validType:'length[0,32]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">产品定尺/米:</td>
	            <td width="210" align="right">
	            	<input name="prodLength" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
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
	            	<input name="prodNum" class="easyui-textbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td> 
	            <td width="120" align="right">计划产出量/吨:</td>
	            <td width="210" align="right">
	            	<input name="planOutputVolume" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>           
	        </tr>
	        <tr>
	            <td width="120" align="right">计划完工日期:</td>
	            <td width="210" align="right">
	            	<input id="planFinishDateM3002" name="planFinishDateM3002" class="easyui-datebox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">短溢装:</td>
	            <td width="210" align="right">
	            	<input name="planDyz" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">备注:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" id="remark" name="remark" data-options="required:false" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	         <tr hidden="true">
	        	<td width="120" align="right">录入人:</td>
	            <td width="210"  align="right">
	        		<input name="prodInputuser" id="inputuserM3002Edit" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        	<td width="120" align="right">计划完成日期:</td>
	            <td width="210"  align="right">
	        		<input name="planFinistDateM3002Edit" id="planFinistDateM3002Text" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormM3002Edit()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeFormM3002Edit()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitFormM3002Edit(){
		//有效性验证
		if(!$('#prodPlanDetailEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var inputUser = $("#user_id").html();
		$("#inputuserM3002Edit").textbox('setValue',inputUser);
		$("#planFinistDateM3002Text").textbox('setValue',$('#planFinishDateM3002').datebox("getValue"));
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/prodPlanDetail/edit",$("#prodPlanDetailEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','生产调单详细修改成功!详细调度信息编号为：'+data.data,'info',function(){
					$("#prodPlanDetailEditWindow").window('close');
					serachM3001Detail();
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeFormM3002Edit(){
		$("#prodPlanDetailEditWindow").window('close');
	}
</script>
