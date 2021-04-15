<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="prodPlanDoDetailFormM3001" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	    		<td width="120" align="right">产成品生产指令号:</td>
	            <td width="210" align="right">
	            	<input name="planId" id="planIdM3001Detail" class="easyui-textbox" panelHeight="auto"  type="text" data-options="required:false" style="width:200px;"  readonly="readonly"></input>
	            </td>
	       		<td width="120" align="right">用料规格:</td>
            	 <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	             <td width="120" align="right">用料钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" id="prodMaterialM3001Detail" class="easyui-combobox" readonly="readonly" panelHeight="auto" data-options="editable:true" style="width:200px;">
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
	            	<input name="prodWeight" class="easyui-numberbox" readonly="readonly" type="text"  readonly="readonly"  data-options="required:false,min:0,precision:3,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	        	<td width="120"  align="right">用料数量/条:</td>
	            <td width="210"  align="right">
	            	<input name="prodNum" class="easyui-numberbox" readonly="readonly" type="text" readonly="readonly"  data-options="required:false,min:0,precision:0,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120"  align="right">生产部门:</td>
	            <td width="210"  align="right">
	            	<select name="planDepartment" panelHeight="auto" readonly="readonly" class="easyui-combobox" data-options="editable:true,required:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${planDepartmentList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        	<td width="120"  align="right">制造产品名称:</td>
	            <td width="210"  align="right"> 
	            	<input name="makeName" id="makeNameM3001Detail" class="easyui-textbox" readonly="readonly" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        <tr>
	            <td width="120" align="right">制造产品规格:</td>
	             <td width="210" align="right">
	            	<input name="makeProdNorm" class="easyui-textbox" readonly="readonly" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">生产订单号:</td>
	            <td width="210"  align="right">
	            	<input name="prodOrder" id="prodOrderM3001Detail" class="easyui-textbox" readonly="readonly" data-options="required:false,validType:'length[0,20]'" style="text; width: 200px;"></input>
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
	     <table class="easyui-datagrid" id="prodPlanDetailList" title="产成品生产指令调度计划管理"  style="height:320px"
	       data-options="rownumbers:true,fitColumns:true,collapsible:true,toolbar:toolbarM3001D">
		    <thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
		        	<th data-options="field:'planInfoDetailId',width:160">调度计划编号</th>
		        	<th data-options="field:'makeName',width:100">产品名称</th>
		            <th data-options="field:'prodNorm',width:100">规格</th>
		            <th data-options="field:'prodMaterial',width:100,formatter:BSL.formatProdMaterial">钢种</th>
		            <th data-options="field:'prodLevel',width:120,formatter:BSL.formatProdLevel">质量等级</th>
		            <th data-options="field:'prodLength',width:60">定尺</th>
		            <th data-options="field:'prodNum',width:60">条数</th>
		            <th data-options="field:'planOutputVolume',width:100">计划产出量</th>
		            <th data-options="field:'prodMakeWeight',width:100">累计产出量</th>
		            <th data-options="field:'planOrder',width:100">销售订单号</th>
		            <th data-options="field:'planFinistDate',width:110,formatter:BSL.formatDateTime">计划完工日期</th>
		            <th data-options="field:'planDyz',width:80">短溢装</th>
		            <th data-options="field:'crtDate',formatter:BSL.formatDateTime,width:100">创建日期</th>
		            <th data-options="field:'prodInputuser',width:100">产品录入人</th>
		            <th data-options="field:'remark',width:100">备注</th>
		        </tr>
		    </thead>
		</table>
	</form>
	<div id="prodPlanDetailAddWindow" class="easyui-window" title="新增生产调度指令" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/prodPlanDetail/M3002-add'" style="width:780px;height:260px;padding:10px;">
	</div>
	<div id="prodPlanDetailEditWindow" class="easyui-window" title="编辑生产调度指令" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/prodPlanDetail/M3002-edit'" style="width:780px;height:280px;padding:10px;">
	</div>
