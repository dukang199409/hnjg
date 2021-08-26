<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="pEditByIding:10px 10px 10px 10px">
	<form id="salePlanDoDetailFormWx" class="itemForm" method="post">
	   <table>
	   		<tr>
	   			<td width="120" align="right">销售出库通知单号:</td>
	            <td width="210" align="right">
	            	<input name="bsId" id="bsIdM5003wDetail" class="easyui-textbox" type="text" readonly="readonly" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">通知单类别: </td>
	            <td width="210" align="right">
	            	<select name="bsFlag" class="easyui-combobox" readonly="readonly" panelHeight="auto" data-options="editable:true" style="width:200px;">
			         	  <option value="">请选择...</option>
				           <c:forEach items="${bsFlagList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>	           
	        </tr>
	        <tr>	          
	            <td width="120" align="right">供应商:</td>
	            <td width="210" align="right">
	            	<input name="bsCompany" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">客户:</td>
	            <td width="210" align="right">
	            	<input name="bsCustomer" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">含质保书: </td>
	            <td width="210" align="right">
	            	<select name="bsHasguarantee" class="easyui-combobox" readonly="readonly" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <c:forEach items="${nyFlagList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">应出库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="bsWeight" class="easyui-numberbox" readonly="readonly" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            
	            <td width="120" align="right">运输单位:</td>
	            <td width="210" align="right"> 
	            	<input name="bsTransport" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">运输车号:</td>
	            <td width="210" align="right">
	            	<input name="bsCarno" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">提货方式:</td>
	            <td width="210" align="right">
		            <select name="bsGettype" class="easyui-combobox" readonly="readonly" panelHeight="auto" data-options="editable:true,required:false" style="width:200px;">
				       <option value="">请选择...</option>
				      <c:forEach items="${bsGettypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
				</td>
				<td width="120" align="right">订单号:</td>
	            <td width="210" align="right">
	            	<input name="bsOrderNo" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	         <tr>
	        	<td width="120" align="right">实际出库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="bsRelweight" class="easyui-numberbox" readonly="readonly" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">实际出库数量:</td>
	            <td width="210" align="right">
	            	<input name="bsAmt" class="easyui-numberbox" readonly="readonly" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">预计发货日期:</td>
	            <td width="210" align="right">
	            	<input name="arrDate" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
	            </td>
				<td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>
	        </tr>
	    </table>
	    <table class="easyui-datagrid" id="saleDetailListM5003w" title="销售出库详细信息管理"  style="height:300px"
	       data-options="singleSelect:true,rownumbers:true,fitColumns:true,collapsible:true,toolbar:toolbarM5003wD">
	    	<thead>
		        <tr>
		        	<th data-options="field:'ck',checkbox:true"></th>
		        	<th data-options="field:'saleSerno',width:120">销售计划流水</th>
		            <th data-options="field:'saleFlag',width:130,formatter:BSL.formatSaleType">出库标准</th>
		            <th data-options="field:'prodNorm',width:100">计划出库规格</th>
		            <th data-options="field:'prodMaterial',width:100,formatter:BSL.formatProdMaterial">计划出库钢种</th>
		            <th data-options="field:'prodLength',width:100">计划出库定尺</th>
		            <th data-options="field:'saleNum',width:100">计划出库数量</th>
		            <th data-options="field:'saleWeight',width:100">计划出库重量/吨</th>
		            <th data-options="field:'prodId',width:150,formatter:BSL.formatWasteType">计划出库产品编号</th>
		            <th data-options="field:'prodSumnum',width:100">累计出库数量</th>
		            <th data-options="field:'prodSumweight',width:120">累计出库重量/吨</th>
		            <th data-options="field:'saleStatus',width:120,formatter:BSL.formatSaleStatus">产品出库状态</th>
		        </tr>
		    </thead>
		</table>
	</form>
</div>
<script type="text/javascript">

	function getM5003wDSelectionsIds(){
		var saleDetailList = $("#saleDetailListM5003w");
		var sels = saleDetailList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].saleSerno);
		}
		ids = ids.join(",");
		return ids;
	}
	
	var toolbarM5003wD = [{
        text:'销售出库单PDF打印',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM5003wDSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录PDF打印!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录PDF打印!');
        		return ;
        	}
        	$.messager.confirm('确认','是否需要PDF打印？',function(r){
				if (r){
        	    	var mapParam = new Map();
        			mapParam.set("url","/print/exportPdf");
        			mapParam.set("planId",$("#bsIdM5003wDetail").textbox('getValue'));
        			mapParam.set("planDetailId",ids);
        			mapParam.set("tradeType","M5001o");
        			mapParam.set("loginUserId",$("#user_id").html());
        			BSL.toNewPagePDF(mapParam);
        	    }
			});
        }
    },{
        text:'销售出库单详单PDF打印',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM5003wDSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录PDF打印!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条记录PDF打印!');
        		return ;
        	}
        	$.messager.confirm('确认','是否需要PDF打印？',function(r){
				if (r){
        	    	var mapParam = new Map();
        			mapParam.set("url","/print/exportPdf");
        			mapParam.set("planId",$("#bsIdM5003wDetail").textbox('getValue'));
        			mapParam.set("planDetailId",ids);
        			mapParam.set("tradeType","M5001");
        			mapParam.set("loginUserId",$("#user_id").html());
        			BSL.toNewPagePDF(mapParam);
        	    }
			});
        }
    }];
	
	//重新查询
	function serachM5003wDetail(){
		var params = {"bsId": $("#bsIdM5003wDetail").val()};
		$.post("/doSalePlan/detail",params, function(data){
				if(data.status == 200){
					$('#saleDetailListM5003w').datagrid('loadData', data.data);
				} else {
					$.messager.alert('提示','详情查询失败：'+data.msg);
				}
			});
	}

</script>
