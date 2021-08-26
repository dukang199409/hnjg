<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="实物状态强制维护" style="padding:10px 10px 10px 10px">
		<form id="changeStatusForm" class="itemForm" method="post">
		   <table>
		        <tr>
		            <td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="changeProdId" id="changeProdIdM6001" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,20]',events:{blur:onChangeProdIdM6001}" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">产品名称:</td>
		            <td width="210" align="right">
		            	<input name="prodName" id="prodNameM6001" class="easyui-textbox" type="text" readonly="readonly" style="width:200px;"></input>
		            </td>
		        </tr>
		         <tr>
		        	<td width="120" align="right">维护前状态:</td>
		            <td width="210" align="right">
		            	<select name="beforeStatus" id="beforeStatusM6001" class="easyui-combobox" panelHeight="auto" readonly="readonly"  style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">维护后状态:</td>
		            <td width="210" align="right">
		            	<select name="afterStatus" id="afterStatusM6001" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				         <c:forEach items="${prodStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
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
		            	<input name="inputuser" id="inputuserM6001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
			    </tr>	        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM6001Form()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM6001Form()">重置</a>
		</div>
	</div>
</div>
<script>

	//根据编号查询产品信息
	function onChangeProdIdM6001(){
		var prodId = $("#changeProdIdM6001").textbox('getValue');
		if(prodId != ''){
			var params = {"prodId":prodId};
			$.post("/changeStatus/getProdInfo",params, function(data){
				if(data.status == 200){
					$("#prodNameM6001").textbox('setValue',data.data.prodName);
					$("#beforeStatusM6001").combobox('setValue',data.data.prodStatus);
				}else{
					$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
				}
			});
		}
	}

	//提交表单
	function submitM6001Form(){
	
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#inputuserM6001").textbox('setValue',inputUser);
		
		//有效性验证
		if(!$('#changeStatusForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		if($("#afterStatusM6001").combobox("getValue") == ""){
			$.messager.alert('提示','维护后状态必输!');
			return ;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/changeStatus/change",$("#changeStatusForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','维护产品状态成功!产品编号为：'+data.data,'info',function(){
					$('#changeStatusForm').form('reset');
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function clearM6001Form(){
		$('#changeStatusForm').form('reset');
	}
	
</script>