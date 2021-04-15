<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="卷板库存台账信息查询" style="padding:10px 10px 10px 10px">
		<form id="rawSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		            <td width="120" align="right">原料物料编码:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM1003" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="prodLuno" id="prodLunoM1003" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		       		<td width="120" align="right">卷板状态:</td>
		            <td width="210" align="right">
		            	<select name="prodStatus" id="prodStatusM1003" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodStatusList}" var="a">
			          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
			         		</c:forEach>
						</select>
		            </td>
		        </tr>
		       <tr>
		            <td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM1003"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodMaterialList}" var="a">
			          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
			         		</c:forEach>
						</select>
		            </td>
		       		<td width="120" align="right">规格:</td>
		            <td width="210" align="right">
		            	<input name="prodNorm" id="prodNormM1003"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">出库指令号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutPlan" id="prodOutPlanM1003"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">卷板来源:</td>
		            <td width="210" align="right">
		            	<select name="prodSource" id="prodSourceM1003"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodSourceList}" var="a">
			          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
			         	  </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">厂家钢卷号:</td>
		            <td width="210" align="right">
		            	<input name="prodOrirawid" id="prodOrirawidM1003"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		       <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM1003" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM1003" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM1003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM1003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM1003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM1003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM1003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM1003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM1003Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM1003Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="rawList" title="卷板库存台账信息管理"  style="height:570px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM1003,collapsible:true,pagination:true,url:'/raw/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM1003,pageSize:30,toolbar:toolbarM1003">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:130,sortable:true">原料物料编码</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">物料名称</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodLength',width:100,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	        	<th data-options="field:'prodOutPlan',width:100,sortable:true">出库指令号</th>
	            <th data-options="field:'prodLevel',width:110,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodRecordWeight',width:125,sortable:true">原料来料重量/吨</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
	            <th data-options="field:'prodSource',width:120,formatter:BSL.formatProdSource,sortable:true">产品来源</th>
	            <th data-options="field:'prodOutCarno',width:100,sortable:true">发货车次流水</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodCustomer',width:100,sortable:true">客户</th>
	            <th data-options="field:'prodCheckuser',width:70,sortable:true">录入人</th>
	            <th data-options="field:'crtDate',width:150,formatter:BSL.formatFullDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'prodOutDate',width:150,formatter:BSL.formatFullDateTime,sortable:true">出库日期</th>
	            <th data-options="field:'updDate',width:150,formatter:BSL.formatFullDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:226,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="rawCheckAddWindow" class="easyui-window" title="新增入库" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/raw/M1003-add'" style="width:810px;height:380px;padding:10px;">
	</div>
	<div id="rawEditWindow" class="easyui-window" title="编辑入库单录入信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/raw/M1003-edit'" style="width:810px;height:410px;padding:10px;">
	</div>
	<div id="rawReAddWindow" class="easyui-window" title="剩余材料重新入库" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/raw/M1003-readd'" style="width:810px;height:250px;padding:10px;">
	</div>
	<div id="rawSMAddAlertWindow" class="easyui-window" title="扫码新增入库单录入信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/raw/M1003-AddSM'" style="width:520px;height:570px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM1003(){
		var queryParams = $('#rawList').datagrid('options').queryParams;
		
		queryParams.prodId = $('#prodIdM1003').val();
		queryParams.prodLuno = $('#prodLunoM1003').val();
		queryParams.prodOrirawid = $('#prodOrirawidM1003').val();
		queryParams.prodStatus = $('#prodStatusM1003').combobox("getValue");
		queryParams.prodMaterial = $('#prodMaterialM1003').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM1003').val();
		queryParams.prodOutPlan = $('#prodOutPlanM1003').val();
		queryParams.prodSource = $('#prodSourceM1003').combobox("getValue");
		queryParams.startDate = $('#startDateM1003').datebox("getValue");
		queryParams.endDate = $('#endDateM1003').datebox("getValue");
	}

	//排序查询
	function sortSerachM1003(sort,order){
		$("#sortM1003").textbox('setValue',sort);
		$("#orderM1003").textbox('setValue',order);
		searchM1003Form();
	}

	//查询按钮
	function searchM1003Form(){
		//page页码
		var page = $("#rawList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#rawList").datagrid('options').pageSize; 
		$("#pageM1003").textbox('setValue',page);
		$("#rowsM1003").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/raw/listByCriteria",$("#rawSearchForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#rawList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM1003").textbox('setValue',data.className);
				$("#methodNameM1003").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM1003();
	}
	
	/* 重置表单 */
	function clearM1003Form(){
		$('#rawSearchForm').form('reset');
	}

	/*获取表格卷板号*/
    function getM1003SelectionsIds(){
    	var receiptList = $("#rawList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM1003 = [{
        text:'新增入库',
        iconCls:'icon-add',
        handler:function(){
        	var ids = getM1003SelectionsIds();
        	var data = $("#rawList").datagrid("getSelections")[0];
        	$("#rawCheckAddWindow").window({
        		onLoad :function(){
        			//回显数据
        			if(data == null){
        				data = {"prodRuc":'0'};
        			}else if(data.prodRuc == null || data.prodRuc == ''){
        				data.prodRuc = '0';
        			}
        			$("#rawCheckAddForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
    	text:'扫码新增',
        iconCls:'icon-man',
        handler:function(){
         	$("#rawSMAddAlertWindow").window({
         		onLoad :function(){
         			//给焦点
 					$("#preRawSM1003").textbox('textbox').focus();
         		},
				onBeforeClose:function(){ 
        			//关闭时删除计时器，解决快速计时的bug
 					clearInterval(timeM1003);
 					//关闭时获取内容
 					var rawInfo = $("#preRawSM1003").textbox('getValue');
 					if(rawInfo == ''){
    					$.messager.alert('提示', '扫码获取数据失败');
    					return;
    				}else{
	       				var params = {'rawInfo':rawInfo};
            	    	//格式化数组
            	    	$.post("/raw/rawAddSMDeal", params, function(data) {
            	    		if (data.status == 200) {
    							 $("#rawCheckAddWindow").window({
    				        		onLoad :function(){
    				        			 $('#rawCheckAddForm').form("load",data.data);
    				        		}
    				        	}).window("open"); 
    						} else {
    						 	$.messager.alert('提示', data.msg);
    						} 
   						});
    				}
				}
 	        }).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getM1003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录编辑!');
        		return ;
        	}
        	var data = $("#rawList").datagrid("getSelections")[0];
        	/* if(data.prodStatus != "1"){
        		$.messager.alert('提示','本交易只允许修改入库的卷板信息!');
        		return ;
        	} */
        	var user = $("#user_id").html(); 
        	if(user != '000000'){
        		$.messager.alert('提示','只有超级管理员才允许修改产品信息!');
        		return ;
        	}
        	$("#rawEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			data.crtDateM1003Edit = BSL.formatDate(data.crtDate)
        			$("#rawEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'剩余原料重新入库',
        iconCls:'icon-add',
        handler:function(){
        	$("#rawReAddWindow").window({
        		onLoad :function(){
        			
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getM1003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录删除!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多删除一条记录!');
        		return ;
        	}
        	var data = $("#rawList").datagrid("getSelections")[0];
        	if(data.prodStatus != "1"){
	    		$.messager.alert('提示','只有在库状态的产品才允许删除!');
	    		return ;
	    	}
        	var user = $("#user_id").html(); 
        	if(user != '000000'){
        		$.messager.alert('提示','只有超级管理员才允许删除产品信息!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除本条记录？',function(r){
        	    if (r){
        	    	var params = {"prodId":ids};
                	$.post("/raw/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除成功!',undefined,function(){
            					searchM1003Form();
            				});
            			} else {
            				$.messager.alert('提示', data.msg);
            			}
            		});
        	    }
        	});
        }
    },{
        text:'补打PDF标签文件',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM1003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录补打PDF!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录补打PDF!');
        		return ;
        	}
        	var data = $("#rawList").datagrid("getSelections")[0];
        	if(data.prodStatus == "0"){
        		$.messager.alert('提示','未入库的卷板不支持补打PDF!');
        		return ;
        	}
        	$.messager.confirm('确认','是否需要补打PDF标签文件？',function(r){
				if (r){
					var prodId = data.prodId;
        	    	var mapParam = new Map();
        			mapParam.set("url","/import/importPdf");
        			mapParam.set("prodId",prodId);
        			mapParam.set("tradeType","M1003");
        			BSL.toNewPagePDF(mapParam);
        	    }
			});
        }
    },{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#rawList").datagrid("getData").className;
			var methodName = $("#rawList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM1003').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM1003').val();
			}
			//获取表头信息
			var header = $("#rawList").datagrid("options").columns[0];
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
			mapParam.set("excelName","卷板库存台账信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","10000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("prodId",$('#prodIdM1003').val());
			mapParam.set("prodLuno",$('#prodLunoM1003').val());
			mapParam.set("prodOrirawid",$('#prodOrirawidM1003').val());
			mapParam.set("prodStatus",$('#prodStatusM1003').combobox("getValue"));
			mapParam.set("prodMaterial",$('#prodMaterialM1003').combobox("getValue"));
			mapParam.set("prodNorm",$('#prodNormM1003').val());
			mapParam.set("prodOutPlan",$('#prodOutPlanM1003').val());
			mapParam.set("prodSource",$('#prodSourceM1003').combobox("getValue"));
			mapParam.set("startDate",$('#startDateM1003').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM1003').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>