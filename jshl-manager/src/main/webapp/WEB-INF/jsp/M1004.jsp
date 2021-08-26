<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="easyui-panel" title="原料入库通知单重量比对查询" style="padding:10px 10px 10px 10px">
		<form id="rawCompareForm" class="itemForm" method="post">
		   <table>
		        <tr>
		            <td width="120" align="right">原料物料编码:</td>
		            <td width="210" align="right">
		            	<input name="prodId" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
		            </td>
		        </tr>
	       		 <tr hidden="true">
		              <td width="120" align="right">排序字段:</td>
		            <td width="210" align="right">
		            	<input name="sort" id="sortM1004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">排序规则:</td>
		            <td width="210" align="right">
		            	<input name="order" id="orderM1004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		        </tr>	
		         <tr hidden="true">
		            <td width="120" align="right">页码:</td>
		            <td width="210" align="right">
		            	<input name="page" id="pageM1004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		            <td width="120" align="right">每页记录数:</td>
		            <td width="210" align="right">
		            	<input name="rows" id="rowsM1004" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
		            </td>
		         </tr>		        
		    </table>
		</form>
		<div style="margin-top:10px" align="center">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchM1004Form()">查询</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearM1004Form()">重置</a>
		</div>
	</div>
	 <table class="easyui-datagrid" id="rawCompareListDetail" title="原料入库通知单详细重量比对"  style="height:550px"
	       data-options="singleSelect:true,rownumbers:true,fitColumns:true,collapsible:true,pageSize:30,pagination:true,url:'/rawcompare/listDetail'">
	    <thead>
	        <tr>
	        	<th data-options="field:'prodId',width:100,sortable:true">原料物料编码</th>
	        	<th data-options="field:'prodLuno',width:100,sortable:true">炉(批)号</th>
	        	<th data-options="field:'prodName',width:70,sortable:true">物料名称</th>
	        	<th data-options="field:'prodNorm',sortable:true,width:100">规格</th>
	        	<th data-options="field:'prodMaterial',width:70,formatter:BSL.formatProdMaterial,sortable:true">钢种</th>
	            <th data-options="field:'prodRecordWeight',width:125,sortable:true">原料来料重量/吨</th>
	            <th data-options="field:'prodRelWeight',width:125,sortable:true">来料复磅重量/吨</th>
	            <th data-options="field:'prodPrintWeight',width:125,sortable:true">产品打印重量/吨/</th>
	            <th data-options="field:'compareWeight',width:65,sortable:true">磅差/吨</th>
	            <th data-options="field:'compareNum',width:65,sortable:true">磅差率</th>
	            <th data-options="field:'prodStatus',width:100,formatter:BSL.formatProdStatus,sortable:true">状态</th>
	            <th data-options="field:'crtDate',width:150,formatter:BSL.formatDateTime,sortable:true">入库日期</th>
	            <th data-options="field:'remark',width:226,sortable:true">备注</th>
	        </tr>
	    </thead>
	</table>
</div>
<script>

	//排序查询
	function sortSerachM1004(sort,order){
		$("#sortM1004").textbox('setValue',sort);
		$("#orderM1004").textbox('setValue',order);
		searchM1004Form();
	}

	//查询按钮
	function searchM1004Form(){
		//查询之前先清空
		$('#rawCompareListDetail').datagrid('loadData',{total:0,rows:[]})
		
		 //page页码
		var page = $("#rawCompareListDetail").datagrid('options').pageNumber;
		//rows每页记录条数  
        var rows = $("#rawCompareListDetail").datagrid('options').pageSize; 
		$("#pageM1004").textbox('setValue',page);
		$("#rowsM1004").textbox('setValue',rows);
		//ajax的post方式提交表单
		//$("#receiptSearchForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/rawcompare/listDetail",$("#rawCompareForm").serialize(), function(data){
			if(data.status == 200){				
				var values = [];  
				if(data.data.length == 0){
					
				}else{
					var compareWeight;
					var compareNum;
					for (var i = 0; i < data.data.length; i++) {
		                if(data.data[i].prodRecordWeight > 0){
		                	compareWeight = data.data[i].prodRelWeight - data.data[i].prodRecordWeight;
		                	compareNum = compareWeight/data.data[i].prodRecordWeight;
		                	compareNum = compareNum.toFixed(3);
		                }else{
		                	compareWeight = 0;
		                	compareNum = 0;
		                }
		                var a = {
		                    'ck' : true,
		                    'prodId' : data.data[i].prodId,
		                    'prodName' : data.data[i].prodName,
		                    'prodLuno' : data.data[i].prodLuno,
		                    'prodNorm' : data.data[i].prodNorm,
		                    'prodMaterial' : data.data[i].prodMaterial,
		                    'prodRecordWeight' : data.data[i].prodRecordWeight,
		                    'prodRelWeight' : data.data[i].prodRelWeight,
		                    'prodPrintWeight' : data.data[i].prodPrintWeight,
		                    'compareWeight' : compareWeight,
		                    'compareNum' : compareNum,
		                    'prodStatus' : data.data[i].prodStatus,
		                    'crtDate' : data.data[i].crtDate,
		                    'remark' : data.data[i].remark
		                };
		                values.push(a);				
		            }
				}				
	            $('#rawCompareListDetail').datagrid('loadData', values);
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
		
	}
	
	/* 重置表单 */
	function clearM1004Form(){
		$('#rawCompareForm').form('reset');
	}
	
    
</script>