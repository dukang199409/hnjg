<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="外协厂转场接收" style="padding:10px 10px 10px 10px">
		<form id="wxZcRecvForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售出库通知单号:</td>
		            <td width="210" align="right">
		            	<input name="bsId" id="bsIdM3104" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">通知单类别: </td>
		            <td width="210" align="right">
		            	<select name="bsFlag" id="bsFlagM3104" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${bsFlagWxList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>	           
		        </tr>
		       <tr>
		            <td width="120" align="right">通知单状态:</td>
		            <td width="210" align="right">
		            	<select name="bsStatus" id="bsStatusM3104" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				         <c:forEach items="${bsStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM3104" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM3104" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM3104" class="easyui-textbox" type="text" data-options="required:false"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM3104" class="easyui-textbox" type="text" data-options="required:false"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM3104" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM3104" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM3104" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM3104" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>		        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3104Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3104Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="wxZcRecvList" title="外协厂转场接收通知单管理"  style="height:620px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM3104,collapsible:true,pagination:true,url:'/prodWxJs/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM3104,pageSize:30,toolbar:toolbarM3104">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'bsId',width:130,sortable:true">销售出库通知单号</th>
	            <th data-options="field:'bsFlag',width:120,formatter:BSL.formatBsFlag,sortable:true">通知单类别</th>
	            <th data-options="field:'bsCompany',width:100,sortable:true">供应商</th>
	            <th data-options="field:'bsCustomer',width:170,sortable:true">客户</th>
	            <th data-options="field:'bsOrderNo',width:100,sortable:true">订单号</th>
	            <th data-options="field:'bsHasguarantee',width:80,formatter:BSL.formatIsOrNotStatus,sortable:true">含质保书</th>
	            <th data-options="field:'bsAmt',width:130,sortable:true">实际出库产品数量</th>
	            <th data-options="field:'bsWeight',width:120,sortable:true">应出库重量/吨</th>
	            <th data-options="field:'bsRelweight',width:130,sortable:true">实际出库总重量/吨</th>
	            <th data-options="field:'bsJsnum',width:120,sortable:true">已接收数量</th>
	            <th data-options="field:'bsJsweight',width:120,sortable:true">已接收重量/吨</th>
	            <th data-options="field:'bsTransport',width:80,sortable:true">运输单位</th>
	            <th data-options="field:'bsCarno',width:80,sortable:true">运输车号</th>
	            <th data-options="field:'bsGettype',width:100,formatter:BSL.formatBsGettype,sortable:true">提货方式</th>
	            <th data-options="field:'bsStatus',width:100,formatter:BSL.formatBsStatus,sortable:true">通知单状态</th>
	            <th data-options="field:'bsPrice',width:120,sortable:true">实际出库总价格</th>
	            <th data-options="field:'bsArrdate',width:120,formatter:BSL.formatDateTime,sortable:true">预计发货日期</th>
	            <th data-options="field:'bsInputuser',width:70,sortable:true">制单人</th>
	            <th data-options="field:'bsCheckuser',width:70,sortable:true">审批人</th>
	            <th data-options="field:'crtDate',width:120,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'remark',width:250,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="getJsWasteDetailWindow" class="easyui-window" title="通知单发货废品详情" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/prodWxJs/M3104-detailwaste'" style="width:1120px;height:660px;padding:10px;">
	</div>
	<div id="getJsProdDetailWindow" class="easyui-window" title="通知单发货产品详情" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/prodWxJs/M3104-detailprod'" style="width:1300px;height:800px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM3104(){
		var queryParams = $('#wxZcRecvList').datagrid('options').queryParams;
		queryParams.bsId = $('#bsIdM3104').val();
		queryParams.bsFlag = $('#bsFlagM3104').combobox("getValue");
		queryParams.bsStatus = $('#bsStatusM3104').combobox("getValue");
		queryParams.startDate = $('#startDateM3104').datebox("getValue");
		queryParams.endDate = $('#endDateM3104').datebox("getValue");
	}

	//排序查询
	function sortSerachM3104(sort,order){
		$("#sortM3104").textbox('setValue',sort);
		$("#orderM3104").textbox('setValue',order);
		searchM3104Form();
	}

	//查询按钮
	function searchM3104Form(){
		//page页码
		var page = $("#wxZcRecvList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#wxZcRecvList").datagrid('options').pageSize; 
		$("#pageM3104").textbox('setValue',page);
		$("#rowsM3104").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#wxZcRecvForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/prodWxJs/listByCriteria",$("#wxZcRecvForm").serialize(), function(data){
			if(data.status == 200){
	            $('#wxZcRecvList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM3104").textbox('setValue',data.className);
				$("#methodNameM3104").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM3104();
	}
	
	/* 重置表单 */
	function clearM3104Form(){
		$('#wxZcRecvForm').form('reset');
	}

    function getM3104SelectionsIds(){
    	var wxZcRecvList = $("#wxZcRecvList");
    	var sels = wxZcRecvList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].bsId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM3104 = [{
        text:'选择接收',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getM3104SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录选择接收!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多选择接收一条记录!');
        		return ;
        	}
        	var info = $("#wxZcRecvList").datagrid("getSelections")[0];
        	var bsFlag = info.bsFlag;
        	if(bsFlag == '11'){
        		$("#getJsWasteDetailWindow").window({
	        			onLoad :function(){
	        	  	    var params = {"salePlanId":ids};
	        			$("#wxJsWasteDetailSearchForm").form("load",params);
	        			$.post("/prodWxJs/wasteByBsId",params, function(data){
	        				if(data.status == 200){
	        		           	$('#wxJsWasteDetailList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	        				}else{
	        					$.messager.alert('提示',data.msg);
	        				}
	        			}); 
	  	  			}
		        }).window("open");
        	}else{
      	    	$("#getJsProdDetailWindow").window({
    	        		onLoad :function(){
    	        		var params = {"prodOutPlan":ids,"page":"1","rows":"30"};
    	        		$("#wxJsProdDetailSearchForm").form("load",params);	
    	        		$.post("/prodWxJs/canJsProds",params, function(data){
	        				if(data.status == 200){
	        		           	$('#canJsProdList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	        				}else{
	        					$.messager.alert('提示',data.msg);
	        				}
	        			});
    	        		$.post("/prodWxJs/alreadyJsProds",params, function(data){
	        				if(data.status == 200){
	        		           	$('#alreadyJsProdList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	        				}else{
	        					$.messager.alert('提示',data.msg);
	        				}
	        			});
      	  			}
    	        }).window("open");
        	}
        }
    },{
        text:'刷新',
        iconCls:'icon-reload',
        handler:function(){
        	var ids = getM3104SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录刷新!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录刷新!');
        		return ;
        	}
        	var params = {"bsId":ids};
        	$.post("/salePlan/reload",params, function(data){
    			if(data.status == 200){
    				$.messager.alert('提示','刷新成功!',undefined,function(){
    					searchM3104Form();
    				});
    			} else {
    				$.messager.alert('提示','刷新失败：'+data.msg);
    			}
    		});
        }
    },{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#wxZcRecvList").datagrid("getData").className;
			var methodName = $("#wxZcRecvList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM3104').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM3104').val();
			}
			//获取表头信息
			var header = $("#wxZcRecvList").datagrid("options").columns[0];
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
			mapParam.set("excelName","外协厂转场接收通知单信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("bsId",$('#bsIdM3104').val());
			mapParam.set("bsFlag",$('#bsFlagM3104').combobox("getValue"));
			mapParam.set("bsStatus",$('#bsStatusM3104').combobox("getValue"));
			mapParam.set("startDate",$('#startDateM3104').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM3104').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>