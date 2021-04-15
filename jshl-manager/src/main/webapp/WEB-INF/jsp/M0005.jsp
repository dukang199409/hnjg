<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="系统参数查询" style="padding:10px 10px 10px 10px">
		<form id="paramSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">参数编号:</td>
		            <td width="210" align="right">
		            	<input name="paramId" id="paramIdM1005" class="easyui-textbox" type="text" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">参数名称:</td>
		            <td width="210" align="right">
		            	<input name="paramName" id="paramNameM1005" class="easyui-textbox" type="text" style="width:200px;"></input>
		            </td>           
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM0005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM0005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM0005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM0005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM0005Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM0005Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="paramList" title="系统参数信息管理"  style="height:620px"
	       data-options="singleSelect:false,fitColumns:false,collapsible:true,pagination:true,url:'/param/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM0005,pageSize:30,toolbar:toolbarM0005">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'paramId',width:100">参数编码</th>
	            <th data-options="field:'paramName',width:180">参数名称</th>
	            <th data-options="field:'paramValue',width:120">参数值</th>
	            <th data-options="field:'updDate',width:120,formatter:BSL.formatDateTime">创建日期</th>
	            <th data-options="field:'remark',width:160">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="paramEditWindow" class="easyui-window" title="编辑数据字典子项信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/receipt/M0005-edit'" style="width:780px;height:210px;padding:10px;">
	</div>
</div>
<script>
	
	function onBeforeLoadM0005(){
		var queryParams = $('#paramList').datagrid('options').queryParams;
		queryParams.enumEnglishName = $('#enumEnglishNameM0005').val();
		queryParams.enumChineseName = $('#enumChineseNameM0005').val();
	}
	
	//排序查询
	function sortSerachM0005(sort,order){
		$("#sortM0005").textbox('setValue',sort);
		$("#orderM0005").textbox('setValue',order);
		searchM0005Form();
	}

	//查询按钮
	function searchM0005Form(){
		//page页码
		var page = $("#paramList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#paramList").datagrid('options').pageSize; 
		$("#pageM0005").textbox('setValue',page);
		$("#rowsM0005").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/param/listByCriteria",$("#paramSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#paramList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM0005").textbox('setValue',data.className);
				$("#methodNameM0005").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM0005();
	}
	
	/* 重置表单 */
	function clearM0005Form(){
		$('#paramSearchForm').form('reset');
	}

    function getM0005SelectionsIds(){
    	var receiptList = $("#paramList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].paramId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM0005 = [{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getM0005SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录编辑!');
        		return ;
        	}
        	
        	$("#paramEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#paramList").datagrid("getSelections")[0];
        			$("#paramEditForm").form("load",data);
        		}
        	}).window("open");
        }
    }];
    
</script>