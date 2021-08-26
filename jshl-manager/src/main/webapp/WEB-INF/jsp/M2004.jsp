<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="卷板生产出库信息查询" style="padding:10px 10px 10px 10px">
		<form id="rawOutputSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">原料入库通知单号:</td>
		            <td width="210" align="right">
		            	<input name="prodPlanNo" id="prodPlanNoM2004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">原料物料编码:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM2004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		       <tr>
		       		<td width="120" align="right">卷板状态:</td>
		            <td width="210" align="right">
		            	<select name="prodStatus" id="prodStatusM2004" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${rawStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">来源钢卷号:</td>
		            <td width="210" align="right">
		            	<input name="prodOrirawid" id="prodOrirawidM2004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		         <tr>
		            <td width="120"  align="right">生产机组:</td>
		            <td width="210"  align="right">
		            	<select name="planJz" id="planJzM2004"  panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodUnitsZJList}" var="a">
				          	   	<option value="${a.enumKey}">${a.enumValue}</option>
				          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM2004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM2004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM2004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM2004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		        	<td width="120" align="right">录入人:</td>
		            <td width="210" align="right">
		            	<input name="prodInputuser" id="prodInputuserM2004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM2004Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM2004Form()">重置</a>
		</div>
	</div>
		 <table class="easyui-datagrid" id="rawOutputList" title="卷板生产出库信息管理"  style="height:620px"
		       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM2004,collapsible:true,pagination:true,url:'/rawOutput/query',method:'post',onBeforeLoad:onBeforeLoadM2004,pageSize:30,toolbar:toolbarM2004">
		    <thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
		        	<th data-options="field:'prodId',width:125,sortable:true">原料物料编码</th>
		            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
		        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
		        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
		        	<th data-options="field:'prodName',width:100,sortable:true">物料名称</th>
		        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
		        	<th data-options="field:'prodLength',sortable:true,width:80">定尺</th>
		        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
		            <th data-options="field:'prodLevel',width:110,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
		            <th data-options="field:'prodRecordWeight',width:125,sortable:true">原料来料重量/吨</th>
		            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
		            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
		            <th data-options="field:'prodSource',width:120,formatter:BSL.formatProdSource,sortable:true">产品来源</th> 
		            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
		            <th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
		            <th data-options="field:'prodInputuser',width:70,sortable:true">制单人</th>
		            <th data-options="field:'crtDate',width:150,formatter:BSL.formatFullDateTime,sortable:true">入库日期</th>
		            <th data-options="field:'updDate',width:150,formatter:BSL.formatFullDateTime,sortable:true">修改日期</th>
		            <th data-options="field:'remark',width:226,sortable:true">备注</th>
		        </tr>
		    </thead>
		</table>
	</div>
	
	<div id="rawOutputSMWindow" class="easyui-window" title="钢卷扫码出库" data-options="modal:true,closed:true,iconCls:'icon-man',href:'/rawOutput/M2004-SM'" style="width:780px;height:300px;padding:10px;">
	</div>
	<div id="rawOutputSMAlertWindow" class="easyui-window"  title="等待扫码..."  data-options="modal:true,closed:true,maximizable:false,minimizable:false,collapsible:false,iconCls:'icon-man',href:'/rawOutput/M2004-SMAlert'" style="width:420px;height:170px;padding:10px;">
	</div>
	
	<div id="sendFlagEditWindowM2004" class="easyui-window" title="取样送检信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/sendFlag/M1006-edit'" style="width:810px;height:170px;padding:10px;">
	</div>
