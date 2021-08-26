<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="销售退货/磅差处理" style="padding:10px 10px 10px 10px">
		<form id="prodReturnForm" class="itemForm" method="post">
		   <table>
		   		 <tr>
		        	<td width="120" align="right">处理方式:</td>
		            <td width="210" align="right">
		            	<select name="dealType" id="dealTypeM5007" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				            <c:forEach items="${dealTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM5007" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]',events:{blur:onProdIdM5007}" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">销售出库单号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutPlan" id="prodOutPlanM5007" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,20]',events:{blur:onProdOutPlanM5007}" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">车次流水:</td>
	            	<td width="210" align="right">
		            	<select name="prodOutCarno" id="prodOutCarnoM5007" class="easyui-combobox" panelHeight="auto" data-options="editable:true,required:true" style="width:200px;">
				          <option value="">请选择...</option>
						</select>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">产品名称:</td>
		            <td width="210" align="right">
		            	<input name="prodName" id="prodNameM5007" readonly="readonly" class="easyui-textbox" type="text" style="width:200px;"></input>
		            </td>
		        	<td width="120" align="right">产品类别:</td>
		            <td width="210" align="right">
		            	<select name="prodType" id="prodTypeM5007" readonly="readonly" class="easyui-combobox" panelHeight="auto" data-options="required:false,editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				            <c:forEach items="${prodTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		       <tr>
		        	<td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="prodLuno" id="prodLunoM5007" readonly="readonly" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM5007" readonly="readonly" class="easyui-combobox" panelHeight="auto" data-options="required:false,editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			         		 </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr>
		       		<td width="120" align="right">规格:</td>
		            <td width="210" align="right">
		            	<input name="prodNorm" id="prodNormM5007" readonly="readonly" class="easyui-textbox"  readonly="readonly" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
		            </td> 
		        	<td width="120" align="right">定尺:</td>
		            <td width="210" align="right">
		            	<input name="prodLength" id="prodLengthM5007" readonly="readonly" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">单价:</td>
		            <td width="210" align="right">
		            	<input name="prodPrice" id="prodPriceM5007" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:2,validType:'length[0,10]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">磅差处理/退货重量/吨:</td>
		            <td width="210" align="right">
		            	<input name="prodRelWeight" id="prodRelWeightM5007" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="left">
	        			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM5007()" style="width:70px;">称重</a>
	        		</td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">录入人:</td>
		            <td width="210" align="right">
		            	<input name="prodInputuser" id="inputuserM5007" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
			    </tr>	        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM5007Form()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5007Form()">重置</a>
		</div>
	</div>
</div>
<script>

	//联动称重机获取实际重量
	function getWeightCheckM5007(){
		var params = {};
		$.post("/rxtx/weighing",params, function(data){
			if(data.status == 200){
				$("#prodRelWeightM5007").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}

	//根据编号查询产品信息
	function onProdIdM5007(){
		var dealType = $("#dealTypeM5007").combobox('getValue');
		if(dealType == ""){
			$.messager.alert('提示','处理类型不能为空!');
			return;
		}
		var prodId = $("#prodIdM5007").textbox('getValue');
		if(prodId != ''){
			var params = {"prodId":prodId};
			$.post("/changeStatus/getProdInfo",params, function(data){
				if(data.status == 200){
					$("#prodOutPlanM5007").textbox('setValue',data.data.prodOutPlan)
					$("#prodNameM5007").textbox('setValue',data.data.prodName);
					$("#prodTypeM5007").combobox('setValue',data.data.prodType);
					$("#prodLunoM5007").textbox('setValue',data.data.prodLuno);
					$("#prodMaterialM5007").combobox('setValue',data.data.prodMaterial);
					$("#prodNormM5007").textbox('setValue',data.data.prodNorm);
					$("#prodLengthM5007").numberbox('setValue',data.data.prodLength);
					if(data.data.prodType != 2){
						if(dealType == '2'){
							$("#prodRelWeightM5007").numberbox('setValue',data.data.prodRelWeight);
						}
					}
				}else{
					$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
				}
			});
		}
	}
	
	//根据出库单号获取车次枚举
	function onProdOutPlanM5007(){
		var prodOutPlan = $("#prodOutPlanM5007").textbox("getValue");
		if(prodOutPlan == null || prodOutPlan == ''){
			return;
		}
		var params = {'prodOutPlan':prodOutPlan}
		$.post("/prodReturn/carInfo",params,function(data){
			if(data.status == 200){
				//回显车次枚举
				$("#prodOutCarnoM5007").combobox('setValue', '');
				$("#prodOutCarnoM5007").combobox('loadData', {});
				var dataSource = [];
				console.log(data.data);
				for(var i=0;i<data.data.length;i++){
					var value = data.data[i];
					dataSource.push({"value":value,"text":value});
				}
				$("#prodOutCarnoM5007").combobox("loadData",dataSource);
				
			} else {
				$.messager.alert('提示', data.msg);
			}
		});
	}
	
	//提交表单
	function submitM5007Form(){
		
		var dealType = $("#dealTypeM5007").combobox('getValue');
		
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#inputuserM5007").textbox('setValue',inputUser);
		
		//有效性验证
		if(!$('#prodReturnForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/prodReturn/deal",$("#prodReturnForm").serialize(), function(data){
			if(data.status == 200){
				if(dealType == '2'){
					$.messager.alert('提示','退货处理成功','info',function(){
						$('#prodReturnForm').form('reset');
					});
				}else{
					$.messager.alert('提示','磅差处理成功','info',function(){
						$('#prodReturnForm').form('reset');
					});
				}
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function clearM5007Form(){
		$('#prodReturnForm').form('reset');
	}
	
</script>