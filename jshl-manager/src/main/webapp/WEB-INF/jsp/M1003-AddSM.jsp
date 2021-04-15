<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<table cellpadding="5">
     	 <tr>
        	<td width="150"  align="right">扫码倒计时:</td>
            <td width="210"  align="left">
            	<div id="showSMAlertM1003">600秒</div>
            </td>       
        </tr>
         <tr>
        	<td width="150"  align="right">原料入库数据:</td>
            <td width="210"  align="right">
            	<input name="preRawInfo" id="preRawSM1003" class="easyui-textbox" type="text" data-options="required:false,multiline:true" style="width:300px;height:400px;"></input>
            </td>  
             <td width="60"  align="left">
            	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeSMAlertM1003()">确认</a>
            </td>        
        </tr>
	 </table>
</div>
<script type="text/javascript">

	var startSec = 600;
	var timeM1003 = setInterval(function timeChange(){
		startSec = startSec - 1;
		//console.log(startSec);
		showSMAlertM1003.innerHTML = startSec + "秒"; 
		if(startSec<=0){
			clearInterval(timeM1003);
		}
	}, 1000);
	
	function closeSMAlertM1003(){
		$("#rawSMAddAlertWindow").window('close');
	}
	
</script>
