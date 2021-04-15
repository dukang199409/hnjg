<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="产品质量证明书打印" style="padding:10px 10px 10px 10px">
		<form id="prodQualitySerachForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">车号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutCarno" id="prodOutCarnoM5010" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,100]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">是否打印:</td>
		           	<td width="210" align="right">
		            	<select name="printNum" id="printNumM5010" class="easyui-combobox" data-options="editable:true" panelHeight="auto" style="width:200px;">
				          <option value="">请选择...</option>
			          	  <option value="0">否</option>
			          	  <option value="1">是</option>
						</select>
		            </td>
		        </tr>
		         <tr>
		        	<td width="120" align="right">外协厂质保书标志:</td>
		           	<td width="210" align="right">
		            	<select name="wxFlag" id="wxFlagM5010" class="easyui-combobox" data-options="editable:true" panelHeight="auto" style="width:200px;">
				          <option value="">请选择...</option>
			          	  <option value="0">否</option>
			          	  <option value="1">是</option>
						</select>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">出库开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM5010" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">出库结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM5010" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM5010" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM5010" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM5010" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM5010" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM5010" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM5010" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM5010Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5010Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="prodQualityList" title="产品质量证明书打印查询结果"  style="height:600px"
	       data-options="singleSelect:false,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM5010,collapsible:true,pagination:true,url:'/qualityPrint/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM5010,pageSize:30,toolbar:toolbarM5010">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	            <th data-options="field:'prodOutCarno',width:230,sortable:true">发货车次流水</th>
	            <th data-options="field:'bsCustomer',width:230,sortable:true">客户</th>
	            <th data-options="field:'bsGettype',width:100,formatter:BSL.formatBsGettype,sortable:true">运送方式</th>
	            <th data-options="field:'sumProdNum',width:125,sortable:true">出库数量/支</th>
	            <th data-options="field:'sumProdWeight',width:125,sortable:true">出库重量/吨</th>
				<th data-options="field:'retWeight',width:125,sortable:true">退货重量/吨</th>
	            <th data-options="field:'chaWeight',width:125,sortable:true">磅差重量/吨</th>
	            <th data-options="field:'prodCarno',width:170,sortable:true">发货车号</th>
	            <th data-options="field:'carDate',width:170,sortable:true">发货时间</th>
	            <th data-options="field:'printNum',width:125,sortable:true">打印次数</th>
	            <th data-options="field:'wxFlag',width:155,sortable:true,formatter:BSL.formatIsOrNotStatus">外协厂质保书标志</th>
	        </tr>
	    </thead>
	</table>
	
	<div id="qualityPrintDetailWindow" class="easyui-window" title="发货车次流水发货详细" data-options="modal:true,closed:true,href:'/qualityPrint/M5010-detail'" style="width:1120px;height:620px;padding:10px;">
	</div>
	<div id="M5010EditWindow" class="easyui-window" title="发货车次流水发货详细" data-options="modal:true,closed:true,href:'/qualityPrint/M5010-edit'" style="width:800px;height:220px;padding:10px;">
	</div>
	
