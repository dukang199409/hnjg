<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="销售出库汇总信息查询" style="padding:10px 10px 10px 10px">
		<form id="saleInfoGroupSerachForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售客户:</td>
		            <td width="210" align="right">
		            	<input name="bsCustomer" id="bsCustomerM5009" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,100]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">订单号:</td>
		            <td width="210" align="right">
		            	<input name="bsOrderNo" id="bsOrderNoM5009" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		         <tr>
		        	<td width="120" align="right">销售出库单号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutPlan" id="prodOutPlanM5009"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">销售出库详细流水:</td>
		            <td width="210" align="right">
		            	<input name="prodSaleSerno" id="prodSaleSernoM5009" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		         <tr>
		        	<td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM5009"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodMaterialList}" var="a">
			          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
			         		</c:forEach>
						</select>
		            </td>
		       		<td width="120" align="right">规格:</td>
		            <td width="210" align="right">
		            	<input name="prodNorm" id="prodNormM5009"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	 <td width="120" align="right">定尺:</td>
	           		 <td width="210" align="right">
	            		  <input name="prodLength" id="prodLengthM5009" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            	 </td>
	            	 <td width="120" align="right">外协厂标志:</td>
		             <td width="210" align="right">
		            	<select name="prodDclFlag" id="prodDclFlagM5009"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodDclFlagList}" var="a">
			          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
			         		</c:forEach>
						</select>
		            </td>
		        </tr>
		         <tr>
	            	 <td width="120" align="right">运送方式:</td>
		             <td width="210" align="right">
		            	<select name="bsGettype" id="bsGettypeM5009"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
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
		            	<input name="startDate" id="startDateM5009" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">出库结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM5009" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM5009" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM5009" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM5009" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM5009" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM5009" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM5009" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>		
		        <tr hidden="true">
		            <td width="120" align="right">录入人角色:</td>
		            <td width="210" align="right">
		            	<input name="userType" id="userTypeM5009" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
			    </tr>		        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM5009Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5009Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="saleInfoGroupList" title="销售出库汇总信息"  style="height:600px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM5009,collapsible:true,pagination:true,url:'/saleProdByPlan/outProdGroups',method:'post',onBeforeLoad:onBeforeLoadM5009,pageSize:30,toolbar:toolbarM5009">
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
	            <th data-options="field:'printNum',width:125,sortable:true">打印次数</th>
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

	function onBeforeLoadM5009(){
		initDate();
		var queryParams = $('#saleInfoGroupList').datagrid('options').queryParams;
		queryParams.bsCustomer = $('#bsCustomerM5009').val();
		queryParams.bsOrderNo = $('#bsOrderNoM5009').val();
		queryParams.prodOutPlan = $('#prodOutPlanM5009').val();
		queryParams.prodSaleSerno = $('#prodSaleSernoM5009').val();
		queryParams.prodMaterial = $('#prodMaterialM5009').combobox("getValue");
		queryParams.prodDclFlag = $('#prodDclFlagM5009').combobox("getValue");
		queryParams.bsGettype = $('#bsGettypeM5009').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM5009').val();
		queryParams.prodLength = $('#prodLengthM5009').val();
		queryParams.startDate = $('#startDateM5009').datebox("getValue");
		queryParams.endDate = $('#endDateM5009').datebox("getValue");
		queryParams.userType = $("#userTypeIndex").textbox('getValue');
	}

	//排序查询
	function sortSerachM5009(sort,order){
		$("#sortM5009").textbox('setValue',sort);
		$("#orderM5009").textbox('setValue',order);
		searchM5009Form();
	}

	//查询按钮
	function searchM5009Form(){
		initDate();
		//page页码
		var page = $("#saleInfoGroupList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#saleInfoGroupList").datagrid('options').pageSize; 
		$("#pageM5009").textbox('setValue',page);
		$("#rowsM5009").textbox('setValue',rows);
		$("#userTypeM5009").textbox('setValue',$("#userTypeIndex").textbox('getValue'));
		//ajax的post方式提交表单
		$.post("/saleProdByPlan/outProdGroups",$("#saleInfoGroupSerachForm").serialize(), function(data){
			if(data.status == 200){
	            $('#saleInfoGroupList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM5009").textbox('setValue',data.className);
				$("#methodNameM5009").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM5009();
	}
	
	//初始化日期
	function initDate(){
		var seperator = "-";
		var dateStart = $("#startDateM5009").datebox('getValue');
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
			$("#startDateM5009").datebox('setValue',currentdate);
		}
		var endStart = $("#endDateM5009").datebox('getValue');
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
			$("#endDateM5009").datebox('setValue',nowDate);
		}
	}
	
	/* 重置表单 */
	function clearM5009Form(){
		$('#saleInfoGroupSerachForm').form('reset');
	}
	
	function getM5009SelectionsIds(){
    	var salePlanList = $("#saleInfoGroupList");
    	var sels = salePlanList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodOutCarno);
    	}
    	ids = ids.join(",");
    	return ids;
    }
	
	 var toolbarM5009 = [{
	        text:'产品质量证明书PDF打印',
	        iconCls:'icon-print',
	        handler:function(){
	        	var ids = getM5009SelectionsIds();
	        	if(ids.length == 0){
	        		$.messager.alert('提示','必须选择一条以上记录PDF打印!');
	        		return ;
	        	}
	        	$.messager.confirm('确认','确认打印？',function(r){
					if (r){
	        	    	var mapParam = new Map();
	        			mapParam.set("url","/print/exportPdf");
	        			mapParam.set("prodOutCarnos",ids);
	        			mapParam.set("tradeType","M5001Quality");
	        			mapParam.set("loginUserId",$("#user_id").html());
	        			BSL.toNewPagePDF(mapParam);
	        	    }
				});
	        }
	    },{
	        text:'销售出库单补打',
	        iconCls:'icon-print',
	        handler:function(){
	        	var ids = getM5009SelectionsIds();
	        	if(ids.length == 0){
	        		$.messager.alert('提示','必须选择一条记录PDF打印!');
	        		return ;
	        	}
	        	if(ids.indexOf(',') > 0){
	        		$.messager.alert('提示','只能选择一条记录PDF打印!');
	        		return ;
	        	}
	        	if(ids == "" || ids == null){
	        		$.messager.alert('提示','车次流水号不能为空!');
	        		return ;
	        	}
	        	var data = $("#saleInfoGroupList").datagrid("getSelections")[0];
	        	$.messager.confirm('确认','确认打印？',function(r){
					if (r){
	        	    	var mapParam = new Map();
	        			mapParam.set("url","/print/exportPdf");
	        			mapParam.set("prodOutCarno",ids);
	        			mapParam.set("planId",data.prodOutPlan);
	        			mapParam.set("prodBc",data.prodRuc);
	        			mapParam.set("tradeType","M5001ProdsByCar");
	        			mapParam.set("loginUserId",$("#user_id").html());
	        			BSL.toNewPagePDF(mapParam);
	        	    }
				});
	        }
	    },{
	    	text : '导出EXCEL',
	        iconCls : 'icon-save',
	        handler : function() {
	        	//获取后台传递参数className methodName
				var className = $("#saleInfoGroupList").datagrid("getData").className;
				var methodName = $("#saleInfoGroupList").datagrid("getData").methodName;
				/**
				 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
				 * 主要是查询时，datagrid属性className、methodName会丢失
				 */
				if(className == "" || className==null || className == undefined){
					className = $('#classNameM5009').val();
				}
				if(methodName == "" || methodName==null || methodName == undefined){
					methodName = $('#methodNameM5009').val();
				}
				//获取表头信息
				var header = $("#saleInfoGroupList").datagrid("options").columns[0];
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
				mapParam.set("excelName","销售出库汇总信息");
				mapParam.set("loginUserId",$("#user_id").html());
				mapParam.set("rows","5000");
				mapParam.set("page","1");
				//查询条件 把所有查询条件带上
				mapParam.set("bsCustomer",$('#bsCustomerM5009').val());
				mapParam.set("bsOrderNo",$('#bsOrderNoM5009').val());
				mapParam.set("prodOutPlan",$('#prodOutPlanM5009').val());
				mapParam.set("prodSaleSerno",$('#prodSaleSernoM5009').val());
				mapParam.set("prodMaterial",$('#prodMaterialM5009').combobox("getValue"));
				mapParam.set("prodNorm",$('#prodNormM5009').val());
				mapParam.set("prodLength",$('#prodLengthM5009').val());
				mapParam.set("prodDclFlag",$('#prodDclFlagM5009').combobox("getValue"));
				mapParam.set("bsGettype",$('#bsGettypeM5009').combobox("getValue"));
				mapParam.set("startDate",$('#startDateM5009').datebox("getValue"));
				mapParam.set("endDate",$('#endDateM5009').datebox("getValue"));
				BSL.toExcel(mapParam);
	        }
	    }];
 

</script>