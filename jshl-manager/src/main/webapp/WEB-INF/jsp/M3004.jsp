<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="纵剪带生产出库信息查询" style="padding:10px 10px 10px 10px">
		<form id="halfRawOutputSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">纵剪带指令号:</td>
		            <td width="210" align="right">
		            	<input name="prodPlanNo" id="prodPlanNoM3004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">盘号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM3004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		       <tr>
		       		<td width="120" align="right">产品状态:</td>
		            <td width="210" align="right">
		            	<select name="prodStatus" id="prodStatusM3004" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${rawStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120"  align="right">生产机组:</td>
		            <td width="210"  align="right">
		            	<select name="planJz" id="planJzM3004" panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodUnitsSCList}" var="a">
				          	   	<option value="${a.enumKey}">${a.enumValue}</option>
				          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM3004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM3004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM3004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM3004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3004Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3004Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="halfRawOutputList" title="纵剪带生产出库信息管理"  style="height:620px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM3004,collapsible:true,pagination:true,url:'/halfRawOutput/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM3004,pageSize:30,toolbar:toolbarM3004">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:170,sortable:true">盘号</th>
	        	<th data-options="field:'prodPlanNo',width:150,sortable:true">纵剪带指令号</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatHalfProdStatus,sortable:true">状态</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	        	<th data-options="field:'prodOriId',width:100,sortable:true">来源盘号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">物料名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	            <th data-options="field:'prodLevel',width:110,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodInputuser',width:70,sortable:true">入库人</th>
	            <th data-options="field:'prodCheckuser',width:70,sortable:true">修改人</th>
	            <th data-options="field:'crtDate',width:150,formatter:BSL.formatFullDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'updDate',width:150,formatter:BSL.formatFullDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	
	<div id="halfRawOutputSMWindow" class="easyui-window" title="半成品扫码出库" data-options="modal:true,closed:true,iconCls:'icon-man',href:'/halfRawOutput/M3004-SM'" style="width:780px;height:300px;padding:10px;">
	</div>
	<div id="halfRawOutputSMAlertWindow" class="easyui-window"  title="等待扫码..."  data-options="modal:true,closed:true,maximizable:false,minimizable:false,collapsible:false,iconCls:'icon-man',href:'/halfRawOutput/M3004-SMAlert'" style="width:420px;height:170px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM3004(){
		var queryParams = $('#halfRawOutputList').datagrid('options').queryParams;
		queryParams.prodPlanNo = $('#prodPlanNoM3004').val();
		queryParams.prodId = $('#prodIdM3004').val();
		queryParams.prodStatus = $('#prodStatusM3004').combobox("getValue");
		queryParams.planJz = $('#planJzM3004').combobox("getValue");
	}

	//排序查询
	function sortSerachM3004(sort,order){
		$("#sortM3004").textbox('setValue',sort);
		$("#orderM3004").textbox('setValue',order);
		searchM3004Form();
	}

	//查询按钮
	function searchM3004Form(){
		//page页码
		var page = $("#halfRawOutputList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#halfRawOutputList").datagrid('options').pageSize; 
		$("#pageM3004").textbox('setValue',page);
		$("#rowsM3004").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/halfRawOutput/listByCriteria",$("#halfRawOutputSearchForm").serialize(), function(data){
			if(data.status == 200){				
	           	$('#halfRawOutputList').datagrid('loadData', {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM3004();
	}
	
	/* 重置表单 */
	function clearM3004Form(){
		$('#halfRawOutputSearchForm').form('reset');
	}

	/*获取表格卷板号*/
    function getM3004SelectionsIds(){
    	var receiptList = $("#halfRawOutputList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
   
	var toolbarM3004 = [
			{
				text : '扫码出库',
				iconCls : 'icon-man',
				handler : function() {
					$("#halfRawOutputSMAlertWindow").window({
						onLoad :function(){
		        			//给焦点
							$("#prodIdSM3004").textbox('textbox').focus();
		        		},
						 onBeforeClose:function(){ 
							 //关闭时删除计时器，解决快速计时的bug
							 clearInterval(timeM3004);
							 //关闭时获取半成品编号
							 var prodId = $("#prodIdSM3004").textbox('getValue');
							 if(prodId != '' && prodId != null){
								 params = {"prodId" : prodId};
								 $.post("/halfRawOutput/getById", params, function(data) {
									if (data.status == 200) {
										 $("#halfRawOutputSMWindow").window({
							        		 onLoad :function(){
												 $("#halfRawOutputSMForm").form("load",data.data);
							        		 }
							        	 }).window("open");
									 } else {
										 $.messager.alert('提示', data.msg);
									 }
								 });
							 }else{
								 $.messager.alert('提示', "扫码获取数据失败");
							 }
					     }
		        	}).window("open");
				}
			},{
				text : '确认出库',
				iconCls : 'icon-add',
				handler : function() {
					var ids = getM3004SelectionsIds();
					if (ids.length == 0) {
						$.messager.alert('提示', '必须选择一条记录确认出库!');
						return;
					}
					if (ids.indexOf(',') > 0) {
						$.messager.alert('提示', '一次只能选择一条记录确认出库!');
						return;
					}
					var data = $("#halfRawOutputList").datagrid("getSelections")[0];
					if (data.prodStatus != "1") {
						$.messager.alert('提示', '该半成品状态不支持出库!');
						return;
					}
					//setInputUser();
					$.messager.confirm('确认', '该半成品确定出库吗？', function(r) {
						if (r) {
							var params = {
								"prodId" : ids,
								"planJz": $('#planJzM3004').combobox("getValue"),
								"prodInputuser" : $("#user_id").html()
							};
							$.post("/halfRawOutput/output", params, function(data) {
								if (data.status == 200) {
									$.messager.alert('提示','出库成功，编号为：'+data.data,'info',function(){
												searchM3004Form();
											});
								} else {
									$.messager.alert('提示', data.msg);
								}
							});
						}
					});
				}
			},
			{
				text : '未用退回',
				iconCls : 'icon-cancel',
				handler : function() {
					var ids = getM3004SelectionsIds();
					var data = $("#halfRawOutputList").datagrid("getSelections")[0];
					if (data.prodStatus == "1") {
						$.messager.alert('提示', '该半成品处于入库状态，无须退回！');
						return;
					}
					$.messager.confirm('确认', '该半成品确定未用退回吗？', function(r) {
						if (r) {
							var params = {
									"prodId" : ids,
									"prodInputuser" : $("#user_id").html()
							};
							$.post("/halfRawOutput/reInsert", params, function(data) {
								if (data.status == 200) {
									$.messager.alert('提示', "退回完成，编号为："+data.data, undefined,
											function() {
												searchM3004Form();
											});
								} else {
									$.messager.alert('提示', data.msg);
								}
							});
						}
					});
				}
			}, {
				text : '完成',
				iconCls : 'icon-ok',
				handler : function() {
					var ids = getM3004SelectionsIds();
					var data = $("#halfRawOutputList").datagrid("getSelections")[0];
					if (data.prodStatus != "2") {
						$.messager.alert('提示','只有已出库的才能点击完成！');
						return;
					}
					$.messager.confirm('确认', '该半成品确定完成吗？若未用完，请改做重新入库交易。是否继续？', function(r) {
						if (r) {
							var params = {
									"prodId" : ids,
									"prodInputuser" : $("#user_id").html()
							};
							$.post("/halfRawOutput/finish", params, function(data) {
								if (data.status == 200) {
									$.messager.alert('提示', "完成成功，编号为："+data.data, undefined,
											function() {
												searchM3004Form();
											});
								} else {
									$.messager.alert('提示', data.msg);
								}
							});
						}
					});
				}
			} ];
</script>