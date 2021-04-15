<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="rawOutputSMForm" class="itemForm" method="post">
	    <table cellpadding="5">
	         <tr>
	        	<td width="120"  align="right">原料物料编码:</td>
	            <td width="210"  align="right">
	            	<input name="prodId" class="easyui-textbox" type="text" readonly="readonly" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">物料名称:</td>
	            <td width="210" align="right">
	            	<input name="prodName" class="easyui-textbox" type="text" disabled="disabled" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>	           
	        </tr>
	        <tr>	
	        	<td width="120" align="right">原料入库通知单号:</td>
	            <td width="210" align="right">
	            	<input name="prodPlanNo" class="easyui-textbox" type="text" disabled="disabled" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>  
	            <td width="120" align="right">炉(批)号:</td>
	            <td width="210" align="right">
	            	<input name="prodLuno" id="prodLunoM2004SM" class="easyui-textbox" type="text" disabled="disabled" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
	            </td>  
	        </tr>
	        <tr>
	        	<td width="120" align="right">钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" class="easyui-combobox" panelHeight="auto" disabled="disabled"  data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
				</td>
	            <td width="120" align="right">规格:</td>
	             <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" disabled="disabled" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>      
	        </tr>
	        <tr>
	            <td width="120" align="right">产品实际重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" class="easyui-numberbox" disabled="disabled" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">入库仓库/区:</td>
	            <td width="210" align="right">
	            	<select name="prodRuc" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
			           <option value="">请选择...</option>
			          <c:forEach items="${prodRucList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td> 
	        </tr>
	        <tr>
	        	<td width="120"  align="right">生产机组:</td>
	            <td width="210"  align="right">
	            	<select name="planJz" panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${prodUnitsZJList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" type="text" disabled="disabled"  data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	        	<td width="120" align="right">录入人:</td>
	            <td width="210"  align="right">
	        		<input name="prodInputuser" id="inputuserM2004SM" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormM2004SM()">确认出库</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeFormM2004SM()">关闭</a>
	</div>
	
	<div id="sendFlagEditWindowM2004SM" class="easyui-window" title="取样送检信息" data-options="modal:true,closed:true,iconCls:'receipt-edit',href:'/sendFlag/M1006-edit'" style="width:810px;height:170px;padding:10px;">
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitFormM2004SM(){
		var inputUser = $("#user_id").html();
		$("#inputuserM2004SM").textbox('setValue',inputUser);
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/rawOutput/out",$("#rawOutputSMForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','出库成功，编号为：'+data.data,'info',function(){
					$("#rawOutputSMWindow").window('close');
					$("#rawOutputList").datagrid("reload");
					//取样送检提示
					var params = {"luId" : $("#prodLunoM2004SM").textbox('getValue'),'rows':'30','page':'1'};
					$.post("/sendFlag/listByCriteria", params, function(data2) {
						if (data2.status == 200) {
							if(data2.total > 0 && data2.rows[0].sendFlag == '1'){
								$.messager.confirm('确认', '该炉号已经取样送检，送检结果为：'+data2.rows[0].sendResult+',是否继续送检？', function(r) {
									if (r) {
										$("#sendFlagEditWindowM2004SM").window({
							        		onLoad :function(){
							        			var paramsTmp = data2.rows[0];
							        			paramsTmp.flag = 'M2004SM';
							        			$("#sendFlagEditForm").form("load",paramsTmp);
							        		}
							        	}).window("open");
									}
								});
							}else{
								$.messager.confirm('确认', '该炉号未送检,是否取样送检？', function(r) {
									if (r) {
										$("#sendFlagEditWindowM2004SM").window({
							        		onLoad :function(){
							        			//回显数据
							        			$("#sendFlagEditForm").form("load",{"luId" : $("#prodLunoM2004SM").textbox('getValue'),'sendFlag':'1','flag':'M2004SM'});
							        		}
							        	}).window("open");
									}
								});
							}
						} else {
							$.messager.alert('提示', data.msg);
						}
					});
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeFormM2004SM(){
		$("#rawOutputSMWindow").window('close');
	}
</script>
