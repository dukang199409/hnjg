<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="废品接收信息查询" style="padding:10px 10px 10px 10px">
		<form id="wxJsAllWasteSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">接收通知单号:</td>
		            <td width="210" align="right">
		            	<input name="salePlanId" id="salePlanIdM3106" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>  
		        	<td width="120" align="right">接收计划流水:</td>
		            <td width="210" align="right">
		            	<input name="saleSerno" id="saleSernoM3106" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td> 
		        </tr>	
		        <tr> 
		        	<td width="120" align="right">废品类别:</td>
		            <td width="210" align="right">
		            	<select name="wasteType" id="wasteTypeM3106" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				            <c:forEach items="${wasteTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		        </tr> 
		         <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM3106" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM3106" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM3106" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM3106" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM3106" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM3106" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM3106" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM3106" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>	       
		    </table>
		</form>
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3106Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3106Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="wxJsAllWasteList" title="废品接收信息管理"  style="height:640px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM3106,collapsible:true,pagination:true,url:'/wxJsAllWasteManager/listWaste',method:'post',onBeforeLoad:onBeforeLoadM3106,pageSize:30,toolbar:toolbarM3106">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'salePlanId',width:110,sortable:true">接收通知单号</th>
	        	<th data-options="field:'saleSerno',width:120,sortable:true">接收计划流水</th>
	            <th data-options="field:'prodId',width:150,formatter:BSL.formatWasteType,sortable:true">废品类型</th>
	            <th data-options="field:'prodSumweight',width:100,sortable:true">累计出库重量/吨</th>
	            <th data-options="field:'prodJsweight',width:120,sortable:true">已接收重量/吨</th>
	            <th data-options="field:'saleStatus',width:120,formatter:BSL.formatSaleStatus,sortable:true">产品出库状态</th>
	            <th data-options="field:'inputuser',width:70,sortable:true">制单人</th>
	            <th data-options="field:'crtDate',width:90,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'updDate',width:90,formatter:BSL.formatDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:150,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM3106(){
		var queryParams = $('#wxJsAllWasteList').datagrid('options').queryParams;
		queryParams.salePlanId = $('#salePlanIdM3106').val();
		queryParams.saleSerno = $('#saleSernoM3106').val();
		queryParams.wasteType = $('#wasteTypeM3106').combobox("getValue");
		queryParams.startDate = $('#startDateM3106').datebox("getValue");
		queryParams.endDate = $('#endDateM3106').datebox("getValue");
	}
	
	//排序查询
	function sortSerachM3106(sort,order){
		$("#sortM3106").textbox('setValue',sort);
		$("#orderM3106").textbox('setValue',order);
		searchM3106Form();
	}
	//查询按钮
	function searchM3106Form(){
		//page页码
		var page = $("#wxJsAllWasteList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#wxJsAllWasteList").datagrid('options').pageSize; 
		$("#pageM3106").textbox('setValue',page);
		$("#rowsM3106").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#saleDetailSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/wxJsAllWasteManager/listWaste",$("#wxJsAllWasteSearchForm").serialize(), function(data){
			if(data.status == 200){
	           	$('#wxJsAllWasteList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM3106").textbox('setValue',data.className);
				$("#methodNameM3106").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
	
	//重置按钮
	function clearM3106Form(){
		$("#wxJsAllWasteSearchForm").form('reset');
	}
	
    function getM3106SelectionsIds(){
    	var saleDetailList = $("#wxJsAllWasteList");
    	var sels = saleDetailList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].saleSerno);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM3106 = [{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#wxJsAllWasteList").datagrid("getData").className;
			var methodName = $("#wxJsAllWasteList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM3106').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM3106').val();
			}
			//获取表头信息
			var header = $("#wxJsAllWasteList").datagrid("options").columns[0];
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
			mapParam.set("excelName","外协厂废品接收信息汇总");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","10000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("salePlanId",$('#salePlanIdM3106').val());
			mapParam.set("saleSerno",$('#saleSernoM3106').val());
			mapParam.set("wasteType",$('#wasteTypeM3106').combobox("getValue"));
			mapParam.set("startDate",$('#startDateM3106').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM3106').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>