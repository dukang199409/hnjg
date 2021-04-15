<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="外协厂产品接收信息查询" style="padding:10px 10px 10px 10px">
		<form id="wxJsProdDetailSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">出库通知单单号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutPlan" id="prodOutPlanM3104DetailProd" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">出库计划流水:</td>
		            <td width="210" align="right">
		            	<input name="prodSaleSerno" id="prodSaleSernoM3104DetailProd" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td> 
		        </tr>
		        <tr>
		        	<td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM3104DetailProd" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM3104DetailProd" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
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
	            		<input name="prodNorm" id="prodNormM3104DetailProd" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            	</td>
		            <td width="120" align="right">车次:</td>
		            <td width="210" align="right">
		            	<input name="prodOutCarno" id="prodOutCarnoM3104DetailProd" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		        	<td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page1" id="pageM3104DetailProdOut" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows1" id="rowsM3104DetailProdOut" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort1" id="sortM3104DetailProdOut" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order1" id="orderM3104DetailProdOut" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM3104DetailProd" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM3104DetailProd" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM3104DetailProd" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM3104DetailProd" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3104DetailProdForm()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3104DetailProdForm()">关闭</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="canJsProdList" title="外协厂可接收产品信息管理"  style="height:320px"
	       data-options="singleSelect:false,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM3104DetailProd,collapsible:true,pagination:true,url:'/prodWxJs/canJsProds',method:'post',onBeforeLoad:onBeforeLoadM3104DetailProd1,pageSize:30,toolbar:toolbarM3104DetailProd">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:180,sortable:true">产品编号</th>
	        	<th data-options="field:'prodType',width:100,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'prodPlanNo',width:140,sortable:true">单号/指令号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">产品名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodOutPlan',width:100,sortable:true">销售出库单号</th>
	            <th data-options="field:'prodSaleSerno',width:100,sortable:true">销售计划流水</th>
	            <th data-options="field:'prodOutCarno',width:200,sortable:true">车次流水</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
	            <th data-options="field:'prodOutWeight',width:125,sortable:true">销售复磅重量/吨</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	        	<th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodLevel',width:90,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'crtDate',width:100,formatter:BSL.formatDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'updDate',width:150,formatter:BSL.formatDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	 <table class="easyui-datagrid" id="alreadyJsProdList" title="外协厂已接收产品信息管理"  style="height:320px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM3104DetailProdOut,collapsible:true,pagination:true,url:'/prodWxJs/alreadyJsProds',method:'post',onBeforeLoad:onBeforeLoadM3104DetailProd2,pageSize:30,toolbar:toolbarM3104DetailProdOut">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:180,sortable:true">产品编号</th>
	        	<th data-options="field:'prodType',width:80,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'prodPlanNo',width:140,sortable:true">单号/指令号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">产品名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodOutPlan',width:100,sortable:true">销售出库单号</th>
	            <th data-options="field:'prodSaleSerno',width:100,sortable:true">销售计划流水</th>
	            <th data-options="field:'prodOutCarno',width:200,sortable:true">车次流水</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
				<th data-options="field:'prodOutWeight',width:125,sortable:true">销售复磅重量/吨</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	        	<th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodLevel',width:90,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'crtDate',width:100,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="jsProdWindow" class="easyui-window" title="产品接收入库" data-options="modal:true,closed:true,iconCls:'saleProd-edit',href:'/prodWxJs/M3104DetailProd-out'" style="width:780px;height:320px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM3104DetailProd1(){
		
		var queryParams = $('#canJsProdList').datagrid('options').queryParams;
		queryParams.prodOutPlan = $('#prodOutPlanM3104DetailProd').val();
		queryParams.prodSaleSerno = $('#prodSaleSernoM3104DetailProd').val();
		queryParams.prodId = $('#prodIdM3104DetailProd').val();
		queryParams.prodMaterial = $('#prodMaterialM3104DetailProd').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM3104DetailProd').val();
		queryParams.prodOutCar = $('#prodOutCarM3104DetailProd').val();
		
	}
	
	function onBeforeLoadM3104DetailProd2(){
		
		var queryParams = $('#alreadyJsProdList').datagrid('options').queryParams;
		queryParams.prodOutPlan = $('#prodOutPlanM3104DetailProd').val();
		queryParams.prodSaleSerno = $('#prodSaleSernoM3104DetailProd').val();
		queryParams.prodId = $('#prodIdM3104DetailProd').val();
		queryParams.prodMaterial = $('#prodMaterialM3104DetailProd').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM3104DetailProd').val();
		queryParams.prodOutCar = $('#prodOutCarM3104DetailProd').val();
		
	}

	//排序查询
	function sortSerachM3104DetailProd(sort,order){
		$("#sortM3104DetailProd").textbox('setValue',sort);
		$("#orderM3104DetailProd").textbox('setValue',order);
		searchM3104DetailProdForm();
	}
	function sortSerachM3104DetailProdOut(sort,order){
		$("#sortM3104DetailProdOut").textbox('setValue',sort);
		$("#orderM3104DetailProdOut").textbox('setValue',order);
		searchM3104DetailProdForm();
	}

	//查询按钮
	function searchM3104DetailProdForm(){
		//page页码
		var page = $("#canJsProdList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#canJsProdList").datagrid('options').pageSize; 
		$("#pageM3104DetailProd").textbox('setValue',page);
		$("#rowsM3104DetailProd").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#wxJsProdDetailSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/prodWxJs/canJsProds",$("#wxJsProdDetailSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#canJsProdList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		
		//page页码
		var page1 = $("#alreadyJsProdList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows1 = $("#alreadyJsProdList").datagrid('options').pageSize; 
		$("#pageM3104DetailProdOut").textbox('setValue',page1);
		$("#rowsM3104DetailProdOut").textbox('setValue',rows1);
		//ajax的post方式提交表单
		//$("#wxJsProdDetailSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/prodWxJs/alreadyJsProds",$("#wxJsProdDetailSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#alreadyJsProdList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		
		onBeforeLoadM3104DetailProd1();
		onBeforeLoadM3104DetailProd2();
	}
	
	/* 重置表单 */
	function clearM3104DetailProdForm(){
		$("#getJsProdDetailWindow").window('close');
	}

    function getM3104DetailProdSelectionsIds(){
    	var canJsProdList = $("#canJsProdList");
    	var sels = canJsProdList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    function getM3104DetailProdOutSelectionsIds(){
    	var canJsProdList = $("#alreadyJsProdList");
    	var sels = canJsProdList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].prodId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM3104DetailProd = [{
		text:'产品接收',
		iconCls:'icon-add',
		handler:function(){
			var ids = getM3104DetailProdSelectionsIds();
			if(ids.length == 0){
				$.messager.alert('提示','至少选择一条记录出库!');
				return ;
			}
			$.messager.confirm('确认','确定批量接收？',function(r){
	    	    if (r){
					var prodOutPlan = $("#prodOutPlanM3104DetailProd").textbox('getValue');
					if(prodOutPlan == ''){
			     		$.messager.alert('提示', '出库通知单号不能为空！');
			     		return;
			     	}
					//获取所有表格内容
					var strArrays = ids.split(',');
        	    	var arrays = [];
        	    	for(var i=0;i<strArrays.length;i++){
        	    		arrays.push(strArrays[i]);
        	    	}
					var params = {'arrays':arrays,'prodOutPlan':prodOutPlan,'prodCheckuser':$("#user_id").html()};
					//格式化数组
			    	var ps = $.param(params, true);
					//批量接收
			    	$.post("/prodWxJs/prodJsPl", ps, function(data) {
			    		if (data.status == 200) {
			    			$.messager.alert('提示','批量销售接收成功!',undefined,function(){
								searchM3104DetailProdForm();
							});
						} else {
						 	$.messager.alert('提示', data.msg);
						} 
					});
	    	    }
			});
		}
	}];
    
    var toolbarM3104DetailProdOut = [{
        text:'接收退回',
        iconCls:'icon-reload',
        handler:function(){
        	var ids = getM3104DetailProdOutSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条接收记录退回!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多退回一条接收记录!');
        		return ;
        	}
        	//回显数据
			var data = $("#alreadyJsProdList").datagrid("getSelections")[0];
        	if(data.prodStatus != '7'){
        		$.messager.alert('提示','只有已接收的产品才允许退回!');
        		return ;
        	}
        	$.messager.confirm('确认','确定出库退回？',function(r){
        	    if (r){
        	    	var checkuser = $("#user_id").html();
        	    	var params = {"prodId":data.prodId,"checkuser":checkuser};
                	$.post("/prodWxJs/reBack",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','退回成功!',undefined,function(){
            					searchM3104DetailProdForm();
            				});
            			} else {
            				$.messager.alert('提示','退回失败：'+data.msg);
            			}
            		});
        	    }
        	});
        }
    }];
    
</script>