<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="废品销售出库信息查询" style="padding:10px 10px 10px 10px">
		<form id="saleWasteWxDetailSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售通知单号:</td>
		            <td width="210" align="right">
		            	<input name="salePlanId" id="salePlanIdM5005w" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>  
		            <td width="120" align="right">销售计划流水:</td>
		            <td width="210" align="right">
		            	<input name="saleSerno" id="saleSernoM5005w" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		       <tr>
		            <td width="120" align="right">销售计划状态:</td>
		            <td width="210" align="right">
		            	<select name="saleStatus" id="saleStatusM5005w" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${saleStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">废品类别:</td>
		            <td width="210" align="right">
		            	<select name="prodId" id="prodIdM5005w" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
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
		            	<input name="startDate" id="startDateM5005w" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM5005w" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM5005w" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM5005w" class="easyui-textbox" type="text" data-options="required:false"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM5005w" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM5005w" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM5005w" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM5005w" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM5005wForm()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5005wForm()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="saleWasteWxDetailList" title="废品销售出库信息管理"  style="height:570px"
	       data-options="singleSelect:true,rownumbers:true,fitColumns:true,remoteSort:false,onSortColumn:sortSerachM5005w,collapsible:true,pagination:true,url:'/saleWasteWx/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM5005w,pageSize:30,toolbar:toolbarM5005w">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'salePlanId',width:110,sortable:true">销售通知单号</th>
	        	<th data-options="field:'saleSerno',width:120,sortable:true">销售计划流水</th>
	            <th data-options="field:'bsCustomer',width:170,sortable:true">客户</th>
	            <th data-options="field:'saleWeight',width:100,sortable:true">计划出库重量/吨</th>
	            <th data-options="field:'prodId',width:150,formatter:BSL.formatWasteType,sortable:true">计划出库废品类型</th>
	            <th data-options="field:'prodSumweight',width:100,sortable:true">累计出库重量/吨</th>
	            <th data-options="field:'saleStatus',width:120,formatter:BSL.formatSaleStatus,sortable:true">产品出库状态</th>
	            <th data-options="field:'inputuser',width:70,sortable:true">制单人</th>
	            <th data-options="field:'crtDate',width:90,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'updDate',width:90,formatter:BSL.formatDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:60,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="saleWasteWxDetailOutWindow" class="easyui-window" title="废品销售出库" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/saleDetail/M5005w-out'" style="width:780px;height:230px;padding:10px;">
	</div>
	<div id="saleWasteWxDetailRebackWindow" class="easyui-window" title="废品销售未用退回" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/saleDetail/M5005w-reback'" style="width:780px;height:230px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM5005w(){
		var queryParams = $('#saleWasteWxDetailList').datagrid('options').queryParams;
		queryParams.salePlanId = $('#salePlanIdM5005w').val();
		queryParams.saleSerno = $('#saleSernoM5005w').val();
		queryParams.prodId = $('#prodIdM5005w').val();
		queryParams.saleStatus = $('#saleStatusM5005w').combobox("getValue");
		queryParams.startDate = $('#startDateM5005w').datebox("getValue");
		queryParams.endDate = $('#startDateM5005w').datebox("getValue");
		
	}

	//排序查询
	function sortSerachM5005w(sort,order){
		$("#sortM5005w").textbox('setValue',sort);
		$("#orderM5005w").textbox('setValue',order);
		searchM5005wForm();
	}

	//查询按钮
	function searchM5005wForm(){
		//page页码
		var page = $("#saleWasteWxDetailList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#saleWasteWxDetailList").datagrid('options').pageSize; 
		$("#pageM5005w").textbox('setValue',page);
		$("#rowsM5005w").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#saleDetailSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/saleWasteWx/listByCriteria",$("#saleWasteWxDetailSearchForm").serialize(), function(data){
			if(data.status == 200){
	           	$('#saleWasteWxDetailList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM5005w").textbox('setValue',data.className);
				$("#methodNameM5005w").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM5005w();
	}
	
	/* 重置表单 */
	function clearM5005wForm(){
		$('#saleWasteWxDetailSearchForm').form('reset');
	}

    function getM5005wSelectionsIds(){
    	var saleDetailList = $("#saleWasteWxDetailList");
    	var sels = saleDetailList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].saleSerno);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM5005w = [{
        text:'废品出库',
        iconCls:'icon-add',
        handler:function(){
        	var ids = getM5005wSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录编辑!');
        		return ;
        	}
        	$("#saleWasteWxDetailOutWindow").window({
        		onLoad :function(){
        			var data = $("#saleWasteWxDetailList").datagrid("getSelections")[0];
        			$("#saleWasteWxDetailOutForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'未用退回',
        iconCls:'icon-reload',
        handler:function(){
        	var ids = getM5005wSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录编辑!');
        		return ;
        	}
        	$("#saleWasteWxDetailRebackWindow").window({
        		onLoad :function(){
        			var data = $("#saleWasteWxDetailList").datagrid("getSelections")[0];
        			$("#saleWasteWxDetailRebackForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#saleWasteWxDetailList").datagrid("getData").className;
			var methodName = $("#saleWasteWxDetailList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM5005w').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM5005w').val();
			}
			//获取表头信息
			var header = $("#saleWasteWxDetailList").datagrid("options").columns[0];
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
			mapParam.set("excelName","废品销售出库信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("salePlanId",$('#salePlanIdM5005w').val());
			mapParam.set("saleSerno",$('#saleSernoM5005w').val());
			mapParam.set("prodId",$('#prodIdM5005w').combobox("getValue"));
			mapParam.set("saleStatus",$('#saleStatusM5005w').combobox("getValue"));
			mapParam.set("startDate",$('#startDateM5005w').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM5005w').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>