<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="纵剪带库存台账信息查询" style="padding:10px 10px 10px 10px">
		<form id="semiSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">纵剪带指令号:</td>
		            <td width="210" align="right">
		            	<input name="prodPlanNo" id="prodPlanNoM2005"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">盘号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM2005"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="prodLuno" id="prodLunoM2005" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">父级钢卷号:</td>
		            <td width="210" align="right">
		            	<input name="prodParentNo" id="prodParentNoM2005" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		       <tr>
		       		<td width="120" align="right">产品状态:</td>
		            <td width="210" align="right">
		            	<select name="prodStatus" id="prodStatusM2005"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodHalfStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	 </c:forEach>
						</select>
		            </td>
		       		<td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM2005"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
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
		            <td width="120" align="right">出库指令号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutPlan" id="prodOutPlanM2005"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">制造纵剪带用途:</td>
		            <td width="210" align="right">
		            	<select name="prodUserType" id="prodMakeTypeM2005" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${makeTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          		</c:forEach>
						</select>
		            </td>
		             <td width="120" align="right">生产班次:</td>
		            	<td width="210" align="right">
		            	<select name="prodBc" id="prodBcM2005" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				           <option value="">请选择...</option>
				          <c:forEach items="${prodBcList}" var="a">
				          	   	<option value="${a.enumKey}">${a.enumValue}</option>
				          </c:forEach>
						</select>
		            </td> 
		        </tr>
		        <tr>
		             <td width="120" align="right">生产机组:</td>
		            	<td width="210" align="right">
		            	<select name="prodMakeJz" id="prodMakeJzM2005" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				           <option value="">请选择...</option>
				          <c:forEach items="${prodUnitsZJList}" var="a">
				          	   	<option value="${a.enumKey}">${a.enumValue}</option>
				          </c:forEach>
						</select>
		            </td> 
		        </tr>
		         <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM2005" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM2005" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM2005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM2005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM2005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM2005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM2005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM2005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>	
		        <tr hidden="true">
		        	<td width="120" align="right">录入人:</td>
		            <td width="210" align="right">
		            	<input name="prodInputuser" id="prodInputuserM2005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>		        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM2005Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM2005Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="semiList" title="纵剪带库存台账信息管理"  style="height:600px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM2005,collapsible:true,pagination:true,url:'/semi/query',method:'post',onBeforeLoad:onBeforeLoadM2005,pageSize:30,toolbar:toolbarM2005">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	            <th data-options="field:'prodId',width:165,sortable:true">盘号</th>
	        	<th data-options="field:'prodPlanNo',width:150,sortable:true">纵剪带生产指令号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">物料名称</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatHalfProdStatus,sortable:true">状态</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodParentNo',width:100,sortable:true">父级钢卷号</th>
	        	<th data-options="field:'prodOriId',width:100,sortable:true">原盘号</th>
	        	<th data-options="field:'prodOutPlan',width:100,sortable:true">出库指令号</th>
	            <th data-options="field:'prodLevel',width:110,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">入库重量/吨</th>
	            <th data-options="field:'prodUserType',width:120,formatter:BSL.formatMakeType,sortable:true">纵剪带用途</th>
	            <th data-options="field:'prodBc',width:120,formatter:BSL.formatProdBcStatus,sortable:true">生产班次</th>
	            <th data-options="field:'prodMakeJz',width:120,formatter:BSL.formatPlanJz,sortable:true">生产机组</th>
	            <th data-options="field:'prodOutCarno',width:100,sortable:true">发货车次流水</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodCustomer',width:100,sortable:true">客户</th>
	            <th data-options="field:'prodInputuser',width:70,sortable:true">录入人</th>
	            <th data-options="field:'prodCheckuser',width:70,sortable:true">修改人</th>
	            <th data-options="field:'crtDate',width:150,formatter:BSL.formatFullDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'prodOutDate',width:150,formatter:BSL.formatFullDateTime,sortable:true">出库日期</th>
	            <th data-options="field:'updDate',width:150,formatter:BSL.formatFullDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:226,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="semiAddWindow" class="easyui-window" title="新增入库" data-options="modal:true,closed:true,iconCls:'icon-add',href:'/semi/M2005-add'" style="width:780px;height:330px;padding:10px;">
	</div>
	<div id="semiEditWindow" class="easyui-window" title="编辑入库单录入信息" data-options="modal:true,closed:true,iconCls:'icon-edit',href:'/semi/M2005-edit'" style="width:780px;height:340px;padding:10px;">
	</div>
	<div id="semiReAddWindow" class="easyui-window" title="剩余半成品重新入库" data-options="modal:true,closed:true,iconCls:'icon-reload',href:'/semi/M2005-readd'" style="width:780px;height:250px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM2005(){
		var queryParams = $('#semiList').datagrid('options').queryParams;
		queryParams.prodPlanNo = $('#prodPlanNoM2005').val();
		queryParams.prodId = $('#prodIdM2005').val();
		queryParams.prodLuno = $('#prodLunoM2005').val();
		queryParams.prodParentNo = $('#prodParentNoM2005').val();
		queryParams.prodStatus = $('#prodStatusM2005').combobox("getValue");
		queryParams.prodBc = $('#prodBcM2005').combobox("getValue");
		queryParams.prodMaterial = $('#prodMaterialM2005').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM2005').val();
		queryParams.prodOutPlan = $('#prodOutPlanM2005').val();
		queryParams.prodMakeType = $('#prodMakeTypeM2005').combobox("getValue");
		queryParams.prodMakeJz = $('#prodMakeJzM2005').combobox("getValue");
		queryParams.startDate = $('#startDateM2005').datebox("getValue");
		queryParams.endDate = $('#endDateM2005').datebox("getValue");
	}

	//排序查询
	function sortSerachM2005(sort,order){
		$("#sortM2005").textbox('setValue',sort);
		$("#orderM2005").textbox('setValue',order);
		searchM2005Form();
	}

	//查询按钮
	function searchM2005Form(){
		//page页码
		var page = $("#semiList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#semiList").datagrid('options').pageSize; 
		$("#pageM2005").textbox('setValue',page);
		$("#rowsM2005").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/semi/query",$("#semiSearchForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#semiList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM2005").textbox('setValue',data.className);
				$("#methodNameM2005").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM2005();
	}
	
	/* 重置表单 */
	function clearM2005Form(){
		$('#semiSearchForm').form('reset');
	}

	/*获取表格卷板号*/
    function getM2005SelectionsIds(){
    	var receiptList = $("#semiList");
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
		$("#prodInputuserM2005").textbox('setValue',inputUser);
	}
    
   
	var toolbarM2005 = [
			{
				text : '新增入库',
				iconCls : 'icon-add',
				handler : function() {
					$("#semiAddWindow").window({
		        		onLoad :function(){
		        		}
		        	}).window("open");
				}
			},{
		        text:'编辑',
		        iconCls:'icon-edit',
		        handler:function(){
		        	var ids = getM2005SelectionsIds();
		        	if(ids.length == 0){
		        		$.messager.alert('提示','必须选择一条记录编辑!');
		        		return ;
		        	}
		        	if(ids.indexOf(',') > 0){
		        		$.messager.alert('提示','只能选择一条记录编辑!');
		        		return ;
		        	}
		        	var data = $("#semiList").datagrid("getSelections")[0];
		        	/* if(data.prodStatus != "1"){
		        		$.messager.alert('提示','本交易只允许修改入库的半成品信息!');
		        		return ;
		        	} */
		        	var user = $("#user_id").html(); 
		        	if(user != '000000'){
		        		$.messager.alert('提示','只有超级管理员才允许修改产品信息!');
		        		return ;
		        	}
		        	$("#semiEditWindow").window({
		        		onLoad :function(){
		        			//回显数据
		        			$("#semiEditForm").form("load",data);
		        		}
		        	}).window("open");
		        }
		    },{
		        text:'删除',
		        iconCls:'icon-cancel',
		        handler:function(){
		        	var ids = getM2005SelectionsIds();
		        	if(ids.length == 0){
		        		$.messager.alert('提示','必须选择一条记录删除!');
		        		return ;
		        	}
		        	if(ids.indexOf(',') > 0){
		        		$.messager.alert('提示','一次最多删除一条记录!');
		        		return ;
		        	}
		        	var data = $("#semiList").datagrid("getSelections")[0];
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
		        	    	var params = {"prodId":ids,'user':user};
		                	$.post("/semi/delete",params, function(data){
		            			if(data.status == 200){
		            				$.messager.alert('提示','删除成功!',undefined,function(){
		            					searchM2005Form();
		            				});
		            			} else {
		            				$.messager.alert('提示', data.msg);
		            			}
		            		});
		        	    }
		        	});
		        }
		    },{
		        text:'剩余半成品重新入库',
		        iconCls:'icon-reload',
		        handler:function(){
		        	var ids = getM2005SelectionsIds();
		        	if(ids.length == 0){
		        		$.messager.alert('提示','必须选择一条记录重新入库!');
		        		return ;
		        	}
		        	if(ids.indexOf(',') > 0){
		        		$.messager.alert('提示','一次只能选择一条记录重新入库!');
		        		return ;
		        	}
		        	var data = $("#semiList").datagrid("getSelections")[0];
		        	if(data.prodStatus != "2"){
		        		$.messager.alert('提示','只有已出库的半成品才能重新入库!');
		        		return ;
		        	}
		        	$("#semiReAddWindow").window({
		        		onLoad :function(){
		        			//回显数据
		        			data.prodOriId = data.prodId;
		        			$("#semiReAddWindow").form("load",data);
		        		}
		        	}).window("open");
		        }
		    },
		    {
		        text:'补打PDF标签文件',
		        iconCls:'icon-print',
		        handler:function(){
		        	var ids = getM2005SelectionsIds();
		        	if(ids.length == 0){
		        		$.messager.alert('提示','必须选择一条记录补打PDF!');
		        		return ;
		        	}
		        	if(ids.indexOf(',') > 0){
		        		$.messager.alert('提示','只能选择一条记录补打PDF!');
		        		return ;
		        	}
		        	var data = $("#semiList").datagrid("getSelections")[0];
		        	if(data.prodStatus == "0"){
		        		$.messager.alert('提示','未入库的半成品不支持补打PDF!');
		        		return ;
		        	}
		        	$.messager.confirm('确认','是否需要补打PDF标签文件？',function(r){
						if (r){
							var prodId = data.prodId;
		        	    	var mapParam = new Map();
		        			mapParam.set("url","/import/importPdf");
		        			mapParam.set("prodId",prodId);
		        			mapParam.set("tradeType","M2005");
		        			BSL.toNewPagePDF(mapParam);
		        	    }
					});
		        }
		    },{
		    	text : '导出EXCEL',
		        iconCls : 'icon-save',
		        handler : function() {
		        	//获取后台传递参数className methodName
					var className = $("#semiList").datagrid("getData").className;
					var methodName = $("#semiList").datagrid("getData").methodName;
					/**
					 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
					 * 主要是查询时，datagrid属性className、methodName会丢失
					 */
					if(className == "" || className==null || className == undefined){
						className = $('#classNameM2005').val();
					}
					if(methodName == "" || methodName==null || methodName == undefined){
						methodName = $('#methodNameM2005').val();
					}
					//获取表头信息
					var header = $("#semiList").datagrid("options").columns[0];
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
					mapParam.set("url","/export/exportExcel");
					mapParam.set("fields",fields);
					mapParam.set("titles",titles);
					mapParam.set("className",className);
					mapParam.set("methodName",methodName);
					mapParam.set("excelName","纵剪带库存台账信息");
					mapParam.set("loginUserId",$("#user_id").html());
					mapParam.set("rows","10000");
					mapParam.set("page","1");
					//查询条件 把所有查询条件带上
					mapParam.set("prodPlanNo",$('#prodPlanNoM2005').val());
					mapParam.set("prodId",$('#prodIdM2005').val());
					mapParam.set("prodLuno",$('#prodLunoM2005').val());
					mapParam.set("prodParentNo",$('#prodParentNoM2005').val());
					mapParam.set("prodStatus",$('#prodStatusM2005').combobox("getValue"));
					mapParam.set("prodMaterial",$('#prodMaterialM2005').combobox("getValue"));
					mapParam.set("prodNorm",$('#prodNormM2005').val());
					mapParam.set("prodBc",$('#prodBcM2005').combobox("getValue"));
					mapParam.set("prodOutPlan",$('#prodOutPlanM2005').val());
					mapParam.set("prodMakeJz",$('#prodMakeJzM2005').combobox("getValue"));
					mapParam.set("prodMakeType",$('#prodMakeTypeM2005').combobox("getValue"));
					mapParam.set("startDate",$('#startDateM2005').datebox("getValue"));
					mapParam.set("endDate",$('#endDateM2005').datebox("getValue"));
					
					BSL.toExcel(mapParam);
		        }
		    }];
</script>