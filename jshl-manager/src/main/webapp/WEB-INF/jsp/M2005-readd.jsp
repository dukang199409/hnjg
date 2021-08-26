<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="semiReAddForm" class="itemForm" method="post">
	  <table>
	       <tr>
	            <td width="120"  align="right">剩余入库盘号:</td>
	            <td width="210"  align="right">
	            	<input name="prodOriId" readonly="readonly" class="easyui-textbox" data-options="required:true" style="width:200px;"/>
	            </td>         
	            <td width="120" align="right">物料名称:</td>
	            <td width="210" align="right">
	            	<input name="prodName" class="easyui-textbox" type="text" disabled="disabled" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>      
	        </tr>
	        <tr>	 
	            <td width="120" align="right">钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" class="easyui-combobox" panelHeight="auto" readonly="readonly" data-options="editable:true" style="width:200px;">
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
				</td>
				<td width="120" align="right">规格:</td>
	             <td width="210" align="right">
		            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
		         </td>
	        </tr>
	        <tr>
	           <td width="120" align="right">质量等级:</td>
	            <td width="210" align="right">
	            	<select name="prodLevel" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			           <option value="">请选择...</option>
			           <c:forEach items="${prodLevelList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">产品实际重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" id="prodRelWeightM2005Readd" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            	 <!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM2005()">称重</a> -->
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightReaddM2005()" style="width:70px;">称重</a>
	        	</td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">入库仓库/区:</td>
	            <td width="210" align="right">
	            	<select name="prodRuc" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
			           <option value="">请选择...</option>
			          <c:forEach items="${prodRucList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td> 
	        	<td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	            <td width="120" align="right">录入人:</td>
	            <td width="210" align="right">
	            	<input name="prodInputuser" id="prodInputuserM2005Readd" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditM2005Form()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearEditM2005Form()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//联动称重机获取实际重量
	function getWeightReaddM2005(){
		var params = {};
		$.post("/rxtx/weighingByD",params, function(data){
			if(data.status == 200){
				$("#prodRelWeightM2005Readd").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//提交表单
	function submitEditM2005Form(){
		//有效性验证
		if(!$('#semiReAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//记录录入人员信息
		var checkUser = $("#user_id").html(); 
		$("#prodInputuserM2005Readd").textbox('setValue',checkUser);
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.messager.confirm('确认','是否确认入库？',function(r){
			if (r){
				$.post("/semi/readd",$("#semiReAddForm").serialize(), function(data){
					if(data.status == 200){
						$.messager.alert('提示','剩余半成品重新入库成功!单号为：'+data.data,'info',function(){
							$.messager.confirm('确认','是否需要生成PDF标签文件？',function(r){
								if (r){
				        	    	var mapParam = new Map();
				        			mapParam.set("url","/import/importPdf");
				        			mapParam.set("prodId",data.data);
				        			mapParam.set("tradeType","M2005");
				        			BSL.toNewPagePDF(mapParam);
				        	    }
							});
							$("#semiReAddWindow").window('close');
							searchM2005Form();
							//$("#presemiList").datagrid("reload");
						});
					}else{
						$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
					}
				});
			}
		});
	}
	
	function clearEditM2005Form(){
		$("#semiReAddWindow").window('close');
	}
</script>