</div>
<script type="text/javascript">

	function getM3002SelectionsIds(){
		var receiptList = $("#prodPlanDetailList");
		var sels = receiptList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].planInfoDetailId);
		}
		ids = ids.join(",");
		return ids;
	}
	
	var toolbarM3001D = [{
	    text:'新增',
	    iconCls:'icon-add',
	    handler:function(){
	    	$("#prodPlanDetailAddWindow").window({
	    		onLoad :function(){
	    			var ids = getM3002SelectionsIds();
	    			if(ids.length > 0){
	    				//回显数据
	    				var data = $("#prodPlanDetailList").datagrid("getSelections")[0];
	    				data.planId = $("#planIdM3001Detail").val();
	        			data.planFinistDateM3002 = BSL.formatDate(data.planFinistDate); 
	        			$("#prodPlanDetailAddForm").form("load",data);
	    			}else{
	    				var data = {"planId":$("#planIdM3001Detail").val()};
	    				$("#prodPlanDetailAddForm").form("load",data);
	    			}
	    		}
	    	}).window("open");
	    }
	},{
	    text:'编辑',
	    iconCls:'icon-edit',
	    handler:function(){
	    	var ids = getM3002SelectionsIds();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','必须选择一条记录编辑!');
	    		return ;
	    	}
	    	if(ids.indexOf(',') > 0){
	    		$.messager.alert('提示','只能选择一条记录编辑!');
	    		return ;
	    	}
	    	
	    	$("#prodPlanDetailEditWindow").window({
	    		onLoad :function(){
	    			//回显数据
	    			var data = $("#prodPlanDetailList").datagrid("getSelections")[0];
	    			data.planId = $("#planIdM3001Detail").val();
	    			data.planFinishDateM3002 = BSL.formatDate(data.planFinistDate); 
	    			$("#prodPlanDetailEditForm").form("load",data);
	    		}
	    	}).window("open");
	    }
	},{
	    text:'删除',
	    iconCls:'icon-cancel',
	    handler:function(){
	    	var ids = getM3002SelectionsIds();
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
	            	$.post("/prodPlanDetail/delete",params, function(data){
	        			if(data.status == 200){
	        				$.messager.alert('提示','删除成功!',undefined,function(){
	        					serachM3001Detail();
	        				});
	        			} else {
	        				$.messager.alert('提示','删除失败：'+data.msg);
	        			}
	        		});
	    	    }
	    	});
	    }
	}];
	
	//重新查询
	function serachM3001Detail(){
		var params = {"planId": $("#planIdM3001Detail").val()};
		$.post("/doProdPlan/detail",params, function(data){
  			if(data.status == 200){
  				/* $('#prodPlanDetailList').datagrid('loadData', data.data); */
  				var values = [];  
  				for (var i = 0; i < data.data.length; i++) {
  	                var a = {
  	                	'planInfoDetailId' : data.data[i].planInfoDetailId,
  	                	'makeName':$("#makeNameM3001Detail").textbox("getValue"),
	                    'prodNorm' : data.data[i].prodNorm,
	                    'prodMaterial' : $("#prodMaterialM3001Detail").combobox("getValue"),
	                    'prodLevel' : data.data[i].prodLevel,
	                    'prodLength' : data.data[i].prodLength,
	                    'prodNum' : data.data[i].prodNum,
	                    'planOutputVolume' : data.data[i].planOutputVolume,
	                    'planOrder' : $("#prodOrderM3001Detail").textbox("getValue"),
	                    'planFinistDate' : data.data[i].planFinistDate,
	                    'collectedUnits' : data.data[i].collectedUnits,
	                    'planDyz' : data.data[i].planDyz,
	                    'crtDate' : data.data[i].crtDate,
	                    'prodInputuser' : data.data[i].prodInputuser,
	                    'remark' : data.data[i].remark
  	                };
  	                values.push(a);				
  	            }
  	            $('#prodPlanDetailList').datagrid('loadData', values);
  			} else {
  				$.messager.alert('提示','详情查询失败：'+data.msg);
  			}
  		});
	}
	
	
</script>
