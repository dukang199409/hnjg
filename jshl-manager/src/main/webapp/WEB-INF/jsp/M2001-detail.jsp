<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="planDetailFormM2001" class="itemForm" method="post">
	    <table cellpadding="5">
	    	<tr>
	    		<td width="120" align="right">纵剪带生产指令号:</td>
	            <td width="210" align="right">
	            	<input name="planId" id="planIdM2001Detail" class="easyui-textbox" panelHeight="auto"  type="text" data-options="required:false" style="width:200px;"  readonly="readonly"></input>
	            </td>
	       		<td width="120" align="right">用料规格:</td>
	            <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" readonly="readonly"  data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	        	 <td width="120" align="right">钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" class="easyui-combobox" readonly="readonly" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	            <td width="120"  align="right">用料炉号:</td>
	            <td width="210"  align="right">
	            	<input name="planLuno" class="easyui-textbox" panelHeight="auto"  type="text" data-options="required:false" style="width:200px;"  readonly="readonly"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120"  align="right">用料重量/吨:</td>
	            <td width="210"  align="right">
	            	<input name="prodWeight" class="easyui-numberbox" type="text"  readonly="readonly"  data-options="required:false,min:0,precision:3,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	        	<td width="120"  align="right">用料数量/卷:</td>
	            <td width="210"  align="right">
	            	<input name="prodNum" class="easyui-numberbox" type="text" readonly="readonly"  data-options="required:true,min:0,precision:0,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120"  align="right">生产部门:</td>
	            <td width="210"  align="right">
	            	<select name="planDepartment" panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true" style="width:200px;" readonly="readonly">
			          <option value="">请选择...</option>
			         <c:forEach items="${planDepartmentList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	            
	        	<td width="120"  align="right">制造产品名称:</td>
	            <td width="210"  align="right">
	            	<input name="makeName" class="easyui-textbox" readonly="readonly" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	            <td width="120" align="right">制造纵剪带用途:</td>
	            <td width="210" align="right">
	            	<select name="makeType" class="easyui-combobox" readonly="readonly" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${makeTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        	<td width="120" align="right">生产订单号:</td>
	            <td width="210"  align="right">
	            	<input name="prodOrder" class="easyui-textbox" readonly="readonly" data-options="required:false,validType:'length[0,20]'" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	        	<td width="120" align="right">工厂:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" name="company" readonly="readonly" data-options="required:false,validType:'length[0,20]'" style="text; width: 200px;"></input>
	            </td>
	            <td width="120" align="right">来料客户:</td>
	            <td width="210" align="right">
	            	<select name="customer" class="easyui-combobox" panelHeight="auto" readonly="readonly" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${customerList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr> 
	            <td width="120" align="right">备注:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" name="remark" readonly="readonly" data-options="required:false,validType:'length[0,120]'" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	        	<td width="120" align="right"></td>
	            <td width="210"  align="right">
	            </td>
	            <td width="120" align="right"></td>
	            <td width="210"  align="right">
	            </td>
	        </tr>
	    </table>
	     <table class="easyui-datagrid" id="planDetailList" title="纵剪带生产调度单维护信息管理"  style="height:320px"
		       data-options="singleSelect:true,rownumbers:true,fitColumns:true,collapsible:true,toolbar:toolbarM2001D">
		    <thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
		        	<th data-options="field:'planInfoDetailId',width:140">调度计划编号</th>
		            <th data-options="field:'prodNorm',width:100">制造规格</th>
		            <th data-options="field:'prodLevel',width:120,formatter:BSL.formatProdLevel">质量等级</th>
		            <th data-options="field:'prodWeight',width:80">用料重量/吨</th>
		            <th data-options="field:'prodNum',width:80">条数</th>
		            <th data-options="field:'planOutputVolume',width:100">计划产出量</th>
		            <th data-options="field:'planFinistDate',width:110,formatter:BSL.formatDateTime">计划完工日期</th>
		            <th data-options="field:'collectedUnits',width:80,formatter:BSL.formatMakeDept">实收机组</th>
		            <th data-options="field:'crtDate',formatter:BSL.formatDateTime,width:100">创建日期</th>
		            <th data-options="field:'prodInputuser',width:100">产品录入人</th>
		            <th data-options="field:'remark',width:100">备注</th>
		        </tr>
		    </thead>
		</table>
	</form>
	<div id="planDetailAddWindow" class="easyui-window" title="新增纵剪计划" data-options="modal:true,closed:true,iconCls:'icon_add',href:'/planDetail/M2002-add'" style="width:780px;height:270px;padding:10px;">
	</div>
	<div id="planDetailEditWindow" class="easyui-window" title="编辑纵剪计划" data-options="modal:true,closed:true,iconCls:'icon-edit',href:'/planDetail/M2002-edit'" style="width:780px;height:320px;padding:10px;">
	</div>
</div>
<script type="text/javascript">

	function getSelectionsPlanInfoDetailId(){
		var receiptList = $("#planDetailList");
		var sels = receiptList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].planInfoDetailId);
		}
		ids = ids.join(",");
		return ids;
	}
	
	var toolbarM2001D = [{
	    text:'新增',
	    iconCls:'icon-add',
	    handler:function(){
	    	$("#planDetailAddWindow").window({
	    		onLoad :function(){
	    			var ids = getSelectionsPlanInfoDetailId();
	    			if(ids.length > 0){
	    				//回显数据
	        			var data = $("#planDetailList").datagrid("getSelections")[0];
	        			data.planId = $("#planIdM2001Detail").val();
	        			data.planFinistDateM2002 = BSL.formatDate(data.planFinistDate); 
	        			$("#planDetailAddForm").form("load",data);
	    			}else{
	    				var data = {"planId":$("#planIdM2001Detail").val()};
	    				$("#planDetailAddForm").form("load",data);
	    			}
	    		}
	    	}).window("open");
	    }
	},{
	    text:'编辑',
	    iconCls:'icon-edit',
	    handler:function(){
	    	var ids = getSelectionsPlanInfoDetailId();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','必须选择一条记录编辑!');
	    		return ;
	    	}
	    	if(ids.indexOf(',') > 0){
	    		$.messager.alert('提示','只能选择一条记录编辑!');
	    		return ;
	    	}
	    	
	    	$("#planDetailEditWindow").window({
	    		onLoad :function(){
	    			//回显数据
	    			var data = $("#planDetailList").datagrid("getSelections")[0];
	    			data.planFinishDateM2002 = BSL.formatDate(data.planFinistDate); 
	    			data.planId = $("#planIdM2001Detail").val();
	    			$("#planDetailEditForm").form("load",data);
	    		}
	    	}).window("open");
	    }
	},{
	    text:'删除',
	    iconCls:'icon-cancel',
	    handler:function(){
	    	var ids = getSelectionsPlanInfoDetailId();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','必须选择一条记录删除!');
	    		return ;
	    	}
	    	if(ids.indexOf(',') > 0){
	    		$.messager.alert('提示','一次最多删除一条记录!');
	    		return ;
	    	}
	    	$.messager.confirm('确认','确定删除本条记录？',function(r){
	    	    if (r){
	    	    	var params = {"planInfoDetailId":ids};
	            	$.post("/planDetail/delete",params, function(data){
	        			if(data.status == 200){
	        				$.messager.alert('提示','删除成功!',undefined,function(){
	        					serachM2001Detail();
	        				});
	        			} else {
	        				$.messager.alert('提示',data.msg);
	        			}
	        		});
	    	    }
	    	});
	    }
	}];
	
	//重新查询
	function serachM2001Detail(){
		var params = {"planId": $("#planIdM2001Detail").val()};
      	$.post("/plan/detail",params, function(data){
  			if(data.status == 200){
  				$('#planDetailList').datagrid('loadData', data.data);
  			} else {
  				$.messager.alert('提示','详情查询失败：'+data.msg);
  			}
  		});
	}
	
</script>
