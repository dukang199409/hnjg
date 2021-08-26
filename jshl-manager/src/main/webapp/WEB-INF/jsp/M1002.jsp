<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="原料入库通知单执行查询" style="padding:10px 10px 10px 10px">
		<form id="receiptExeSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">原料入库通知单号:</td>
		            <td width="210" align="right">
		            	<input name="bsId" id="bsIdM1002" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">通知单类别: </td>
		            <td width="210" align="right">
		            	<select name="bsFlag" id="bsFlagM1002" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${bsFlagList}" var="a">
			          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
			         		</c:forEach>
						</select>
		            </td>	           
		        </tr>
		       <tr>
		            <td width="120" align="right">通知单状态:</td>
		            <td width="210" align="right">
		            	<select name="bsStatus" id="bsStatusM1002" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
					         <c:forEach items="${bsStatusBList}" var="a">
				          	   <option value="${a.enumKey}">${a.enumValue}</option>
				          </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM1002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM1002" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM1002" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM1002" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM1002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM1002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM1002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM1002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM1002Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM1002Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="receiptListExe" title="原料入库通知单执行信息管理"  style="height:620px"
	       data-options="singleSelect:true,rownumbers:true,fitColumns:true,remoteSort:false,onSortColumn:sortSerachM1002,collapsible:true,pagination:true,url:'/receipt/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM1002,pageSize:30,toolbar:toolbarM1002">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'bsId',width:120,sortable:true">原料入库通知单号</th>
	            <th data-options="field:'bsFlag',width:80,formatter:BSL.formatBsFlag,sortable:true">通知单类别</th>
	            <th data-options="field:'bsCompany',width:100,sortable:true">供应商</th>
	            <th data-options="field:'bsCustomer',width:100,sortable:true">客户</th>
	            <th data-options="field:'bsHasguarantee',width:80,formatter:BSL.formatIsOrNotStatus,sortable:true">含质保书</th>
	            <th data-options="field:'bsAmt',width:60,sortable:true">卷数</th>
	            <th data-options="field:'bsWeight',width:130,sortable:true">原料来料总重量/吨</th>
	            <th data-options="field:'bsTransport',width:100,sortable:true">运输单位</th>
	            <th data-options="field:'bsCarno',width:70,sortable:true">运输车号</th>
	            <th data-options="field:'bsGettype',width:100,formatter:BSL.formatBsGettype,sortable:true">提货方式</th>
	            <th data-options="field:'bsStatus',width:100,formatter:BSL.formatBsStatus,sortable:true">通知单状态</th>
	            <th data-options="field:'bsRelweight',width:120,sortable:true">实际重量/吨</th>
	            <th data-options="field:'bsNorm',width:120,sortable:true">预计来料规格</th>
	            <th data-options="field:'bsArrdate',width:120,formatter:BSL.formatDateTime,sortable:true">预计来料时间</th>
	            <th data-options="field:'bsInputuser',width:70,sortable:true">制单人</th>
	            <th data-options="field:'bsCheckuser',width:70,sortable:true">审批人</th>
	            <th data-options="field:'crtDate',width:120,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'remark',width:206,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="receiptExeDetailInfoWindow" class="easyui-window" title="原料入库通知单详情" data-options="modal:true,closed:true,iconCls:'icon_add',href:'/plan/M1002-detail'" style="width:1120px;height:640px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM1002(){
		var queryParams = $('#receiptListExe').datagrid('options').queryParams;
		
		queryParams.bsId = $('#bsIdM1002').val();
		queryParams.bsFlag = $('#bsFlagM1002').combobox("getValue");
		queryParams.bsStatus = $('#bsStatusM1002').combobox("getValue");
		queryParams.startDate = $('#startDateM1002').datebox("getValue");
		queryParams.endDate = $('#endDateM1002').datebox("getValue");
	}
	
	//排序查询
	function sortSerachM1002(sort,order){
		$("#sortM1002").textbox('setValue',sort);
		$("#orderM1002").textbox('setValue',order);
		searchM1002Form();
	}

	//查询按钮
	function searchM1002Form(){
		//page页码
		var page = $("#receiptListExe").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#receiptListExe").datagrid('options').pageSize; 
		$("#pageM1002").textbox('setValue',page);
		$("#rowsM1002").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/receipt/listByCriteria",$("#receiptExeSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#receiptListExe').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM1002").textbox('setValue',data.className);
				$("#methodNameM1002").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM1002();
	}
	
	/* 重置表单 */
	function clearM1002Form(){
		$('#receiptExeSearchForm').form('reset');
	}

    function getM1002SelectionsIds(){
    	var receiptList = $("#receiptListExe");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].bsId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM1002 = [{
        text:'详情',
        iconCls:'icon-search',
        handler:function(){
        	var ids = getM1002SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录查看!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多查看一条记录!');
        		return ;
        	}
        	var dataG = $("#receiptListExe").datagrid("getSelections")[0];
  	    	var params = {"bsId":ids};
          	$.post("/receipt/detail",params, function(data){
      			if(data.status == 200){
      				$("#receiptExeDetailInfoWindow").window({
      	        		onLoad :function(){
      	        			//回显数据
      	        			dataG.arrDate = BSL.formatDate(dataG.bsArrdate); 
      	        			$("#receiptExeDetailForm").form("load",dataG);
      	        			//回显详细
      	        			var values = [];  
	      	  				for (var i = 0; i < data.data.length; i++) {
	      	  	                var a = {
	     		                    'prodId' : data.data[i].prodId,
	     		                    'prodName' : data.data[i].prodName,
	     		                    'prodNorm' : data.data[i].prodNorm,
	     		                    'prodMaterial' : data.data[i].prodMaterial,
	     		                    'prodLuno' : data.data[i].prodLuno,
	     		                    'prodLength' : data.data[i].prodLength,
	     		                    'prodLevel' : data.data[i].prodLevel,
	     		                    'prodRecordWeight' : data.data[i].prodRecordWeight,
	     		                    'prodSourceCompany' : data.data[i].prodSourceCompany,
	     		                    'prodStatus' : data.data[i].prodStatus,
	     		                    'prodCompany' : data.data[i].prodCompany
	      	  	                };
	      	  	                values.push(a);				
	      	  	            }
	      	  	            $('#receiptExeDetailInfoList').datagrid('loadData', values);
	      	  			}
      	        	}).window("open");
      			} else {
      				$.messager.alert('提示','详情查询失败：'+data.msg);
      			}
      		});
        }
    },{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#receiptList").datagrid("getData").className;
			var methodName = $("#receiptList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM1002').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM1002').val();
			}
			//获取表头信息
			var header = $("#receiptList").datagrid("options").columns[0];
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
			mapParam.set("excelName","原料入库通知单执行信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("bsId",$('#bsIdM1002').val());
			mapParam.set("bsFlag",$('#bsFlagM1002').combobox("getValue"));
			mapParam.set("bsStatus",$('#bsStatusM1002').combobox("getValue"));
			mapParam.set("startDate",$('#startDateM1002').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM1002').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>