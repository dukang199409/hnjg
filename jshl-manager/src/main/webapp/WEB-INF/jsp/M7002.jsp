<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="库存变动信息查询" style="padding:10px 10px 10px 10px">
		<form id="changeInfoSerachForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">变动流水号:</td>
		            <td width="210" align="right">
		            	<input name="transSerno" id="transSernoM7002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM7002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		         <tr>
		       		<td width="120" align="right">单号/指令号:</td>
		            <td width="210" align="right">
		            	<input name="planSerno" id="planSernoM7002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        	<td width="120" align="right">产品类型:</td>
		            <td width="210" align="right">
		            	<select name="prodType" id="prodTypeM7002" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		       <tr>
		            <td width="120" align="right">交易代码:</td>
		            <td width="210" align="right">
		            	<select name="transCode" id="transCodeM7002"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${transCodeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		       		<td width="120" align="right">废品类型:</td>
		            <td width="210" align="right">
		            	<select name="rubbishType" id="rubbishTypeM7002" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${wasteTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr>
		       		<td width="120" align="right">原产品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodOriId" id="prodOriIdM7002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM7002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM7002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM7002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM7002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM7002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM7002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM7002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM7002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM7002Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM7002Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="changeInfoList" title="库存变动信息"  style="height:590px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM7002,collapsible:true,pagination:true,url:'/changeInfo/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM7002,pageSize:30,toolbar:toolbarM7002">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'transSerno',width:140,sortable:true">变动流水号</th>
	        	<th data-options="field:'prodId',width:180,sortable:true,formatter:BSL.formatWasteType">产品编号</th>
	        	<th data-options="field:'prodType',width:100,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'planSerno',width:140,sortable:true">单号/指令号</th>
	        	<th data-options="field:'transCode',width:100,formatter:BSL.formatTransCode,sortable:true">交易代码</th>
	        	<th data-options="field:'rubbishType',width:120,sortable:true,formatter:BSL.formatWasteType">废品类型</th>
	        	<th data-options="field:'prodOriId',width:140,sortable:true">原产品编号</th>
	        	<th data-options="field:'rubbishWeight',width:120,sortable:true">变动重量/吨</th>
	        	<th data-options="field:'price',width:120,sortable:true">价格</th>
	        	<th data-options="field:'targetPlace',width:120,sortable:true">去向</th>
	            <th data-options="field:'inputuser',width:70,sortable:true">录入人</th>
	            <th data-options="field:'crtDate',width:100,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM7002(){
		var queryParams = $('#changeInfoList').datagrid('options').queryParams;
		queryParams.transSerno = $('#transSernoM7002').val();
		queryParams.prodId = $('#prodIdM7002').val();
		queryParams.planSerno = $('#planSernoM7002').val();
		queryParams.prodType = $('#prodTypeM7002').combobox("getValue");
		queryParams.transCode = $('#transCodeM7002').combobox("getValue");
		queryParams.rubbishType = $('#rubbishTypeM7002').combobox("getValue");
		queryParams.prodOriId = $('#prodOriIdM7002').val();
		queryParams.startDate = $('#startDateM7002').datebox("getValue");
		queryParams.endDate = $('#endDateM7002').datebox("getValue");
	}

	//排序查询
	function sortSerachM7002(sort,order){
		$("#sortM7002").textbox('setValue',sort);
		$("#orderM7002").textbox('setValue',order);
		searchM7002Form();
	}

	//查询按钮
	function searchM7002Form(){
		//page页码
		var page = $("#changeInfoList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#changeInfoList").datagrid('options').pageSize; 
		$("#pageM7002").textbox('setValue',page);
		$("#rowsM7002").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/changeInfo/listByCriteria",$("#changeInfoSerachForm").serialize(), function(data){
			if(data.status == 200){				
			    $('#changeInfoList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM7002").textbox('setValue',data.className);
				$("#methodNameM7002").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM7002();
	}
	
	/* 重置表单 */
	function clearM7002Form(){
		$('#changeInfoSerachForm').form('reset');
	}
	
	 var toolbarM7002 = [{
	    	text : '导出EXCEL',
	        iconCls : 'icon-save',
	        handler : function() {
	        	//获取后台传递参数className methodName
				var className = $("#changeInfoList").datagrid("getData").className;
				var methodName = $("#changeInfoList").datagrid("getData").methodName;
				/**
				 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
				 * 主要是查询时，datagrid属性className、methodName会丢失
				 */
				if(className == "" || className==null || className == undefined){
					className = $('#classNameM7002').val();
				}
				if(methodName == "" || methodName==null || methodName == undefined){
					methodName = $('#methodNameM7002').val();
				}
				//获取表头信息
				var header = $("#changeInfoList").datagrid("options").columns[0];
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
				mapParam.set("excelName","库存变动信息");
				mapParam.set("loginUserId",$("#user_id").html());
				mapParam.set("rows","5000");
				mapParam.set("page","1");
				//查询条件 把所有查询条件带上
				mapParam.set("transSerno",$('#transSernoM7002').val());
				mapParam.set("prodId",$('#prodIdM7002').val());
				mapParam.set("planSerno",$('#planSernoM7002').val());
				mapParam.set("prodType",$('#prodTypeM7002').combobox("getValue"));
				mapParam.set("transCode",$('#transCodeM7002').combobox("getValue"));
				mapParam.set("rubbishType",$('#rubbishTypeM7002').combobox("getValue"));
				mapParam.set("prodOriId",$('#prodOriIdM7002').val());
				mapParam.set("startDate",$('#startDateM7002').datebox("getValue"));
				mapParam.set("endDate",$('#endDateM7002').datebox("getValue"));
				
				BSL.toExcel(mapParam);
	        }
	    }];
    
</script>