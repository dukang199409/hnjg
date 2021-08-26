<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="pEditByIding:10px 10px 10px 10px">
	<form id="qualityPrintForm" class="itemForm" method="post">
	   <table>
	   		<tr>
	   			<td width="120" align="right">发货车次流水信息:</td>
	            <td width="210" align="right">
	            	<input name="prodOutCarno" id="prodOutCarnoM5010Detail" class="easyui-textbox" type="text" readonly="readonly" style="width:200px;"></input>
	            </td>
	         <tr> 
	        	<td width="120" align="right"></td>
	            <td width="210"  align="right">
	            </td>
	            <td width="120" align="right"></td>
	            <td width="210"  align="right">
	            </td>
	        </tr>
	    </table>
	   <table class="easyui-datagrid" id="prodQualityDetailList" title="发货车次流水详细信息管理"  style="height:460px"
	       data-options="singleSelect:true,rownumbers:true,collapsible:true,toolbar:toolbarM5010D">
	    <thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
	            	<th data-options="field:'prodOutCarno',width:200,sortable:true">发货车次流水</th>
		        	<th data-options="field:'prodPlanNo',width:150,sortable:true">生产批号</th>
		        	<th data-options="field:'prodLuno',width:100,sortable:true">炉(批)号</th>
		        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
		        	<th data-options="field:'prodName',width:100,sortable:true">产品名称</th>
		        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
		        	<th data-options="field:'prodMaterial',width:100,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
		        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
		        	<th data-options="field:'prodLevel',width:90,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
		            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
		            <th data-options="field:'sumProdNum',width:125,sortable:true">出库数量</th>
		            <th data-options="field:'sumProdWeight',width:125,sortable:true">出库重量/吨</th>
		        	<th data-options="field:'chemicalC',width:125">化学成份C(x100)</th>
		        	<th data-options="field:'chemicalMn',width:125">化学成份Mn(x100)</th>
		        	<th data-options="field:'chemicalSi',width:125">化学成份Si(x100)</th>
		        	<th data-options="field:'chemicalS',width:125">化学成份S(x1000)</th>
		        	<th data-options="field:'chemicalP',width:125">化学成份P(x1000)</th>
		        	<th data-options="field:'chemicalTi',width:125">化学成份Ti(x100)</th>
		        	<th data-options="field:'chemicalNi',width:125">化学成份Ni(x100)</th>
		        	<th data-options="field:'chemicalCr',width:125">化学成份Cr(x100)</th>
		        	<th data-options="field:'chemicalCu',width:125">化学成份Cu(x100)</th>
		        	<th data-options="field:'chemicalNb',width:125">化学成份Nb(x100)</th>
		        	<th data-options="field:'mechanicalS',width:140">力学性能屈服点σs(MPa)</th>
		        	<th data-options="field:'mechanicalB',width:140">力学性能抗拉强度σb(Mpa)</th>
		        	<th data-options="field:'mechanicalL',width:125">力学性能伸长率(%)</th>
		        	<th data-options="field:'impactT',width:125">V型冲击力温度T(℃)</th>
		        	<th data-options="field:'impactN',width:125">V型冲击力数值(J)</th>
		       	</tr>
	    	</thead>
		</table>
		<div id="qualityAddWindow2" class="easyui-window" title="新增质量信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/raw/M1005-add'" style="width:810px;height:500px;padding:10px;">
		</div>
	</form>
</div>
<script type="text/javascript">
	
	function getM5010DSelectionsIds(){
		var prodQualityDetailList = $("#prodQualityDetailList");
		var sels = prodQualityDetailList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].prodLuno);
		}
		ids = ids.join(",");
		return ids;
	}
	
	var toolbarM5010D = [{
	    text:'炉号质量补录',
	    iconCls:'icon-edit',
	    handler:function(){
	    	var ids = getM5010DSelectionsIds();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','必须选择一条记录补录!');
	    		return ;
	    	}
	    	if(ids.indexOf(',') > 0){
	    		$.messager.alert('提示','只能选择一条记录补录!');
	    		return ;
	    	}
	    	//回显数据
			var data = $("#prodQualityDetailList").datagrid("getSelections")[0];
	    	var prodLuno = data.prodLuno;
    		$("#qualityAddWindow2").window({
        		onLoad :function(){
        			//回显数据
        			$("#qualityAddForm").form("load",{"luId":prodLuno});
        		}
        	}).window("open");
    		
	    }
	},{
	    text:'刷新',
	    iconCls:'icon-reload',
	    handler:function(){
	    	var params = {"prodOutCarno": $("#prodOutCarnoM5010Detail").val()};
			$.post("/qualityPrint/detail",params, function(data){
	  			if(data.status == 200){
	  				$('#prodQualityDetailList').datagrid('loadData', data.data);
	  			} else {
	  				$.messager.alert('提示','详情查询失败：'+data.msg);
	  			}
	  		});
	    }
	}];
	
	
</script>
