<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="外协厂待处理品库存台账信息查询" style="padding:10px 10px 10px 10px">
		<form id="dclProdDealSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">生产指令号:</td>
		            <td width="210" align="right">
		            	<input name="prodPlanNo" id="prodPlanNoM3102" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">待处理品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM3102" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="prodLuno" id="prodLunoM3102" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">父级盘号:</td>
		            <td width="210" align="right">
		            	<input name="prodParentNo" id="prodParentNoM3102" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		       <tr>
		       		<td width="120" align="right">待处理品状态:</td>
		            <td width="210" align="right">
		            	<select name="prodStatus" id="prodStatusM3102" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${dclStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	 </c:forEach>
						</select>
		            </td>
		       		<td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM3102" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
						</select>
					</td>
		        </tr>
		        <tr>
		            <td width="120" align="right">规格:</td>
		            <td width="210" align="right">
	            		<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            	</td>
	            	<td width="120" align="right">接收通知单号:</td>
		            <td width="210" align="right">
		            	<input name="prodFhck" id="prodFhckM3102" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		         <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM3102" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM3102" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM3102" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM3102" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM3102" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM3102" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM3102" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM3102" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3102Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3102Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="dclProdDealInfoList" title="待处理品库存台账信息管理"  style="height:600px"
	       data-options="singleSelect:false,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM3102,collapsible:true,pagination:true,url:'/dclDeal/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM3102,pageSize:30,toolbar:toolbarM3102">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	            <th data-options="field:'prodId',width:180,sortable:true">待处理品编号</th>
	        	<th data-options="field:'prodPlanNo',width:120,sortable:true">生产指令号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">物料名称</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodRelWeight',width:105,sortable:true">重量/吨</th>
	            <th data-options="field:'prodNum',width:125,sortable:true">单包支数</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodFhck',width:120,sortable:true">接收通知单号</th>
	            <th data-options="field:'prodLevel',width:110,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodParentNo',width:120,sortable:true">父级盘号</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'prodInputuser',width:70,sortable:true">录入人</th>
	            <th data-options="field:'crtDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'updDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="dclProdDealWindow" class="easyui-window" title="待处理品处理" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/dclProd/M3102-deal'" style="width:780px;height:340px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM3102(){
		var queryParams = $('#dclProdDealInfoList').datagrid('options').queryParams;
		queryParams.prodPlanNo = $('#prodPlanNoM3102').val();
		queryParams.prodId = $('#prodIdM3102').val();
		queryParams.prodLuno = $('#prodLunoM3102').val();
		queryParams.prodParentNo = $('#prodParentNoM3102').val();
		queryParams.prodStatus = $('#prodStatusM3102').combobox("getValue");
		queryParams.prodMaterial = $('#prodMaterialM3102').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM3102').val();
		queryParams.prodFhck = $('#prodFhckM3102').val();
		queryParams.startDate = $('#startDateM3102').datebox("getValue");
		queryParams.endDate = $('#endDateM3102').datebox("getValue");
	}

	//排序查询
	function sortSerachM3102(sort,order){
		$("#sortM3102").textbox('setValue',sort);
		$("#orderM3102").textbox('setValue',order);
		searchM3102Form();
	}

	//查询按钮
	function searchM3102Form(){
		//page页码
		var page = $("#dclProdDealInfoList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#dclProdDealInfoList").datagrid('options').pageSize; 
		$("#pageM3102").textbox('setValue',page);
		$("#rowsM3102").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/dclDeal/listByCriteria",$("#dclProdDealSearchForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#dclProdDealInfoList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM3102").textbox('setValue',data.className);
				$("#methodNameM3102").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM3102();
	}
	
	/* 重置表单 */
	function clearM3102Form(){
		$('#dclProdDealSearchForm').form('reset');
	}

    function getM3102SelectionsIds(){
    	var receiptList = $("#dclProdDealInfoList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM3102 = [{
        text:'处理',
        iconCls:'icon-add',
        handler:function(){
        	var ids = getM3102SelectionsIds();
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录处理!');
        		return ;
        	}
        	var data = {};
        	if(ids.length > 0){
        		data = $("#dclProdDealInfoList").datagrid("getSelections")[0];
            	if(data.prodStatus != "1"){
    	    		$.messager.alert('提示','只有已入库的待处理品才能处理!');
    	    		return ;
    	    	}
        		data.prodOriId = data.prodId;
        		data.prodBc = '6';
        		data.prodRuc = '6';
            	data.prodLength = '';
            	data.prodName = '';
            	data.prodRelWeight = '';
        	}
			$("#dclProdDealWindow").window({
        		onLoad :function(){
        			//回显数据
        			$("#prodDealForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'处理完成',
        iconCls:'icon-ok',
        handler:function(){
        	var ids = getM3102SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条或多条记录处理完成!');
        		return ;
        	}
        	$.messager.confirm('确认','确定完成这些记录？',function(r){
        	    if (r){
        	    	var arrays = ids.split(',');
        	    	var params = {'arrays':arrays,'user':$("#user_id").html()};
        	    	//格式化数组
        	    	var ps = $.param(params, true);
                	$.post("/dclDeal/dealFinAll",ps, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','批量更新成功!',undefined,function(){
            					searchM3102Form();
            				});
            			} else {
            				$.messager.alert('提示','批量完成失败：'+data.msg);
            			}
            		});
        	    }
        	});
        }
    },{
        text:'补打PDF标签文件',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM3102SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录补打PDF!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录补打PDF!');
        		return ;
        	}
        	var data = $("#dclProdDealInfoList").datagrid("getSelections")[0];
        	$.messager.confirm('确认','是否需要补打PDF标签文件？',function(r){
				if (r){
					var prodId = data.prodId;
        	    	var mapParam = new Map();
        			mapParam.set("url","/import/importPdf");
        			mapParam.set("prodId",prodId);
        			mapParam.set("tradeType","M3101");
        			BSL.toNewPagePDF(mapParam);
        	    }
			});
        }
    },{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#dclProdDealInfoList").datagrid("getData").className;
			var methodName = $("#dclProdDealInfoList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM3102').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM3102').val();
			}
			//获取表头信息
			var header = $("#dclProdDealInfoList").datagrid("options").columns[0];
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
			mapParam.set("excelName","待处理品库存台账信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","10000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("prodPlanNo",$('#prodPlanNoM3102').val());
			mapParam.set("prodId",$('#prodIdM3102').val());
			mapParam.set("prodLuno",$('#prodLunoM3102').val());
			mapParam.set("prodParentNo",$('#prodParentNoM3102').val());
			mapParam.set("prodStatus",$('#prodStatusM3102').combobox("getValue"));
			mapParam.set("prodMaterial",$('#prodMaterialM3102').combobox("getValue"));
			mapParam.set("prodNorm",$('#prodNormM3102').val());
			mapParam.set("prodFhck",$('#prodFhckM3102').val());
			mapParam.set("startDate",$('#startDateM3102').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM3102').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>