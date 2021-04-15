<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="外协厂出库汇总信息查询" style="padding:10px 10px 10px 10px">
		<form id="wxSaleGroupM3107Form" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售客户:</td>
		            <td width="210" align="right">
		            	<input name="bsCustomer" id="bsCustomerM3107" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,100]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">订单号:</td>
		            <td width="210" align="right">
		            	<input name="bsOrderNo" id="bsOrderNoM3107" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		         <tr>
		        	<td width="120" align="right">销售出库单号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutPlan" id="prodOutPlanM3107"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">销售出库详细流水:</td>
		            <td width="210" align="right">
		            	<input name="prodSaleSerno" id="prodSaleSernoM3107" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		         <tr>
		        	<td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM3107"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodMaterialList}" var="a">
			          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
			         		</c:forEach>
						</select>
		            </td>
		       		<td width="120" align="right">规格:</td>
		            <td width="210" align="right">
		            	<input name="prodNorm" id="prodNormM3107"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	 <td width="120" align="right">定尺:</td>
	           		 <td width="210" align="right">
	            		  <input name="prodLength" id="prodLengthM3107" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            	 </td>
	            	 <td width="120" align="right">运送方式:</td>
		             <td width="210" align="right">
		            	<select name="bsGettype" id="bsGettypeM3107"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${bsGettypeList}" var="a">
			          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
			         		</c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">出库开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM3107" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">出库结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM3107" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM3107" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM3107" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM3107" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM3107" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM3107" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM3107" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3107Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3107Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="wxSaleInfoM3107List" title="销售出库汇总信息"  style="height:600px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM3107,collapsible:true,pagination:true,url:'/saleProdByPlan/outWxProdGroups',method:'post',onBeforeLoad:onBeforeLoadM3107,pageSize:30,toolbar:toolbarM3107">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'bsCustomer',width:220,sortable:true">销售客户</th>
	            <th data-options="field:'prodOutCarno',width:220,sortable:true">发货车次流水</th>
	        	<th data-options="field:'bsOrderNo',width:120,sortable:true">订单号</th>
	        	<th data-options="field:'bsInputuser',width:120,sortable:true">制单人员</th>
	            <th data-options="field:'prodRuc',width:120,formatter:BSL.formatProdRuc,sortable:true">出库仓库</th>
	            <th data-options="field:'prodOutPlan',width:120,sortable:true">销售出库单号</th>
	            <th data-options="field:'prodSaleSerno',width:150,sortable:true">销售出库详细流水</th>
	            <th data-options="field:'bsFlag',width:150,formatter:BSL.formatBsFlag,sortable:true">通知单类别</th>
	            <th data-options="field:'outDate',width:100,formatter:BSL.formatDateTime,sortable:true">出库日期</th>
	        	<th data-options="field:'prodDclFlag',width:130,formatter:BSL.formatProdDclFlag,sortable:true">外协厂标志</th>
	        	<th data-options="field:'bsGettype',width:100,formatter:BSL.formatBsGettype,sortable:true">运送方式</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">产品名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:100,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodUnitz',width:80,sortable:true">主单位</th>
	        	<th data-options="field:'prodUnit',width:80,sortable:true">单位</th>
	            <th data-options="field:'sumProdNum',width:125,sortable:true">出库数量</th>
	            <th data-options="field:'sumProdWeight',width:125,sortable:true">出库重量/吨</th>
				<th data-options="field:'sumChaweight',width:125,sortable:true">磅差重量/吨</th>
				<th data-options="field:'sumRetweight',width:125,sortable:true">退货重量/吨</th>
				<th data-options="field:'salePrice',width:100,sortable:true">出库单价</th>
	        	<th data-options="field:'sumAmt',width:100,sortable:true">出库金额</th>
	        	<th data-options="field:'prodCurr',width:100,sortable:true">币种</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM3107(){
		initDate();
		var queryParams = $('#wxSaleInfoM3107List').datagrid('options').queryParams;
		queryParams.bsCustomer = $('#bsCustomerM3107').val();
		queryParams.bsOrderNo = $('#bsOrderNoM3107').val();
		queryParams.prodOutPlan = $('#prodOutPlanM3107').val();
		queryParams.prodSaleSerno = $('#prodSaleSernoM3107').val();
		queryParams.prodMaterial = $('#prodMaterialM3107').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM3107').val();
		queryParams.prodLength = $('#prodLengthM3107').val();
		queryParams.bsGettype = $('#bsGettypeM3107').combobox("getValue");
		queryParams.startDate = $('#startDateM3107').datebox("getValue");
		queryParams.endDate = $('#endDateM3107').datebox("getValue");
	}

	//排序查询
	function sortSerachM3107(sort,order){
		$("#sortM3107").textbox('setValue',sort);
		$("#orderM3107").textbox('setValue',order);
		searchM3107Form();
	}

	//查询按钮
	function searchM3107Form(){
		initDate();
		//page页码
		var page = $("#wxSaleInfoM3107List").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#wxSaleInfoM3107List").datagrid('options').pageSize; 
		$("#pageM3107").textbox('setValue',page);
		$("#rowsM3107").textbox('setValue',rows);
		//ajax的post方式提交表单
		$.post("/saleProdByPlan/outWxProdGroups",$("#wxSaleGroupM3107Form").serialize(), function(data){
			if(data.status == 200){
	            $('#wxSaleInfoM3107List').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM3107").textbox('setValue',data.className);
				$("#methodNameM3107").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM3107();
	}
	
	//初始化日期
	function initDate(){
		var seperator = "-";
		var dateStart = $("#startDateM3107").datebox('getValue');
		if(dateStart=='' || dateStart==null){
			//去年今天
			var nowDate = new Date();
		    var date = new Date(nowDate);
		    date.setDate(date.getDate()-365);
		    var year2 = date.getFullYear();
		    var month2 = date.getMonth() + 1;
		    var strDate2 = date.getDate();
		    if (month2 >= 1 && month2 <= 9) {
		        month2 = "0" + month2;
		    }
		    if (strDate2 >= 0 && strDate2 <= 9) {
		        strDate2 = "0" + strDate2;
		    }
		    var currentdate = year2 + seperator + month2 + seperator + strDate2;
			$("#startDateM3107").datebox('setValue',currentdate);
		}
		var endStart = $("#endDateM3107").datebox('getValue');
		if(endStart=='' || endStart==null){
		    //今天
		    var nowDate = new Date();
		    var year = nowDate.getFullYear();
		    var month = nowDate.getMonth() + 1;
		    var strDate = nowDate.getDate();
		    if (month >= 1 && month <= 9) {
		    	month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var nowDate = year + seperator + month + seperator + strDate;
			$("#endDateM3107").datebox('setValue',nowDate);
		}
	}
	
	/* 重置表单 */
	function clearM3107Form(){
		$('#wxSaleGroupM3107Form').form('reset');
	}
	
	 var toolbarM3107 = [{
	    	text : '导出EXCEL',
	        iconCls : 'icon-save',
	        handler : function() {
	        	//获取后台传递参数className methodName
				var className = $("#wxSaleInfoM3107List").datagrid("getData").className;
				var methodName = $("#wxSaleInfoM3107List").datagrid("getData").methodName;
				/**
				 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
				 * 主要是查询时，datagrid属性className、methodName会丢失
				 */
				if(className == "" || className==null || className == undefined){
					className = $('#classNameM3107').val();
				}
				if(methodName == "" || methodName==null || methodName == undefined){
					methodName = $('#methodNameM3107').val();
				}
				//获取表头信息
				var header = $("#wxSaleInfoM3107List").datagrid("options").columns[0];
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
				mapParam.set("excelName","外协厂销售出库汇总信息");
				mapParam.set("loginUserId",$("#user_id").html());
				mapParam.set("rows","5000");
				mapParam.set("page","1");
				//查询条件 把所有查询条件带上
				mapParam.set("bsCustomer",$('#bsCustomerM3107').val());
				mapParam.set("bsOrderNo",$('#bsOrderNoM3107').val());
				mapParam.set("prodOutPlan",$('#prodOutPlanM3107').val());
				mapParam.set("prodSaleSerno",$('#prodSaleSernoM3107').val());
				mapParam.set("prodMaterial",$('#prodMaterialM3107').combobox("getValue"));
				mapParam.set("prodNorm",$('#prodNormM3107').val());
				mapParam.set("prodLength",$('#prodLengthM3107').val());
				mapParam.set("bsGettype",$('#bsGettypeM3107').combobox("getValue"));
				mapParam.set("startDate",$('#startDateM3107').datebox("getValue"));
				mapParam.set("endDate",$('#endDateM3107').datebox("getValue"));
				BSL.toExcel(mapParam);
	        }
	    }];
 

</script>