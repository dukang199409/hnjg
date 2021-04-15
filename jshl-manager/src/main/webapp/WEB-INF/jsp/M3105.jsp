<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="外协厂接收信息查询" style="padding:10px 10px 10px 10px">
		<form id="prodWxJsProdsSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		            <td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM3105" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        	<td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="prodLuno" id="prodLunoM3105" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">产品类别:</td>
		            <td width="210" align="right">
		            	<select name="prodType" id="prodTypeM3105" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				            <c:forEach items="${prodTypeBList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		       		<td width="120" align="right">产品状态:</td>
		            <td width="210" align="right">
		            	<select name="prodStatus" id="prodStatusM3105" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodStatusDList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	 </c:forEach>
						</select>
		            </td>
		        </tr>
		       <tr>
		       		<td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM3105" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
						</select>
					</td>
		            <td width="120" align="right">规格:</td>
		            <td width="210" align="right">
	            		<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            	</td>
		        </tr>
		        <tr>
		            <td width="120" align="right">出库指令号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutPlan" id="prodOutPlanM3105" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">接收通知单号:</td>
		            <td width="210" align="right">
		            	<input name="prodFhck" id="prodFhckM3105" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		         <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM3105" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM3105" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM3105" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM3105" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM3105" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM3105" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM3105" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM3105" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3105Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3105Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="prodWxJsProdsInfoList" title="外协厂接收信息管理"  style="height:600px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM3105,collapsible:true,pagination:true,url:'/wxJsProdsManager/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM3105,pageSize:30,toolbar:toolbarM3105">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	            <th data-options="field:'prodId',width:180,sortable:true">产品编号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">物料名称</th>
	        	<th data-options="field:'prodType',width:100,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodRelWeight',width:105,sortable:true">复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:105,sortable:true">入库重量/吨</th>
	            <th data-options="field:'prodNum',width:125,sortable:true">件数</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodOutPlan',width:100,sortable:true">出库指令号</th>
	            <th data-options="field:'prodFhck',width:100,sortable:true">接收通知单号</th>
	            <th data-options="field:'prodLevel',width:110,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodDclFlag',width:130,formatter:BSL.formatProdDclFlag,sortable:true">产成品外协厂标志</th>
	            <th data-options="field:'prodOutCarno',width:100,sortable:true">发货车次流水</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodCustomer',width:100,sortable:true">客户</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	            <th data-options="field:'prodInputuser',width:70,sortable:true">录入人</th>
	            <th data-options="field:'crtDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'prodOutDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">出库日期</th>
	            <th data-options="field:'updDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="prodWxEditWindow" class="easyui-window" title="外协厂编辑产品信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/prodWxManager/M3105-edit'" style="width:780px;height:300px;padding:10px;">
	</div>
	<div id="prodWxAddWindowB" class="easyui-window" title="外协厂产品补录入库" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/prodWxManager/M3105-addb'" style="width:780px;height:340px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM3105(){
		var queryParams = $('#prodWxJsProdsInfoList').datagrid('options').queryParams;
		queryParams.prodId = $('#prodIdM3105').val();
		queryParams.prodLuno = $('#prodLunoM3105').val();
		queryParams.prodType = $('#prodTypeM3105').combobox("getValue");
		queryParams.prodStatus = $('#prodStatusM3105').combobox("getValue");
		queryParams.prodMaterial = $('#prodMaterialM3105').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM3105').val();
		queryParams.prodFhck = $('#prodFhck').val();
		queryParams.prodOutPlan = $('#prodOutPlanM3105').val();
		queryParams.startDate = $('#startDateM3105').datebox("getValue");
		queryParams.endDate = $('#endDateM3105').datebox("getValue");
	}

	//排序查询
	function sortSerachM3105(sort,order){
		$("#sortM3105").textbox('setValue',sort);
		$("#orderM3105").textbox('setValue',order);
		searchM3105Form();
	}

	//查询按钮
	function searchM3105Form(){
		//page页码
		var page = $("#prodWxJsProdsInfoList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#prodWxJsProdsInfoList").datagrid('options').pageSize; 
		$("#pageM3105").textbox('setValue',page);
		$("#rowsM3105").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/wxJsProdsManager/listByCriteria",$("#prodWxJsProdsSearchForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#prodWxJsProdsInfoList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM3105").textbox('setValue',data.className);
				$("#methodNameM3105").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM3105();
	}
	
	/* 重置表单 */
	function clearM3105Form(){
		$('#prodWxJsProdsSearchForm').form('reset');
	}

    function getM3105SelectionsIds(){
    	var receiptList = $("#prodWxJsProdsInfoList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM3105 = [{
        text:'补打PDF标签文件',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM3105SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录补打PDF!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录补打PDF!');
        		return ;
        	}
        	var data = $("#prodWxJsProdsInfoList").datagrid("getSelections")[0];
        	if(data.prodStatus == "0"){
        		$.messager.alert('提示','未入库的产品不支持补打PDF!');
        		return ;
        	}
        	$.messager.confirm('确认','是否需要补打PDF标签文件？',function(r){
				if (r){
					var prodId = data.prodId;
        	    	var mapParam = new Map();
        			mapParam.set("url","/import/importPdf");
        			mapParam.set("prodId",prodId);
        			mapParam.set("tradeType","M3005");
        			BSL.toNewPagePDF(mapParam);
        	    }
			});
        }
    },{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#prodWxJsProdsInfoList").datagrid("getData").className;
			var methodName = $("#prodWxJsProdsInfoList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM3105').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM3105').val();
			}
			//获取表头信息
			var header = $("#prodWxJsProdsInfoList").datagrid("options").columns[0];
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
			mapParam.set("excelName","外协厂接收信息汇总");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","10000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("prodId",$('#prodIdM3105').val());
			mapParam.set("prodLuno",$('#prodLunoM3105').val());
			mapParam.set("prodType",$('#prodTypeM3105').combobox("getValue"));
			mapParam.set("prodStatus",$('#prodStatusM3105').combobox("getValue"));
			mapParam.set("prodMaterial",$('#prodMaterialM3105').combobox("getValue"));
			mapParam.set("prodNorm",$('#prodNormM3105').val());
			mapParam.set("prodFhck",$('#prodFhckM3105').val());
			mapParam.set("prodOutPlan",$('#prodOutPlanM3105').val());
			mapParam.set("startDate",$('#startDateM3105').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM3105').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>