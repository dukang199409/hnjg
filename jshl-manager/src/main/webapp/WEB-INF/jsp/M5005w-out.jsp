<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="pEditing:10px 10px 10px 10px">
	<form id="saleWasteWxDetailOutForm" class="itemForm" method="post">
	   <table>
	   		<tr>
	        	<td width="120" align="right">销售计划流水:</td>
	            <td width="210" align="right">
	            	<input name="saleSerno" id="saleSernoM5005wOut" class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	           <td width="120" align="right">销售通知单号:</td>
	            <td width="210" align="right">
	            	<input name="salePlanId" class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>       
	        </tr>
	       <tr> 
	       		<td width="120" align="right">计划出库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="saleWeight" class="easyui-numberbox" type="text" readonly="readonly" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">累计出库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodSumweight" class="easyui-numberbox" type="text" readonly="readonly" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	            <td width="120" align="right">废品类型:</td>
	            <td width="210" align="right">
	            	<select name="prodId" class="easyui-combobox" panelHeight="auto" readonly="readonly" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${wasteTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
	            </td> 
	            <td width="120" align="right">销售单价:</td>
	            <td width="210" align="right">
	            	<input name="salePrice" class="easyui-numberbox" type="text" readonly="readonly" data-options="required:false,min:0,precision:2,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120"  align="right">备注:</td>
	            <td width="210"  align="right">
	             	<input class="easyui-textbox" name="remark" data-options="required:false" style="text; width: 200px;"></input>
	            </td>
	        	<td width="120" align="right">本次出库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodOutWeight" id="prodOutWeightOutM5005w" class="easyui-numberbox" type="text"  data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM5005wout()" style="width:70px;">称重</a>
	        	</td>
	        </tr>
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM5005wOutForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeM5005wOutForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">

	//联动称重机获取实际重量
	function getWeightCheckM5005wout(){
		var params = {};
		$.post("/rxtx/weighing",params, function(data){
			if(data.status == 200){
				$("#prodOutWeightOutM5005w").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//提交表单
	function submitM5005wOutForm(){
		//有效性验证
		if(!$('#saleWasteWxDetailOutForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		var prodOutWeight =  $("#prodOutWeightOutM5005w").numberbox('getValue');
		var saleSerno = $("#saleSernoM5005wOut").textbox('getValue');
		var params = {"saleSerno":saleSerno,"prodOutWeight":prodOutWeight,"inputUser":inputUser};
		//ajax的post方式提交表单
		//$("#itemEditForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/saleWasteWx/out",params, function(data){
			if(data.status == 200){
				$.messager.alert('提示','废品出库成功!单号为：'+data.data,'info',function(){
					$("#saleWasteWxDetailOutWindow").window('close');
					searchM5005wForm();
					//$("#saleDetailList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeM5005wOutForm(){
		$("#saleWasteWxDetailOutWindow").window('close');
	}
	
</script>
