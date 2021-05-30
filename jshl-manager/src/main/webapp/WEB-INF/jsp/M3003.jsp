<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="产成品生产调度单执行信息查询" style="padding:10px 10px 10px 10px">
		<form id="prodPlanDoSearchForm" class="itemForm" method="post">
		   <table>
		        <tr>
		        	<td width="120" align="right">生产调度指令号:</td>
		            <td width="210" align="right">
		            	<input name="planId" id="planIdM3003" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">指令状态:</td>
		            <td width="210" align="right">
		            	<select name="planStatus" id="planStatusM3003" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${planStatusList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	 </c:forEach>
						</select>
		            </td>	           
		        </tr>
		         <tr>
		        	<td width="120" align="right">用料炉号:</td>
		            <td width="210" align="right">
		            	<input name="planLuno" id="planLunoM3003" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
		            </td>
		         	<td width="120" align="right">用料规格:</td>
	            	 <td width="210" align="right">
		            	<input name="prodNorm" id="prodNormM3003" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
		            </td>
		        </tr>
		         <tr>
		            <td width="120" align="right">用料钢种:</td>
		            <td width="210" align="right">
		            	<select name="prodMaterial" id="prodMaterialM3003" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          		</c:forEach>
						</select>
		            </td>
		            <td width="120" align="right">制造产品名称:</td>
		            <td width="210" align="right">
		            	<input name="makeName" id="makeNameM3003" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr>
		       		<td width="120" align="right">制造产品规格:</td>
		             <td width="210" align="right">
		            	<input name="makeProdNorm" id="makeProdNormM3003" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">生产机组:</td>
		            <td width="210"  align="right">
		            	<select name="planJz" id="planJzM3003"  panelHeight="auto" class="easyui-combobox" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodUnitsSCList}" var="a">
				          	   	<option value="${a.enumKey}">${a.enumValue}</option>
				          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		         <tr>
		            <td width="120"  align="right">用料产品类型:</td>
		            <td width="210"  align="right">
		            	<select name="prodType" id="prodTypeM3003"  panelHeight="auto" class="easyui-combobox" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodTypeNeedList}" var="a">
				          	   	<option value="${a.enumKey}">${a.enumValue}</option>
				          	  </c:forEach>
						</select>
		            </td>
		        </tr>
		        <tr>
		            <td width="120" align="right">开始日期:</td>
		            <td width="210" align="right">
		            	<input name="startDate" id="startDateM3003" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">结束日期:</td>
		            <td width="210" align="right">
		            	<input name="endDate" id="endDateM3003" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM3003" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM3003" class="easyui-textbox" type="text" data-options="required:false" panelHeight="225px"  style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM3003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM3003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>
		        <tr hidden="true">
		             <td width="120" align="right">类名:</td>
		            <td width="210" align="right">
		            	<input name="className" id="classNameM3003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">方法名:</td>
		            <td width="210" align="right">
		            	<input name="methodName" id="methodNameM3003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>			        
		    </table>
		</form>
		
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM3003Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM3003Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="prodPlanDoList" title="产成品生产调度单执行信息管理"  style="height:620px"
	       data-options="singleSelect:true,rownumbers:true,remoteSort:false,onSortColumn:sortSerachM3003,collapsible:true,pagination:true,url:'/doProdPlan/listByCriteria',method:'post',onBeforeLoad:onBeforeLoadM3003,pageSize:30,toolbar:toolbarM3003">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'planId',width:130,sortable:true">产品生产指令号</th>
	            <th data-options="field:'planStatus',width:100,formatter:BSL.formatPlanStatus,sortable:true">指令状态</th>
	            <th data-options="field:'planJz',width:100,sortable:true,formatter:BSL.formatPlanJz">生产机组</th>
	            <th data-options="field:'prodType',width:100,sortable:true,formatter:BSL.formatProdType">用料产品类型</th>
	            <th data-options="field:'prodNorm',width:100,sortable:true">用料规格</th>
	        	<th data-options="field:'prodMaterial',width:100,formatter:BSL.formatProdMaterial,sortable:true">用料钢种</th>	
	        	<th data-options="field:'prodWeight',width:100,sortable:true">用料重量/吨</th>          
	        	<th data-options="field:'prodNum',width:100,sortable:true">用料数量/条</th>    
	            <th data-options="field:'makeName',width:120,sortable:true">制造产品名称</th>     
	        	<th data-options="field:'makeProdNorm',width:130,sortable:true">制造产品规格</th>
	            <th data-options="field:'alreadyNum',width:120,sortable:true">已生产数量</th>          
	        	<th data-options="field:'alreadySum',width:120,sortable:true">已生产重量/吨</th>  
	        	<th data-options="field:'outNum',width:100,sortable:true">投料数量</th>  
	        	<th data-options="field:'outWeight',width:100,sortable:true">投料重量</th>                  
	        	<th data-options="field:'ccrate',width:100,sortable:true">成材率</th>  
	            <th data-options="field:'prodOrder',width:120,sortable:true">销售订单号</th>
	            <th data-options="field:'company',width:120,sortable:true">工厂</th>
	            <th data-options="field:'customer',width:120,sortable:true,formatter:BSL.formatCustomer">来料客户</th>
	            <th data-options="field:'planDepartment',width:100,sortable:true,formatter:BSL.formatMakeDept">生产部门</th>
	            <th data-options="field:'crtDate',width:100,formatter:BSL.formatDateTime,sortable:true">日期</th>
	            <th data-options="field:'inputuser',width:90,sortable:true">制单人</th>
	            <th data-options="field:'remark',width:200,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
	<div id="prodPlanInfoDoWindow" class="easyui-window" title="产成品生产指令详情" data-options="modal:true,closed:true,iconCls:'icon_add',href:'/doProdPlan/M3003-detail'" style="width:1220px;height:650px;padding:10px;">
	</div>
	<div id="planLunoInfoWindowM3003" class="easyui-window" title="用料炉号详情" data-options="modal:true,closed:true,iconCls:'icon_add',href:'/doPlan/M2001-planLunoDetail'" style="width:780px;height:700px;padding:10px;">
	</div>
