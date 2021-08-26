<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="累计库存变动统计报表查询" style="padding:10px 10px 10px 10px">
		<form id="stockChangeSumSerachForm" class="itemForm" method="post">
		   <table>
		        <tr>
		           <td width="120" align="right">产品类型:</td>
		            <td width="210" align="right">
		            	<select name="prodType" id="prodTypeM8003" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">交易代码:</td>
		            <td width="210" align="right">
		            	<select name="transCode" id="transCodeM8003"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${transCodeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		         <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM8003" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM8003" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM8003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM8003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM8003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM8003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM8003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM8003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM8003Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM8003Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="stockChangeSumList" title="累计库存变动统计报表"  style="height:590px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM8003,fitColumns:true,collapsible:true,pagination:true,url:'/report/listByCriteriaM8003',method:'post',onBeforeLoad:onBeforeLoadM8003,pageSize:30,toolbar:toolbarM8003">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'prodType',width:100,formatter:BSL.formatProdType,sortable:true">产品类别</th>
				<th data-options="field:'transCode',width:100,formatter:BSL.formatTransCode,sortable:true">交易代码</th>
				<th data-options="field:'sumWeight',width:100,sortable:true">变动重量/吨</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM8003(){
		var queryParams = $('#stockChangeSumList').datagrid('options').queryParams;
		queryParams.prodType = $('#prodTypeM8003').combobox("getValue");
		queryParams.transCode = $('#transCodeM8003').combobox("getValue");
		queryParams.startDate = $('#startDateM8003').datebox("getValue");
		queryParams.endDate = $('#endDateM8003').datebox("getValue");
	}

	//排序查询
	function sortSerachM8003(sort,order){
		$("#sortM8003").textbox('setValue',sort);
		$("#orderM8003").textbox('setValue',order);
		searchM8003Form();
	}

	//查询按钮
	function searchM8003Form(){
		//page页码
		var page = $("#stockChangeSumList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#stockChangeSumList").datagrid('options').pageSize; 
		$("#pageM8003").textbox('setValue',page);
		$("#rowsM8003").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/report/listByCriteriaM8003",$("#stockChangeSumSerachForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#stockChangeSumList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM8003").textbox('setValue',data.className);
				$("#methodNameM8003").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM8003();
	}
	
	/* 重置表单 */
	function clearM8003Form(){
		$('#stockChangeSumSerachForm').form('reset');
	}
	
	 var toolbarM8003 = [{
	    	text : '导出EXCEL',
	        iconCls : 'icon-save',
	        handler : function() {
	        	//获取后台传递参数className methodName
				var className = $("#stockChangeSumList").datagrid("getData").className;
				var methodName = $("#stockChangeSumList").datagrid("getData").methodName;
				/**
				 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
				 * 主要是查询时，datagrid属性className、methodName会丢失
				 */
				if(className == "" || className==null || className == undefined){
					className = $('#classNameM8003').val();
				}
				if(methodName == "" || methodName==null || methodName == undefined){
					methodName = $('#methodNameM8003').val();
				}
				//获取表头信息
				var header = $("#stockChangeSumList").datagrid("options").columns[0];
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
				mapParam.set("excelName","累计库存变动统计报表");
				mapParam.set("loginUserId",$("#user_id").html());
				mapParam.set("rows","5000");
				mapParam.set("page","1");
				//查询条件 把所有查询条件带上
				mapParam.set("prodType",$('#prodTypeM8003').combobox("getValue"));
				mapParam.set("transCode",$('#transCodeM8003').combobox("getValue"));
				mapParam.set("startDate",$('#startDateM8003').datebox("getValue"));
				mapParam.set("endDate",$('#endDateM8003').datebox("getValue"));
				BSL.toExcel(mapParam);
	        }
	    }];
    
</script>