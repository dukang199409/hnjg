<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="销售出库通知单打印查询" style="padding:10px 10px 10px 10px">
		<form id="saleProdSearchByPlanFormM5003w" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售出库单号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutPlan" id="prodOutPlanM5003wPrint" readonly="readonly" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">销售计划流水:</td>
		            <td width="210" align="right">
		            	<input name="prodSaleSerno" id="prodSaleSernoM5003wPrint" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		        <tr> 
		        	<td width="120" align="right">规格(多个以空格隔开):</td>
		            <td width="210" align="right">
	            		<input name="prodNorm" id="prodNormM5003wPrint"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,340]'" style="width:200px;"></input>
	            	</td>
		       		<td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM5003wPrint" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodMaterialList}" var="a">
			          	   		<option value="${a.enumKey}">${a.enumValue}</option>
			          		</c:forEach>
						</select>
					</td>
		        </tr>
		        <tr> 
		        	<td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="prodLuno" id="prodLunoM5003wPrint" class="easyui-textbox" type="text"  data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		        	<td width="120" align="right">产品定尺(米):</td>
		            <td width="210" align="right">
		            	<input name="prodLength" id="prodLengthM5003wPrint"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
		            </td>
	           </tr>
	            <tr> 
	           		<td width="120" align="right">产品状态:</td>
		            <td width="210" align="right">
		            	<select name="prodStatus" id="prodStatusM5003wPrint" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodStatusEList}" var="a">
			          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
			         		</c:forEach>
						</select>
		            </td>
		         </tr>
	           <tr hidden="true">
		            <td width="120" align="right">通知单类别:</td>
		            <td width="210" align="right">
		            	<input name="bsFlag" id="bsFlagM5003wPrint" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>   
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM5003wPrint" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM5003wPrint" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM5003wPrintForm()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5003wPrintForm()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="saleProdByPlanListM5003w" title="销售出库通知单打印管理"  style="height:430px"
	       data-options="singleSelect:false,rownumbers:true,collapsible:true,pagination:true,url:'/saleProdByPlan/outProds',method:'post',onBeforeLoad:onBeforeLoadM5003wPrint,pageSize:30,toolbar:toolbarM5003wPrint">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:150">产品编号</th>
	            <th data-options="field:'prodOutPlan',width:100">销售出库单号</th>
	            <th data-options="field:'prodSaleSerno',width:150">销售计划流水</th>
	        	<th data-options="field:'prodType',formatter:BSL.formatProdType,width:120">产品类别</th>
	        	<th data-options="field:'prodPlanNo',width:150">单号/指令号</th>
	        	<th data-options="field:'prodName',width:100">产品名称</th>
	        	<th data-options="field:'prodNorm',width:120">规格</th>
	        	<th data-options="field:'prodMaterial',formatter:BSL.formatProdMaterial,width:80">钢种</th>
	        	<th data-options="field:'prodLength',width:70">定尺/米</th>
	        	<th data-options="field:'prodLuno',width:100">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodRelWeight',width:120">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:120">原料入库重量/吨</th>
				<th data-options="field:'prodOutWeight',width:120">销售复磅重量/吨</th>
	            <th data-options="field:'prodStatus',formatter:BSL.formatProdStatus,width:80">状态</th>
	        	<th data-options="field:'prodCompany',width:120">厂家</th>
	            <th data-options="field:'prodLevel',formatter:BSL.formatProdLevel,width:100">质量等级</th>
	            <th data-options="field:'crtDate',formatter:BSL.formatDateTime,width:80">入库日期</th>
	            <th data-options="field:'updDate',formatter:BSL.formatDateTime,width:80">修改日期</th>
	            <th data-options="field:'remark',width:130">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM5003wPrint(){
		var queryParams = $('#saleProdByPlanListM5003w').datagrid('options').queryParams;
		queryParams.prodOutPlan = $('#prodOutPlanM5003wPrint').val();
		queryParams.prodSaleSerno = $('#prodSaleSernoM5003wPrint').val();
		queryParams.prodNorm = $('#prodNormM5003wPrint').val();
		queryParams.prodMaterial = $('#prodMaterialM5003wPrint').combobox("getValue");
		queryParams.prodLuno = $('#prodLunoM5003wPrint').val();
		queryParams.prodLength = $('#prodLengthM5003wPrint').numberbox("getValue");
		queryParams.prodStatus = $('#prodStatusM5003wPrint').numberbox("getValue");
	}
	
	function getM5003wPrintSelectionsIds(){
    	var receiptList = $("#saleProdByPlanListM5003w");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }

	//查询按钮
	function searchM5003wPrintForm(){
		//page页码
		var page = $("#saleProdByPlanListM5003w").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#saleProdByPlanListM5003w").datagrid('options').pageSize; 
		$("#pageM5003wPrint").textbox('setValue',page);
		$("#rowsM5003wPrint").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#saleProdSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/saleProdByPlan/outProds",$("#saleProdSearchByPlanFormM5003w").serialize(), function(data){
			if(data.status == 200){
	            $('#saleProdByPlanListM5003w').datagrid('loadData',  {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM5003wPrint();
	}
	
	/* 重置表单 */
	function clearM5003wPrintForm(){
		var planid = $('#prodOutPlanM5003wPrint').textbox("getValue");
		$('#saleProdSearchByPlanFormM5003w').form('reset');
		$('#prodOutPlanM5003wPrint').textbox("setValue",planid);
		$('#saleProdByPlanListM5003w').datagrid('loadData',{ total: 0, rows: [] });
	}
	
	 var toolbarM5003wPrint = [{
	        text:'打印销售出库单pdf',
	        iconCls:'icon-print',
	        handler:function(){
				//获取通知单类别
				var bsFlag = $('#bsFlagM5003wPrint').textbox("getValue");
	        	var ids = getM5003wPrintSelectionsIds();
	        	if(ids.length == 0){
	        		$.messager.alert('提示','至少选择一条记录PDF选择打印!');
	        		return ;
	        	}
	        	$.messager.confirm('确认','确认选择这些记录打印？',function(r){
					if (r){
						var strArrays = ids.split(',');
	        	    	var prodIds = [];
	        	    	var prodRucs = [];
	        	    	var prodInfo;
	        	    	for(var i=0;i<strArrays.length;i++){
	        	    		prodIds.push(ids);
	        	    		prodInfo = $("#saleProdByPlanListM5003w").datagrid("getSelections")[i];
	        	    		prodRucs.push(prodInfo.prodRuc);
	        	    	}
	        	    	var mapParam = new Map();
	        			mapParam.set("url","/print/exportPdf");
	        			mapParam.set("planId",$('#prodOutPlanM5003wPrint').val());
	        			mapParam.set("prodIds",prodIds);
	        			mapParam.set("prodRucs",prodRucs);
	        			mapParam.set("saleFlag","0");
	        			mapParam.set("tradeType","M5001Prods");
	        			mapParam.set("loginUserId",$("#user_id").html());
	        			BSL.toNewPagePDF(mapParam);
	        	    }
				});
	        	
	        }
	    }];
 

</script>