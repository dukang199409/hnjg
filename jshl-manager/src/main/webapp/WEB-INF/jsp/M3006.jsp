<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="产品制造信息查询" style="padding:10px 10px 10px 10px">
		<form id="semiMakeM3006Form" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">产品生产处理指令号:</td>
		            <td width="210" align="right">
		            	<input name="prodOutPlan" id="prodOutPlanM3006"  class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">物料编码:</td>
		            <td width="210" align="right">
		            	<input name="prodId" id="prodIdM3006"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		       <tr>
		        	<td width="120" align="right">来源钢卷号:</td>
		            <td width="210" align="right">
		            	<input name="prodOrirawid" id="prodOrirawidM3006" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		       		<td width="120" align="right">产品状态:</td>
		            <td width="210" align="right">
		            	<select name="prodStatus" id="prodStatusM3006"  class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodHalfStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	 </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM3006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM3006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM3006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM3006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		              <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM3006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM3006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>	
		        <tr hidden="true">
		        	<td width="120" align="right">录入人:</td>
		            <td width="210" align="right">
		            	<input name="prodInputuser" id="prodInputuserM3006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>		        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3006Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3006Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="semiMakeM3006List" title="纵剪带制造信息管理"  style="height:600px"
	       data-options="singleSelect:true,rownumbers:true,collapsible:true,pagination:true,url:'/semi/queryMakeInfo',method:'post',onBeforeLoad:onBeforeLoadM3006,pageSize:30,toolbar:toolbarM3006">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'prodOutPlan',width:100">出库指令号</th>
	            <th data-options="field:'prodId',width:165">物料编码</th>
	            <th data-options="field:'prodType',width:100,formatter:BSL.formatProdType">产品类型</th>
	        	<th data-options="field:'prodPlanNo',width:150">生产指令号</th>
	        	<th data-options="field:'prodName',width:100">物料名称</th>
	        	<th data-options="field:'prodLuno',width:120">炉(批)号</th>
	        	<th data-options="field:'prodOrirawid',width:100">来源钢卷号</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatHalfProdStatus">状态</th>
	            <th data-options="field:'prodRelWeight',width:125">复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125">入库重量/吨</th>
	            <th data-options="field:'prodRuNum',width:150">制造产品包数</th>
	            <th data-options="field:'prodRuWeight',width:150">制造产品重量/吨</th>
	        	<th data-options="field:'prodNorm',width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:80,formatter:BSL.formatProdMaterial">钢种</th>
	            <th data-options="field:'prodInputuser',width:70">录入人</th>
	            <th data-options="field:'prodCheckuser',width:70">修改人</th>
	            <th data-options="field:'crtDate',width:150,formatter:BSL.formatFullDateTime">入库日期</th>
	            <th data-options="field:'updDate',width:150,formatter:BSL.formatFullDateTime">修改日期</th>
	            <th data-options="field:'remark',width:226">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM3006(){
		var queryParams = $('#semiMakeM3006List').datagrid('options').queryParams;
		queryParams.prodOutPlan = $('#prodOutPlanM3006').val();
		queryParams.prodId = $('#prodIdM3006').val();
		queryParams.prodStatus = $('#prodStatusM3006').combobox("getValue");
		queryParams.prodOrirawid = $('#prodOrirawidM3006').val();
	}
	
	//查询按钮
	function searchM3006Form(){
		var prodOutPlan = $('#prodOutPlanM3006').val();
		if(prodOutPlan == null || prodOutPlan == ''){
			$.messager.alert('提示','产品生产处理指令号不能为空');
			return;
		}
		//page页码
		var page = $("#semiMakeM3006List").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#semiMakeM3006List").datagrid('options').pageSize; 
		$("#pageM3006").textbox('setValue',page);
		$("#rowsM3006").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/semi/queryMakeInfo",$("#semiMakeM3006Form").serialize(), function(data){
			if(data.status == 200){				
	            $('#semiMakeM3006List').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM3006").textbox('setValue',data.className);
				$("#methodNameM3006").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM3006();
	}
	
	/* 重置表单 */
	function clearM3006Form(){
		$('#semiMakeM3006Form').form('reset');
	}
   
	var toolbarM3006 = [{
		    	text : '导出EXCEL',
		        iconCls : 'icon-save',
		        handler : function() {
		        	//获取后台传递参数className methodName
					var className = $("#semiMakeM3006List").datagrid("getData").className;
					var methodName = $("#semiMakeM3006List").datagrid("getData").methodName;
					/**
					 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
					 * 主要是查询时，datagrid属性className、methodName会丢失
					 */
					if(className == "" || className==null || className == undefined){
						className = $('#classNameM3006').val();
					}
					if(methodName == "" || methodName==null || methodName == undefined){
						methodName = $('#methodNameM3006').val();
					}
					//获取表头信息
					var header = $("#semiMakeM3006List").datagrid("options").columns[0];
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
					mapParam.set("excelName","纵剪带制造信息");
					mapParam.set("loginUserId",$("#user_id").html());
					mapParam.set("rows","10000");
					mapParam.set("page","1");
					//查询条件 把所有查询条件带上
					mapParam.set("prodOutPlan",$('#prodOutPlanM3006').val());
					mapParam.set("prodId",$('#prodIdM3006').val());
					mapParam.set("prodStatus",$('#prodStatusM3006').combobox("getValue"));
					mapParam.set("prodOrirawid",$('#prodOrirawidM3006').val());
					
					BSL.toExcel(mapParam);
		        }
		    }];
</script>