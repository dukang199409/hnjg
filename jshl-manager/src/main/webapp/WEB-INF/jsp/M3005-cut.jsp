<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="prodInfoCutForm" class="itemForm" method="post">
	   <table>
	         <tr>
	            <td width="120"  align="right">件号:</td>
	            <td width="210"  align="right">
	            	<input name="prodId" readonly="readonly" class="easyui-textbox" data-options="required:true" style="width:200px;"/>
	            </td>   
	            <td width="120"  align="right">产品生产指令:</td>
	            <td width="210"  align="right">
	            	<input name="prodPlanNo" readonly="readonly" class="easyui-textbox" data-options="required:true" style="width:200px;"/>
	            </td>      
	        </tr>
	        <tr> 
	           <td width="120" align="right">单包支数:</td>
	            <td width="210" align="right">
	            	<input name="prodNum" readonly="readonly" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">产品实际重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" readonly="readonly" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	           <td width="120" align="right">包1-支数:</td>
	            <td width="210" align="right">
	            	<input name="prodNum1" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">包1-重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight1" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	           <td width="120" align="right">包2-支数:</td>
	            <td width="210" align="right">
	            	<input name="prodNum2" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">包2-重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight2" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	           <td width="120" align="right">包3-支数:</td>
	            <td width="210" align="right">
	            	<input name="prodNum3" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">包3-重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight3" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	           <td width="120" align="right">包4-支数:</td>
	            <td width="210" align="right">
	            	<input name="prodNum4" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">包4-重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight4" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	            <td width="120" align="right">录入人:</td>
	            <td width="210" align="right">
	            	<input name="prodInputuser" id="prodInputuserM3005Cut" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM3005CutForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeM3005CutForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitM3005CutForm(){
		
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#prodInputuserM3005Cut").textbox('setValue',inputUser);
		//有效性验证
		if(!$('#prodInfoCutForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/prodManager/cut",$("#prodInfoCutForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','拆分产品信息成功!单号为：'+data.data,'info',function(){
					$("#prodCutWindow").window('close');
					searchM3005Form();
					//$("#receiptList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeM3005CutForm(){
		$("#prodCutWindow").window('close');
	}
	
</script>
