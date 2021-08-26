<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="saleDetailAddByIdForm" class="itemForm" method="post">
	   <table>
	        <tr>
	            <td width="120" align="right">生产销售通知单号:</td>
	            <td width="210" align="right">
	            	<input name="salePlanId" id="salePlanIdM5002AddById"  class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>  
	            <td width="120" align="right">通知单类别: </td>
	            <td width="210" align="right">
	            	<select name="bsFlag" id="bsFlagM5002AddById" class="easyui-combobox" disabled="disabled" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			           <c:forEach items="${bsFlagList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>
	        </tr>
	    </table>
	    <table class="easyui-datagrid" id="useFullProdList" title="可出库产品信息表"  style="height:360px"
	       data-options="rownumbers:true,iconCls:'icon-edit',fitColumns:true,collapsible:true,pagination:true,pageSize:10,onClickCell: onClickCell">
		    <thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
		            <th data-options="field:'prodId',width:150,formatter:BSL.formatWasteType">产品编号</th>
		            <th data-options="field:'prodName',width:80,formatter:BSL.formatWasteType">产品名称</th>
		            <th data-options="field:'prodType',width:80,formatter:BSL.formatProdType">产品类型</th>
		            <th data-options="field:'prodNorm',width:80">产品规格</th>
		            <th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial">产品钢种</th>
		            <th data-options="field:'prodPrintWeight',width:120" >产品库存重量/吨</th>
		            <th data-options="field:'saleWeight',width:120,editor:{type:'numberbox',options:{precision:3}}" >产品销售重量/吨</th>
		            <th data-options="field:'salePrice',width:60,editor:{type:'numberbox',options:{precision:2}}">单价</th>
		            <th data-options="field:'remark',width:60,editor:{type:'text'}">备注</th>
		        </tr>
		    </thead>
		</table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM5002AddByIdForm()">批量提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5002AddByIdForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	
	//获取表格prodId字段
	function getM5002AddByIdSelectionsIds(){
    	var saleDetailList = $("#useFullProdList");
    	var sels = saleDetailList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
	
	//提交表单
	function submitM5002AddByIdForm(){
		//提交前保存表格
		if (endEditing()){
			$('#useFullProdList').datagrid('acceptChanges');
		}
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		//有效性验证
		if(!$('#saleDetailAddByIdForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		
		//校验销售单号不能为空
		var salePlanId = $("#salePlanIdM5002AddById").textbox("getValue");
		var bsFlag = $("#bsFlagM5002AddById").combobox("getValue");
		if(salePlanId == ""){
			$.messager.alert('提示','生产销售通知单号不能为空!');
			return ;
		}
		//获取选择内容
		var ids = getM5002AddByIdSelectionsIds();
    	if(ids.length == 0){
    		$.messager.alert('提示','没有出库记录无法提交!');
    		return ;
    	}
    	var strArrays = ids.split(',');
    	var arrays = [];
    	for(var i=0;i<strArrays.length;i++){
    		var data = $("#useFullProdList").datagrid("getSelections")[i];
    		if(data.salePrice == void(0) || data.salePrice == ''){
    			$.messager.alert('提示','销售单价必须输入!产品代码:'+data.prodId);
        		return ;
    		}
    		if(bsFlag == '4'){
    			if(data.saleWeight == void(0) || data.saleWeight == ''){
    				$.messager.alert('提示','废品预出库重量必须输入!产品代码:'+data.prodId);
            		return ;
	    		}
    		}
    		arrays.push(data.prodId+';'+data.saleWeight+';'+data.salePrice+';'+data.remark);
    	}
    	var params = {'inputUser':inputUser,'salePlanId':salePlanId,'arrays':arrays};
    	var ps = $.param(params, true);
    	console.log(ps);
		//ajax的post方式提交表单
		//$("#itemAddByIdForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/saleDetail/addSaleDetailById",ps, function(data){
			if(data.status == 200){
				$.messager.alert('提示','批量新增销售出库详细信息成功!起始单号为：'+data.data,'info',function(){
					$("#saleDetailAddByIdWindow").window('close');
					serachM5001Detail();
					searchM5001Form();
					//$("#saleDetailList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function clearM5002AddByIdForm(){
		var planId = $("#salePlanIdM5002AddById").textbox('getValue');
		var bslFlag = $("#bsFlagM5002AddById").combobox('getValue');
		$('#saleDetailAddByIdForm').form('reset');
		$("#salePlanIdM5002AddById").textbox('setValue',planId);
		$("#bsFlagM5002AddById").combobox('setValue',bslFlag);
		$('#useFullProdList').datagrid('loadData',{ total: 0, rows: [] });
	}
	

	//这三个方法实现表格内容编辑
	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#useFullProdList').datagrid('validateRow', editIndex)){
			$('#useFullProdList').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell(index){
		if (editIndex != index){
			if (endEditing()){
				$('#useFullProdList').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$('#useFullProdList').datagrid('selectRow', editIndex);
			}
		}
	}
	
</script>
