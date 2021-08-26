<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <div class="easyui-panel" title="用户角色查询" style="padding:10px 10px 10px 10px">
	<form id="userTypeSearchForm" class="userForm">
	   <table>
	        <tr>
	        	<td width="120" align="right">员工编号:</td>
	            <td width="210" align="right">
	            	<input id="userIdM0002" name="userId" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">员工姓名: </td>
	            <td width="210" align="right">
	            	<input id="userNameM0002" name="userName" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">角色类型: </td>
	            <td width="210"  align="right">
	            	<select id="userTypeM0002"  name="userType" panelHeight="auto" class="easyui-combobox" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <!-- <option value="00">00-管理员</option> -->
			          <c:forEach items="${userTypeList}" var="a">
			          		<option value="${a.enumKey}">${a.enumKey}-${a.enumValue}</option>
			           </c:forEach>
					</select>
	            </td>	           
	        </tr>
	        <tr hidden="true">
	            <td width="120" align="right">页码:</td>
	            <td width="210" align="right">
	            	<input name="page" id="pageM0002" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">每页记录数:</td>
	            <td width="210" align="right">
	            	<input name="rows" id="rowsM0002" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	             <td width="120" align="right">排序字段:</td>
	            <td width="210" align="right">
	            	<input name="sort" id="sortM0002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">排序规则:</td>
	            <td width="210" align="right">
	            	<input name="order" id="orderM0002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	            <td width="120" align="right">类名:</td>
	            <td width="210" align="right">
	            	<input name="className" id="classNameM0002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">方法名:</td>
	            <td width="210" align="right">
	            	<input name="methodName" id="methodNameM0002" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	        </tr>	
	    </table>
	</form>
	
	<div style="margin-top:10px" align="center">
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchFormM0002()">查询</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearFormM0002()">重置</a>
	</div>
 </div>
 <table class="easyui-datagrid" id="userTypeList" title="用户角色管理" style="height:600px"
       data-options="singleSelect:true,rownumbers:true,fitColumns:true,remoteSort:false,onSortColumn:sortSerachM0002,collapsible:true,pagination:true,url:'/userType/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM0002,pageSize:30,toolbar:toolbarM0002">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'userId',width:100,sortable:true">员工编号</th>
            <th data-options="field:'userName',width:120,sortable:true">员工姓名</th>
            <th data-options="field:'userType',width:200,formatter:BSL.formatUserType,sortable:true">员工角色</th>
            <th data-options="field:'crtDate',width:120,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
            <th data-options="field:'remark',width:300">备注</th>
        </tr>
    </thead>
</table>
<script>

	function onBeforeLoadM0002(){
		var queryParams = $('#userTypeList').datagrid('options').queryParams;
		queryParams.userId = $('#userIdM0002').val();
		queryParams.userName = $('#userNameM0002').val();
		queryParams.userType = $('#userTypeM0002').combobox("getValue");
	}

	//排序查询
	function sortSerachM0002(sort,order){
		$("#sortM0002").textbox('setValue',sort);
		$("#orderM0002").textbox('setValue',order);
		searchFormM0002();
	}

	//提交表单
	function searchFormM0002(){
		//page页码
		var page = $("#userTypeList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#userTypeList").datagrid('options').pageSize; 
        $("#pageM0002").textbox('setValue',page);
		$("#rowsM0002").textbox('setValue',rows);
		//ajax的post方式提交表单
		$.post("/userType/listByCriteria", $("#userTypeSearchForm").serialize(), function(data){
            if(data.status == 200){
            	$("#classNameM0002").textbox('setValue',data.className);
    			$("#methodNameM0002").textbox('setValue',data.methodName);
                $('#userTypeList').datagrid('loadData', {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM0002();
	}
	
	function clearFormM0002(){
		$('#userTypeSearchForm').form('reset');
	}
	
    function getM0002SelectionsIds(){
    	var userTypeList = $("#userTypeList");
    	var sels = userTypeList.datagrid("getSelections");
    	return sels;
    }
    
    var toolbarM0002 = [{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var sels = getM0002SelectionsIds();
        	if(sels.length == 0){
        		$.messager.alert('提示','未选中员工角色信息!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除员工角色吗？',function(r){
        	    if (r){
        	    	var params = {"userId":sels[0].userId,"userType":sels[0].userType};
                	$.post("/userType/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除员工角色信息成功!',undefined,function(){
            					$("#userTypeList").datagrid("reload");
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
			var className = $("#userTypeList").datagrid("getData").className;
			var methodName = $("#userTypeList").datagrid("getData").methodName;
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM0002').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM0002').val();
			}
			//获取表头信息
			var header = $("#userTypeList").datagrid("options").columns[0];
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
			mapParam.set("url","/export/exportExcelExtra");
			mapParam.set("fields",fields);
			mapParam.set("titles",titles);
			mapParam.set("className",className);
			mapParam.set("methodName",methodName);
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1");
			mapParam.set("excelName","用户角色信息");
			mapParam.set("userId",$('#userIdM0002').val());
			mapParam.set("userName",$('#userNameM0002').val());
			mapParam.set("userType",$('#userTypeM0002').combobox("getValue"));
			
			BSL.toExcel(mapParam);
       		 }
 	}];
</script>