<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="炉号质量信息查询" style="padding:10px 10px 10px 10px">
		<form id="qualitySearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">炉(批)号:</td>
		            <td width="210" align="right">
		            	<input name="luId" id="luIdM1005" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">厂家:</td>
		            <td width="210" align="right">
		            	<input name="luCompany" id="luCompanyM1005"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,100]'" style="width:200px;"></input>
		            </td>
		        </tr>
		       <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM1005" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM1005" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM1005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM1005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM1005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM1005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>
	       		 <tr hidden="true">
		            <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM1005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM1005" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM1005Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM1005Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="qualityList" title="炉号质量信息管理"  style="height:570px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM1005,collapsible:true,pagination:true,url:'/quality/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM1005,pageSize:30,toolbar:toolbarM1005">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'luId',width:100,sortable:true">炉(批)号</th>
	        	<th data-options="field:'luCompany',width:100,sortable:true">厂家</th>
	        	<th data-options="field:'chemicalC',width:100">化学成份C(x100)</th>
	        	<th data-options="field:'chemicalSi',width:100">化学成份Si(x100)</th>
	        	<th data-options="field:'chemicalMn',width:100">化学成份Mn(x100)</th>
	        	<th data-options="field:'chemicalP',width:100">化学成份P(x1000)</th>
	        	<th data-options="field:'chemicalS',width:100">化学成份S(x1000)</th>
	        	<th data-options="field:'chemicalTi',width:100">化学成份Ti(x100)</th>
	        	<th data-options="field:'chemicalNi',width:100">化学成份Ni(x100)</th>
	        	<th data-options="field:'chemicalCr',width:100">化学成份Cr(x100)</th>
	        	<th data-options="field:'chemicalCu',width:100">化学成份Cu(x100)</th>
	        	<th data-options="field:'chemicalNb',width:100">化学成份Nb(x100)</th>
	        	<th data-options="field:'mechanicalS',width:100">力学性能屈服点σs(MPa)</th>
	        	<th data-options="field:'mechanicalB',width:100">力学性能抗拉强度σb(Mpa)</th>
	        	<th data-options="field:'mechanicalL',width:100">力学性能伸长率(%)</th>
	        	<th data-options="field:'bendwc',width:100">弯曲180ºd=a</th>
	        	<th data-options="field:'bendyb',width:100">压扁</th>
	        	<th data-options="field:'impactT',width:100">V型冲击力温度T(℃)</th>
	        	<th data-options="field:'impactN1',width:100">V型冲击力数值1(J)</th>
	        	<th data-options="field:'impactN2',width:100">V型冲击力数值2(J)</th>
	        	<th data-options="field:'impactN3',width:100">V型冲击力数值3(J)</th>
	        	<th data-options="field:'crtDate',width:100,formatter:BSL.formatDateTime">创建日期</th>
	            <th data-options="field:'updDate',width:100,formatter:BSL.formatDateTime">修改日期</th>
	            <th data-options="field:'remark',width:100">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="qualityAddWindow" class="easyui-window" title="新增质量信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/raw/M1005-add'" style="width:810px;height:500px;padding:10px;">
	</div>
	<div id="qualityEditWindow" class="easyui-window" title="修改质量信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/raw/M1005-edit'" style="width:810px;height:500px;padding:10px;">
	</div>
	<div id="qualityImportWindow" class="easyui-window" title="质量信息批量导入" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/raw/M1005-import'" style="width:600px;height:180px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM1005(){
		var queryParams = $('#qualityList').datagrid('options').queryParams;
		queryParams.luId = $('#luIdM1005').val();
		queryParams.luCompany = $('#luCompanyM1005').val();
		queryParams.startDate = $('#startDateM1005').datebox("getValue");
		queryParams.endDate = $('#endDateM1005').datebox("getValue");
	}

	//排序查询
	function sortSerachM1005(sort,order){
		$("#sortM1005").textbox('setValue',sort);
		$("#orderM1005").textbox('setValue',order);
		searchM1005Form();
	}

	//查询按钮
	function searchM1005Form(){
		//page页码
		var page = $("#qualityList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#qualityList").datagrid('options').pageSize; 
		$("#pageM1005").textbox('setValue',page);
		$("#rowsM1005").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/quality/listByCriteria",$("#qualitySearchForm").serialize(), function(data){
			if(data.status == 200){				
	            $('#qualityList').datagrid('loadData', {"total":data.total,"rows":data.rows});
	            $("#classNameM1005").textbox('setValue',data.className);
				$("#methodNameM1005").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM1005();
	}
	
	/* 重置表单 */
	function clearM1005Form(){
		$('#qualitySearchForm').form('reset');
	}

	/*获取表格卷板号*/
    function getM1005SelectionsIds(){
    	var receiptList = $("#qualityList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].luId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM1005 = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	var ids = getM1005SelectionsIds();
        	var data = $("#qualityList").datagrid("getSelections")[0];
        	$("#qualityAddWindow").window({
        		onLoad :function(){
        			//回显数据
        			$("#qualityAddForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'批量导入',
        iconCls:'icon-reload',
        handler:function(){
        	$("#qualityImportWindow").window({
        		onLoad :function(){
        			//回显数据
        			
        		}
        	}).window("open");
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getM1005SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录编辑!');
        		return ;
        	}
        	var data = $("#qualityList").datagrid("getSelections")[0];
        	/* if(data.prodStatus != "1"){
        		$.messager.alert('提示','本交易只允许修改入库的卷板信息!');
        		return ;
        	} */
        	var user = $("#user_id").html(); 
        	$("#qualityEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			data.crtDateM1005Edit = BSL.formatDate(data.crtDate)
        			$("#qualityEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getM1005SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录删除!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多删除一条记录!');
        		return ;
        	}
        	var data = $("#qualityList").datagrid("getSelections")[0];
        	$.messager.confirm('确认','确定删除本条记录？',function(r){
        	    if (r){
        	    	var params = {"luId":ids};
                	$.post("/quality/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除成功!',undefined,function(){
            					searchM1005Form();
            				});
            			} else {
            				$.messager.alert('提示', data.msg);
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
			var className = $("#qualityList").datagrid("getData").className;
			var methodName = $("#qualityList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM1005').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM1005').val();
			}
			//获取表头信息
			var header = $("#qualityList").datagrid("options").columns[0];
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
			mapParam.set("excelName","炉号质量信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","10000");
			mapParam.set("page","1");
			//查询条件 把所有查询条件带上
			mapParam.set("luId",$('#luIdM1005').val());
			mapParam.set("luCompany",$('#luCompanyM1005').val());
			mapParam.set("startDate",$('#startDateM1005').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM1005').datebox("getValue"));
			BSL.toExcel(mapParam);
        }
    }];
    
</script>