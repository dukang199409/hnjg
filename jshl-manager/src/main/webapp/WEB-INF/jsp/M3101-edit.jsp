<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="dclProdInfoEditForm" class="itemForm" method="post">
	   <table>
	         <tr>
	            <td width="120"  align="right">待处理品编号:</td>
	            <td width="210"  align="right">
	            	<input name="prodId" readonly="readonly" class="easyui-textbox" data-options="required:true" style="width:200px;"/>
	            </td>   
	            <td width="120"  align="right">产品生产指令:</td>
	            <td width="210"  align="right">
	            	<input name="prodPlanNo" readonly="readonly" class="easyui-textbox" data-options="required:true" style="width:200px;"/>
	            </td>      
	        </tr>
	        <tr>        
	            <td width="120" align="right">产品名称:</td>
	            <td width="210" align="right">
	            	<input name="prodName" class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>  
	            <td width="120" align="right">产品钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" class="easyui-combobox" panelHeight="auto" readonly="readonly" data-options="editable:true" style="width:200px;">
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
				</td>      
	        </tr>
	        <tr>	 
	            <td width="120" align="right">父级盘号:</td>
	            <td width="210" align="right">
	            	<input name="prodParentNo" id="prodIdCfm_M2004" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,20]'"  style="width:200px;"></input>
	            </td>  
	            <td width="120" align="right">产品规格:</td>
	            <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>  
	        </tr>
	        <tr> 
	        	<td width="120" align="right">定尺(米):</td>
	            <td width="210" align="right">
	            	<input name="prodLength" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">产品实际重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" id="prodRelWeightM3101Edit" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightEditM3101()" style="width:70px;">称重</a>
	        	</td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">单包件数:</td>
	        	<td width="210" align="right">
	            	<input name="prodNum" id="prodNumM3101" class="easyui-numberbox" type="text" data-options="required:true,min:1,precision:0,validType:'length[0,10]'" value="1" style="width:200px;"></input>
	            </td>
	           <td width="120" align="right">质量等级:</td>
	            <td width="210" align="right">
	            	<select name="prodLevel" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${prodLevelList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>	
	          <td width="120" align="right">生产班次:</td>
	            <td width="210" align="right">
	            	<select name="prodBc" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
			           <option value="">请选择...</option>
			          <c:forEach items="${prodBcList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>   
	            <td width="120" align="right">入库仓库/区:</td>
	            <td width="210" align="right">
	            	<select name="prodRuc" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
			           <option value="">请选择...</option>
			          <c:forEach items="${prodRucList}" var="a">
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
	            	<input name="prodInputuser" id="prodInputuserM3101Edit" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM3101EditForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeM3101EditForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//联动称重机获取实际重量
	function getWeightEditM3101(){
		var params = {};
		$.post("/rxtx/weighing",params, function(data){
			if(data.status == 200){
				$("#prodRelWeightM3101Edit").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//提交表单
	function submitM3101EditForm(){
		
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#prodInputuserM3101Edit").textbox('setValue',inputUser);
		//有效性验证
		if(!$('#dclProdInfoEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/dclProd/editProd",$("#dclProdInfoEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改待处理品信息成功!单号为：'+data.data,'info',function(){
					$("#dclProdEditWindow").window('close');
					searchM3101Form();
					//$("#receiptList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeM3101EditForm(){
		$("#dclProdEditWindow").window('close');
	}
	
</script>
