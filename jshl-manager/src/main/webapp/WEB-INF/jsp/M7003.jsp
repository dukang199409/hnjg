<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="状态维护信息查询" style="padding:10px 10px 10px 10px">
		<form id="changeStatusInfoSerachForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">变动流水号:</td>
		            <td width="210" align="right">
		            	<input name="changeSerno" id="changeSernoM7003" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="changeProdId" id="changeProdIdM7003" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		         <tr>
		        	<td width="120" align="right">维护类型:</td>
		            <td width="210" align="right">
		            	<select name="changeType" id="changeTypeM7003" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				         <c:forEach items="${changeTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM7003" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM7003" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM7003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM7003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM7003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM7003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM7003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM7003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM7003Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM7003Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="changeStatusInfoList" title="状态维护信息"  style="height:590px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM7003,fitColumns:true,collapsible:true,pagination:true,url:'/changeInfo/listByCriteriaM7003',method:'post',onBeforeLoad:onBeforeLoadM7003,pageSize:30,toolbar:toolbarM7003">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'changeSerno',width:140,sortable:true">变动流水号</th>
	        	<th data-options="field:'changeProdId',width:140,sortable:true,formatter:BSL.formatWasteType">产品编号</th>
	        	<th data-options="field:'changeType',width:140,formatter:BSL.formatChangeType,sortable:true">维护类别</th>
	        	<th data-options="field:'beforeStatus',width:140,formatter:BSL.formatProdStatus,sortable:true">维护前状态/重量</th>
	        	<th data-options="field:'afterStatus',width:140,formatter:BSL.formatProdStatus,sortable:true">维护后状态/重量</th>
	            <th data-options="field:'inputuser',width:140,sortable:true">录入人</th>
	            <th data-options="field:'crtDate',width:140,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'remark',width:140,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM7003(){
		var queryParams = $('#changeStatusInfoList').datagrid('options').queryParams;
		queryParams.changeSerno = $('#changeSernoM7003').val();
		queryParams.changeProdId = $('#changeProdIdM7003').val();
		queryParams.changeType = $('#changeTypeM7003').combobox("getValue");
		queryParams.startDate = $('#startDateM7003').datebox("getValue");
		queryParams.endDate = $('#endDateM7003').datebox("getValue");
	}

	//排序查询
	function sortSerachM7003(sort,order){
		$("#sortM7003").textbox('setValue',sort);
		$("#orderM7003").textbox('setValue',order);
		searchM7003Form();
	}

	//查询按钮
	function searchM7003Form(){
		//page页码
		var page = $("#changeStatusInfoList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#changeStatusInfoList").datagrid('options').pageSize; 
		$("#pageM7003").textbox('setValue',page);
		$("#rowsM7003").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/changeInfo/listByCriteriaM7003",$("#changeStatusInfoSerachForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#changeStatusInfoList').datagrid('loadData',{"total":data.total,"rows":data.rows});
	            $("#classNameM7003").textbox('setValue',data.className);
				$("#methodNameM7003").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM7003();
	}
	
	/* 重置表单 */
	function clearM7003Form(){
		$('#changeStatusInfoSerachForm').form('reset');
	}
	
	 var toolbarM7003 = [{
	    	text : '导出EXCEL',
	        iconCls : 'icon-save',
	        handler : function() {
	        	//获取后台传递参数className methodName
				var className = $("#changeStatusInfoList").datagrid("getData").className;
				var methodName = $("#changeStatusInfoList").datagrid("getData").methodName;
				/**
				 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
				 * 主要是查询时，datagrid属性className、methodName会丢失
				 */
				if(className == "" || className==null || className == undefined){
					className = $('#classNameM7003').val();
				}
				if(methodName == "" || methodName==null || methodName == undefined){
					methodName = $('#methodNameM7003').val();
				}
				//获取表头信息
				var header = $("#changeStatusInfoList").datagrid("options").columns[0];
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
				mapParam.set("excelName","状态维护信息");
				mapParam.set("loginUserId",$("#user_id").html());
				mapParam.set("rows","5000");
				mapParam.set("page","1");
				//查询条件 把所有查询条件带上
				mapParam.set("changeSerno",$('#changeSernoM7003').val());
				mapParam.set("changeProdId",$('#changeProdIdM7003').val());
				mapParam.set("changeType",$('#changeTypeM7003').combobox("getValue"));
				mapParam.set("startDate",$('#startDateM7003').datebox("getValue"));
				mapParam.set("endDate",$('#endDateM7003').datebox("getValue"));
				BSL.toExcel(mapParam);
	        }
	    }];
    
</script>