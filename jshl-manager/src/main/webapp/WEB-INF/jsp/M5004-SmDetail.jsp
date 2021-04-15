<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="pEditing:10px 10px 10px 10px">
	<form id="saleProdSmDetailForm" class="itemForm" method="post">
			 <table>
		   		<tr>
		   			<td width="120" align="right">销售通知单号:</td>
		            <td width="210" align="right">
		            	<input name="salePlanId" id="salePlanIdM5004SmDetail" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120"  align="right">详细计划信息:</td>
		   			<td width="210" align="right">
		            	<input name="saleSerno" id="saleSernoM5004SmDetail" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		   		</tr>
	   		</table>
		   <table class="easyui-datagrid" id="saleProdSmDetailList" style="height:500px"
		       data-options="rownumbers:true,fitColumns:true,collapsible:true,toolbar:toolbarM5004SmDetail">
		    <thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
		        	<th data-options="field:'prodId'">产品编号</th>
		        	<th data-options="field:'prodType',formatter:BSL.formatProdType">产品类别</th>
		        	<th data-options="field:'prodName'">产品名称</th>
		        	<th data-options="field:'prodNorm'">规格</th>
		        	<th data-options="field:'prodMaterial',formatter:BSL.formatProdMaterial">钢种</th>
		        	<th data-options="field:'prodLength'">定尺/米</th>
		            <th data-options="field:'prodPrintWeight'">原料入库重量/吨</th>
		            <th data-options="field:'prodStatus',formatter:BSL.formatProdStatus,">状态</th>
		        	<th data-options="field:'prodLuno'">炉(批)号</th>
		        </tr>
		    </thead>
		</table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM5004SmDetailForm()">确认批量出库</a>
	</div>
</div>
<script type="text/javascript">

	//删除选中的行
	var toolbarM5004SmDetail = [{
		text:'删除',
	    iconCls:'icon-cancel',
	    handler:function(){
	    	//获取所有选中的行
	    	var datas = $('#saleProdSmDetailList').datagrid('getSelections');
	    	if(datas.length>0){
		    	for(var i=0;i<datas.length;i++){
	    	        var data= datas[i];
	    	        var index = $('#saleProdSmDetailList').datagrid('getRowIndex',data);
	    	        $('#saleProdSmDetailList').datagrid('deleteRow',index);	    	        
		    	}
	    	}
	    }
	     	
	}];
	
	//提交表单
	function submitM5004SmDetailForm(){
		$.messager.confirm('确认','确定批量销售出库？',function(r){
    	    if (r){
				var salePlanId = $("#salePlanIdM5004SmDetail").textbox('getValue');
				var saleSerno = $("#saleSernoM5004SmDetail").textbox('getValue');
				if(salePlanId == '' && saleSerno==''){
		     		$.messager.alert('提示', '销售出库单号和销售计划流水不能同时为空！');
		     		return;
		     	}
				//获取所有表格内容
				var arrays = [];
				var rows=$("#saleProdSmDetailList").datagrid('getRows');
				for(var i=0;i<rows.length;i++){
					var id = rows[i]['prodId'];//获取指定列
					arrays.push(id);
				}
				var params = {'arrays':arrays,'salePlanId':salePlanId,'saleSerno':saleSerno,'prodCheckuser':$("#user_id").html()};
				//格式化数组
		    	var ps = $.param(params, true);
				//批量出库
		    	$.post("/saleProd/prodOutPl", ps, function(data) {
		    		if (data.status == 200) {
		    			$.messager.alert('提示','批量销售出库成功!',undefined,function(){
		    				$("#saleProdSmDetailWindow").window('close');
							searchM5004Form();
						});
					} else {
					 	$.messager.alert('提示', data.msg);
					} 
				});
    	    }
		});
	}
	
</script>
