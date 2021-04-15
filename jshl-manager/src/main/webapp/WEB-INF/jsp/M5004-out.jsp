<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="pEditing:10px 10px 10px 10px">
	<form id="saleProdOutForm" class="itemForm" method="post">
	   <table>
	   		<tr>
	   			<td width="120" align="right">销售通知单号:</td>
	            <td width="210" align="right">
	            	<input name="salePlanId" id="salePlanIdM5004Out" class="easyui-textbox" disabled="disabled" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120"  align="right">详细计划信息:</td>
	   			<td width="210" align="right">
	            	<input name="saleSerno" id="saleSernoM5004Out" class="easyui-textbox" disabled="disabled" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	   		</tr>
	   		<tr>
	            <td width="120" align="right">产品编号:</td>
	            <td width="210" align="right">
	            	<input name="prodId" id="prodIdM5004Out"  class="easyui-textbox" type="text" disabled="disabled" data-options="required:true,validType:'length[0,20]'"  style="width:200px;"></input>
	            </td>
	            <td width="120"  align="right">单号/指令号:</td>
	            <td width="210"  align="right">
	            	<input name="prodPlanNo"  class="easyui-textbox" disabled="disabled" data-options="required:true" style="width:200px;"/>
	            </td>           
	        </tr>
	        <tr>	
	        	<td width="120"  align="right">炉(批)号:</td>
	            <td width="210"  align="right">
	            	<input name="prodLuno" class="easyui-textbox" disabled="disabled"  data-options="required:true,validType:'length[0,20]'" style="width:200px;"/>
	            </td>           
	            <td width="120" align="right">物料名称:</td>
	            <td width="210" align="right">
	            	<input name="prodName" class="easyui-textbox" disabled="disabled"  type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">规格:</td>
	            <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" disabled="disabled"  data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td> 
	            <td width="120" align="right">钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" class="easyui-combobox" disabled="disabled"  data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">定尺(米):</td>
	            <td width="210" align="right">
	            	<input name="prodLength" class="easyui-numberbox" type="text" disabled="disabled" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	             <td width="120" align="right">来料复磅重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" disabled="disabled" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">来料入库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodPrintWeight" id="prodPrintWeightOutM5004" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" disabled="disabled" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">销售复磅重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodOutWeight" id="prodOutWeightOutM5004" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM5004()" style="width:70px;">称重</a>
	        	</td>	            
	        </tr>
	        <tr>	   
	        	<td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM5004OutForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeM5004OutForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//联动称重机获取实际重量
	function getWeightCheckM5004(){
		var params = {};
		$.post("/rxtx/weighing",params, function(data){
			if(data.status == 200){
				$("#prodOutWeightOutM5004").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//提交表单
	function submitM5004OutForm(){
		
		//有效性验证
		if(!$('#saleProdOutForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		//记录录入人员信息
		var prodOutWeight = $("#prodOutWeightOutM5004").numberbox('getValue');
		var inputUser = $("#user_id").html();
		var saleSerno = $("#saleSernoM5004Out").textbox('getValue');
		var salePlanId = $("#salePlanIdM5004Out").textbox('getValue');
		var prodId = $("#prodIdM5004Out").textbox('getValue');
		var prodPrintWeight = $("#prodPrintWeightOutM5004").numberbox('getValue');
		var params = {"prodCheckuser":inputUser,"saleSerno":saleSerno,"salePlanId":salePlanId,"prodId":prodId,"prodOutWeight":prodOutWeight};
		if(prodOutWeight != '' && prodOutWeight > 0){
			var bc = (prodOutWeight - prodPrintWeight)/prodPrintWeight;
			if(bc < -0.003 || bc > 0.003){
				$.messager.confirm('确认','出库复磅重量与入库重量相差超过正负3/1000，确认继续出库？',function(r){
	        	    if (r){
	        			//ajax的post方式提交表单
	        			//$("#itemEditForm").serialize()将表单序列号为key-value形式的字符串
	        			$.post("/saleProd/saleOut",params, function(data){
	        				if(data.status == 200){
	        					$.messager.alert('提示','出库成功!单号为：'+data.data,'info',function(){
	        						$("#saleProdOutWindow").window('close');
	        						searchM5004Form();
	        					});
	        				}else{
	        					$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
	        				}
	        			});
	        	    }
				})
			}else{
				//ajax的post方式提交表单
				//$("#itemEditForm").serialize()将表单序列号为key-value形式的字符串
				$.post("/saleProd/saleOut",params, function(data){
					if(data.status == 200){
						$.messager.alert('提示','出库成功!单号为：'+data.data,'info',function(){
							$("#saleProdOutWindow").window('close');
							searchM5004Form();
						});
					}else{
						$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
					}
				});
			}
		}else{
			$.post("/saleProd/saleOut",params, function(data){
				if(data.status == 200){
					$.messager.alert('提示','出库成功!单号为：'+data.data,'info',function(){
						$("#saleProdOutWindow").window('close');
						searchM5004Form();
					});
				}else{
					$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
				}
			});
		}
	}
	
	function closeM5004OutForm(){
		$("#saleProdOutWindow").window('close');
	}
	
</script>
