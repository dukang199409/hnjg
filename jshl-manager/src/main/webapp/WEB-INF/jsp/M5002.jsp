<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="销售出库通知单维护信息查询" style="padding:10px 10px 10px 10px">
		<form id="saleDetailSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售通知单号:</td>
		            <td width="210" align="right">
		            	<input name="salePlanId" id="salePlanIdM5002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>  
		            <td width="120" align="right">销售计划流水:</td>
		            <td width="210" align="right">
		            	<input name="saleSerno" id="saleSernoM5002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		       <tr>
		            <td width="120" align="right">销售计划类别:</td>
		            <td width="210" align="right">
		            	<select name="saleFlag" id="saleFlagM5002"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				         <c:forEach items="${saleFlagList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">销售计划状态:</td>
		            <td width="210" align="right">
		            	<select name="saleStatus" id="saleStatusM5002"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				         <c:forEach items="${saleStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM5002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">计划出库钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM5002" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
						</select>
		            </td>
		        </tr>
		         <tr>
		       		<td width="120" align="right">计划出库规格:</td>
		             <td width="210" align="right">
		            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
		            </td>   
		            <td width="120" align="right">计划出库定尺:</td>
	           		 <td width="210" align="right">
	            		  <input name="prodLength" id="prodLengthM5002" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            	 </td>
		        </tr>
		       <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM5002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM5002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM5002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM5002" class="easyui-textbox" type="text" data-options="required:false"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM5002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM5002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM5002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM5002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM5002Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5002Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="saleDetailList5002" title="销售出库通知单维护信息详情"  style="height:570px"
	       data-options="singleSelect:true,rownumbers:true,fitColumns:true,remoteSort:false,onSortColumn:sortSerachM5002,collapsible:true,pagination:true,url:'/saleDetail/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM5002,pageSize:30,toolbar:toolbarM5002">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'salePlanId',width:110,sortable:true">销售通知单号</th>
	        	<th data-options="field:'saleSerno',width:120,sortable:true">销售计划流水</th>
	            <th data-options="field:'saleFlag',width:130,formatter:BSL.formatSaleType,sortable:true">出库标准</th>
	            <th data-options="field:'saleNum',width:100,sortable:true">计划出库数量</th>
	            <th data-options="field:'saleWeight',width:100,sortable:true">计划出库重量/吨</th>
	            <th data-options="field:'prodId',width:150,formatter:BSL.formatWasteType,sortable:true">计划出库产品编号</th>
	            <th data-options="field:'prodMaterial',width:100,formatter:BSL.formatProdMaterial,sortable:true">计划出库钢种</th>
	            <th data-options="field:'prodNorm',width:100,sortable:true">计划出库规格</th>
	            <th data-options="field:'prodLength',width:100,sortable:true">计划出库定尺</th>
	            <th data-options="field:'prodSumnum',width:100,sortable:true">累计出库数量</th>
	            <th data-options="field:'prodSumweight',width:120,sortable:true">累计出库重量/吨</th>
	            <th data-options="field:'salePrice',width:70,sortable:true">单价/元</th>
	            <th data-options="field:'saleStatus',width:120,formatter:BSL.formatSaleStatus,sortable:true">产品出库状态</th>
	            <th data-options="field:'inputuser',width:70,sortable:true">制单人</th>
	            <th data-options="field:'crtDate',width:90,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'updDate',width:90,formatter:BSL.formatDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:60,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="saleDetailAddWindow" class="easyui-window" title="新增销售出库详细信息" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/saleDetail/M5002-add'" style="width:780px;height:280px;padding:10px;">
	</div>
	<div id="saleDetailAddByIdWindow" class="easyui-window" title="新增按产品销售出库详细信息" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/saleDetail/M5002-addById'" style="width:1080px;height:540px;padding:10px;">
	</div>
	<div id="saleDetailEditWindow" class="easyui-window" title="编辑销售出库详细信息" data-options="modal:true,closed:true,iconCls:'saleDetail-edit',href:'/saleDetail/M5002-edit'" style="width:780px;height:280px;padding:10px;">
	</div>
	<div id="saleDetailEditByIdWindow" class="easyui-window" title="编辑按产品销售出库详细信息" data-options="modal:true,closed:true,iconCls:'saleDetail-edit',href:'/saleDetail/M5002-editById'" style="width:780px;height:260px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM5002(){
		var queryParams = $('#saleDetailList5002').datagrid('options').queryParams;
		queryParams.salePlanId = $('#salePlanIdM5002').val();
		queryParams.saleSerno = $('#saleSernoM5002').val();
		queryParams.prodId = $('#prodIdM5002').val();
		queryParams.saleFlag = $('#saleFlagM5002').combobox("getValue");
		queryParams.saleStatus = $('#saleStatusM5002').combobox("getValue");
		queryParams.prodMaterial = $('#prodMaterialM5002').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM5002').val();
		queryParams.prodLength = $('#prodLengthM5002').val();
		queryParams.startDate = $('#startDateM5002').datebox("getValue");
		queryParams.endDate = $('#endDateM5002').datebox("getValue");
	}

	//排序查询
	function sortSerachM5002(sort,order){
		$("#sortM5002").textbox('setValue',sort);
		$("#orderM5002").textbox('setValue',order);
		searchM5002Form();
	}

	//查询按钮
	function searchM5002Form(){
		//page页码
		var page = $("#saleDetailList5002").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#saleDetailList5002").datagrid('options').pageSize; 
		$("#pageM5002").textbox('setValue',page);
		$("#rowsM5002").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#saleDetailSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/saleDetail/listByCriteria",$("#saleDetailSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#saleDetailList5002').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM5002").textbox('setValue',data.className);
				$("#methodNameM5002").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM5002();
	}
	
	/* 重置表单 */
	function clearM5002Form(){
		$('#saleDetailSearchForm').form('reset');
	}
    
    var toolbarM5002 = [{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#saleDetailList5002").datagrid("getData").className;
			var methodName = $("#saleDetailList5002").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM5002').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM5002').val();
			}
			//获取表头信息
			var header = $("#saleDetailList5002").datagrid("options").columns[0];
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
			mapParam.set("excelName","销售出库通知单维护信息查询");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("salePlanId",$('#salePlanIdM5002').val());
			mapParam.set("saleSerno",$('#saleSernoM5002').val());
			mapParam.set("prodId",$('#prodIdM5002').val());
			mapParam.set("saleFlag",$('#saleFlagM5002').combobox("getValue"));
			mapParam.set("saleStatus",$('#saleStatusM5002').combobox("getValue"));
			mapParam.set("prodMaterial",$('#prodMaterialM5002').combobox("getValue"));
			mapParam.set("prodNorm",$('#prodNormM5002').val());
			mapParam.set("prodLength",$('#prodLengthM5002').val());
			mapParam.set("startDate",$('#startDateM5002').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM5002').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>