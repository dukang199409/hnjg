<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="销售出库通知单制单信息查询" style="padding:10px 10px 10px 10px">
		<form id="salePlanSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售出库通知单号:</td>
		            <td width="210" align="right">
		            	<input name="bsId" id="bsIdM5001" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">通知单类别: </td>
		            <td width="210" align="right">
		            	<select name="bsFlag" id="bsFlagM5001" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
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
		            	<select name="bsStatus" id="bsStatusM5001" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				         <c:forEach items="${bsStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">订单号:</td>
		            <td width="210" align="right">
		            	<input name="bsOrderNo" id="bsOrderNoM5001" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM5001" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM5001" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM5001" class="easyui-textbox" type="text" data-options="required:false"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM5001" class="easyui-textbox" type="text" data-options="required:false"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM5001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM5001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM5001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM5001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>	
		         <tr hidden="true">
		            <td width="120" align="right">录入人角色:</td>
		            <td width="210" align="right">
		            	<input name="userType" id="userTypeM5001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
			    </tr>		        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM5001Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5001Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="salePlanList" title="销售出库通知单制单信息管理"  style="height:620px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM5001,collapsible:true,pagination:true,url:'/salePlan/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM5001,pageSize:30,toolbar:toolbarM5001">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'bsId',width:130,sortable:true">销售出库通知单号</th>
	            <th data-options="field:'bsFlag',width:120,formatter:BSL.formatBsFlag,sortable:true">通知单类别</th>
	            <th data-options="field:'bsStatus',width:100,formatter:BSL.formatBsStatus,sortable:true">通知单状态</th>
	            <th data-options="field:'bsCompany',width:100,sortable:true">供应商</th>
	            <th data-options="field:'bsCustomer',width:170,sortable:true">客户</th>
	            <th data-options="field:'bsOrderNo',width:100,sortable:true">订单号</th>
	            <th data-options="field:'bsHasguarantee',width:80,formatter:BSL.formatIsOrNotStatus,sortable:true">含质保书</th>
	            <th data-options="field:'bsAmt',width:130,sortable:true">实际出库产品数量</th>
	            <th data-options="field:'bsWeight',width:120,sortable:true">应出库重量/吨</th>
	            <th data-options="field:'bsTransport',width:80,sortable:true">运输单位</th>
	            <th data-options="field:'bsCarno',width:80,sortable:true">运输车号</th>
	            <th data-options="field:'bsGettype',width:100,formatter:BSL.formatBsGettype,sortable:true">提货方式</th>
	            <th data-options="field:'bsRelweight',width:130,sortable:true">实际出库总重量/吨</th>
	            <th data-options="field:'bsPrice',width:120,sortable:true">实际出库总价格</th>
	            <th data-options="field:'bsFbweight',width:120,sortable:true">复磅重量/吨</th>
	            <th data-options="field:'bsRetweight',width:120,sortable:true">退货重量/吨</th>
	            <th data-options="field:'bsChaweight',width:120,sortable:true">磅差重量/吨</th>
	            <th data-options="field:'bsArrdate',width:120,formatter:BSL.formatDateTime,sortable:true">预计发货日期</th>
	            <th data-options="field:'bsInputuser',width:70,sortable:true">制单人</th>
	            <th data-options="field:'bsCheckuser',width:70,sortable:true">审批人</th>
	            <th data-options="field:'crtDate',width:120,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'remark',width:250,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="salePlanAddWindow" class="easyui-window" title="新增销售出库通知单" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/salePlan/M5001-add'" style="width:780px;height:300px;padding:10px;">
	</div>
	<div id="salePlanEditWindow" class="easyui-window" title="编辑销售出库通知单" data-options="modal:true,closed:true,iconCls:'salePlan-edit',href:'/salePlan/M5001-edit'" style="width:780px;height:350px;padding:10px;">
	</div>
	<div id="salePlanDetailWindow" class="easyui-window" title="销售出库通知单详细" data-options="modal:true,closed:true,href:'/salePlan/M5001-detail'" style="width:1120px;height:660px;padding:10px;">
	</div>
	<div id="printChooseWindow" class="easyui-window" title="打印" data-options="modal:true,closed:true,href:'/doSalePlan/M5001-print'" style="width:1250px;height:730px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM5001(){
		var queryParams = $('#salePlanList').datagrid('options').queryParams;
		queryParams.bsId = $('#bsIdM5001').val();
		queryParams.bsFlag = $('#bsFlagM5001').combobox("getValue");
		queryParams.bsStatus = $('#bsStatusM5001').combobox("getValue");
		queryParams.bsOrderNo = $('#bsOrderNoM5001').val();
		queryParams.startDate = $('#startDateM5001').datebox("getValue");
		queryParams.endDate = $('#endDateM5001').datebox("getValue");
		queryParams.userType = $("#userTypeIndex").textbox('getValue');
	}

	//排序查询
	function sortSerachM5001(sort,order){
		$("#sortM5001").textbox('setValue',sort);
		$("#orderM5001").textbox('setValue',order);
		searchM5001Form();
	}

	//查询按钮
	function searchM5001Form(){
		//page页码
		var page = $("#salePlanList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#salePlanList").datagrid('options').pageSize; 
		$("#pageM5001").textbox('setValue',page);
		$("#rowsM5001").textbox('setValue',rows);
		$("#userTypeM5001").textbox('setValue',$("#userTypeIndex").textbox('getValue'));
		//ajax的post方式提交表单
		//$("#salePlanSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/salePlan/listByCriteria",$("#salePlanSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#salePlanList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM5001").textbox('setValue',data.className);
				$("#methodNameM5001").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM5001();
	}
	
	/* 重置表单 */
	function clearM5001Form(){
		$('#salePlanSearchForm').form('reset');
	}

    function getM5001SelectionsIds(){
    	var salePlanList = $("#salePlanList");
    	var sels = salePlanList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].bsId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM5001 = [{
        text:'详情维护',
        iconCls:'icon-search',
        handler:function(){
        	var ids = getM5001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录查看!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多查看一条记录!');
        		return ;
        	}
        	var dataG = $("#salePlanList").datagrid("getSelections")[0];
  	    	var params = {"bsId":ids};
          	$.post("/salePlan/detail",params, function(data){
      			if(data.status == 200){
      				$("#salePlanDetailWindow").window({
      	        		onLoad :function(){
      	        			//回显数据
      	        			dataG.arrDate = BSL.formatDate(dataG.bsArrdate); 
      	        			$("#salePlanDetailForm").form("load",dataG);
      	        			//回显详细
      	        			var values = [];  
	      	  				for (var i = 0; i < data.data.length; i++) {
	      	  	                var a = {
	      	  	                	'saleSerno' : data.data[i].saleSerno,
	     		                    'saleFlag' : data.data[i].saleFlag,
	     		                    'prodNorm' : data.data[i].prodNorm,
	     		                    'prodMaterial' : data.data[i].prodMaterial,
	     		                    'prodLength' : data.data[i].prodLength,
	     		                    'saleNum' : data.data[i].saleNum,
	     		                    'saleWeight' : data.data[i].saleWeight,
	     		                    'prodId' : data.data[i].prodId,
	     		                    'prodSumnum' : data.data[i].prodSumnum,
	     		                    'prodSumweight' : data.data[i].prodSumweight,
	     		                    'salePrice' : data.data[i].salePrice,
	     		                    'saleStatus' : data.data[i].saleStatus
	      	  	                };
	      	  	                values.push(a);				
	      	  	            }
	      	  	            $('#saleDetailList').datagrid('loadData', values);
	      	  			}
      	        	}).window("open");
      			} else {
      				$.messager.alert('提示','详情查询失败：'+data.msg);
      			}
      		});
        }
    },{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$("#salePlanAddWindow").window({
        		onLoad :function(){
        			var ids = getM5001SelectionsIds();
        			if(ids.length > 0){
        				//回显数据
        				var data = $("#salePlanList").datagrid("getSelections")[0];
            			data.priceView = BSL.formatPrice(data.price);
            			if(data.bsArrdate != "" && data.bsArrdate != null){
            				data.bsArrdateAddM5001 = BSL.formatDate(data.bsArrdate); 
            			}
            			$("#salePlanAddForm").form("load",data);
        			}
        		}
        	}).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getM5001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录编辑!');
        		return ;
        	}
        	$("#salePlanEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#salePlanList").datagrid("getSelections")[0];
        			data.priceView = BSL.formatPrice(data.price);
        			data.crtDateM5001EditDate = BSL.formatDate(data.crtDate); 
        			if(data.bsArrdate != "" && data.bsArrdate != null){
        				data.bsArrdateEditDateM5001 = BSL.formatDate(data.bsArrdate); 
        			}
        			$("#salePlanEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'刷新',
        iconCls:'icon-reload',
        handler:function(){
        	var ids = getM5001SelectionsIds();
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
    					searchM5001Form();
    				});
    			} else {
    				$.messager.alert('提示','刷新失败：'+data.msg);
    			}
    		});
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getM5001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录删除!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多删除一条记录!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除本条记录？',function(r){
        	    if (r){
        	    	var params = {"bsId":ids};
                	$.post("/salePlan/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除成功!',undefined,function(){
            					searchM5001Form();
            				});
            			} else {
            				$.messager.alert('提示','删除失败：'+data.msg);
            			}
            		});
        	    }
        	});
        }
    },{
        text:'销售出库单PDF打印',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM5001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录PDF打印!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录PDF打印!');
        		return ;
        	}
        	var data = $("#salePlanList").datagrid("getSelections")[0];
        	$.messager.confirm('确认','是否需要PDF打印？',function(r){
				if (r){
        	    	var mapParam = new Map();
        			mapParam.set("url","/print/exportPdf");
        			mapParam.set("planId",ids);
        			mapParam.set("bsFlag",data.bsFlag);
        			mapParam.set("tradeType","M5001o");
        			mapParam.set("loginUserId",$("#user_id").html());
        			BSL.toNewPagePDF(mapParam);
        	    }
			});
        }
    },{
        text:'销售出库单详单PDF打印',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM5001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录PDF打印!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录PDF打印!');
        		return ;
        	}
        	$.messager.confirm('确认','是否需要PDF打印？',function(r){
				if (r){
        	    	var mapParam = new Map();
        			mapParam.set("url","/print/exportPdf");
        			mapParam.set("planId",ids);
        			mapParam.set("tradeType","M5001");
        			mapParam.set("loginUserId",$("#user_id").html());
        			BSL.toNewPagePDF(mapParam);
        	    }
			});
        }
    },{
        text:'销售出库单PDF选择打印',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM5001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录PDF选择打印!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录PDF选择打印!');
        		return ;
        	}
        	var info = $("#salePlanList").datagrid("getSelections")[0];
        	var prodSourceCompany = "";
        	if(info.bsFlag == "2"){
        		prodSourceCompany = "半成品库";
        	}else if(info.bsFlag == "3"){
        		prodSourceCompany = "成品库";
        	}else if(info.bsFlag == "8" || info.bsFlag == "9"){
        		prodSourceCompany = "委外仓";
        	}
        	$("#printChooseWindow").window({
        		onLoad :function(){
        			//回显数据
        			$("#saleProdSearchByPlanForm").form("load",{"prodOutPlan":ids,"bsFlag":info.bsFlag,"prodSourceCompany":prodSourceCompany});
        			var params = {"prodOutPlan":ids,"page":"1","rows":"30"};
        			$.post("/saleProdByPlan/outProds",params, function(data){
        				if(data.status == 200){
        		            $('#saleProdByPlanList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
        				}else{
        					$.messager.alert('提示',data.msg);
        				}
        			});
        		}
        	}).window("open");
        	
        }
    },{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#salePlanList").datagrid("getData").className;
			var methodName = $("#salePlanList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM5001').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM5001').val();
			}
			//获取表头信息
			var header = $("#salePlanList").datagrid("options").columns[0];
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
			mapParam.set("excelName","销售出库通知单制单信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("bsId",$('#bsIdM5001').val());
			mapParam.set("bsFlag",$('#bsFlagM5001').combobox("getValue"));
			mapParam.set("bsStatus",$('#bsStatusM5001').combobox("getValue"));
			mapParam.set("bsOrderNo",$('#bsOrderNoM5001').val());
			mapParam.set("startDate",$('#startDateM5001').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM5001').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>