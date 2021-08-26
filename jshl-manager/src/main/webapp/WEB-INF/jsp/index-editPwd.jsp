<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="editPwdForm" class="itemForm" method="post">
	   <table>
	        <tr>
	            <td width="120" align="right">旧密码: </td>
	            <td width="210" align="right">
	            	<input name="oldPwd" id="oldPwd" class="easyui-textbox"  type="password" data-options="required:true,validType:'length[6,18]'" style="width:200px;"></input>
	            </td>         
	        </tr>
	        <tr>	
	            <td width="120" align="right">新密码:</td>
	            <td width="210" align="right">
	            	<input name="newPwd" id="newPwd" class="easyui-textbox" type="password" data-options="required:true,validType:'length[6,18]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">确认密码:</td>
	            <td width="210" align="right">
	            	<input name="cfmPwd" id="cfmPwd" class="easyui-textbox" type="password" data-options="required:true,validType:'length[6,18]'" style="width:200px;" maxLength="10"></input>
	            </td>
	        </tr>
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditPwdForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeEditPwdForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitEditPwdForm(){
		//有效性验证
		if(!$('#editPwdForm').form('validate')){
			$.messager.alert('提示','密码格式校验失败!');
			return;
		}
		var inputUser = $("#user_id").html();
		var oldPwd = $("#oldPwd").textbox("getValue");
		var newPwd = $("#newPwd").textbox("getValue");
		var cfmPwd = $("#cfmPwd").textbox("getValue");
		if( newPwd != cfmPwd ){
			$.messager.alert('提示','两次密码输入不一致！');
			return;
		}
		if( oldPwd == newPwd ){
			$.messager.alert('提示','新旧密码输入不能一致！');
			return;
		}
		var params = {"inputUser":inputUser,"oldPwd":oldPwd,"newPwd":newPwd};
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/user/editPwd",params, function(data){
			if(data.status == 200){
				$.messager.alert('提示','密码修改成功！','info',function(){
					$("#changePwdWindow").window('close');
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+'，错误信息：'+data.msg);
			}
		});
	}
	
	function closeEditPwdForm(){
		$("#changePwdWindow").window('close');
	}
	
</script>
