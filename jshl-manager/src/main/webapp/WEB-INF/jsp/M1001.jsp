<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="原料入库通知单信息查询" style="padding:10px 10px 10px 10px">
		<form id="receiptSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">原料入库通知单号:</td>
		            <td width="210" align="right">
		            	<input name="bsId" id="bsIdM1001" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">通知单类别: </td>
		            <td width="210" align="right">
		            	<select name="bsFlag" id="bsFlagM1001" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${bsFlagSList}" var="a">
			          		<option value="${a.enumKey}">${a.enumKey}-${a.enumValue}</option>
			         	  </c:forEach>
						</select>
		            </td>	           
		        </tr>
		       <tr>
		            <td width="120" align="right">通知单状态:</td>
		            <td width="210" align="right">
		            	<select name="bsStatus" id="bsStatusM1001" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${bsStatusBList}" var="a">
			          		<option value="${a.enumKey}">${a.enumKey}-${a.enumValue}</option>
			         	  </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM1001" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM1001" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM1001" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM1001" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM1001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM1001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM1001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM1001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" id="searchM1001Form" onclick="searchM1001Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM1001Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="receiptList" title="原料入库通知单信息管理"  style="height:620px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM1001,collapsible:true,pagination:true,url:'/receipt/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM1001,pageSize:30,toolbar:toolbarM1001">
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
	<div id="receiptAddWindow" class="easyui-window" title="新增原料入库通知单" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/receipt/M1001-add'" style="width:780px;height:310px;padding:10px;">
	</div>
	<div id="receiptEditWindow" class="easyui-window" title="编辑原料入库通知单" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/receipt/M1001-edit'" style="width:780px;height:340px;padding:10px;">
	</div>
	<div id="receiptDetailInfoWindow" class="easyui-window" title="原料入库通知单详情" data-options="modal:true,closed:true,iconCls:'icon_add',href:'/plan/M1001-detail'" style="width:1120px;height:640px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM1001(){
		var queryParams = $('#receiptList').datagrid('options').queryParams;
		
		queryParams.bsId = $('#bsIdM1001').val();
		queryParams.bsFlag = $('#bsFlagM1001').combobox("getValue");
		queryParams.bsStatus = $('#bsStatusM1001').combobox("getValue");
		queryParams.startDate = $('#startDateM1001').datebox("getValue");
		queryParams.endDate = $('#endDateM1001').datebox("getValue");
	}
	
	//排序查询
	function sortSerachM1001(sort,order){
		$("#sortM1001").textbox('setValue',sort);
		$("#orderM1001").textbox('setValue',order);
		searchM1001Form();
	}

	//查询按钮
	function searchM1001Form(){
		//page页码
		var page = $("#receiptList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#receiptList").datagrid('options').pageSize; 
		$("#pageM1001").textbox('setValue',page);
		$("#rowsM1001").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$('#searchM1001Form').linkbutton('disable');
		$.post("/receipt/listByCriteria",$("#receiptSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#receiptList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM1001").textbox('setValue',data.className);
				$("#methodNameM1001").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
			$('#searchM1001Form').linkbutton('enable');
		});
		 onBeforeLoadM1001();
	}
	
	/* 重置表单 */
	function clearM1001Form(){
		$('#receiptSearchForm').form('reset');
	}

    function getM1001SelectionsIds(){
    	var receiptList = $("#receiptList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].bsId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM1001 = [{
        text:'详情',
        iconCls:'icon-search',
        handler:function(){
        	var ids = getM1001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录查看!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多查看一条记录!');
        		return ;
        	}
        	var dataG = $("#receiptList").datagrid("getSelections")[0];
  	    	var params = {"bsId":ids};
          	$.post("/receipt/detail",params, function(data){
      			if(data.status == 200){
      				$("#receiptDetailInfoWindow").window({
      	        		onLoad :function(){
      	        			//回显数据
      	        			dataG.arrDate = BSL.formatDate(dataG.bsArrdate); 
      	        			$("#receiptDetailForm").form("load",dataG);
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
	      	  	            $('#receiptDetailInfoList').datagrid('loadData', values);
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
        	$("#receiptAddWindow").window({
        		onLoad :function(){
        			var ids = getM1001SelectionsIds();
        			if(ids.length > 0){
	        			//回显数据
	        			var data = $("#receiptList").datagrid("getSelections")[0];
	        			data.priceView = BSL.formatPrice(data.price);
	        			if(data.bsArrdate != "" && data.bsArrdate != null){
	        				data.bsArrdateAdd = BSL.formatDate(data.bsArrdate); 
	        			}
	        			$("#receiptAddForm").form("load",data);
        			}
        		}
        	}).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getM1001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录编辑!');
        		return ;
        	}
        	
        	$("#receiptEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#receiptList").datagrid("getSelections")[0];
        			data.priceView = BSL.formatPrice(data.price);
        			data.crtDateM1001EditDate = BSL.formatDate(data.crtDate);
        			if(data.bsArrdate != "" && data.bsArrdate != null){
        				data.bsArrdateEditDate = BSL.formatDate(data.bsArrdate); 
        			}
        			$("#receiptEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getM1001SelectionsIds();
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
                	$.post("/receipt/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除成功!',undefined,function(){
            					searchM1001Form();
            				});
            			} else {
            				$.messager.alert('提示','删除失败：'+data.msg);
            			}
            		});
        	    }
        	});
        }
    },{
        text:'PDF打印',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM1001SelectionsIds();
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
        			mapParam.set("tradeType","M1001");
        			mapParam.set("loginUserId",$("#user_id").html());
        			BSL.toNewPagePDF(mapParam); 
        			//window.open("@Url.Action('/pdf')");
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
				className = $('#classNameM1001').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM1001').val();
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
			mapParam.set("excelName","原料入库通知单制单信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("bsId",$('#bsIdM1001').val());
			mapParam.set("bsFlag",$('#bsFlagM1001').combobox("getValue"));
			mapParam.set("bsStatus",$('#bsStatusM1001').combobox("getValue"));
			mapParam.set("startDate",$('#startDateM1001').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM1001').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>