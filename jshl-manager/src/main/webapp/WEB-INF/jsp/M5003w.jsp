<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="外协厂销售出库通知单执行信息查询" style="padding:10px 10px 10px 10px">
		<form id="saleWxPlanDoSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售出库通知单号:</td>
		            <td width="210" align="right">
		            	<input name="bsId" id="bsIdM5003w" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">通知单类别: </td>
		            <td width="210" align="right">
		            	<select name="bsFlag" id="bsFlagM5003w" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
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
		            	<select name="bsStatus" id="bsStatusM5003w" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${bsStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">订单号:</td>
		            <td width="210" align="right">
		            	<input name="bsOrderNo" id="bsOrderNoM5003w" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM5003w" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM5003w" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM5003w" class="easyui-textbox" type="text" data-options="required:false"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM5003w" class="easyui-textbox" type="text" data-options="required:false"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM5003w" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM5003w" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM5003w" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM5003w" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>		        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM5003wForm()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5003wForm()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="saleWxPlanDoList" title="销售出库通知单执行信息管理"  style="height:620px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM5003w,collapsible:true,pagination:true,url:'/doSalePlan/listByCriteriaWx',method:'post',onBeforeLoad:onBeforeLoadM5003w,pageSize:30,toolbar:toolbarM5003w">
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
	<div id="salePlanDoDetailWindowWx" class="easyui-window" title="销售出库通知单详细" data-options="modal:true,closed:true,href:'/doSalePlan/M5003w-detail'" style="width:1120px;height:630px;padding:10px;">
	</div>
	<div id="saleAllFbWindowWx" class="easyui-window" title="销售出库通知单整车复磅" data-options="modal:true,closed:true,href:'/doSalePlan/M5003w-fb'" style="width:850px;height:200px;padding:10px;">
	</div>
	<div id="printChooseWindowWx" class="easyui-window" title="打印" data-options="modal:true,closed:true,href:'/doSalePlan/M5003w-print'" style="width:1250px;height:730px;padding:10px;">
	</div>
	<div id="saleChooseWindowM5003w" class="easyui-window" title="选择发货" data-options="modal:true,closed:true,href:'/doSalePlan/M5003w-sale'" style="width:1250px;height:700px;padding:10px;">
	</div>
	<div id="saleWasteWindowM5003w" class="easyui-window" title="废品选择发货" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/doSalePlan/M5003w-salewaste'" style="width:1120px;height:660px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM5003w(){
		var queryParams = $('#saleWxPlanDoList').datagrid('options').queryParams;
		queryParams.bsId = $('#bsIdM5003w').val();
		queryParams.bsFlag = $('#bsFlagM5003w').combobox("getValue");
		queryParams.bsStatus = $('#bsStatusM5003w').combobox("getValue");
		queryParams.bsOrderNo = $('#bsOrderNoM5003w').val();
		queryParams.startDate = $('#startDateM5003w').datebox("getValue");
		queryParams.endDate = $('#endDateM5003w').datebox("getValue");
	}

	//排序查询
	function sortSerachM5003w(sort,order){
		$("#sortM5003w").textbox('setValue',sort);
		$("#orderM5003w").textbox('setValue',order);
		searchM5003wForm();
	}

	//查询按钮
	function searchM5003wForm(){
		//page页码
		var page = $("#saleWxPlanDoList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#saleWxPlanDoList").datagrid('options').pageSize; 
		$("#pageM5003w").textbox('setValue',page);
		$("#rowsM5003w").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#saleWxPlanDoSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/doSalePlan/listByCriteriaWx",$("#saleWxPlanDoSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#saleWxPlanDoList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM5003w").textbox('setValue',data.className);
				$("#methodNameM5003w").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM5003w();
	}
	
	/* 重置表单 */
	function clearM5003wForm(){
		$('#saleWxPlanDoSearchForm').form('reset');
	}

    function getM5003wSelectionsIds(){
    	var saleWxPlanDoList = $("#saleWxPlanDoList");
    	var sels = saleWxPlanDoList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].bsId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM5003w = [{
        text:'详情',
        iconCls:'icon-search',
        handler:function(){
        	var ids = getM5003wSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录查看!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多查看一条记录!');
        		return ;
        	}
        	var dataG = $("#saleWxPlanDoList").datagrid("getSelections")[0];
  	    	var params = {"bsId":ids};
          	$.post("/doSalePlan/detail",params, function(data){
      			if(data.status == 200){
      				$("#salePlanDoDetailWindowWx").window({
      	        		onLoad :function(){
      	        			//回显数据
      	        			dataG.arrDate = BSL.formatDate(dataG.bsArrdate); 
      	        			$("#salePlanDoDetailFormWx").form("load",dataG);
      	        			//回显详细
      	        			var values = [];  
	      	  				for (var i = 0; i < data.data.length; i++) {
	      	  	                var a = {
	      	  	                	'saleSerno' : data.data[i].saleSerno,
	     		                    'saleFlag' : data.data[i].saleFlag,
	     		                    'saleNum' : data.data[i].saleNum,
	     		                    'saleWeight' : data.data[i].saleWeight,
	     		                    'prodId' : data.data[i].prodId,
	     		                    'prodNorm' : data.data[i].prodNorm,
	     		                    'prodMaterial' : data.data[i].prodMaterial,
	     		                    'prodLength' : data.data[i].prodLength,
	     		                    'prodSumnum' : data.data[i].prodSumnum,
	     		                    'prodSumweight' : data.data[i].prodSumweight,
	     		                    'saleStatus' : data.data[i].saleStatus
	      	  	                };
	      	  	                values.push(a);				
	      	  	            }
	      	  	            $('#saleDetailListM5003w').datagrid('loadData', values);
	      	  			}
      	        	}).window("open");
      			} else {
      				$.messager.alert('提示','详情查询失败：'+data.msg);
      			}
      		});
        }
    },{
        text:'整车复磅',
        iconCls:'icon-man',
        handler:function(){
        	var ids = getM5003wSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条通知单复磅!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多复磅一条通知单!');
        		return ;
        	}
        	//获取已出库重量
        	var dataG = $("#saleWxPlanDoList").datagrid("getSelections")[0];
        	var params = {'bsId':dataG.bsId};
        	$.post("/doSalePlan/getOutWeight",params, function(data){
				if(data.status == 200){
		            dataG.bsOutWeight = data.data;
		            //弹出复磅界面
		            $("#saleAllFbWindowWx").window({
		        		onLoad :function(){
		        			//回显数据
		        			data.priceView = BSL.formatPrice(data.price);
		        			$("#saleAllFbFormWx").form("load",dataG);
		        		}
		        	}).window("open");
		           
				}else{
					$.messager.alert('提示',data.msg);
				}
			});
        }
    },{
        text:'选择发货',
        iconCls:'icon-ok',
        handler:function(){
        	var ids = getM5003wSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录选择发货!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多选择发货一条记录!');
        		return ;
        	}
        	//记录发货人员信息
        	var date = $("#saleWxPlanDoList").datagrid("getSelections")[0];
        	if(date.bsFlag == '4' || date.bsFlag == '9' || date.bsFlag == '11'){
        		$("#saleWasteWindowM5003w").window({
        			onLoad :function(){
	        	  	    var params = {"salePlanId":ids,"page":"1","rows":"30"};
	        			$("#saleWasteM5003wForm").form("load",params);
	        			$.post("/saleProdByPlan/waitSaleWassteGroups",params, function(data){
	        				if(data.status == 200){
	        		           	$('#saleWasteM5003wList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	        				}else{
	        					$.messager.alert('提示',data.msg);
	        				}
	        			}); 
	  	  			}
		        }).window("open");
        	}else{
	        	$("#saleChooseWindowM5003w").window({
	        		onLoad :function(){
	        			//回显数据
	        			$("#saleChooseFormM5003w").form("load",{"prodOutPlan":ids,"bsFlag":date.bsFlag});
	        			var params = {"prodOutPlan":ids,"page":"1","rows":"30"};
	        			$.post("/saleProdByPlan/waitSaleProdGroups",params, function(data){
	        				if(data.status == 200){
	        		            $('#saleChooseListM5003w').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	        				}else{
	        					$.messager.alert('提示',data.msg);
	        				}
	        			});
	        		}
	        	}).window("open");
        	}
        }
    },{
        text:'强制完成',
        iconCls:'icon-lock',
        handler:function(){
        	var ids = getM5003wSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条强制完成!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多完成一条记录!');
        		return ;
        	}
        	//记录发货人员信息
    		var checkUser = $("#user_id").html();
   	    	var params = {"bsId":ids,"checkUser":checkUser};
   	    	$.messager.confirm('确认','确认强制完成',function(r){
   	    		if (r){
		           	$.post("/doSalePlan/finish",params, function(data){
		       			if(data.status == 200){
		       				$.messager.alert('提示','强制完成成功!',undefined,function(){
		       					searchM5003wForm();
		       				});
		       			} else {
		       				$.messager.alert('提示','强制完成失败：'+data.msg);
		       			}
		       		});
   	    		}
   	    	});
        }
    },{
        text:'刷新',
        iconCls:'icon-reload',
        handler:function(){
        	var ids = getM5003wSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录刷新!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录刷新!');
        		return ;
        	}
        	var data = $("#saleWxPlanDoList").datagrid("getSelections")[0];
        	if(data.bsFlag == '4' || data.bsFlag == '9'){
        		$.messager.alert('提示','废品发货不记录产品实际重量信息，无法刷新!');
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
        text:'销售出库单PDF打印',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM5003wSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录PDF打印!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录PDF打印!');
        		return ;
        	}
        	var data = $("#saleWxPlanDoList").datagrid("getSelections")[0];
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
        	var ids = getM5003wSelectionsIds();
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
        	var ids = getM5003wSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录PDF选择打印!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录PDF选择打印!');
        		return ;
        	}
        	var info = $("#saleWxPlanDoList").datagrid("getSelections")[0];
        	var prodSourceCompany = "";
        	if(info.bsFlag == "2"){
        		prodSourceCompany = "半成品库";
        	}else if(info.bsFlag == "3"){
        		prodSourceCompany = "成品库";
        	}else if(info.bsFlag == "8" || info.bsFlag == "9"){
        		prodSourceCompany = "委外仓";
        	}
        	$("#printChooseWindowWx").window({
        		onLoad :function(){
        			//回显数据
        			$("#saleProdSearchByPlanFormM5003w").form("load",{"prodOutPlan":ids,"bsFlag":info.bsFlag,"prodSourceCompany":prodSourceCompany});
        			var params = {"prodOutPlan":ids,"page":"1","rows":"30"};
        			$.post("/saleProdByPlan/outProds",params, function(data){
        				if(data.status == 200){
        		            $('#saleProdByPlanListM5003w').datagrid('loadData',  {"total":data.total,"rows":data.rows});
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
			var className = $("#saleWxPlanDoList").datagrid("getData").className;
			var methodName = $("#saleWxPlanDoList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM5003w').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM5003w').val();
			}
			//获取表头信息
			var header = $("#saleWxPlanDoList").datagrid("options").columns[0];
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
			mapParam.set("excelName","外协厂销售出库通知单执行信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("bsId",$('#bsIdM5003w').val());
			mapParam.set("bsFlag",$('#bsFlagM5003w').combobox("getValue"));
			mapParam.set("bsStatus",$('#bsStatusM5003w').combobox("getValue"));
			mapParam.set("bsOrderNo",$('#bsOrderNoM5003w').val());
			mapParam.set("startDate",$('#startDateM5003w').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM5003w').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>