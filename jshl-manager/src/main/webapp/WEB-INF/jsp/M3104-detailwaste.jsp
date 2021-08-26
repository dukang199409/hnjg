<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="废品接收信息查询" style="padding:10px 10px 10px 10px">
		<form id="wxJsWasteDetailSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售通知单号:</td>
		            <td width="210" align="right">
		            	<input name="salePlanId" id="salePlanIdM3104detailwaste" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>  
		        </tr>	        
		    </table>
		</form>
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3104detailwasteForm()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3104detailwasteForm()">关闭</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="wxJsWasteDetailList" title="废品接收信息管理"  style="height:400px"
	       data-options="singleSelect:true,rownumbers:true,collapsible:true,method:'post',url:'/prodWxJs/listByCriteria',toolbar:toolbarM3104detailwaste">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'saleSerno',width:120,sortable:true">销售计划流水</th>
	        	<th data-options="field:'salePlanId',width:110,sortable:true">销售通知单号</th>
	            <th data-options="field:'saleWeight',width:100,sortable:true">计划出库重量/吨</th>
	            <th data-options="field:'prodId',width:120,formatter:BSL.formatWasteType,sortable:true">废品类型</th>
	            <th data-options="field:'prodSumweight',width:130,sortable:true">累计出库重量/吨</th>
	            <th data-options="field:'prodJsweight',width:120,sortable:true">已接收重量/吨</th>
	            <th data-options="field:'saleStatus',width:120,formatter:BSL.formatSaleStatus,sortable:true">产品出库状态</th>
	            <th data-options="field:'inputuser',width:70,sortable:true">制单人</th>
	            <th data-options="field:'crtDate',width:90,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'updDate',width:90,formatter:BSL.formatDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:150,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="jsWasteWindow" class="easyui-window" title="废品接收" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/prodWxJs/M3104-jswaste'" style="width:780px;height:230px;padding:10px;">
	</div>
	<div id="jsWasteRebackWindow" class="easyui-window" title="接收退回" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/prodWxJs/M3104-rebackwaste'" style="width:780px;height:230px;padding:10px;">
	</div>
</div>
<script>

	//查询按钮
	function searchM3104detailwasteForm(){
		//ajax的post方式提交表单
		//$("#saleDetailSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/prodWxJs/wasteByBsId",$("#wxJsWasteDetailSearchForm").serialize(), function(data){
			if(data.status == 200){
	           	$('#wxJsWasteDetailList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
	
	//关闭按钮
	function clearM3104detailwasteForm(){
		$("#getJsWasteDetailWindow").window('close');
	}
	
    function getM3104detailwasteSelectionsIds(){
    	var saleDetailList = $("#wxJsWasteDetailList");
    	var sels = saleDetailList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].saleSerno);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM3104detailwaste = [{
        text:'废品接收',
        iconCls:'icon-add',
        handler:function(){
        	var ids = getM3104detailwasteSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录接收!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录接收!');
        		return ;
        	}
        	var data = $("#wxJsWasteDetailList").datagrid("getSelections")[0];
        	$("#jsWasteWindow").window({
        		onLoad :function(){
        			$("#jsWasteForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'接收退回',
        iconCls:'icon-reload',
        handler:function(){
        	var ids = getM3104detailwasteSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录编辑!');
        		return ;
        	}
        	var data = $("#wxJsWasteDetailList").datagrid("getSelections")[0];
        	$("#jsWasteRebackWindow").window({
        		onLoad :function(){
        			$("#jsRebackWasteForm").form("load",data);
        		}
        	}).window("open");
        }
    }];
    
</script>