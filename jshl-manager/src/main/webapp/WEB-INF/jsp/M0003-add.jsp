<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="roleAddForm" class="itemForm" method="post">
	   <table>
	        <tr>
	            <td width="120"  align="right">人员角色:</td>
	            <td width="210"  align="right">
	            	<select name="userType" id="userTypeM0003Add" panelHeight="150" class="easyui-combobox" data-options="editable:true,onChange:onUserTypeChangeM0003" style="width:200px;">
			          <option value="">请选择...</option>
			          <!-- <option value="00">00-管理员</option> -->
			           <c:forEach items="${userTypeList}" var="a">
			          		<option value="${a.enumKey}">${a.enumKey}-${a.enumValue}</option>
			           </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" id="remarkM0003Add" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>	           
	        </tr>
	    </table>
	    <table class="easyui-datagrid" id="menuList" title="可限制交易表"  style="height:360px"
	       data-options="rownumbers:true,iconCls:'icon-edit',fitColumns:true,collapsible:true,pageSize:30">
		    <thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
		            <th data-options="field:'menuId',width:100">交易代码</th>
		            <th data-options="field:'menuName',width:200">交易名称</th>
		        </tr>
		    </thead>
		</table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM0003AddForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM0003AddForm()">重置</a>
	</div>
</div>
<script type="text/javascript">

	//根据角色查询可限制信息
	function onUserTypeChangeM0003(n,o){
		var userType = $("#userTypeM0003Add").combobox("getValue");
		if(userType == '' || userType == null){
			$.messager.alert('提示','人员角色不能为空!');
			return;
		}
		var params = {"userType":userType};
		$.post("/role/getMenu",params, function(data){
			if(data.status == 200){
				var menus = data.data;
				var values = [];  
				for(var i=0;i<menus.length;i++){
	                var a = {
	                    'ck' : true,
	                    'menuId' : menus[i].menuId,
	                    'menuName' : menus[i].menuName
	                };
	                values.push(a);		
				}
				$('#menuList').datagrid('loadData', values);
			} else {
				$.messager.alert('提示','查询可限制交易列表失败：'+data.msg);
			}
		});
	}

	//获取表格menuId字段
	function getM0003AddByIdSelectionsIds(){
		var menuList = $("#menuList");
		var sels = menuList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].menuId);
		}
		ids = ids.join(",");
		return ids;
	}
	
	//提交表单
	function submitM0003AddForm(){
		
		var userType = $("#userTypeM0003Add").combobox("getValue");
		var remake = $("#remarkM0003Add").textbox("getValue");
		//获取选择内容
		var ids = getM0003AddByIdSelectionsIds();
    	if(ids.length == 0){
    		$.messager.alert('提示','没有限制交易记录无法提交!');
    		return ;
    	}
    	var strArrays = ids.split(',');
    	var arrays = [];
    	for(var i=0;i<strArrays.length;i++){
    		var data = $("#menuList").datagrid("getSelections")[i];
    		arrays.push(userType+';'+data.menuId+';'+data.menuName+';'+remake);
    	}
    	var params = {'arrays':arrays};
    	var ps = $.param(params, true);
		
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/role/addRole",ps, function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增权限信息成功!','info',function(){
					$("#roleAddWindow").window('close');
					searchM0003Form();
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function clearM0003AddForm(){
		$('#roleAddForm').form('reset');
		$('#menuList').datagrid('loadData',{ total: 0, rows: [] });
	}  
	
</script>
