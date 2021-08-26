<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="pEditByIding:10px 10px 10px 10px">
	<form id="salePlanDetailForm" class="itemForm" method="post">
	   <table>
	   		<tr>
	   			<td width="120" align="right">销售出库通知单号:</td>
	            <td width="210" align="right">
	            	<input name="bsId" id="bsIdM5001Detail" class="easyui-textbox" type="text" readonly="readonly" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">通知单类别: </td>
	            <td width="210" align="right">
	            	<select name="bsFlag" class="easyui-combobox" id="bsFlagM5001Detail" readonly="readonly" panelHeight="auto" data-options="editable:true" style="width:200px;">
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
	            <td width="120" align="right">实际出库总价:</td>
	            <td width="210" align="right">
	            	<input name="bsPrice" class="easyui-numberbox" readonly="readonly" type="text" data-options="required:false,min:0,precision:2,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">预计发货日期:</td>
	            <td width="210" align="right">
	            	<input name="arrDate" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
				<td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" readonly="readonly" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
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
	   <table class="easyui-datagrid" id="saleDetailList" title="销售出库通知单维护信息管理"  style="height:300px"
	       data-options="singleSelect:true,rownumbers:true,fitColumns:true,collapsible:true,toolbar:toolbarM5001D">
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
		            <th data-options="field:'salePrice',width:70">单价/元</th>
		            <th data-options="field:'saleStatus',width:120,formatter:BSL.formatSaleStatus">产品出库状态</th>
		       	</tr>
	    	</thead>
		</table>
	</form>
	<div id="saleDetailAddWindow" class="easyui-window" title="新增销售出库详细信息" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/saleDetail/M5002-add'" style="width:780px;height:310px;padding:10px;">
	</div>
	<div id="saleDetailAddByIdWindow" class="easyui-window" title="新增按产品销售出库详细信息" data-options="modal:true,closed:true,iconCls:'edit_add',href:'/saleDetail/M5002-addById'" style="width:1080px;height:540px;padding:10px;">
	</div>
	<div id="saleDetailEditWindow" class="easyui-window" title="编辑销售出库详细信息" data-options="modal:true,closed:true,iconCls:'saleDetail-edit',href:'/saleDetail/M5002-edit'" style="width:780px;height:320px;padding:10px;">
	</div>
	<div id="saleDetailEditByIdWindow" class="easyui-window" title="编辑按产品销售出库详细信息" data-options="modal:true,closed:true,iconCls:'saleDetail-edit',href:'/saleDetail/M5002-editById'" style="width:780px;height:300px;padding:10px;">
	</div>
