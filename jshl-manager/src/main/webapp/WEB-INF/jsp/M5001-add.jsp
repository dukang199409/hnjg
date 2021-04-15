<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="salePlanAddForm" class="itemForm" method="post">
	   <table>
	        <tr>
	            <td width="120" align="right">通知单类别: </td>
	            <td width="210" align="right">
	            	<select name="bsFlag" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			         	  <option value="">请选择...</option>
				         <c:forEach items="${bsFlagList}" var="a">
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
			           <c:forEach items="${nyFlagList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">应出库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="bsWeight" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">运输单位:</td>
	            <td width="210" align="right">
	            	<input name="bsTransport" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">提货方式:</td>
	            <td width="210" align="right">
		            <select name="bsGettype" class="easyui-combobox" panelHeight="auto" data-options="editable:true,required:false" style="width:200px;">
				       <option value="">请选择...</option>
				       <c:forEach items="${bsGettypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
				</td>
	            <td width="120" align="right">运输车号:</td>
	            <td width="210" align="right">
	            	<input name="bsCarno" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
        		<td width="120" align="right">订单号:</td>
	            <td width="210" align="right">
	            	<input name="bsOrderNo" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	             <td width="120" align="right">预计发货日期:</td>
	            <td width="210" align="right">
	            	<input id="bsArrdateAddM5001" name="bsArrdateAddM5001" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
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
	            <td width="210" align="right">
	            	<input name="bsInputuser" id="inputuserM5001Add" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">预计发货时间:</td>
	            <td width="210"  align="right">
	        		<input name="bsArrdateAddTextM5001" id="bsArrdateAddTextM5001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM5001AddForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5001AddForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitM5001AddForm(){
		
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#inputuserM5001Add").textbox('setValue',inputUser);
		
		//有效性验证
		if(!$('#salePlanAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		var bsArrDate = $('#bsArrdateAddM5001').datebox("getValue");
		$("#bsArrdateAddTextM5001").textbox('setValue',bsArrDate);
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/salePlan/addsalePlan",$("#salePlanAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增销售出库通知单成功!单号为：'+data.data,'info',function(){
					$("#salePlanAddWindow").window('close');
					searchM5001Form();
					//$("#salePlanList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function clearM5001AddForm(){
		$('#salePlanAddForm').form('reset');
	}
	
</script>
