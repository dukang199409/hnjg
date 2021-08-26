<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="数据字典信息查询" style="padding:10px 10px 10px 10px">
		<form id="enumSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">数据字典英文名称:</td>
		            <td width="210" align="right">
		            	<input name="enumEnglishName" id="enumEnglishNameM0004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,50]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">数据字典中文名称:</td>
		            <td width="210" align="right">
		            	<input name="enumChineseName" id="enumChineseNameM0004" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,100]'" style="width:200px;"></input>
		            </td>           
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM0004" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM0004" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM0004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM0004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM0004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM0004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM0004Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM0004Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="enumList" title="数据字典信息管理"  style="height:620px"
	       data-options="singleSelect:false,rownumbers:true,fitColumns:false,remoteSort:false,onSortColumn:sortSerachM0004,collapsible:true,pagination:true,url:'/enum/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM0004,pageSize:30,toolbar:toolbarM0004">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'enumEnglishName',width:150,sortable:true">数据字典英文名称</th>
	            <th data-options="field:'enumChineseName',width:150,sortable:true">数据字典中文名称</th>
	            <th data-options="field:'enumKey',width:120,sortable:true">数据字典key值</th>
	            <th data-options="field:'enumValue',width:150,sortable:true">数据字典value值</th>
	            <th data-options="field:'enumOrder',width:150,sortable:true">数据字典排序值</th>
	        </tr>
	    </thead>
	</table>
	<div id="enumAddWindow" class="easyui-window" title="新增数据字典子项信息" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/receipt/M0004-add'" style="width:780px;height:210px;padding:10px;">
	</div>
	<div id="enumEditWindow" class="easyui-window" title="编辑数据字典子项信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/receipt/M0004-edit'" style="width:780px;height:210px;padding:10px;">
	</div>
</div>
<script>
	
	function onBeforeLoadM0004(){
		var queryParams = $('#enumList').datagrid('options').queryParams;
		queryParams.enumEnglishName = $('#enumEnglishNameM0004').val();
		queryParams.enumChineseName = $('#enumChineseNameM0004').val();
	}
	
	//排序查询
	function sortSerachM0004(sort,order){
		$("#sortM0004").textbox('setValue',sort);
		$("#orderM0004").textbox('setValue',order);
		searchM0004Form();
	}

	//查询按钮
	function searchM0004Form(){
		//page页码
		var page = $("#enumList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#enumList").datagrid('options').pageSize; 
		$("#pageM0004").textbox('setValue',page);
		$("#rowsM0004").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/enum/listByCriteria",$("#enumSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#enumList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM0004").textbox('setValue',data.className);
				$("#methodNameM0004").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM0004();
	}
	
	/* 重置表单 */
	function clearM0004Form(){
		$('#enumSearchForm').form('reset');
	}

    function getM0004SelectionsIds(){
    	var receiptList = $("#enumList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].enumEnglishName);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM0004 = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	var ids = getM0004SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条字典新增!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条字典新增!');
        		return ;
        	}
        	$("#enumAddWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#enumList").datagrid("getSelections")[0];
        			data.enumKey = '';
        			data.enumValue = '';
        			data.enumOrder = '';
        			$("#enumAddForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getM0004SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录编辑!');
        		return ;
        	}
        	
        	$("#enumEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#enumList").datagrid("getSelections")[0];
        			$("#enumEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getM0004SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录删除!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除这些记录？',function(r){
        	    if (r){
        	    	var strArrays = ids.split(',');
        	    	var arrays = [];
        	    	for(var i=0;i<strArrays.length;i++){
        	    		var data = $("#enumList").datagrid("getSelections")[i];
        	    		arrays.push(data.enumEnglishName+';'+data.enumKey);
        	    	}
        	    	var params = {'arrays':arrays};
        	    	//格式化数组
        	    	var ps = $.param(params, true);
                	$.post("/enum/delete",ps, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','批量删除成功!',undefined,function(){
            					searchM0004Form();
            				});
            			} else {
            				$.messager.alert('提示','批量删除失败：'+data.msg);
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
			var className = $("#enumList").datagrid("getData").className;
			var methodName = $("#enumList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM0004').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM0004').val();
			}
			//获取表头信息
			var header = $("#enumList").datagrid("options").columns[0];
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
			mapParam.set("excelName","数据字典信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("enumEnglishName",$('#enumEnglishNameM0004').val());
			mapParam.set("enumChineseName",$('#enumChineseNameM0004').val());
			
			BSL.toExcel(mapParam);
        }
    },{
        text:'一键同步',
        iconCls:'icon-reload',
        handler:function(){
        	$.messager.confirm('确认','确定一键同步？',function(r){
        	    if (r){
                	$.post("/enum/synchData",null, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示', '一键同步');
            			} else {
            				$.messager.alert('提示','一键同步失败：'+data.msg);
            			}
            		});
        	    }
        	});
        }
    }];
    
</script>