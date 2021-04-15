<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="外协厂废品库存信息查询" style="padding:10px 10px 10px 10px">
		<form id="wasteWxSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">废品类型:</td>
		            <td width="210" align="right">
		            	<select name="wasteType" id="wasteTypeM4002" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${wasteTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM4002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM4002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM4002Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM4002Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="wasteWxInfoList" title="外协厂废品库存信息管理"  style="height:620px"
	       data-options="singleSelect:true,rownumbers:true,fitColumns:false,collapsible:true,url:'/wasteWx/listByCriteria',method:'post',toolbar:toolbarM4002">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'wasteType',width:120,formatter:BSL.formatWasteType">废品类型</th>
	        	<th data-options="field:'wasteWeight',width:200">废品库存重量/吨</th>
	        	<th data-options="field:'wasteInWeight',width:200">累计入库重量/吨</th>
	        	<th data-options="field:'wasteOutWeight',width:200">累计出库重量/吨</th>
	            <th data-options="field:'crtDate',width:150,formatter:BSL.formatDateTime">修改日期</th>
	            <th data-options="field:'remark',width:226">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="wasteWxInWindow" class="easyui-window" title="外协厂废品入库" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/waste/M4002-in'" style="width:780px;height:200px;padding:10px;">
	</div>
	<div id="wasteWxCheckWindow" class="easyui-window" title="外协厂重量校对修改" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/waste/M4002-check'" style="width:820px;height:230px;padding:10px;">
	</div>
</div>
<script>

	//查询按钮
	function searchM4002Form(){
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/wasteWx/listByCriteria",$("#wasteWxSearchForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#wasteWxInfoList').datagrid('loadData',  {"total":data.total,"rows":data.rows});
	            $("#classNameM4002").textbox('setValue',data.className);
				$("#methodNameM4002").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
	
	/* 重置表单 */
	function clearM4002Form(){
		$('#wasteWxSearchForm').form('reset');
	}

    function getM4002SelectionsIds(){
    	var receiptList = $("#wasteWxInfoList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].wasteType);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM4002 = [{
        text:'废品入库',
        iconCls:'icon-add',
        handler:function(){
        	$("#wasteWxInWindow").window({
        		onLoad :function(){
	        		var ids = getM4002SelectionsIds();
	        		if(ids.length == 1){
	        			var data = $("#wasteWxInfoList").datagrid("getSelections")[0];
	        			$("#wasteWxInForm").form("load",{"wasteType":data.wasteType});
	        		}
        		}
        	}).window("open");
        }
    },{
        text:'重量校对修改',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getM4002SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录!');
        		return ;
        	}        	
        	$("#wasteWxCheckWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#wasteWxInfoList").datagrid("getSelections")[0];
        			data.wasteTypeS = data.wasteType;
        			$("#wasteWxCheckForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#wasteWxInfoList").datagrid("getData").className;
			var methodName = $("#wasteWxInfoList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM4002').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM4002').val();
			}
			//获取表头信息
			var header = $("#wasteWxInfoList").datagrid("options").columns[0];
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
			mapParam.set("excelName","外协厂废品库存信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("wasteType",$('#wasteTypeM4002').combobox("getValue"));
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>