</div>
<script>

	function onBeforeLoadM3003(){
		var queryParams = $('#prodPlanDoList').datagrid('options').queryParams;
		queryParams.planId = $('#planIdM3003').val();
		queryParams.planStatus = $('#planStatusM3003').combobox("getValue");
		queryParams.planLuno = $('#planLunoM3003').val();
		queryParams.makeName = $('#makeNameM3003').val();
		queryParams.makeProdNorm = $('#makeProdNormM3003').val();
		queryParams.prodMaterial = $('#prodMaterialM3003').combobox("getValue");
		queryParams.prodNorm = $('#prodNormM3003').val();
		queryParams.planJz = $('#planJzM3003').combobox("getValue");
		queryParams.prodType = $('#prodTypeM3003').combobox("getValue");
		queryParams.startDate = $('#startDateM3003').datebox("getValue");
		queryParams.endDate = $('#endDateM3003').datebox("getValue");
	}

	//排序查询
	function sortSerachM3003(sort,order){
		$("#sortM3003").textbox('setValue',sort);
		$("#orderM3003").textbox('setValue',order);
		searchM3003Form();
	}

	//查询按钮
	function searchM3003Form(){
		//page页码
		var page = $("#prodPlanDoList").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#prodPlanDoList").datagrid('options').pageSize; 
		$("#pageM3003").textbox('setValue',page);
		$("#rowsM3003").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/doProdPlan/listByCriteria",$("#prodPlanDoSearchForm").serialize(), function(data){
			if(data.status == 200){
	            $('#prodPlanDoList').datagrid('loadData', {"total":data.total,"rows":data.rows});
				$("#classNameM3003").textbox('setValue',data.className);
				$("#methodNameM3003").textbox('setValue',data.methodName);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		onBeforeLoadM3003();
	}
	
	/* 重置表单 */
	function clearM3003Form(){
		$('#prodPlanDoSearchForm').form('reset');
	}

    function getM3003SelectionsIds(){
    	var receiptList = $("#prodPlanDoList");
    	var sels = receiptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].planId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbarM3003 = [{
        text:'详情',
        iconCls:'icon-search',
        handler:function(){
        	var ids = getM3003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录查看!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多查看一条记录!');
        		return ;
        	}
        	var dataG = $("#prodPlanDoList").datagrid("getSelections")[0];
  	    	var params = {"planId":ids};
          	$.post("/doProdPlan/detail",params, function(data){
      			if(data.status == 200){
      				$("#prodPlanInfoDoWindow").window({
      	        		onLoad :function(){
      	        			//回显数据
      	        			$("#prodPlanDoDetailFormM3003").form("load",dataG);
      	        			//回显详细
      	        			var values = [];  
	      	  				for (var i = 0; i < data.data.length; i++) {
	      	  	                var a = {
	      	  	                	'planInfoDetailId' : data.data[i].planInfoDetailId,
	      	  	                	'makeName':dataG.makeName,
	      		                    'prodNorm' : data.data[i].prodNorm,
	      		                    'prodMaterial' : dataG.prodMaterial,
	      		                    'prodLevel' : data.data[i].prodLevel,
	      		                    'prodLength' : data.data[i].prodLength,
	      		                    'prodNum' : data.data[i].prodNum,
	      		                    'planOutputVolume' : data.data[i].planOutputVolume,
	      		                    'planOrder' : dataG.prodOrder,
	      		                    'planFinistDate' : data.data[i].planFinistDate,
	      		                    'collectedUnits' : data.data[i].collectedUnits,
	      		                    'planDyz' : data.data[i].planDyz,
	      		                    'crtDate' : data.data[i].crtDate,
	      		                    'prodInputuser' : data.data[i].prodInputuser,
	      		                    'remark' : data.data[i].remark,
	      		                    'prodMakeWeight':data.data[i].prodMakeWeight
	      	  	                };
	      	  	                values.push(a);				
	      	  	            }
	      	  	            $('#prodPlanDoDetailListM3003').datagrid('loadData', values);
	      	  			}
      	        	}).window("open");
      			} else {
      				$.messager.alert('提示','详情查询失败：'+data.msg);
      			}
      		});
        }
    },{
        text:'执行',
        iconCls:'icon-reload',
        handler:function(){
        	var ids = getM3003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录执行!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多执行一条记录!');
        		return ;
        	}
        	var data = $("#prodPlanDoList").datagrid("getSelections")[0];
        	if(data.planStatus == "1"){
        		$.messager.alert('提示','正在执行中的指令无需重复执行!');
        		return ;
        	}
        	if(data.planStatus == "3" || data.planStatus == "4"){
        		if($("#user_id").html() != "000000"){
        			$.messager.alert('提示','只有超级管理员才允许把已完成/终止的指令重新执行!');
            		return ;
        		}
        	}
        	$.messager.confirm('确认','确定执行本条指令？',function(r){
        	    if (r){
        	    	var params = {"planId":ids,"inputuser": $("#user_id").html()};
                	$.post("/doProdPlan/execute",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','执行成功!',undefined,function(){
            					searchM3003Form();
            				});
            			} else {
            				$.messager.alert('提示','执行失败：'+data.msg);
            			}
            		});
        	    }
        	});
        }
    },{
        text:'撤销执行',
        iconCls:'icon-redo',
        handler:function(){
        	var ids = getM3003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录撤销执行!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多撤销执行一条记录!');
        		return ;
        	}
        	var data = $("#prodPlanDoList").datagrid("getSelections")[0];
        	if(data.planStatus != "1" && data.planStatus != "2"){
        		$.messager.alert('提示','只有执行中或暂停的指令才能撤销执行!');
        		return ;
        	}
        	$.messager.confirm('确认','确定撤销执行本条指令？',function(r){
        	    if (r){
        	    	var params = {"planId":ids};
                	$.post("/doProdPlan/redo",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','撤销执行成功!',undefined,function(){
            					searchM3003Form();
            				});
            			} else {
            				$.messager.alert('提示','撤销执行失败：'+data.msg);
            			}
            		});
        	    }
        	});
        }
    },{
        text:'暂停',
        iconCls:'icon-lock',
        handler:function(){
        	var ids = getM3003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条指令暂停!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','最多选择一条指令暂停!');
        		return ;
        	}
        	var data = $("#prodPlanDoList").datagrid("getSelections")[0];
        	if(data.planStatus != "1"){
        		$.messager.alert('提示','只有执行中的指令才能暂停!');
        		return ;
        	}
        	$.messager.confirm('确认','确定暂停本条指令？',function(r){
        	    if (r){
        	    	var params = {"planId":ids,"inputuser": $("#user_id").html()};
                	$.post("/doProdPlan/pause",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','暂停成功!',undefined,function(){
            					searchM3003Form();
            				});
            			} else {
            				$.messager.alert('提示','暂停失败：'+data.msg);
            			}
            		});
        	    }
        	});
        }
    },{
        text:'完成',
        iconCls:'icon-ok',
        handler:function(){
        	var ids = getM3003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条指令完成!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条指令完成!');
        		return ;
        	}
        	var data = $("#prodPlanDoList").datagrid("getSelections")[0];
        	if(data.planStatus != "1"){
        		$.messager.alert('提示','只有执行中的指令才能完成!');
        		return ;
        	}
        	$.messager.confirm('确认','完成该指令将会把所用到的所有半成品状态置为已分剪。若有未用完的半成品请先重新入库！若有已出库未拆封的半成品请先做退回交易！是够继续完成？',function(r){
        		if (r){
	        		$.messager.confirm('确认','请确认该指令造出的产品是否均已入库？',function(r2){
		        	    if (r2){
		        	    	var params = {"planId":ids,"inputuser": $("#user_id").html()};
		                	$.post("/doProdPlan/finish",params, function(data){
		            			if(data.status == 200){
		            				$.messager.alert('提示','指令完成!',undefined,function(){
		            					searchM3003Form();
		            				});
		            			} else {
		            				$.messager.alert('提示','指令完成失败：'+data.msg);
		            			}
		            		});
		        	    }
	        		});
        		}
        	});
        }
    },{
        text:'强制终止',
        iconCls:'icon-man',
        handler:function(){
        	var ids = getM3003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条指令强制终止!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一条指令强制终止!');
        		return ;
        	}
        	var data = $("#prodPlanDoList").datagrid("getSelections")[0];
        	if(data.planStatus != "1"){
        		$.messager.alert('提示','只有执行中的指令才能强制终止!');
        		return ;
        	}
        	$.messager.confirm('确认','终止该指令将会把所用到的所有半成品状态置为已分剪。若有未用完的半成品请先重新入库！若有已出库未拆封的半成品请先做退回交易！是够继续终止？',function(r){
        	    if (r){
        	    	$.messager.confirm('确认','请确认该指令造出的产品是否均已入库？',function(r2){
        	    		if (r2){  
        	    			var params = {"planId":ids,"inputuser": $("#user_id").html()};
		                	$.post("/doProdPlan/stop",params, function(data){
		            			if(data.status == 200){
		            				$.messager.alert('提示','指令终止完成!',undefined,function(){
		            					searchM3003Form();
		            				});
		            			} else {
		            				$.messager.alert('提示','指令终止失败：'+data.msg);
		            			}
		            		});
        	    		}
        	    	});
        	    }
        	});
        }
    },
    {
        text:'PDF打印',
        iconCls:'icon-print',
        handler:function(){
        	var ids = getM3003SelectionsIds();
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
        			mapParam.set("planId",ids);
        			mapParam.set("tradeType","M3001");
        			mapParam.set("loginUserId",$("#user_id").html());
        			BSL.toNewPagePDF(mapParam);
        	    }
			});
        }
    },{
        text:'用料炉号',
        iconCls:'icon-man',
        handler:function(){
        	var data = $("#prodPlanDoList").datagrid("getSelections")[0];
        	var ids = getM3003SelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一条记录查看用料炉号!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','一次最多查看一条记录!');
        		return ;
        	}
        	var params = {"planId":ids};
        	$.post("/planLuInfo/list",params, function(data){
				if(data.status == 200){
					$("#planLunoInfoWindowM3003").window({
		        		onLoad :function(){
				           	$("#planLunoInfoFormM2001").form("load",params);
		           			var values = [];  
		  	  				for (var i = 0; i < data.data.length; i++) {
		  	  	                var a = {
		  	  	                	'planId' : data.data[i].planId,
		 		                    'planLuno' : data.data[i].planLuno
		  	  	                };
		  	  	                values.push(a);				
		  	  	            }
		  	  	            $('#planLunoInfoListM2001').datagrid('loadData', values);
		        		}
		        	}).window("open");
				}else{
					$.messager.alert('提示',data.msg);
				}
			});
        }
    },{
        text : '导出EXCEL',
        iconCls : 'icon-save',
        handler : function() {
			//获取后台传递参数className methodName
			var className = $("#prodPlanDoList").datagrid("getData").className;
			var methodName = $("#prodPlanDoList").datagrid("getData").methodName;
			/**
			 * datagrid属性中获取不到className、methodName时，需要从隐藏域中获取；
			 * 主要是查询时，datagrid属性className、methodName会丢失
			 */
			if(className == "" || className==null || className == undefined){
				className = $('#classNameM3003').val();
			}
			if(methodName == "" || methodName==null || methodName == undefined){
				methodName = $('#methodNameM3003').val();
			}
			//获取表头信息
			var header = $("#prodPlanDoList").datagrid("options").columns[0];
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
			mapParam.set("excelName","产成品生产调度单执行信息");
			mapParam.set("loginUserId",$("#user_id").html());
			mapParam.set("rows","5000");
			mapParam.set("page","1"); 
			//查询条件 把所有查询条件带上
			mapParam.set("planId",$('#planIdM3003').val());
			mapParam.set("planStatus",$('#planStatusM3003').combobox("getValue"));
			mapParam.set("planLuno",$('#planLunoM3003').val());
			mapParam.set("makeName",$('#makeNameM3003').val());
			mapParam.set("makeProdNorm",$('#makeProdNormM3003').val());
			mapParam.set("prodMaterial",$('#prodMaterialM3003').combobox("getValue"));
			mapParam.set("prodNorm",$('#prodNormM3003').val());
			mapParam.set("planJz",$('#planJzM3003').combobox("getValue"));
			mapParam.set("prodType",$('#prodTypeM3003').combobox("getValue"));
			mapParam.set("startDate",$('#startDateM3003').datebox("getValue"));
			mapParam.set("endDate",$('#endDateM3003').datebox("getValue"));
			
			BSL.toExcel(mapParam);
        		}
    }];
    
</script>