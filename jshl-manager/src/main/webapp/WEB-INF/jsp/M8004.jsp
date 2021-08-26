<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<div class="easyui-panel" title="单炉库存重量统计报表查询" style="padding:10px 10px 10px 10px">
		<form id="luInfoForm" class="itemForm" method="post">
		   <table>
		        <tr>
		            <td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="prodLuno" id="prodLunoM8004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		        </tr>
		         <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM8004" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM8004" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM8004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM8004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM8004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM8004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM8004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM8004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM8004Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM8004Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="luInfoList" title="单炉库存重量统计报表"  style="height:590px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM8004,collapsible:true,pagination:true,url:'/report/listByCriteriaM8004',method:'post',onBeforeLoad:onBeforeLoadM8004,pageSize:30,toolbar:toolbarM8004">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'transDate',width:100,formatter:BSL.formatDateTime,sortable:true">统计日期</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'rawAllNum',width:120,sortable:true">入库卷板总数/卷</th>
	        	<th data-options="field:'rawAllWeight',width:120,sortable:true">入库卷板总重/吨</th>
	        	<th data-options="field:'rawNum',width:100,sortable:true">库存卷板数量/卷</th>
	            <th data-options="field:'rawWeight',width:100,sortable:true">库存卷板重量/吨</th>
	            <th data-options="field:'saleRawNum',width:100,sortable:true">已售卷板数量/卷</th>
	            <th data-options="field:'saleRawWeight',width:100,sortable:true">已售卷板重量/吨</th>
	        	<th data-options="field:'halfAllNum',width:120,sortable:true">入库半成品总数/条</th>
	        	<th data-options="field:'halfAllWeight',width:120,sortable:true">入库半成品总重/吨</th>
	        	<th data-options="field:'halfNum',width:100,sortable:true">库存半成品数量/条</th>
	            <th data-options="field:'halfWeight',width:100,sortable:true">库存半成品重量/吨</th>
	        	<th data-options="field:'saleHalfNum',width:100,sortable:true">已售半成品数量/条</th>
	            <th data-options="field:'saleHalfWeight',width:100,sortable:true">已售半成品重量/吨</th>
	        	<th data-options="field:'prodAllNum',width:120,sortable:true">入库成品总数/包</th>
	        	<th data-options="field:'prodAllWeight',width:120,sortable:true">入库成品总重/吨</th>
	        	<th data-options="field:'prodNum',width:100,sortable:true">库存成品包数/包</th>
	            <th data-options="field:'prodWeight',width:100,sortable:true">库存成品重量/吨</th>
	        	<th data-options="field:'saleProdNum',width:100,sortable:true">已售成品包数/包</th>
	            <th data-options="field:'saleProdWeight',width:100,sortable:true">已售成品重量/吨</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM8004(){
		var queryParams = $('#luInfoList').datagrid('options').queryParams;
		queryParams.prodLuno = $('#prodLunoM8004').val();
		queryParams.startDate = $('#startDateM8004').datebox("getValue");
		queryParams.endDate = $('#endDateM8004').datebox("getValue");
	}

	//排序查询
	function sortSerachM8004(sort,order){
		$("#sortM8004").textbox('setValue',sort);
		$("#orderM8004").textbox('setValue',order);
		searchM8004Form();
	}

	//查询按钮
	function searchM8004Form(){
		//page页码
		var page = $("#luInfoList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#luInfoList").datagrid('options').pageSize; 
		$("#pageM8004").textbox('setValue',page);
		$("#rowsM8004").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/report/listByCriteriaM8004",$("#luInfoForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#luInfoList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM8004").textbox('setValue',data.className);
				$("#methodNameM8004").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM8004();
	}
	
	/* 重置表单 */
	function clearM8004Form(){
		$('#luInfoForm').form('reset');
	}
	
	 var toolbarM8004 = [{
	    	text : '导出EXCEL',
	        iconCls : 'icon-save',
	        handler : function() {
	        	//获取后台传递参数className methodName
				var className = $("#luInfoList").datagrid("getData").className;
				var methodName = $("#luInfoList").datagrid("getData").methodName;
				/**
				 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
				 * 主要是查询时，datagrid属性className、methodName会丢失
				 */
				if(className == "" || className==null || className == undefined){
					className = $('#classNameM8004').val();
				}
				if(methodName == "" || methodName==null || methodName == undefined){
					methodName = $('#methodNameM8004').val();
				}
				//获取表头信息
				var header = $("#luInfoList").datagrid("options").columns[0];
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
				mapParam.set("excelName","单炉库存重量统计报表");
				mapParam.set("loginUserId",$("#user_id").html());
				mapParam.set("rows","5000");
				mapParam.set("page","1");
				//查询条件 把所有查询条件带上
				mapParam.set("prodLuno",$('#prodLunoM8004').val());
				mapParam.set("startDate",$('#startDateM8004').datebox("getValue"));
				mapParam.set("endDate",$('#endDateM8004').datebox("getValue"));
				BSL.toExcel(mapParam);
	        }
	    }];
    
</script>