<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="用户权限查询" style="padding:10px 10px 10px 10px">
		<form id="userRoleForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">规则编号:</td>
		            <td width="210" align="right">
		            	<input name="bslRoleId" id="bslRoleIdM0003" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        	<td width="120"  align="right">人员角色:</td>
		            <td width="210"  align="right">
		            	<select name="userType" id="userTypeM0003"  panelHeight="150" class="easyui-combobox" data-options="editable:false" style="width:200px;">
		            	  <option value="">请选择...</option>
		            	  <c:forEach items="${userTypeList}" var="a">
			          		<option value="${a.enumKey}">${a.enumKey}-${a.enumValue}</option>
			         	  </c:forEach>
						</select>
		            </td>
		         </tr>
		         <tr>
		            <td width="120" align="right">限制界面编号:</td>
		            <td width="210" align="right">
		            	<input name="noOpenPages" id="noOpenPagesM0003"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		         	<td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM0003" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM0003" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		            <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM0003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM0003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM0003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM0003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM0003Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM0003Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="userRoleList" title="用户权限管理"  style="height:620px"
	       data-options="singleSelect:false,rownumbers:true,fitColumns:true,remoteSort:false,onSortColumn:sortSerachM0003,collapsible:true,pagination:true,url:'/role/listByCriteria',onBeforeLoad:onBeforeLoadM0003,method:'post',pageSize:30,toolbar:toolbarM0003">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'roleId',width:100,sortable:true">规则编号</th>
	            <th data-options="field:'userType',width:150,formatter:BSL.formatUserType,sortable:true">人员角色</th>
	            <th data-options="field:'menuId',width:150,sortable:true">界面编号</th>
	            <th data-options="field:'menuName',width:150,sortable:true">界面名称</th>
	            <th data-options="field:'remark',width:200,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="roleAddWindow" class="easyui-window" title="新增用户权限" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/role/M0003-add'" style="width:820px;height:520px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM0003(){
		var queryParams = $('#userRoleList').datagrid('options').queryParams;
		queryParams.bslRoleId = $('#bslRoleIdM0003').val();
		queryParams.userType = $('#userTypeM0003').combobox("getValue");
		queryParams.noOpenPages = $('#noOpenPagesM0003').val();
	}
	
	//排序查询
	function sortSerachM0003(sort,order){
		$("#sortM0003").textbox('setValue',sort);
		$("#orderM0003").textbox('setValue',order);
		searchM0003Form();
	}

	//查询按钮
	function searchM0003Form(){
		//page页码
		var page = $("#userRoleList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#userRoleList").datagrid('options').pageSize; 
		$("#pageM0003").textbox('setValue',page);
		$("#rowsM0003").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/role/listByCriteria",$("#userRoleForm").serialize(), function(data){
			if(data.status == 200){
	            $('#userRoleList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM0003").textbox('setValue',data.className);
				$("#methodNameM0003").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM0003();
	}
	
	/* 重置表单 */
	function clearM0003Form(){
		$('#userRoleForm').form('reset');
	}

    function getM0003SelectionsIds(){
    	var userRoleList = $("#userRoleList");
    	var sels = userRoleList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].roleId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM0003 = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$("#roleAddWindow").window({
        		onLoad :function(){
        			
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getM0003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中记录删除!');
        		return;
        	}
        	$.messager.confirm('确认','确定删除这些记录？',function(r){
        	    if (r){
        	    	var strArrays = ids.split(',');
        	    	var arrays = [];
        	    	for(var i=0;i<strArrays.length;i++){
        	    		var data = $("#userRoleList").datagrid("getSelections")[i];
        	    		arrays.push(strArrays[i]+';'+data.userType+';'+data.menuId);
        	    	}
        	    	var params = {'arrays':arrays};
        	    	//格式化数组
        	    	var ps = $.param(params, true);
                	$.post("/role/delete",ps, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','批量删除成功!',undefined,function(){
            					searchM0003Form();
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
			var className = $("#userRoleList").datagrid("getData").className;
			var methodName = $("#userRoleList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM0003').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM0003').val();
			}
			//获取表头信息
			var header = $("#userRoleList").datagrid("options").columns[0];
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
			mapParam.set("excelName","用户权限信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("bslRoleId",$('#bslRoleIdM0003').val());
			mapParam.set("userType",$('#userTypeM0003').combobox("getValue"));
			mapParam.set("noOpenPages",$('#noOpenPagesM0003').val());
			
			BSL.toExcel(mapParam);
        }
    }];
    
</script>