</div>
<script type="text/javascript">
	
	function getM5002SelectionsIds(){
		var saleDetailList = $("#saleDetailList");
		var sels = saleDetailList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].saleSerno);
		}
		ids = ids.join(",");
		return ids;
	}
	
	var toolbarM5001D = [{
	    text:'新增',
	    iconCls:'icon-add',
	    handler:function(){
	    	$("#saleDetailAddWindow").window({
	    		onLoad :function(){
	    			var ids = getM5002SelectionsIds();
	    			if(ids.length > 0){
	    				//回显数据
	    				var data = $("#saleDetailList").datagrid("getSelections")[0];
	    				data.salePlanId = $("#bsIdM5001Detail").textbox('getValue');
	    				data.bsFlag = $("#bsFlagM5001Detail").combobox('getValue');
	        			$("#saleDetailAddForm").form("load",data);
	    			}else{
	    				var data = {};
	    				data.salePlanId = $("#bsIdM5001Detail").textbox('getValue');
	    				data.bsFlag = $("#bsFlagM5001Detail").combobox('getValue');
	    				$("#saleDetailAddForm").form("load",data);
	    			}
	    		}
	    	}).window("open");
	    }
	},{
	    text:'固定产品销售新增',
	    iconCls:'icon-add',
	    handler:function(){
	    	$("#saleDetailAddByIdWindow").window({
	    		onLoad :function(){
	    			var ids = getM5002SelectionsIds();
	    			var data = {};
	    			if(ids.length > 0){
	    				//回显数据
	    				data = $("#saleDetailList").datagrid("getSelections")[0];
	    				data.salePlanId = $("#bsIdM5001Detail").textbox('getValue');
	    				data.bsFlag = $("#bsFlagM5001Detail").combobox('getValue');
	        			$("#saleDetailAddByIdForm").form("load",data);
	    			}else{
	    				data.salePlanId = $("#bsIdM5001Detail").textbox('getValue');
	    				data.bsFlag = $("#bsFlagM5001Detail").combobox('getValue');
	    			}
    				$("#saleDetailAddByIdForm").form("load",data);
    				//加载可出库产品表格
    				var params = {"salePlanId":$("#bsIdM5001Detail").textbox('getValue')};
    		    	$.post("/saleDetail/getBsFlag",params, function(result){
    					if(result.status == 200){
    						var prods = result.data.lists;
    						var values = [];  
    						if(data.bsFlag != '4' && data.bsFlag != '9' && data.bsFlag != '11'){
    							for(var i=0;i<prods.length;i++){
    				                var a = {
    				                    'ck' : true,
    				                    'prodId' : prods[i].prodId,
    				                    'prodName' : prods[i].prodName,
    				                    'prodNorm' : prods[i].prodNorm,
    				                    'prodType' : prods[i].prodType,
    				                    'prodMaterial' : prods[i].prodMaterial,
    				                    'saleWeight' : prods[i].prodRelWeight,
    				                    'prodPrintWeight' : prods[i].prodPrintWeight
    				                };
    				                values.push(a);		
    							}
    						}else{
    							for(var i=0;i<prods.length;i++){
    				                var a = {
    				                    'ck' : true,
    				                    'prodId' : prods[i].wasteType,
    				                    'prodName' : prods[i].wasteType,
    				                    'prodType' : "3",
    				                    'prodPrintWeight' : prods[i].wasteWeight
    				                };
    				                values.push(a);		
    							}
    						}
    						$('#useFullProdList').datagrid('loadData', values);
    					} else {
    						$.messager.alert('提示','查询通知单状态失败：'+result.msg);
    					}
    				});
	    		}
	    	}).window("open");
	    }
	},{
	    text:'编辑',
	    iconCls:'icon-edit',
	    handler:function(){
	    	var ids = getM5002SelectionsIds();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','必须选择一条记录编辑!');
	    		return ;
	    	}
	    	if(ids.indexOf(',') > 0){
	    		$.messager.alert('提示','只能选择一条记录编辑!');
	    		return ;
	    	}
	    	//回显数据
			var data = $("#saleDetailList").datagrid("getSelections")[0];
	    	var salePlanId = $("#bsIdM5001Detail").textbox('getValue');
	    	var bsFlag = $("#bsFlagM5001Detail").combobox('getValue');
			if(salePlanId == '' || salePlanId == null){
				$.messager.alert('提示','生产销售通知单号不能为空!');
				return;
			}
	    	if(data.saleFlag == '2'){
	    		$("#saleDetailEditByIdWindow").window({
	        		onLoad :function(){
	        			//回显数据
	        			data.priceView = BSL.formatPrice(data.price);
	        			data.salePlanId = salePlanId;
	    				data.bsFlag = bsFlag;
	        			$("#saleDetailEditByIdForm").form("load",data);
	        		}
	        	}).window("open");
	    	}else{
	    		$("#saleDetailEditWindow").window({
	        		onLoad :function(){
	        			//回显数据
	        			data.priceView = BSL.formatPrice(data.price);
	        			data.salePlanId = salePlanId;
	    				data.bsFlag = bsFlag;
	        			$("#saleDetailEditForm").form("load",data);
	        		}
	        	}).window("open");
	    	}
	    }
	},{
        text:'销售出库单PDF打印',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM5002SelectionsIds();
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
        			mapParam.set("planId",$("#bsIdM5001Detail").textbox('getValue'));
        			mapParam.set("planDetailId",ids);
        			mapParam.set("tradeType","M5001o");
        			mapParam.set("loginUserId",$("#user_id").html());
        			BSL.toNewPagePDF(mapParam);
        	    }
			});
        }
    },{
        text:'销售出库单祥单PDF打印',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM5002SelectionsIds();
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
        			mapParam.set("planId",$("#bsIdM5001Detail").textbox('getValue'));
        			mapParam.set("planDetailId",ids);
        			mapParam.set("tradeType","M5001");
        			mapParam.set("loginUserId",$("#user_id").html());
        			BSL.toNewPagePDF(mapParam);
        	    }
			});
        }
    },{
	    text:'删除',
	    iconCls:'icon-cancel',
	    handler:function(){
	    	var ids = getM5002SelectionsIds();
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
	    	    	var params = {"saleSerno":ids};
	            	$.post("/saleDetail/delete",params, function(data){
	        			if(data.status == 200){
	        				$.messager.alert('提示','删除成功!',undefined,function(){
	        					serachM5001Detail()
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
	function serachM5001Detail(){
		var params = {"bsId": $("#bsIdM5001Detail").val()};
		$.post("/salePlan/detail",params, function(data){
  			if(data.status == 200){
  				$('#saleDetailList').datagrid('loadData', data.data);
  			} else {
  				$.messager.alert('提示','详情查询失败：'+data.msg);
  			}
  		});
	}
	
</script>
