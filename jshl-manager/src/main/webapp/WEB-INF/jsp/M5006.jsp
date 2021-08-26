<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<div class="easyui-panel" title="销售出库产品信息查询" style="padding:10px 10px 10px 10px">
		<form id="saleProdSearchByPlanForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售出库单号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutPlan" id="prodOutPlanM5006" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">销售计划流水:</td>
		            <td width="210" align="right">
		            	<input name="prodSaleSerno" id="prodSaleSernoM5006" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM5006" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM5006" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM5006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM5006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM5006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM5006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM5006Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5006Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="saleProdByPlanList" title="销售出库详单信息管理"  style="height:600px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM5006,collapsible:true,pagination:true,url:'/saleProdByPlan/outProds',method:'post',onBeforeLoad:onBeforeLoadM5006,pageSize:30,toolbar:toolbarM5006">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:180,sortable:true">产品编号</th>
	            <th data-options="field:'prodOutPlan',width:120,sortable:true">销售出库单号</th>
	            <th data-options="field:'prodSaleSerno',width:120,sortable:true">销售计划流水</th>
	        	<th data-options="field:'prodType',width:100,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'prodPlanNo',width:140,sortable:true">单号/指令号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">产品名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:100,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
				<th data-options="field:'prodOutWeight',width:125,sortable:true">销售复磅重量/吨</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	        	<th data-options="field:'prodCompany',width:80,sortable:true">厂家</th>
	            <th data-options="field:'prodLevel',width:90,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'crtDate',width:100,formatter:BSL.formatDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'updDate',width:150,formatter:BSL.formatDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM5006(){
		var queryParams = $('#saleProdByPlanList').datagrid('options').queryParams;
		queryParams.prodOutPlan = $('#prodOutPlanM5006').val();
		queryParams.prodSaleSerno = $('#prodSaleSernoM5006').val();
	}

	//排序查询
	function sortSerachM5006(sort,order){
		$("#sortM5006").textbox('setValue',sort);
		$("#orderM5006").textbox('setValue',order);
		searchM5006Form();
	}

	//查询按钮
	function searchM5006Form(){
		//page页码
		var page = $("#saleProdByPlanList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#saleProdByPlanList").datagrid('options').pageSize; 
		$("#pageM5006").textbox('setValue',page);
		$("#rowsM5006").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#saleProdSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/saleProdByPlan/outProds",$("#saleProdSearchByPlanForm").serialize(), function(data){
			if(data.status == 200){
	            $('#saleProdByPlanList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM5006").textbox('setValue',data.className);
				$("#methodNameM5006").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM5006();
	}
	
	/* 重置表单 */
	function clearM5006Form(){
		$('#saleProdSearchByPlanForm').form('reset');
	}
	
	 var toolbarM5006 = [{
	    	text : '导出EXCEL',
	        iconCls : 'icon-save',
	        handler : function() {
	        	//获取后台传递参数className methodName
				var className = $("#saleProdByPlanList").datagrid("getData").className;
				var methodName = $("#saleProdByPlanList").datagrid("getData").methodName;
				/**
				 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
				 * 主要是查询时，datagrid属性className、methodName会丢失
				 */
				if(className == "" || className==null || className == undefined){
					className = $('#classNameM5006').val();
				}
				if(methodName == "" || methodName==null || methodName == undefined){
					methodName = $('#methodNameM5006').val();
				}
				//获取表头信息
				var header = $("#saleProdByPlanList").datagrid("options").columns[0];
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
				mapParam.set("excelName","销售出库产品信息查询");
				mapParam.set("loginUserId",$("#user_id").html());
				mapParam.set("rows","10000");
				mapParam.set("page","1");
				//查询条件 把所有查询条件带上
				mapParam.set("prodOutPlan",$('#prodOutPlanM5006').val());
				mapParam.set("prodSaleSerno",$('#prodSaleSernoM5006').val());
				
				BSL.toExcel(mapParam);
	        }
	    }];
 

</script>