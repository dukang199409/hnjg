<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="产成品生产调度单维护信息查询" style="padding:10px 10px 10px 10px">
		<form id="prodPlanDetailSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">产品生产指令号:</td>
		            <td width="210" align="right">
		            	<input name="planId" id="planIdM3002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        	<td width="120" align="right">调度详细计划编号:</td>
		            <td width="210" align="right">
		            	<input name="planInfoDetailId" id="planInfoDetailIdM3002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>  
		        </tr>
		         <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM3002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM3002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM3002" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM3002" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM3002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM3002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="210" align="right">
		            	<input name="className" id="classNameM3002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM3002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3002Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3002Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="prodPlanDetailList3002" title="产成品生产调度单维护信息管理"  style="height:620px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM3002,collapsible:true,pagination:true,url:'/prodPlanDetail/listInfo',method:'post',onBeforeLoad:onBeforeLoadM3002,pageSize:30,toolbar:toolbarM3002">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'planId',width:130,sortable:true">产品生产指令号</th>
	        	<th data-options="field:'planInfoDetailId',width:200,sortable:true">调度详细计划编号</th>
	        	<th data-options="field:'planLunoZ',width:150,sortable:true">用料炉号</th>
	            <th data-options="field:'prodNormZ',width:100,sortable:true">用料规格</th>
	        	<th data-options="field:'prodMaterialZ',width:100,formatter:BSL.formatProdMaterial,sortable:true">用料钢种</th>	
	            <th data-options="field:'makeNameZ',width:100,sortable:true">制造产品名称</th>     
	        	<th data-options="field:'makeProdNormZ',width:100,sortable:true">制造产品规格</th>
	            <th data-options="field:'prodOrderZ',width:120,sortable:true">销售订单号</th>
	            <th data-options="field:'prodLevel',width:100,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodWeight',width:100,sortable:true">用料重量/吨</th>
	            <th data-options="field:'prodNum',width:100,sortable:true">条数</th>
	            <th data-options="field:'planStatusZ',width:100,formatter:BSL.formatPlanStatus,sortable:true">指令状态</th>
	            <th data-options="field:'planOutputVolume',width:120,sortable:true">计划产出量</th>
	            <th data-options="field:'planFinistDate',width:100,formatter:BSL.formatDateTime,sortable:true">计划完工日期</th>
	            <th data-options="field:'collectedUnits',width:100,formatter:BSL.formatMakeDept,sortable:true">实收机组</th>
	            <th data-options="field:'prodInputuser',width:100,sortable:true">产品录入人</th>
	            <th data-options="field:'crtDate',formatter:BSL.formatDateTime,sortable:true,width:100">创建日期</th>
	            <th data-options="field:'remark',width:300,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="prodPlanDetailAddWindow" class="easyui-window" title="新增生产调度指令" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/prodPlanDetail/M3002-add'" style="width:780px;height:260px;padding:10px;">
	</div>
	<div id="prodPlanDetailEditWindow" class="easyui-window" title="编辑生产调度指令" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/prodPlanDetail/M3002-edit'" style="width:780px;height:280px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM3002(){
		var queryParams = $('#prodPlanDetailList3002').datagrid('options').queryParams;
		queryParams.planInfoDetailId = $('#planInfoDetailIdM3002').val();
		queryParams.planId = $('#planIdM3002').val();
		queryParams.startDate = $('#startDateM3002').datebox("getValue");
		queryParams.endDate = $('#endDateM3002').datebox("getValue");
	}

	//排序查询
	function sortSerachM3002(sort,order){
		$("#sortM3002").textbox('setValue',sort);
		$("#orderM3002").textbox('setValue',order);
		searchM3002Form();
	}

	//查询按钮
	function searchM3002Form(){
		//page页码
		var page = $("#prodPlanDetailList3002").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#prodPlanDetailList3002").datagrid('options').pageSize; 
		$("#pageM3002").textbox('setValue',page);
		$("#rowsM3002").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/prodPlanDetail/listInfo",$("#prodPlanDetailSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#prodPlanDetailList3002').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM3002").textbox('setValue',data.className);
				$("#methodNameM3002").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM3002();
	}
	
	/* 重置表单 */
	function clearM3002Form(){
		$('#prodPlanDetailSearchForm').form('reset');
	}
    
    var toolbarM3002 = [{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#prodPlanDetailList3002").datagrid("getData").className;
			var methodName = $("#prodPlanDetailList3002").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM3002').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM3002').val();
			}
			//获取表头信息
			var header = $("#prodPlanDetailList3002").datagrid("options").columns[0];
			var fields = "";
			var titles = "";
			for(var i=1;i<header.length;i++){
				var field = header[i].field;
				var title = header[i].title;
				var hiddenFlag = header[i].hidden;
				if(!hiddenFlag){
					var dh = i == (header.length-1) ? "":",";
					fields = fields + field + dh;
					titles = titles + title + dh;
				}
			}
			var mapParam = new Map();
			//必传
			mapParam.set("url","/export/exportExcelExtra");
			mapParam.set("fields",fields);
			mapParam.set("titles",titles);
			mapParam.set("className",className);
			mapParam.set("methodName",methodName);
			mapParam.set("excelName","产成品生产调度单维护信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("planInfoDetailId",$('#planInfoDetailIdM3002').val());
			mapParam.set("planId",$('#planIdM3002').val());
			mapParam.set("startDate",$('#startDateM3002').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM3002').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>