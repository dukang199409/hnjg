<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>湖南巨工物料管理系统登陆</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
    <link type="text/css" rel="stylesheet" href="/css/login.css"/>
</head>
<body>
<form id="formlogin" method="post" onsubmit="return false;">
    <div class=" w1" id="entry" style="left: 65px; top: 100px;">
        <div class="mc " id="bgDiv" >
            <div id="entry-bg" clstag="passport|keycount|login|02" style="width: 531px; height: 455px; position: absolute; left: 20px; top: 20px; background: url(/images/20190501230142.png) 0px 0px no-repeat;">
			</div>
            <div class="form " style="left: 565px; top: 60px;">
                <div class="item fore1">
                    <span>员工编号</span>
                    <div class="item-ifo">
                        <input type="text" id="loginname" name="userId" class="text"  tabindex="1" autocomplete="off"/>
                        <div class="i-name ico"></div>
                        <label id="loginname_succeed" class="blank invisible"></label>
                        <label id="loginname_error" class="hide"><b></b></label>
                    </div>
                </div>
                <script type="text/javascript">
                    setTimeout(function () {
                        if (!$("#loginname").val()) {
                            $("#loginname").get(0).focus();
                        }
                    }, 0);
                </script>
                <div id="capslock"><i></i><s></s>键盘大写锁定已打开，请注意大小写</div>
                <div class="item fore2">
                    <span>密码</span>
                    <div class="item-ifo">
                        <input type="password" id="nloginpwd" name="password" class="text" tabindex="2" autocomplete="off" onkeydown="MyKeyDown()"/>
                        <div class="i-pass ico"></div>
                        <label id="loginpwd_succeed" class="blank invisible"></label>
                        <label id="loginpwd_error" class="hide"></label>
                    </div>
                </div>
                <div class="item login-btn2013">
                    <input type="button" class="btn-img btn-entry" id="loginsubmit" value="登录" tabindex="8" clstag="passport|keycount|login|06"/>
                </div>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
	var redirectUrl = "${redirect}";
	var LOGIN = {
			checkInput:function() {
				if ($("#loginname").val() == "") {
					$.messager.alert('提示','用户名不能为空!');
					$("#loginname").focus();
					return false;
				}
				if ($("#nloginpwd").val() == "") {
					$.messager.alert('提示','密码不能为空!');
					$("#nloginpwd").focus();
					return false;
				}
				return true;
			},
			doLogin:function() {
				$.post("/user/login", $("#formlogin").serialize(),function(data){
					if (data.status == 200) {
						if (redirectUrl == "") {
							//location.href = "http://localhost:8080/index";
							location.href = "http://121.5.38.133:8080/index";
						} else {
							location.href = redirectUrl;
						}
					} else {
						$.messager.confirm('错误','登录失败，原因是：' + data.msg);
						$("#loginname").select();
					}
				});
			},
			login:function() {
				if (this.checkInput()) {
					this.doLogin();
				}
			}
		
	};
	//回车事件
	function MyKeyDown(){
		 //回车键的键值为13
		if (event.keyCode==13){
			$("#loginsubmit").click();
		} 
	}
	$(function(){
		$("#loginname").select();
		$("#loginsubmit").click(function(){
			LOGIN.login();
		});
	});
	
</script>
</body>
</html>