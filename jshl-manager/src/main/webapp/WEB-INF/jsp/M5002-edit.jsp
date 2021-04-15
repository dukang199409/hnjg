<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="pEditing:10px 10px 10px 10px">
	<form id="saleDetailEditForm" class="itemForm" method="post">
	   <table>
	   		<tr>
	        	<td width="120" align="right">销售计划流水:</td>
	            <td width="210" align="right">
	            	<input name="saleSerno" class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	           <td width="120" align="right">销售通知单号:</td>
	            <td width="210" align="right">
	            	<input name="salePlanId" readonly="readonly" class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>       
	        </tr>
	       <tr>
	       		<td width="120" align="right">通知单类别: </td>
	            <td width="210" align="right">
	            	<select name="bsFlag" id="bsFlagM5002Edit" class="easyui-combobox" readonly="readonly" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <c:forEach items="${bsFlagList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td> 
	            <td width="120" align="right">出库标准:</td>
	            <td width="210" align="right">
	            	<select name="saleFlag" class="easyui-combobox"  panelHeight="auto" style="width:200px;">
			          <c:forEach items="${saleFlagAList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td> 
	       </tr>
	        <tr>
	       		<td width="120" align="right">计划出库规格:</td>
	             <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>   	          
	            <td width="120" align="right">计划出库钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	       <tr> 
	       		<td width="120" align="right">计划出库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="saleWeight" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">计划出库数量:</td>
	            <td width="210" align="right">
	            	<input name="saleNum" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>   
	        </tr>
	        <tr>
	             <td width="120" align="right">计划出库定尺:</td>
	            <td width="210" align="right">
	            	<input name="prodLength" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">销售单价:</td>
	            <td width="210" align="right">
	            	<input name="salePrice" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:2,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120"  align="right">备注:</td>
	             <td width="210"  align="right">
	             	<input class="easyui-textbox" name="remark" data-options="required:false" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	            <td width="120" align="right">录入人:</td>
	            <td width="210" align="right">
	            	<input name="inputuser" id="inputuserM5002Edit" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM5002EditForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeM5002EditForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitM5002EditForm(){
		
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#inputuserM5002Edit").textbox('setValue',inputUser);
		//有效性验证
		if(!$('#saleDetailEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		//ajax的post方式提交表单
		//$("#itemEditForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/saleDetail/editSaleDetail",$("#saleDetailEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改销售出库详细信息成功!单号为：'+data.data,'info',function(){
					$("#saleDetailEditWindow").window('close');
					serachM5001Detail();
					searchM5001Form();
					//$("#saleDetailList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeM5002EditForm(){
		$("#saleDetailEditWindow").window('close');
	}
	
</script>
