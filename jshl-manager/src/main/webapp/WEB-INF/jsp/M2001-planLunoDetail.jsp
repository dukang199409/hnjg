<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="用料炉号查询" style="padding:10px 10px 10px 10px">
		<form id="planLunoInfoFormM2001" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">生产指令号:</td>
		            <td width="210" align="right">
		            	<input name="planId" id="planIdM2001PlanLuno" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		        	<td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="planLuno" id="planLunoM2001PlanLuno" class="easyui-textbox" type="text"  data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM2001PlanLuno" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM2001PlanLuno" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>       
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM2001PlanLunoForm()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM2001PlanLunoForm()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="planLunoInfoListM2001" title="用料炉号管理"  style="height:500px"
	       data-options="singleSelect:false,rownumbers:true,collapsible:true,pagination:true,url:'/planLuInfo/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM2001PlanLuno,pageSize:30,toolbar:toolbarM2001PlanLuno">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'planId',width:250">生产指令号</th>
	            <th data-options="field:'planLuno',width:250">用料炉号</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	function onBeforeLoadM2001PlanLuno(){
		var queryParams = $('#planLunoInfoListM2001').datagrid('options').queryParams;
		queryParams.planId = $('#planIdM2001PlanLuno').val();
		queryParams.planLuno = $('#planLunoM2001PlanLuno').val();
	}

	//查询按钮
	function searchM2001PlanLunoForm(){
		//page页码
		var page = $("#planLunoInfoListM2001").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#planLunoInfoListM2001").datagrid('options').pageSize; 
		$("#pageM2001PlanLuno").textbox('setValue',page);
		$("#rowsM2001PlanLuno").textbox('setValue',rows);
		//ajax的post方式提交表单
		$.post("/planLuInfo/listByCriteria",$("#planLunoInfoFormM2001").serialize(), function(data){
			if(data.status == 200){
	            $('#planLunoInfoListM2001').datagrid('loadData',  {"total":data.total,"rows":data.rows});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM2001PlanLuno();
	}
	
	/* 重置表单 */
	function clearM2001PlanLunoForm(){
		$('#planLunoM2001PlanLuno').textbox("setValue","");
	}
	
	 var toolbarM2001PlanLuno = [{
	        text : '导出EXCEL',
	        iconCls : 'icon-save',
	        handler : function() {
				//获取表头信息
				var header = $("#planLunoInfoListM2001").datagrid("options").columns[0];
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
				mapParam.set("rows","5000");
				mapParam.set("page","1");
				mapParam.set("className","planLunoInfoServiceImpl");
				mapParam.set("methodName","getLunoByPlanFY");
				mapParam.set("excelName","纵剪带指令用料炉号信息");
				mapParam.set("loginUserId",$("#user_id").html());
				/* mapParam.set("rows","1");
				mapParam.set("page","5000"); */
				//查询条件 把所有查询条件带上
				mapParam.set("planId",$('#planIdM2001PlanLuno').val());
				mapParam.set("planLuno",$('#planLunoM2001PlanLuno').val());
				
				BSL.toExcel(mapParam);
	        		}
	 }];
 

</script>