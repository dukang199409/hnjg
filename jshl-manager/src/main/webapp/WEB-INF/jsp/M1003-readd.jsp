<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="rawReAddForm" class="itemForm" method="post">
	   <table>
	   		<tr>	          
	            <td width="120" align="right">原出库原料编码:</td>
	            <td width="210" align="right">
	            	<select name="prodOriId" id="prodOriIdM1003Readd" panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${bslBsProdOriList}" var="a">
			          	<option value="${a.prodId}">${a.prodId}</option>
			          </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">物料名称:</td>
	            <td width="210" align="right">
	            	<input name="prodName" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>	 
	        </tr>
	        <tr>
	        	<td width="120" align="right">规格:</td>
	            <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">来料复磅重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" id="prodRelWeightM1003Readd" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightReaddM1003()" style="width:70px;">称重</a>
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
	            <td width="120" align="right">定尺/米:</td>
	            <td width="210" align="right">
	            	<input name="prodLength"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
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
	            	<input name="prodInputuser" id="prodInputuserM1003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="submitReAddM1003Form" onclick="submitReAddM1003Form()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeReAddM1003Form()">关闭</a>
	</div>
</div>
<script type="text/javascript">

	//联动称重机获取实际重量
	function getWeightReaddM1003(){
		var params = {};
		$.post("/rxtx/weighingByD",params, function(data){
			if(data.status == 200){
				$("#prodRelWeightM1003Readd").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//提交表单
	function submitReAddM1003Form(){
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#prodInputuserM1003").textbox('setValue',inputUser);
		
		//有效性验证
		if(!$('#rawReAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.messager.confirm('确认','是否确认入库？',function(r){
			if (r){
				$('#submitReAddM1003Form').linkbutton('disable');
				$.post("/raw/readd",$("#rawReAddForm").serialize(), function(data){
					if(data.status == 200){
						$.messager.alert('提示','剩余原料重新入库成功!单号为：'+data.data,'info',function(){
							$.messager.confirm('确认','是否需要打印PDF标签文件？',function(r){
								if (r){
									var prodId = data.prodId;
				        	    	var mapParam = new Map();
				        			mapParam.set("url","/import/importPdf");
				        			mapParam.set("prodId",data.data);
				        			mapParam.set("tradeType","M1003");
				        			BSL.toNewPagePDF(mapParam);
				        	    }
							});
							$("#rawReAddWindow").window('close');
							searchM1003Form();
							//$("#preRawList").datagrid("reload");
						});
					}else{
						$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
					}
					$('#submitReAddM1003Form').linkbutton('enable');
				});
			}
		});
	}
	
	function closeReAddM1003Form(){
		$("#rawReAddWindow").window('close');
	}
</script>
