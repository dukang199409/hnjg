<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="历史产品信息查询" style="padding:10px 10px 10px 10px">
		<form id="prodInfoHSerachForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">单号/指令号:</td>
		            <td width="210" align="right">
		            	<input name="prodPlanNo" id="prodPlanNoM7001h" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">产品编号:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM7001h" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		        	<td width="120" align="right">产品类别:</td>
		            <td width="210" align="right">
		            	<select name="prodType" id="prodTypeM7001h" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				            <c:forEach items="${prodTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		        	<td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="prodLuno" id="prodLunoM7001h"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		        </tr>
		       <tr>
		       		<td width="120" align="right">产品状态:</td>
		            <td width="210" align="right">
		            	<select name="prodStatus" id="prodStatusM7001h" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				         <c:forEach items="${prodStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM7001h"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
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
		            	<input name="prodOutPlan" id="prodOutPlanM7001h" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">卷板来源:</td>
		            <td width="210" align="right">
		            	<select name="prodSource" id="prodSourceM7001h" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodSourceList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		         	<td width="120" align="right">外协厂标志:</td>
		            <td width="210" align="right">
		            	<select name="prodDclFlag" id="prodDclFlagM7001h" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodDclFlagList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
						</select>
					</td>
		        </tr>
		        <tr>
		        	
		        	<td width="120" align="right">来源钢卷号:</td>
		            <td width="210" align="right">
		            	<input name="prodOrirawid" id="prodOrirawidM7001h" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        	 <td width="120" align="right">生产机组:</td>
		            	<td width="210" align="right">
		            	<select name="prodMakeJz" id="prodMakeJzM7001h" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				           <option value="">请选择...</option>
				          <c:forEach items="${prodUnitsList}" var="a">
				          	   	<option value="${a.enumKey}">${a.enumValue}</option>
				          </c:forEach>
						</select>
		            </td> 
		        </tr>
		        <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM7001h" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM7001h" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM7001h" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM7001h" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM7001h" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM7001h" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM7001h" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM7001h" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM7001hForm()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM7001hForm()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="prodInfoHList" title="历史产品信息"  style="height:570px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM7001h,collapsible:true,pagination:true,url:'/prodInfo/listH',method:'post',onBeforeLoad:onBeforeLoadM7001h,pageSize:30,toolbar:toolbarM7001h">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodId',width:180,sortable:true">产品编号</th>
	        	<th data-options="field:'prodType',width:100,formatter:BSL.formatProdType,sortable:true">产品类别</th>
	        	<th data-options="field:'prodPlanNo',width:140,sortable:true">单号/指令号</th>
	        	<th data-options="field:'prodName',width:100,sortable:true">产品名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:100,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	        	<th data-options="field:'prodLuno',width:120,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100,sortable:true">来源钢卷号</th>
	            <th data-options="field:'prodRecordWeight',width:125,sortable:true">原料来料重量/吨</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">原料入库重量/吨</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	            <th data-options="field:'prodParentNo',width:180,sortable:true">父级产品编号</th>
	            <th data-options="field:'prodOutPlan',width:100,sortable:true">出库指令号</th>
	            <th data-options="field:'prodSaleSerno',width:100,sortable:true">销售计划号</th>
	        	<th data-options="field:'prodLength',width:80,sortable:true">定尺/米</th>
	        	<th data-options="field:'prodNum',width:80,sortable:true">数量</th>
	        	<th data-options="field:'prodDclFlag',width:130,formatter:BSL.formatProdDclFlag,sortable:true">外协厂标志</th>
	        	<th data-options="field:'prodCompany',width:100,sortable:true">厂家</th>
	            <th data-options="field:'prodLevel',width:90,formatter:BSL.formatProdLevel,sortable:true">质量等级</th>
	            <th data-options="field:'prodSource',width:90,formatter:BSL.formatProdSource,sortable:true">产品来源</th>
	            <th data-options="field:'prodUserType',width:90,formatter:BSL.formatMakeType,sortable:true">纵剪带用途</th>
	            <th data-options="field:'prodBc',width:120,formatter:BSL.formatProdBcStatus,sortable:true">生产班次</th>
	            <th data-options="field:'prodRuc',width:100,sortable:true,formatter:BSL.formatProdRuc">入库仓库/区</th>
	            <th data-options="field:'prodMakeJz',width:120,formatter:BSL.formatPlanJz,sortable:true">生产机组</th>
	        	<th data-options="field:'prodOutCarno',width:100,sortable:true">发货车次流水</th>
	        	<th data-options="field:'prodOriId',width:100,sortable:true">原产品编号</th>
	            <th data-options="field:'prodInputuser',width:70,sortable:true">录入人</th>
	            <th data-options="field:'prodCheckuser',width:70,sortable:true">修改人</th>
	            <th data-options="field:'crtDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'prodOutDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">出库日期</th>
	            <th data-options="field:'updDate',width:140,formatter:BSL.formatFullDateTime,sortable:true">修改日期</th>
	            <th data-options="field:'remark',width:100,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM7001h(){
		var queryParams = $('#prodInfoHList').datagrid('options').queryParams;
		queryParams.prodPlanNo = $('#prodPlanNoM7001h').val();
		queryParams.prodId = $('#prodIdM7001h').val();
		queryParams.prodType = $('#prodTypeM7001h').combobox("getValue");
		queryParams.prodLuno = $('#prodLunoM7001h').val();
		queryParams.prodStatus = $('#prodStatusM7001h').combobox("getValue");
		queryParams.prodMaterial = $('#prodMaterialM7001h').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM7001h').val();
		queryParams.prodOutPlan = $('#prodOutPlanM7001h').val();
		queryParams.prodSource = $('#prodSourceM7001h').combobox("getValue");
		queryParams.prodDclFlag = $('#prodDclFlagM7001h').combobox("getValue");
		queryParams.prodMakeJz = $('#prodMakeJzM7001h').combobox("getValue");
		queryParams.prodOrirawid = $('#prodOrirawidM7001h').val();
		queryParams.startDate = $('#startDateM7001h').datebox("getValue");
		queryParams.endDate = $('#endDateM7001h').datebox("getValue");
	}

	//排序查询
	function sortSerachM7001h(sort,order){
		$("#sortM7001h").textbox('setValue',sort);
		$("#orderM7001h").textbox('setValue',order);
		searchM7001hForm();
	}

	//查询按钮
	function searchM7001hForm(){
		//page页码
		var page = $("#prodInfoHList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#prodInfoHList").datagrid('options').pageSize; 
		$("#pageM7001h").textbox('setValue',page);
		$("#rowsM7001h").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/prodInfo/listH",$("#prodInfoHSerachForm").serialize(), function(data){
			if(data.status == 200){				
				$('#prodInfoHList').datagrid('loadData', {"total":data.total,"rows":data.rows});
				$("#classNameM7001h").textbox('setValue',data.className);
				$("#methodNameM7001h").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM7001h();
	}
	
	/* 重置表单 */
	function clearM7001hForm(){
		$('#prodInfoHSerachForm').form('reset');
	}
    
    var toolbarM7001h = [{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#prodInfoHList").datagrid("getData").className;
			var methodName = $("#prodInfoHList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM7001h').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM7001h').val();
			}
			//获取表头信息
			var header = $("#prodInfoHList").datagrid("options").columns[0];
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
			mapParam.set("excelName","历史产品信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","10000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("prodPlanNo",$('#prodPlanNoM7001h').val());
			mapParam.set("prodId",$('#prodIdM7001h').val());
			mapParam.set("prodType",$('#prodTypeM7001h').combobox("getValue"));
			mapParam.set("prodLuno",$('#prodLunoM7001h').val());
			mapParam.set("prodStatus",$('#prodStatusM7001h').combobox("getValue"));
			mapParam.set("prodMaterial",$('#prodMaterialM7001h').combobox("getValue"));
			mapParam.set("prodNorm",$('#prodNormM7001h').val());
			mapParam.set("prodOutPlan",$('#prodOutPlanM7001h').val());
			mapParam.set("prodSource",$('#prodSourceM7001h').combobox("getValue"));
			mapParam.set("prodMakeJz",$('#prodMakeJzM7001h').combobox("getValue"));
			mapParam.set("prodOrirawid",$('#prodOrirawidM7001h').val());
			mapParam.set("startDate",$('#startDateM7001h').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM7001h').datebox("getValue"));
			mapParam.set("prodDclFlag",$('#prodDclFlagM7001h').combobox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>