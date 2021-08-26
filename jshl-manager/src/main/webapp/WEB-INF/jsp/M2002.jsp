<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="纵剪带生产调度单维护信息查询" style="padding:10px 10px 10px 10px">
		<form id="planDetailSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">调度计划编号:</td>
		            <td width="210" align="right">
		            	<input name="planInfoDetailId" id="planInfoDetailIdM2002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td> 
		        	<td width="120" align="right">纵剪带生产指令号:</td>
		            <td width="210" align="right">
		            	<input name="planId" id="planIdM2002" class="easyui-textbox" data-options="required:false,validType:'length[0,20]'"  style="width:200px;">
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">制造规格: </td>
		            <td width="210" align="right">
		            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
		            </td>
		        </tr>
		         <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM2002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM2002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM2002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM2002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
	            	<td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM2002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM2002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM2002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM2002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>		        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchFormM2002()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearFormM2002()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="planDetailList2002" title="纵剪带生产调度单维护信息管理"  style="height:620px"
	       data-options="singleSelect:true,rownumbers:true,fitColumns:true,remoteSort:false,onSortColumn:sortSerachM2002,collapsible:true,pagination:true,url:'/planDetail/query',method:'post',onBeforeLoad:onBeforeLoadM2002,pageSize:30,toolbar:toolbarM2002">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'planInfoDetailId',sortable:true,width:130">调度计划编号</th>
	        	<th data-options="field:'planId',width:100,sortable:true">纵剪带生产指令号</th>
	            <th data-options="field:'prodNorm',width:100,sortable:true">制造规格</th>
	            <th data-options="field:'prodLevel',width:80,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodWeight',width:60,sortable:true">用料重量/吨</th>
	            <th data-options="field:'prodNum',width:60,sortable:true">条数</th>
	            <th data-options="field:'planOutputVolume',width:80,sortable:true">计划产出量/吨</th>
	            <th data-options="field:'planFinistDate',width:80,formatter:BSL.formatDateTime,sortable:true">计划完工日期</th>
	            <th data-options="field:'collectedUnits',width:80,formatter:BSL.formatMakeDept,sortable:true">实收机组</th>
	            <th data-options="field:'prodInputuser',width:100,sortable:true">产品录入人</th>
	            <th data-options="field:'crtDate',formatter:BSL.formatDateTime,sortable:true,width:100">创建日期</th>
	            <th data-options="field:'remark',width:300,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM2002(){
		var queryParams = $('#planDetailList2002').datagrid('options').queryParams;
		
		queryParams.planInfoDetailId = $('#planInfoDetailIdM2002').val();
		queryParams.planId = $('#planIdM2002').val();
		queryParams.prodNorm = $('#prodNormM2002').val();
		queryParams.startDate = $('#startDateM2002').datebox("getValue");
		queryParams.endDate = $('#endDateM2002').datebox("getValue");
	}

	//排序查询
	function sortSerachM2002(sort,order){
		$("#sortM2002").textbox('setValue',sort);
		$("#orderM2002").textbox('setValue',order);
		searchForm();
	}

	//查询按钮
	function searchFormM2002(){
		
		//page页码
		var page = $("#planDetailList2002").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#planDetailList2002").datagrid('options').pageSize; 
		$("#pageM2002").textbox('setValue',page);
		$("#rowsM2002").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/planDetail/query",$("#planDetailSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#planDetailList2002').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM2002").textbox('setValue',data.className);
				$("#methodNameM2002").textbox('setValue',data.methodName);
			} else {
				$.messager.alert('提示', data.msg);
			}
		});
		
		onBeforeLoadM2002();
	}
	
	/* 重置表单 */
	function clearFormM2002(){
		$('#planDetailSearchForm').form('reset');
	}
	
    var toolbarM2002 = [{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#planDetailList2002").datagrid("getData").className;
			var methodName = $("#planDetailList2002").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM2002').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM2002').val();
			}
			//获取表头信息
			var header = $("#planDetailList2002").datagrid("options").columns[0];
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
			mapParam.set("url","/export/exportExcel");
			mapParam.set("fields",fields);
			mapParam.set("titles",titles);
			mapParam.set("className",className);
			mapParam.set("methodName",methodName);
			mapParam.set("excelName","纵剪带生产调度单维护信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("planInfoDetailId",$('#planInfoDetailIdM2002').val());
			mapParam.set("planId",$('#planIdM2002').val());
			mapParam.set("prodNorm",$('#prodNormM2002').val());
			mapParam.set("startDate",$('#startDateM2002').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM2002').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>