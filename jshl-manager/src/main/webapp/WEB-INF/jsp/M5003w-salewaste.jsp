<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="废品发货信息查询" style="padding:10px 10px 10px 10px">
		<form id="saleWasteM5003wForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售通知单号:</td>
		            <td width="210" align="right">
		            	<input name="salePlanId" id="salePlanIdM5003wsaleWaste" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>   
		        </tr>	    
		    </table>
		</form>
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM5003wsaleWasteForm()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5003wsaleWasteForm()">关闭</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="saleWasteM5003wList" title="废品接收信息管理"  style="height:440px"
	       data-options="singleSelect:false,rownumbers:true,collapsible:true,method:'post',url:'/saleProdByPlan/waitSaleWassteGroups',toolbar:toolbarM5003wsaleWaste">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'saleSerno',width:120">销售计划流水</th>
	        	<th data-options="field:'salePlanId',width:110">销售通知单号</th>
	            <th data-options="field:'saleWeight',width:100">计划出库重量/吨</th>
	            <th data-options="field:'prodId',width:120,formatter:BSL.formatWasteType">废品类型</th>
	            <th data-options="field:'prodSumweight',width:130">累计出库重量/吨</th>
	            <th data-options="field:'saleStatus',width:120,formatter:BSL.formatSaleStatus">产品出库状态</th>
	            <th data-options="field:'inputuser',width:70">制单人</th>
	            <th data-options="field:'crtDate',width:90,formatter:BSL.formatDateTime">创建日期</th>
	            <th data-options="field:'updDate',width:90,formatter:BSL.formatDateTime">修改日期</th>
	            <th data-options="field:'remark',width:150">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	//查询按钮
	function searchM5003wsaleWasteForm(){
		//ajax的post方式提交表单
		//$("#saleDetailSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/saleProdByPlan/waitSaleWassteGroups",$("#saleWasteM5003wForm").serialize(), function(data){
			if(data.status == 200){
	           	$('#saleWasteM5003wList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
	
	//关闭按钮
	function clearM5003wsaleWasteForm(){
		$("#saleWasteWindowM5003w").window('close');
	}
	
    function getM5003wsaleWasteSelectionsIds(){
    	var saleDetailList = $("#saleWasteM5003wList");
    	var sels = saleDetailList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].saleSerno);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM5003wsaleWaste = [{
        text:'确认发货',
        iconCls:'icon-ok',
        handler:function(){
        	var ids = getM5003wsaleWasteSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','至少选择一条记录发货!');
        		return ;
        	}
        	$.messager.confirm('确认','确认选择这些记录发货？',function(r){
				if (r){
					var strArrays = ids.split(',');
        	    	var params = {'arrays':strArrays,"prodOutPlan":$('#salePlanIdM5003wsaleWaste').textbox("getValue"),"checkUser":$("#user_id").html()};
        	    	var ps = $.param(params, true);
        	    	$.post("/doSalePlan/sale",ps, function(data){
        	   			if(data.status == 200){
        	   				$.messager.alert('提示','发货成功!',undefined,function(){
        	   					searchM5003wForm();
        	   					$.messager.confirm('确认','是否需要打印本次发货信息？',function(r){
        	   						if (r){
        	   							var bsId = $("#salePlanIdM5003wsaleWaste").textbox('getValue');
        	   							var prodIds = [];
    		   		        	    	for(var i=0;i<strArrays.length;i++){
    		   		        	    		prodIds.push(ids);
    		   		        	    	}
        	   		        	    	var mapParam = new Map();
        	   		        			mapParam.set("url","/print/exportPdf");
        	   		        			mapParam.set("planId",bsId);
        	   		        			mapParam.set("prodIds",prodIds);
        	   		        			mapParam.set("tradeType","M5001Waste");
        	   		        			mapParam.set("loginUserId",$("#user_id").html());
        	   		        			BSL.toNewPagePDF(mapParam);
        	   		        	    }
        	   					});
        	        			$("#saleWasteWindowM5003w").window('close');
        	   				});
        	   			} else {
        	   				$.messager.alert('提示','发货失败：'+data.msg);
        	   			}
        	   		});
        	    }
			});
        }
    }];
    
</script>