</div>
<script>

	function onBeforeLoadM5010(){
		var queryParams = $('#prodQualityList').datagrid('options').queryParams;
		queryParams.prodOutCarno = $('#prodOutCarnoM5010').val();
		queryParams.printNum = $('#printNumM5010').combobox("getValue");
		queryParams.wxFlag = $('#wxFlagM5010').combobox("getValue");
		queryParams.startDate = $('#startDateM5010').datebox("getValue");
		queryParams.endDate = $('#endDateM5010').datebox("getValue");
	}

	//排序查询
	function sortSerachM5010(sort,order){
		$("#sortM5010").textbox('setValue',sort);
		$("#orderM5010").textbox('setValue',order);
		searchM5010Form();
	}

	//查询按钮
	function searchM5010Form(){
		//page页码
		var page = $("#prodQualityList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#prodQualityList").datagrid('options').pageSize; 
		$("#pageM5010").textbox('setValue',page);
		$("#rowsM5010").textbox('setValue',rows);
		//ajax的post方式提交表单
		$.post("/qualityPrint/listByCriteria",$("#prodQualitySerachForm").serialize(), function(data){
			if(data.status == 200){
	            $('#prodQualityList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM5010").textbox('setValue',data.className);
				$("#methodNameM5010").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM5010();
	}
	
	/* 重置表单 */
	function clearM5010Form(){
		$('#prodQualitySerachForm').form('reset');
	}
	
	function getM5010SelectionsIds(){
    	var salePlanList = $("#prodQualityList");
    	var sels = salePlanList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodOutCarno);
    	}
    	ids = ids.join(",");
    	return ids;
    }
	
	 var toolbarM5010 = [{
	        text:'详情维护',
	        iconCls:'icon-search',
	        handler:function(){
	        	var ids = getM5010SelectionsIds();
	        	if(ids.length == 0){
	        		$.messager.alert('提示','必须选择一条记录维护!');
	        		return ;
	        	}
	        	if(ids.indexOf(',') > 0){
	        		$.messager.alert('提示','一次最多维护一条记录!');
	        		return ;
	        	}
	        	var dataG = $("#prodQualityList").datagrid("getSelections")[0];
	  	    	var params = {"prodOutCarno":ids};
	          	$.post("/qualityPrint/detail",params, function(data){
	      			if(data.status == 200){
	      				$("#qualityPrintDetailWindow").window({
	      	        		onLoad :function(){
	      	        			//回显数据
	      	        			$("#qualityPrintForm").form("load",dataG);
	      	        			//回显详细
	      	        		    $('#prodQualityDetailList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
		      	  			}
	      	        	}).window("open");
	      			} else {
	      				$.messager.alert('提示','详情查询失败：'+data.msg);
	      			}
	      		});
	        }
	    },{
	        text:'编辑',
	        iconCls:'icon-edit',
	        handler:function(){
	        	var ids = getM5010SelectionsIds();
	        	if(ids.length == 0){
	        		$.messager.alert('提示','必须选择一条记录编辑!');
	        		return ;
	        	}
	        	if(ids.indexOf(',') > 0){
	        		$.messager.alert('提示','只能选择一条记录编辑!');
	        		return ;
	        	}
	        	$("#M5010EditWindow").window({
	        		onLoad :function(){
	        			//回显数据
	        			var data = $("#prodQualityList").datagrid("getSelections")[0];
	        			data.carNo = data.prodOutCarno;
	        			$("#M5010EditForm").form("load",data);
	        		}
	        	}).window("open");
	        }
	    },{
	        text:'产品质量证明书PDF打印',
	        iconCls:'icon-print',
	        handler:function(){
	        	var ids = getM5010SelectionsIds();
	        	if(ids.length == 0){
	        		$.messager.alert('提示','必须选择一条以上记录PDF打印!');
	        		return ;
	        	}
	        	$.messager.confirm('确认','确认选择这些记录打印？',function(r){
					if (r){
						var strArrays = ids.split(',');
						var wxFlag;
	        	    	for(var i=0;i<strArrays.length;i++){
	        	    		var data = $("#prodQualityList").datagrid("getSelections")[i];
	        	    		if(i==0){
	        	    			wxFlag = data.wxFlag;
	        	    		}else{
	        	    			if(wxFlag != data.wxFlag){
	        	    				$.messager.alert('提示','多笔汇总打印外协厂标志必须统一!');
	        		        		return ;
	        	    			}
	        	    		}
	        	    		
	        	    	}
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
	    	text : '导出EXCEL',
	        iconCls : 'icon-save',
	        handler : function() {
	        	//获取后台传递参数className methodName
				var className = $("#prodQualityList").datagrid("getData").className;
				var methodName = $("#prodQualityList").datagrid("getData").methodName;
				/**
				 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
				 * 主要是查询时，datagrid属性className、methodName会丢失
				 */
				if(className == "" || className==null || className == undefined){
					className = $('#classNameM5010').val();
				}
				if(methodName == "" || methodName==null || methodName == undefined){
					methodName = $('#methodNameM5010').val();
				}
				//获取表头信息
				var header = $("#prodQualityList").datagrid("options").columns[0];
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
				mapParam.set("excelName","发货出库车次流水信息");
				mapParam.set("loginUserId",$("#user_id").html());
				mapParam.set("rows","5000");
				mapParam.set("page","1");
				//查询条件 把所有查询条件带上
				mapParam.set("prodOutCarno",$('#prodOutCarnoM5010').val());
				mapParam.set("printNum",$('#printNumM5010').combobox("getValue"));
				mapParam.set("wxFlag",$('#wxFlagM5010').combobox("getValue"));
				mapParam.set("startDate",$('#startDateM5010').datebox("getValue"));
				mapParam.set("endDate",$('#endDateM5010').datebox("getValue"));
				BSL.toExcel(mapParam);
	        }
	    }];
 

</script>