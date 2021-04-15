<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="销售出库信息查询" style="padding:10px 10px 10px 10px">
		<form id="saleProdSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">销售出库单号:</td>
		            <td width="210" align="right">
		            	<input name="salePlanId" id="salePlanIdM5004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">销售计划流水:</td>
		            <td width="210" align="right">
		            	<input name="saleSerno" id="saleSernoM5004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		        <tr>
		        	<td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM5004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">产品状态:</td>
		            <td width="210" align="right">
		            	<select name="prodStatus" id="prodStatusM5004" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodStatusEList}" var="a">
			          	   	   <option value="${a.enumKey}">${a.enumValue}</option>
			         		</c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr hidden="true">
		        	<td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page1" id="pageM5004Out" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows1" id="rowsM5004Out" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort1" id="sortM5004Out" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order1" id="orderM5004Out" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM5004" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM5004" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM5004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM5004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM5004Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM5004Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="saleProdList" title="销售出库可出库产品信息管理"  style="height:320px"
	       data-options="singleSelect:false,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM5004,collapsible:true,pagination:true,url:'/saleProd/canOutProds',method:'post',onBeforeLoad:onBeforeLoadM50041,pageSize:30,toolbar:toolbarM5004">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:180,sortable:true">产品编号</th>
	        	<th data-options="field:'prodType',width:100,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'prodPlanNo',width:140,sortable:true">单号/指令号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">产品名称</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
	            <th data-options="field:'prodOutWeight',width:125,sortable:true">销售复磅重量/吨</th>
	            <th data-options="field:'prodOutPlan',width:100,sortable:true">销售出库单号</th>
	            <th data-options="field:'prodSaleSerno',width:100,sortable:true">销售计划流水</th>
	        	<th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodLevel',width:90,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'crtDate',width:100,formatter:BSL.formatDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'updDate',width:150,formatter:BSL.formatDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	 <table class="easyui-datagrid" id="saleProdListOut" title="销售出库已出库信息管理"  style="height:320px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM5004Out,collapsible:true,pagination:true,url:'/saleProd/outProds',method:'post',onBeforeLoad:onBeforeLoadM50042,pageSize:30,toolbar:toolbarM5004Out">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:180,sortable:true">产品编号</th>
	        	<th data-options="field:'prodType',width:80,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'prodPlanNo',width:140,sortable:true">单号/指令号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">产品名称</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
				<th data-options="field:'prodOutWeight',width:125,sortable:true">销售复磅重量/吨</th>
	            <th data-options="field:'prodOutPlan',width:100,sortable:true">销售出库单号</th>
	            <th data-options="field:'prodSaleSerno',width:100,sortable:true">销售计划流水</th>
	        	<th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodLevel',width:90,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'crtDate',width:100,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="saleProdOutWindow" class="easyui-window" title="产品发货出库" data-options="modal:true,closed:true,iconCls:'saleProd-edit',href:'/saleProd/M5004-out'" style="width:780px;height:320px;padding:10px;">
	</div>
	<div id="saleProdFbWindow" class="easyui-window" title="复磅" data-options="modal:true,closed:true,iconCls:'saleProd-edit',href:'/saleProd/M5004-fb'" style="width:780px;height:320px;padding:10px;">
	</div>
	<div id="saleProdOutputSMAlertWindow" class="easyui-window"  title="等待扫码..." data-options="modal:true,closed:true,maximizable:false,minimizable:false,collapsible:false,iconCls:'icon-man',href:'/saleProd/M5004-SMAlert'" style="width:520px;height:570px;padding:10px;">
	</div>
	<div id="saleProdSmDetailWindow" class="easyui-window" title="扫码产品列表" data-options="modal:true,closed:true,iconCls:'saleProd-edit',href:'/saleProd/M5004-SmDetail'" style="width:780px;height:620px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM50041(){
		
		var queryParams = $('#saleProdList').datagrid('options').queryParams;
		queryParams.salePlanId = $('#salePlanIdM5004').val();
		queryParams.saleSerno = $('#saleSernoM5004').val();
		queryParams.prodId = $('#prodIdM5004').val();
		queryParams.prodStatus = $('#prodStatusM5004').combobox("getValue");
		
	}
	
	function onBeforeLoadM50042(){
		
		var queryParams = $('#saleProdListOut').datagrid('options').queryParams;
		queryParams.salePlanId = $('#salePlanIdM5004').val();
		queryParams.saleSerno = $('#saleSernoM5004').val();
		queryParams.prodId = $('#prodIdM5004').val();
		queryParams.prodStatus = $('#prodStatusM5004').combobox("getValue");
		
	}

	//排序查询
	function sortSerachM5004(sort,order){
		$("#sortM5004").textbox('setValue',sort);
		$("#orderM5004").textbox('setValue',order);
		searchM5004Form();
	}
	function sortSerachM5004Out(sort,order){
		$("#sortM5004Out").textbox('setValue',sort);
		$("#orderM5004Out").textbox('setValue',order);
		searchM5004Form();
	}

	//查询按钮
	function searchM5004Form(){
		//page页码
		var page = $("#saleProdList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#saleProdList").datagrid('options').pageSize; 
		$("#pageM5004").textbox('setValue',page);
		$("#rowsM5004").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#saleProdSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/saleProd/canOutProds",$("#saleProdSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#saleProdList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		
		//page页码
		var page1 = $("#saleProdListOut").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows1 = $("#saleProdListOut").datagrid('options').pageSize; 
		$("#pageM5004Out").textbox('setValue',page1);
		$("#rowsM5004Out").textbox('setValue',rows1);
		//ajax的post方式提交表单
		//$("#saleProdSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/saleProd/outProds",$("#saleProdSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#saleProdListOut').datagrid('loadData',  {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		
		onBeforeLoadM50041();
		onBeforeLoadM50042();
	}
	
	/* 重置表单 */
	function clearM5004Form(){
		$('#saleProdSearchForm').form('reset');
	}

    function getM5004SelectionsIds(){
    	var saleProdList = $("#saleProdList");
    	var sels = saleProdList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    function getM5004OutSelectionsIds(){
    	var saleProdList = $("#saleProdListOut");
    	var sels = saleProdList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM5004 = [{
    	text:'产品扫码出库',
        iconCls:'icon-man',
        handler:function(){
         	if($("#saleSernoM5004").textbox('getValue') == '' && $("#salePlanIdM5004").textbox('getValue')==''){
         		$.messager.alert('提示', '销售出库单号和销售计划流水不能同时为空！');
         		return;
         	}
         	$("#saleProdOutputSMAlertWindow").window({
         		onLoad :function(){
         			//给焦点
 					$("#prodIdSM5004").textbox('textbox').focus();
         		},
				onBeforeClose:function(){ 
        			//关闭时删除计时器，解决快速计时的bug
 					clearInterval(timeM5004);
 					//关闭时获取多个产品编号
 					var prodIds = $("#prodIdSM5004").textbox('getValue');
 					if(prodIds == ''){
    					$.messager.alert('提示', '扫码获取数据失败');
    					return;
    				}
 					//处理数据（将数据处理为一串数组）
 					var strArrays = prodIds.split('\n');
    				var prodIdArray = [];
    				if(strArrays.length == 0){
    					$.messager.alert('提示', '扫码获取数据失败');
    					return;
    				}else{
    					for(var i=0;i<strArrays.length;i++){
    						if(strArrays[i] != ""){
	       					 	prodIdArray.push(strArrays[i]);
    						}
	       				}
	       				var params = {'arrays':prodIdArray};
            	    	//格式化数组
            	    	var ps = $.param(params, true);
            	    	$.post("/saleProd/getByProdIds", ps, function(data) {
            	    		if (data.status == 200) {
    							$("#saleProdSmDetailWindow").window({
    				        		onLoad :function(){
    				        			$("#salePlanIdM5004SmDetail").textbox('setValue',$("#salePlanIdM5004").textbox('getValue'));
    				        			$("#saleSernoM5004SmDetail").textbox('setValue',$("#saleSernoM5004").textbox('getValue'));
    				        			$('#saleProdSmDetailList').datagrid('loadData', data.data);
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
		text:'产品出库',
		iconCls:'icon-add',
		handler:function(){
			var ids = getM5004SelectionsIds();
			if(ids.length == 0){
				$.messager.alert('提示','至少选择一条记录出库!');
				return ;
			}
			$.messager.confirm('确认','确定批量销售出库？',function(r){
	    	    if (r){
					var salePlanId = $("#salePlanIdM5004").textbox('getValue');
					var saleSerno = $("#saleSernoM5004").textbox('getValue');
					if(salePlanId == '' && saleSerno==''){
			     		$.messager.alert('提示', '销售出库单号和销售计划流水不能同时为空！');
			     		return;
			     	}
					//获取所有表格内容
					var strArrays = ids.split(',');
        	    	var arrays = [];
        	    	for(var i=0;i<strArrays.length;i++){
        	    		arrays.push(strArrays[i]);
        	    	}
					var params = {'arrays':arrays,'salePlanId':salePlanId,'saleSerno':saleSerno,'prodCheckuser':$("#user_id").html()};
					//格式化数组
			    	var ps = $.param(params, true);
					//批量出库
			    	$.post("/saleProd/prodOutPl", ps, function(data) {
			    		if (data.status == 200) {
			    			$.messager.alert('提示','批量销售出库成功!',undefined,function(){
			    				$("#saleProdSmDetailWindow").window('close');
								searchM5004Form();
							});
						} else {
						 	$.messager.alert('提示', data.msg);
						} 
					});
	    	    }
			});
		}
	}];
    
    var toolbarM5004Out = [{
        text:'误发退回',
        iconCls:'icon-reload',
        handler:function(){
        	var ids = getM5004OutSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条出库记录退回!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多退回一条出库记录!');
        		return ;
        	}
        	//回显数据
			var data = $("#saleProdListOut").datagrid("getSelections")[0];
        	if(data.prodStatus != '5'){
        		$.messager.alert('提示','只有已出库未发货的产品才允许退回!');
        		return ;
        	}
        	$.messager.confirm('确认','确定出库退回？',function(r){
        	    if (r){
        	    	var checkuser = $("#user_id").html();
        	    	var params = {"prodSaleSerno":data.prodSaleSerno,"prodId":data.prodId,"checkuser":checkuser};
                	$.post("/saleProd/reBack",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','退回成功,若出库详细信息录入错误，请删除!',undefined,function(){
            					searchM5004Form();
            				});
            			} else {
            				$.messager.alert('提示','退回失败：'+data.msg);
            			}
            		});
        	    }
        	});
        }
    },{
        text:'产品复磅',
        iconCls:'icon-man',
        handler:function(){
        	var ids = getM5004OutSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录复磅!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多选择一条复磅记录!');
        		return ;
        	}
        	//回显数据
			var data = $("#saleProdListOut").datagrid("getSelections")[0];
        	if(data.prodStatus == '4'){
        		$.messager.alert('提示','已发货的产品不允许复磅!');
        		return ;
        	}
        	$("#saleProdFbWindow").window({
				onLoad :function(){
					//回显数据
					data.prodFbWeight = data.prodOutWeight;
					$("#saleProdFbForm").form("load",data);
				}
			}).window("open");
        }
    }];
    
</script>