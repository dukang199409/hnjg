<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <div class="easyui-panel" title="人员查询" style="padding:10px 10px 10px 10px">
	<form id="userSearchForm" class="userForm">
	   <table>
	        <tr>
	        	<td width="120" align="right">员工编号:</td>
	            <td width="210" align="right">
	            	<input id="userIdM0001" name="userId" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">员工姓名: </td>
	            <td width="210" align="right">
	            	<input id="userNameM0001" name="userName" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>	           
	        </tr>
	        <tr hidden="true">
	           <td width="120" align="right">页码:</td>
	            <td width="210" align="right">
	            	<input name="page" id="pageM0001" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">每页记录数:</td>
	            <td width="210" align="right">
	            	<input name="rows" id="rowsM0001" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	             <td width="120" align="right">排序字段:</td>
	            <td width="210" align="right">
	            	<input name="sort" id="sortM0001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">排序规则:</td>
	            <td width="210" align="right">
	            	<input name="order" id="orderM0001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	            <td width="120" align="right">类名:</td>
	            <td width="210" align="right">
	            	<input name="className" id="classNameM0001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">方法名:</td>
	            <td width="210" align="right">
	            	<input name="methodName" id="methodNameM0001" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	        </tr>	
	    </table>
	</form>
	
	<div style="margin-top:10px" align="center">
    	<a href="javascript:void(0)" class="easyui-linkbutton" id="searchFormM0001" onclick="searchFormM0001()">查询</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearFormM0001()">重置</a>
	</div>
 </div>
 <table class="easyui-datagrid" id="userList" title="人员管理" style="height:600px"
       data-options="singleSelect:true,rownumbers:true,fitColumns:true,remoteSort:false,onSortColumn:sortSerachM0001,collapsible:true,pagination:true,url:'/user/query',method:'post',onBeforeLoad:onBeforeLoadM0001,pageSize:10,toolbar:toolbarM0001">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'userId',width:100,sortable:true">员工编号</th>
            <th data-options="field:'userName',width:100,sortable:true">姓名</th>
            <th data-options="field:'userPassword',width:100">登录密码</th>
            <th data-options="field:'userTel',width:100">电话号码</th>
            <th data-options="field:'userStatus',width:70,formatter:BSL.formatItemStatus,sortable:true">用户状态</th>
            <th data-options="field:'crtDate',width:100,formatter:BSL.formatDateTime,sortable:true">创建日期</th>
            <th data-options="field:'remark',width:300">备注</th>
        </tr>
    </thead>
</table>
<div id="userAddWindow" class="easyui-window" title="新增员工" data-options="modal:true,closed:true,iconCls:'icon-add',href:'/user/M0001-add'" style="height:170px;width:780px" >
</div>
<div id="userEditWindow" class="easyui-window" title="编辑员工" data-options="modal:true,closed:true,iconCls:'icon-edit',href:'/user/M0001-edit'" style="height:210px;width:780px">
</div>
 <div id="userTypeAddWindow" class="easyui-window" title="员工角色配置" data-options="modal:true,closed:true,iconCls:'icon-add',href:'/user/M0002-add'" style="height:170px;width:780px" >
 </div>
<script>

	function onBeforeLoadM0001(){
		var queryParams = $('#userList').datagrid('options').queryParams;
		queryParams.userId = $('#userIdM0001').val();
		queryParams.userName = $('#userNameM0001').val();
	}

	//排序查询
	function sortSerachM0001(sort,order){
		$("#sortM0001").textbox('setValue',sort);
		$("#orderM0001").textbox('setValue',order);
		searchFormM0001();
	}

	//提交表单
	function searchFormM0001(){
		$('#searchFormM0001').linkbutton('disable');
		//page页码
		var page = $("#userList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#userList").datagrid('options').pageSize; 
		$("#pageM0001").textbox('setValue',page);
		$("#rowsM0001").textbox('setValue',rows);
		//ajax的post方式提交表单
		$.post("/user/query", $("#userSearchForm").serialize(), function(data){
			if(data.status == 200){
				$("#classNameM0001").textbox('setValue',data.className);
				$("#methodNameM0001").textbox('setValue',data.methodName);
	            $('#userList').datagrid('loadData', {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
			$('#searchFormM0001').linkbutton('enable');
		}); 
		onBeforeLoadM0001();
	}
	
	function clearFormM0001(){
		$('#userSearchForm').form('reset');
	}
	

    function getM0001SelectionsIds(){
    	var userList = $("#userList");
    	var sels = userList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].userId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM0001 = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$("#userAddWindow").window({
        		onLoad :function(){
        			$("#userNameM0001Add").textbox('textbox').focus();
        		}
        	}).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getM0001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个员工才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个员工!');
        		return ;
        	}
        	
        	$("#userEditWindow").window({
        		onLoad :function(){
        			$("#userNameM0001Edit").textbox('textbox').focus();
        			//回显数据
        			var data = $("#userList").datagrid("getSelections")[0];
        			data.priceView = BSL.formatPrice(data.price);
        			$("#userEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getM0001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中员工!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能一次删除一个员工!');
        		return ;
        	}
        	if($("#user_id").html() == ids){
        		$.messager.alert('提示','不能删除自己！');
        		return ;
        	}
        	
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的员工吗？',function(r){
        	    if (r){
        	    	var params = {"userId":ids};
                	$.post("/user/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除员工成功!',undefined,function(){
            					$("#userList").datagrid("reload");
            				});
            			} else {
            				$.messager.alert('提示',data.msg);
            			}
            		});
        	    }
        	});
        }
    },{
        text:'配置角色',
        iconCls:'icon-ok',
        handler:function(){
        	var ids = getM0001SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个员工才能配置角色!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个员工配置角色!');
        		return ;
        	}
        	
        	$("#userTypeAddWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#userList").datagrid("getSelections")[0];
        			data.priceView = BSL.formatPrice(data.price);
        			$("#userTypeAddForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
			//获取后台传递参数className methodName
			var className = $("#userList").datagrid("getData").className;
			var methodName = $("#userList").datagrid("getData").methodName;
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM0001').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM0001').val();
			}
			//获取表头信息
			var header = $("#userList").datagrid("options").columns[0];
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
			mapParam.set("url","/export/exportExcel");
			mapParam.set("fields",fields);
			mapParam.set("titles",titles);
			mapParam.set("className",className);
			mapParam.set("methodName",methodName);
			mapParam.set("excelName","人员信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("userId",$('#userIdM0001').val());
			mapParam.set("userName",$('#userNameM0001').val());
			
			BSL.toExcel(mapParam);
        }
 	}];
</script>