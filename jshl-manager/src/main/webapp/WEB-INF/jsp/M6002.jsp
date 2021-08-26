<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="指令作废产品还原" style="padding:10px 10px 10px 10px">
		<form id="voidPlanSerachForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">生产指令号:</td>
		            <td width="210" align="right">
		            	<input name="planId" id="planIdM6002" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		        	<td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM6002Out" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM6002Out" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page1" id="pageM6002Make" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows1" id="rowsM6002Make" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM6002Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="voidPlanM6002()">还原</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="voidPlanOutProdsList" title="指令出库用料产品信息"  style="height:320px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,collapsible:true,pagination:true,url:'/voidPlan/outProds',method:'post',onBeforeLoad:onBeforeLoadM6002Out,pageSize:30">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:180,sortable:true">产品编号</th>
	        	<th data-options="field:'prodType',width:100,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'prodPlanNo',width:140,sortable:true">单号/指令号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">产品名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:100,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodRecordWeight',width:125,sortable:true">原料来料重量/吨</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	            <th data-options="field:'prodParentNo',width:180,sortable:true">父级产品编号</th>
	            <th data-options="field:'prodOutPlan',width:100,sortable:true">出库指令号</th>
	            <th data-options="field:'prodSaleSerno',width:100,sortable:true">销售计划号</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodNum',width:80,sortable:true">数量</th>
	        	<th data-options="field:'prodDclFlag',width:130,formatter:BSL.formatProdDclFlag,sortable:true">外协厂标志</th>
	        	<th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodLevel',width:90,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodSource',width:90,formatter:BSL.formatProdSource,sortable:true">产品来源</th>
	            <th data-options="field:'prodUserType',width:90,formatter:BSL.formatMakeType,sortable:true">纵剪带用途</th>
	            <th data-options="field:'prodBc',width:120,formatter:BSL.formatProdBcStatus,sortable:true">生产班次</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'prodMakeJz',width:120,formatter:BSL.formatPlanJz,sortable:true">生产机组</th>
	        	<th data-options="field:'prodOutCarno',width:100,sortable:true">发货车次流水</th>
	        	<th data-options="field:'prodOriId',width:100,sortable:true">原产品编号</th>
	            <th data-options="field:'prodInputuser',width:70,sortable:true">录入人</th>
	            <th data-options="field:'prodCheckuser',width:70,sortable:true">修改人</th>
	            <th data-options="field:'crtDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'prodOutDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">出库日期</th>
	            <th data-options="field:'updDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	 <table class="easyui-datagrid" id="voidPlanMakeProdsList" title="指令制造产品信息"  style="height:320px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,collapsible:true,pagination:true,url:'/voidPlan/makeProds',method:'post',onBeforeLoad:onBeforeLoadM6002Make,pageSize:30">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:180,sortable:true">产品编号</th>
	        	<th data-options="field:'prodType',width:100,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'prodPlanNo',width:140,sortable:true">单号/指令号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">产品名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:100,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodRecordWeight',width:125,sortable:true">原料来料重量/吨</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	            <th data-options="field:'prodParentNo',width:180,sortable:true">父级产品编号</th>
	            <th data-options="field:'prodOutPlan',width:100,sortable:true">出库指令号</th>
	            <th data-options="field:'prodSaleSerno',width:100,sortable:true">销售计划号</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodNum',width:80,sortable:true">数量</th>
	        	<th data-options="field:'prodDclFlag',width:130,formatter:BSL.formatProdDclFlag,sortable:true">外协厂标志</th>
	        	<th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodLevel',width:90,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodSource',width:90,formatter:BSL.formatProdSource,sortable:true">产品来源</th>
	            <th data-options="field:'prodUserType',width:90,formatter:BSL.formatMakeType,sortable:true">纵剪带用途</th>
	            <th data-options="field:'prodBc',width:120,formatter:BSL.formatProdBcStatus,sortable:true">生产班次</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'prodMakeJz',width:120,formatter:BSL.formatPlanJz,sortable:true">生产机组</th>
	        	<th data-options="field:'prodOutCarno',width:100,sortable:true">发货车次流水</th>
	        	<th data-options="field:'prodOriId',width:100,sortable:true">原产品编号</th>
	            <th data-options="field:'prodInputuser',width:70,sortable:true">录入人</th>
	            <th data-options="field:'prodCheckuser',width:70,sortable:true">修改人</th>
	            <th data-options="field:'crtDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'prodOutDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">出库日期</th>
	            <th data-options="field:'updDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM6002Out(){
		
		var queryParams = $('#voidPlanOutProdsList').datagrid('options').queryParams;
		queryParams.planId = $('#planIdM6002').val();
		
	}
	
	function onBeforeLoadM6002Make(){
		
		var queryParams = $('#voidPlanMakeProdsList').datagrid('options').queryParams;
		queryParams.planId = $('#planIdM6002').val();
		
	}

	//查询按钮
	function searchM6002Form(){
		//page页码
		var page = $("#voidPlanOutProdsList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#voidPlanOutProdsList").datagrid('options').pageSize; 
		$("#pageM6002Out").textbox('setValue',page);
		$("#rowsM6002Out").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#voidPlanSerachForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/voidPlan/outProds",$("#voidPlanSerachForm").serialize(), function(data){
			if(data.status == 200){
	            $('#voidPlanOutProdsList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		
		//page页码
		var page1 = $("#voidPlanMakeProdsList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows1 = $("#voidPlanMakeProdsList").datagrid('options').pageSize; 
		$("#pageM6002Make").textbox('setValue',page1);
		$("#rowsM6002Make").textbox('setValue',rows1);
		//ajax的post方式提交表单
		//$("#voidPlanSerachForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/voidPlan/makeProds",$("#voidPlanSerachForm").serialize(), function(data){
			if(data.status == 200){
	            $('#voidPlanMakeProdsList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		
		onBeforeLoadM6002Out();
		onBeforeLoadM6002Make();
	}
	
	/* 重置表单 */
	function voidPlanM6002(){
		//只有超级管理员能还原
		var user = $("#user_id").html(); 
    	if(user != '000000'){
    		$.messager.alert('提示','只有超级管理员才允许还原指令信息!');
    		return ;
    	}
    	
		$.messager.confirm('重要提醒！','还原指令会将该指令的用料产品库存还原入库，该指令的制造产品删除！是够继续还原？',function(r){
    	    if (r){
  	    		var params = {"planId":$('#planIdM6002').val(),"inputuser": $("#user_id").html()};
               	$.post("/voidPlan/void",params, function(data){
           			if(data.status == 200){
           				$.messager.alert('提示','指令还原完成!');
           			} else {
           				$.messager.alert('提示','指令还原失败：'+data.msg);
           			}
           		});
    	    }
    	});
	}

    
</script>