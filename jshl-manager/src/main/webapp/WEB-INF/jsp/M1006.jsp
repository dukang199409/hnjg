<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="炉号质量信息查询" style="padding:10px 10px 10px 10px">
		<form id="sendFlagSerachForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="luId" id="luIdM1006" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">送检标志:</td>
		            <td width="210" align="right">
		            	<select name="sendFlag" id="sendFlagM1006" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				            <option value="">请选择...</option>
				            <c:forEach items="${nyFlagList}" var="a">
				          	   	<option value="${a.enumKey}">${a.enumValue}</option>
				            </c:forEach>
						</select>
		            </td>
		        </tr>
		       <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM1006" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM1006" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM1006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM1006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM1006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM1006" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM1006Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM1006Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="sendFlagList" title="炉号质量信息管理"  style="height:570px"
	       data-options="singleSelect:true,rownumbers:true,collapsible:true,pagination:true,url:'/sendFlag/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM1006,pageSize:30,toolbar:toolbarM1006">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'luId',width:150">炉(批)号</th>
	        	<th data-options="field:'sendFlag',width:150,formatter:BSL.formatIsOrNotStatus">送检标志</th>
	        	<th data-options="field:'sendResult',width:200">送检结果</th>
	        	<th data-options="field:'inputuser',width:150">录入人</th>
	        	<th data-options="field:'sendDate',formatter:BSL.formatDateTime,width:120">录入日期</th>
	            <th data-options="field:'updDate',width:120,formatter:BSL.formatDateTime">修改日期</th>
	            <th data-options="field:'remark',width:200">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="sendFlagAddWindow" class="easyui-window" title="新增送检信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/sendFlag/M1006-add'" style="width:810px;height:170px;padding:10px;">
	</div>
	<div id="sendFlagEditWindow" class="easyui-window" title="修改送检信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/sendFlag/M1006-edit'" style="width:810px;height:170px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM1006(){
		var queryParams = $('#sendFlagList').datagrid('options').queryParams;
		queryParams.luId = $('#luIdM1006').val();
		queryParams.sendFlag = $('#sendFlagM1006').combobox("getValue");
		queryParams.startDate = $('#startDateM1006').datebox("getValue");
		queryParams.endDate = $('#endDateM1006').datebox("getValue");
	}

	//查询按钮
	function searchM1006Form(){
		//page页码
		var page = $("#sendFlagList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#sendFlagList").datagrid('options').pageSize; 
		$("#pageM1006").textbox('setValue',page);
		$("#rowsM1006").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/sendFlag/listByCriteria",$("#sendFlagSerachForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#sendFlagList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM1006").textbox('setValue',data.className);
				$("#methodNameM1006").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM1006();
	}
	
	/* 重置表单 */
	function clearM1006Form(){
		$('#sendFlagSerachForm').form('reset');
	}

	/*获取表格炉号*/
    function getM1006SelectionsIds(){
    	var receiptList = $("#sendFlagList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].luId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM1006 = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	var ids = getM1006SelectionsIds();
        	var data = $("#sendFlagList").datagrid("getSelections")[0];
        	$("#sendFlagAddWindow").window({
        		onLoad :function(){
        			//回显数据
        			$("#sendFlagAddForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getM1006SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录编辑!');
        		return ;
        	}
        	var data = $("#sendFlagList").datagrid("getSelections")[0];
        	/* if(data.prodStatus != "1"){
        		$.messager.alert('提示','本交易只允许修改入库的卷板信息!');
        		return ;
        	} */
        	var user = $("#user_id").html(); 
        	$("#sendFlagEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			$("#sendFlagEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getM1006SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中记录!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次只能删除一条记录!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除？',function(r){
        	    if (r){
        	    	var params = {"luId":ids};
                	$.post("/sendFlag/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除成功!',undefined,function(){
            					$("#sendFlagList").datagrid("reload");
            				});
            			} else {
            				$.messager.alert('提示',data.msg);
            			}
            		});
        	    }
        	});
        }
    },{
    	text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
        	//获取后台传递参数className methodName
			var className = $("#sendFlagList").datagrid("getData").className;
			var methodName = $("#sendFlagList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM1006').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM1006').val();
			}
			//获取表头信息
			var header = $("#sendFlagList").datagrid("options").columns[0];
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
			mapParam.set("excelName","炉号送检结果信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","10000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("luId",$('#luIdM1006').val());
			mapParam.set("sendFlag",$('#sendFlagM1006').combobox("getValue"));
			mapParam.set("startDate",$('#startDateM1006').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM1006').datebox("getValue"));
			BSL.toExcel(mapParam);
        }
    }];
    
</script>