<script>

	function onBeforeLoadM2004(){
		var queryParams = $('#rawOutputList').datagrid('options').queryParams;
		queryParams.prodPlanNo = $('#prodPlanNoM2004').val();
		queryParams.prodId = $('#prodIdM2004').val();
		queryParams.prodStatus = $('#prodStatusM2004').combobox("getValue");
		queryParams.planJz = $('#planJzM2004').combobox("getValue");
		queryParams.prodOrirawid = $('#prodOrirawidM2004').val();
	}

	//排序查询
	function sortSerachM2004(sort,order){
		$("#sortM2004").textbox('setValue',sort);
		$("#orderM2004").textbox('setValue',order);
		searchM2004Form();
	}

	//查询按钮
	function searchM2004Form(){
		//page页码
		var page = $("#rawOutputList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#rawOutputList").datagrid('options').pageSize; 
		$("#pageM2004").textbox('setValue',page);
		$("#rowsM2004").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/rawOutput/query",$("#rawOutputSearchForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#rawOutputList').datagrid('loadData', {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM2004();
	}
	
	/* 重置表单 */
	function clearM2004Form(){
		$('#rawOutputSearchForm').form('reset');
	}

	/*获取表格卷板号*/
    function getM2004SelectionsIds(){
    	var receiptList = $("#rawOutputList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
	
	function setInputUser(){
		var inputUser = $("#user_id").html();
		$("#prodInputuserM2004").textbox('setValue',inputUser);
	}
    
   
	var toolbarM2004 = [
		{
			text : '扫码出库',
			iconCls : 'icon-man',
			handler : function() {
				$("#rawOutputSMAlertWindow").window({
					onLoad :function(){
	        			//给焦点
						$("#prodIdSM2004").textbox('textbox').focus();
	        		},
					 onBeforeClose:function(){ 
						 //关闭时删除计时器，解决快速计时的bug
						 clearInterval(timeM2004);
						 //关闭时获取物料编号
						 var prodId = $("#prodIdSM2004").textbox('getValue');
						 if(prodId != '' && prodId != null){
							 params = {"prodId" : prodId};
							 $.post("/rawOutput/getById", params, function(data) {
								if (data.status == 200) {
									 $("#rawOutputSMWindow").window({
						        		 onLoad :function(){
											 $("#rawOutputSMForm").form("load",data.data);
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
					var ids = getM2004SelectionsIds();
					if (ids.length == 0) {
						$.messager.alert('提示', '必须选择一条记录确认出库!');
						return;
					}
					if (ids.indexOf(',') > 0) {
						$.messager.alert('提示', '一次只能选择一条记录确认出库!');
						return;
					}
					var data = $("#rawOutputList").datagrid("getSelections")[0];
					if (data.prodStatus == "2") {
						$.messager.alert('提示', '该卷板已经出库!');
						return;
					}
					//setInputUser();
					$.messager.confirm('确认', '该卷板确定出库吗？', function(r) {
						if (r) {
							var params = {
								"prodId" : ids,
								"planJz": $('#planJzM2004').combobox("getValue"),
								"prodInputuser" : $("#user_id").html()
							};
							$.post("/rawOutput/out", params, function(dataOut) {
								if (dataOut.status == 200) {
									$.messager.alert('提示', "出库完成，产品编号为："+dataOut.data, undefined,
											function() {
												searchM2004Form();
												//取样送检提示
												params = {"luId" : data.prodLuno,'rows':'30','page':'1'};
												$.post("/sendFlag/listByCriteria", params, function(data2) {
													if (data2.status == 200) {
														if(data2.total > 0 && data2.rows[0].sendFlag == '1'){
															$.messager.confirm('确认', '该炉号已经取样送检，送检结果为：'+data2.rows[0].sendResult+',是否继续送检？', function(r) {
																if (r) {
																	$("#sendFlagEditWindowM2004").window({
														        		onLoad :function(){
														        			//回显数据
														        			var paramsTmp = data2.rows[0];
														        			paramsTmp.flag = 'M2004';
														        			$("#sendFlagEditForm").form("load",paramsTmp);
														        		}
														        	}).window("open");
																}
															});
														}else{
															$.messager.confirm('确认', '该炉号未送检,是否送检？', function(r) {
																if (r) {
																	$("#sendFlagEditWindowM2004").window({
														        		onLoad :function(){
														        			//回显数据
														        			$("#sendFlagEditForm").form("load",{"luId" : data.prodLuno,'sendFlag':'1','flag':'M2004'});
														        		}
														        	}).window("open");
																}
															});
														}
													} else {
														$.messager.alert('提示', data2.msg);
													}
												});
											});
								} else {
									$.messager.alert('提示', dataOut.msg);
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
					var ids = getM2004SelectionsIds();
					var data = $("#rawOutputList").datagrid("getSelections")[0];
					if (data.prodStatus == "1") {
						$.messager.alert('提示', '该卷板处于入库状态，无须退回！');
						return;
					}
					//setInputUser();
					$.messager.confirm('确认', '该卷板确定未用退回吗？', function(r) {
						if (r) {
							var params = {
								"prodId" : ids,
								"prodInputuser" : $("#user_id").html()
							};
							$.post("/rawOutput/reInt", params, function(data) {
								if (data.status == 200) {
									$.messager.alert('提示', "退回完成，产品编号为："+data.data,undefined,
											function() {
												searchM2004Form();
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
					$.messager.confirm('确认', '该卷板确定完成吗？', function(r) {
						if (r) {
							var ids = getM2004SelectionsIds();
							var data = $("#rawOutputList").datagrid("getSelections")[0];
							if (data.prodStatus != "2") {
								$.messager.alert('提示','只有已出库的才能点击完成！');
								return;
							}
							var params = {
								"prodId" : ids,
								"prodInputuser" : $("#user_id").html()
							};
							$.post("/rawOutput/finish", params, function(data) {
								if (data.status == 200) {
									$.messager.alert('提示',"该卷板制造完成，产品编号为："+data.data,undefined,
											function() {
												searchM2004Form();
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