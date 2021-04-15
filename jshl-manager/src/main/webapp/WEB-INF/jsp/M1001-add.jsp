<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="receiptAddForm" class="itemForm" method="post">
	   <table>
	        <tr>
	            <td width="120" align="right">通知单类别: </td>
	            <td width="210" align="right">
	            	<select name="bsFlag" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <!-- <option value="0">自购卷板</option>
			          <option value="1" selected>客户来料</option> -->
			          <c:forEach items="${bsFlagSList}" var="a">
			          	   <option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">供应商:</td>
	            <td width="210" align="right">
	            	<input name="bsCompany" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>	           
	        </tr>
	        <tr>	
	            <td width="120" align="right">客户:</td>
	            <td width="210" align="right">
	            	<input name="bsCustomer" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">含质保书: </td>
	            <td width="210" align="right">
	            	<select name="bsHasguarantee" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <!-- <option value="0">否</option>
			          <option value="1">是</option> -->
			          <c:forEach items="${nyFlagList}" var="a">
			          	   <option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">卷数:</td>
	            <td width="210" align="right">
	            	<input name="bsAmt" class="easyui-numberbox" type="text" data-options="required:true,min:1,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">原料来料总重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="bsWeight" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	         <tr>
	            <td width="120" align="right">预计来料规格:</td>
	            <td width="210" align="right">
	            	<input name="bsNorm" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">预计来料时间:</td>
	            <td width="210" align="right">
	            	<input id="bsArrdateAdd" name="bsArrdateAdd" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">运输单位:</td>
	            <td width="210" align="right">
	            	<input name="bsTransport" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">运输车号:</td>
	            <td width="210" align="right">
	            	<input name="bsCarno" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">提货方式:</td>
	            <td width="210" align="right">
		            <select name="bsGettype" class="easyui-combobox" panelHeight="auto" data-options="editable:true,required:false" style="width:200px;">
				       <option value="">请选择...</option>
				       <!-- <option value="0">客户自提</option>
				       <option value="1">配送</option>
				       <option value="2">代办运输</option> -->
				       <c:forEach items="${bsGettypeList}" var="a">
			          	   <option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
				</td>
	            <td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	            <td width="120" align="right">录入人:</td>
	            <td width="210" align="right">
	            	<input name="bsInputuser" id="bsInputuserM1001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">预计来料时间:</td>
	            <td width="210"  align="right">
	        		<input name="bsArrdateAddText" id="bsArrdateAddText" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="submitM1001AddForm" onclick="submitM1001AddForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM1001AddForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitM1001AddForm(){
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#bsInputuserM1001").textbox('setValue',inputUser);
		
		//有效性验证
		if(!$('#receiptAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		var bsArrDate = $('#bsArrdateAdd').datebox("getValue");
		$("#bsArrdateAddText").textbox('setValue',bsArrDate);
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$('#submitM1001AddForm').linkbutton('disable');
		$.post("/receipt/addReceipt",$("#receiptAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增原料入库通知单成功!单号为：'+data.data,'info',function(){
					$("#receiptAddWindow").window('close');
					searchM1001Form();
					//$("#receiptList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
			$('#submitM1001AddForm').linkbutton('enable');
		});
	}
	
	function clearM1001AddForm(){
		$('#receiptAddForm').form('reset');
	}
	
</script>
