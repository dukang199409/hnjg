<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="退货产品重新入库" style="padding:10px 10px 10px 10px">
		<form id="prodRetReAddForm" class="itemForm" method="post">
		   <table>
		   		 <tr>
		            <td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM5008" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]',events:{blur:onProdIdM5008}" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">产品类别:</td>
		            <td width="210" align="right">
		            	<select name="prodType" id="prodTypeM5008" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				            <c:forEach items="${prodTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">产品名称:</td>
		            <td width="210" align="right">
		            	<input name="prodName" id="prodNameM5008" class="easyui-textbox" type="text" data-options="required:true" style="width:200px;"></input>
		            </td>
		        </tr>
		       <tr>
		            <td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM5008" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			         		 </c:forEach>
						</select>
		            </td>
		       		<td width="120" align="right">规格:</td>
		            <td width="210" align="right">
		            	<input name="prodNorm" id="prodNormM5008" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		        <tr>
		        	<td width="120" align="right">定尺:</td>
		            <td width="210" align="right">
		            	<input name="prodLength" id="prodLengthM5008" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">产品件数:</td>
		            <td width="210" align="right">
		            	<input name="prodNum" id="prodNumM5008" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:0,validType:'length[0,10]'" value="1" style="width:200px;"></input>
		            </td> 
		        </tr>
		        <tr>
		        	<td width="120" align="right">备注:</td>
		            <td width="210" align="right">
		            	<input name="remark" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
		            </td> 
		            <td width="120" align="right">产品总重量/吨:</td>
		            <td width="210" align="right">
		            	<input name="prodRelWeight" id="prodRelWeightM5008" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="left">
	        			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM5008()" style="width:70px;">称重</a>
	        		</td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">录入人:</td>
		            <td width="210" align="right">
		            	<input name="prodInputuser" id="inputuserM5008" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
			    </tr>	        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM5008Form()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5008Form()">重置</a>
		</div>
	</div>
</div>
<script>

	//联动称重机获取实际重量
	function getWeightCheckM5008(){
		var params = {};
		$.post("/rxtx/weighing",params, function(data){
			if(data.status == 200){
				$("#prodRelWeightM5008").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}

	//根据编号查询产品信息
	function onProdIdM5008(){
		var prodId = $("#prodIdM5008").textbox('getValue');
		if(prodId != ''){
			var params = {"prodId":prodId};
			$.post("/changeStatus/getProdInfo",params, function(data){
				if(data.status == 200){
					$("#prodTypeM5008").combobox('setValue',data.data.prodType);
					$("#prodNameM5008").textbox('setValue',data.data.prodName);
					$("#prodMaterialM5008").combobox('setValue',data.data.prodMaterial);
					$("#prodNormM5008").textbox('setValue',data.data.prodNorm);
					$("#prodLengthM5008").numberbox('setValue',data.data.prodLength);
					if(data.data.prodType != 2){
						$("#prodRelWeightM5008").numberbox('setValue',data.data.prodRelWeight);
					}
				}else{
					$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
				}
			});
		}
	}
	
	//提交表单
	function submitM5008Form(){
		
		var prodType = $("#prodTypeM5008").combobox('getValue');
		if(prodType == '3'){
			$.messager.alert('提示','废品不支持退货入库!');
			return;
		}
		
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#inputuserM5008").textbox('setValue',inputUser);
		
		//有效性验证
		if(!$('#prodRetReAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/prodRetRe/reAdd",$("#prodRetReAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','退货产品重新入库成功，产品编号：'+data.data,'info',function(){
					$('#prodRetReAddForm').form('reset');
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function clearM5008Form(){
		$('#prodRetReAddForm').form('reset');
	}
	
</script>