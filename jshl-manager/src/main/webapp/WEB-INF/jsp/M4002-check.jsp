<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="wasteWxCheckForm" class="itemForm" method="post">
	   <table>
	        <tr>
	            <td width="120"  align="right">废品类型:</td>
	            <td width="210"  align="right">
	            	<select name="wasteTypeS" class="easyui-combobox" panelHeight="auto" disabled="disabled" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${wasteTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>  
	            <td width="120" align="right">废品库存重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="wasteWeight" id="wasteWeightM4002Check" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightM4002Check()" style="width:70px;">称重</a>
	        	</td>   
	        </tr>
	        <tr>	
	        	<td width="120" align="right">累计入库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="wasteInWeight" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">累计出库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="wasteOutWeight" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>	
	            <td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>          
	        </tr>
	         <tr hidden="true">
	         	<td width="120" align="right">废品类型:</td>
	        	 <td width="210"  align="right">
	        		<input name="wasteType" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        	<td width="120" align="right">录入人:</td>
	        	 <td width="210"  align="right">
	        		<input name="inputUser" id="inputuserM4002Check" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        </tr>
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM4002CheckForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeM4002CheckForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//联动称重机获取实际重量
	function getWeightM4002Check(){
		var params = {};
		$.post("/rxtx/weighingByD",params, function(data){
			if(data.status == 200){
				$("#wasteWeightM4002Check").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//提交表单
	function submitM4002CheckForm(){
		
		//有效性验证
		if(!$('#wasteWxCheckForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		var inputUser = $("#user_id").html();
		$("#inputuserM4002Check").textbox('setValue',inputUser);
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/wasteWx/check",$("#wasteWxCheckForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','废品重量修改成功!实际重量为：'+data.data,'info',function(){
					$("#wasteWxCheckWindow").window('close');
					searchM4002Form();
					//$("#receiptList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeM4002CheckForm(){
		$('#wasteWxCheckWindow').window('close');
	}
	
</script>
