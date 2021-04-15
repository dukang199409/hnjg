<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<div class="easyui-panel" title="库存日照报表查询" style="padding:10px 10px 10px 10px">
		<form id="stockPhotoSerachForm" class="itemForm" method="post">
		   <table>
		        <tr>
		            <td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM8001" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		         <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM8001" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM8001" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM8001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM8001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM8001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM8001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM8001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM8001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM8001Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM8001Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="stockPhotoList" title="库存日照报表信息"  style="height:590px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM8001,fitColumns:true,collapsible:true,pagination:true,url:'/report/listByCriteriaM8001',method:'post',onBeforeLoad:onBeforeLoadM8001,pageSize:30,toolbar:toolbarM8001">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'transDate',width:100,formatter:BSL.formatDateTime,sortable:true">统计日期</th>
	        	<th data-options="field:'prodId',width:180,sortable:true">产品编号</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	        	<th data-options="field:'prodType',width:80,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'prodName',width:70,sortable:true">产品名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:70,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
	        	<th data-options="field:'prodUserType',width:90,formatter:BSL.formatMakeType,sortable:true">纵剪带用途</th>
	        	<th data-options="field:'prodLength',width:60,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodNum',width:60,sortable:true">数量</th>
	            <th data-options="field:'prodInputuser',width:70,sortable:true">录入人</th>
	            <th data-options="field:'crtDate',width:100,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM8001(){
		var queryParams = $('#stockPhotoList').datagrid('options').queryParams;
		queryParams.prodId = $('#prodIdM8001').val();
		queryParams.startDate = $('#startDateM8001').datebox("getValue");
		queryParams.endDate = $('#endDateM8001').datebox("getValue");
	}

	//排序查询
	function sortSerachM8001(sort,order){
		$("#sortM8001").textbox('setValue',sort);
		$("#orderM8001").textbox('setValue',order);
		searchM8001Form();
	}

	//查询按钮
	function searchM8001Form(){
		//page页码
		var page = $("#stockPhotoList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#stockPhotoList").datagrid('options').pageSize; 
		$("#pageM8001").textbox('setValue',page);
		$("#rowsM8001").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/report/listByCriteriaM8001",$("#stockPhotoSerachForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#stockPhotoList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM8001").textbox('setValue',data.className);
				$("#methodNameM8001").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM8001();
	}
	
	/* 重置表单 */
	function clearM8001Form(){
		$('#stockPhotoSerachForm').form('reset');
	}
	
	 var toolbarM8001 = [{
	    	text : '导出EXCEL',
	        iconCls : 'icon-save',
	        handler : function() {
	        	//获取后台传递参数className methodName
				var className = $("#stockPhotoList").datagrid("getData").className;
				var methodName = $("#stockPhotoList").datagrid("getData").methodName;
				/**
				 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
				 * 主要是查询时，datagrid属性className、methodName会丢失
				 */
				if(className == "" || className==null || className == undefined){
					className = $('#classNameM8001').val();
				}
				if(methodName == "" || methodName==null || methodName == undefined){
					methodName = $('#methodNameM8001').val();
				}
				//获取表头信息
				var header = $("#stockPhotoList").datagrid("options").columns[0];
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
				mapParam.set("excelName","库存日照报表");
				mapParam.set("loginUserId",$("#user_id").html());
				mapParam.set("rows","5000");
				mapParam.set("page","1");
				//查询条件 把所有查询条件带上
				mapParam.set("prodId",$('#prodIdM8001').val());
				mapParam.set("startDate",$('#startDateM8001').datebox("getValue"));
				mapParam.set("endDate",$('#endDateM8001').datebox("getValue"));
				BSL.toExcel(mapParam);
	        }
	    }];
    
</script>