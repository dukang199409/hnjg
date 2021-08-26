<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>湖南巨工物料管理系统</title>

<!-- <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/super/css/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/super/superBlue.css" id="themeCss"/> -->
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<script type="text/javascript" charset="utf-8" src="easyui/theme/super/super.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:70px; background: url(/images/1212121212.png) 0px 0px no-repeat;">
		<span style="float:right;">
			<span id="user_id">${user.userId}</span>|${user.userName}
	         <button style="border:0px;background-color:transparent;text-decoration:underline;font-size:12px" onclick="pwdChange();">修改密码</button>
	         <button style="border:0px;background-color:transparent;text-decoration:underline;font-size:12px" onclick="loginOut();">退出</button>
	    </span>
	</div>
    <div data-options="region:'west',split:true" title="导航菜单" style="width:250px;">
    	<ul id="att"></ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		    	<div class="easyui-panel" title="个人信息" style="padding:10px 10px 10px 10px">
		    		<table>
				        <tr>
				            <td width="120"  align="right">员工编号:</td>
				            <td width="210"  align="right">
				            	<input class="easyui-textbox" type="text" id="userIdIndex" name="userId"  style="width: 200px;" readonly="readonly" value="${user.userId}"></input>
				            </td>
				            <td width="120"  align="right">员工姓名:</td>
				            <td width="210"  align="right">
				            	<input class="easyui-textbox" type="text" name="userName" style="width: 200px;" readonly="readonly" value="${user.userName}"></input>
				            </td>
				        </tr>
				        <tr>
				        	<td width="120"  align="right">用户状态:</td>
				            <td width="210"  align="right">
				            	<select name="userStatus" panelHeight="50" class="easyui-combobox" data-options="editable:false" style="text; width: 200px;" readonly="readonly" value="${user.userStatus}">
						          <option value="0">正常</option>
						          <option value="1">停用</option>
								</select>
				            </td>
				            <td width="120"  align="right">电话号码:</td>
				            <td width="210"  align="right">
				            	<input type="text" class="easyui-textbox textbox" id="userTelIndex" name="userTel"  data-options="prompt:'请输入正确的手机号码' , validType:'telNum'" value="${user.userTel}"  style="width:200px;">
				            </td>
				             <td width="120" align="left">
				        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updUserTel()" style="width:70px;">修改电话</a>
				        	</td>
				        </tr>
				     </table>
			    </div>
			    <div class="easyui-panel" title="个人角色信息" style="padding:10px 10px 10px 10px">
				     <table>
				        <tr>
				            <td width="120"  align="right">用户角色:</td>
				            <td width="550"  align="right">
				            	<input type="text" class="easyui-textbox textbox" id="userTypeName" name="remark" readonly="readonly" 
				            		style="width:540px" >
				            </td>
				        </tr>
				        <tr hidden="true">
				            <td width="120" align="right">用户角色id:</td>
				            <td width="210" align="right">
				            	<input id="userTypeIndex" class="easyui-textbox" type="text" value="${user.remark}"></input>
				            </td>
					    </tr>
				    </table>
			    </div>
		    </div>
		</div>
    </div>
    <div id="changePwdWindow" class="easyui-window" title="密码修改" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/user/index-editPwd'" style="width:460px;height:220px;padding:10px;">
	</div>
<script type="text/javascript">

	//初始化
	$(function(){
		$('#att').tree({
			url:'/enums/allow',
			onClick:function(node){
				var tab = $('#tabs').tabs('getTab',node.text);
				if(tab){
					//切换
					$('#tabs').tabs('select',node.text);
				}else{
					//子节点才可以打开tab页
					if(node.type == "1"){
						//添加新的选项卡
						$('#tabs').tabs('add',{
							title:node.text,
							href:node.href,
							closable:true
						});
					}
				}
			}
		});
		//初始化转换用户角色信息
		loadUserTypeChange();
	});
	
	//修改手机号码
	function updUserTel(){
		var userId = $("#userIdIndex").textbox('getValue');
		var userTel = $("#userTelIndex").textbox('getValue');
		var params = {'userId':userId,'userTel':userTel};
		$.messager.confirm('确认','确定修改电话号码吗？',function(r){
			if(r){
				$.post("/user/editTel",params, function(data){
					if(data.status == 200){
						$.messager.alert('提示','电话号码修改成功！','info',function(){
							$("#userTelIndex").textbox('setValue',userTel)
						});
					}else{
						$.messager.alert('提示','错误码：'+data.status+'，错误信息：'+data.msg);
					}
				});
			}
		});
	}
	
	//初始化转换用户角色信息
	function loadUserTypeChange(){
		var userTypeAll = $("#userTypeIndex").textbox('getValue');
		var userTypes = userTypeAll.split(';');
		var userTypeCh = '';
		for(var i = 0; i < userTypes.length; i++){
			if(userTypes[i] != ''){
				userTypeCh = userTypeCh + BSL.formatUserType(userTypes[i])+';';
			}
	 	}
		$("#userTypeName").textbox('setValue',userTypeCh);
	}

	//退出
	function loginOut(){
		$.messager.confirm('确认','确定退出吗？',function(r){
			if(r){
				var param={"userType":$("#user_type").html()};
		    	$.post("/user/loginOut",param, function(data){
					if(data.status == 200){
						window.location.href=data.data;
					} 
				});
			}
		});
	}
	
	//修改密码
	function pwdChange(){
		$("#changePwdWindow").window({
    		onLoad :function(){
    			
    		}
    	}).window("open");
	}
	
	//加载该用户菜单
	$(function(){
		$('#menu').tree({
			onClick: function(node){
				if($('#menu').tree("isLeaf",node.target)){
					var tabs = $("#tabs");
					var tab = tabs.tabs("getTab",node.text);
					if(tab){
						tabs.tabs("select",node.text);
					}else{
						tabs.tabs('add',{
						    title:node.text,
						    href: node.attributes.url,
						    closable:true,
						    bodyCls:"content"
						});
					}
				}
			}
		});
	});
	
</script>
</body>
</html>