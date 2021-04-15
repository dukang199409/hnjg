<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="semiCheckAddForm" class="itemForm" method="post">
	   <table>
	        <tr> 
	        	 <td width="120"  align="right">生产机组:</td>
		            <td width="210"  align="right">
		            	<select name="prodMakeJz" id="prodMakeJzM2005Add"  panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true,onChange:onplanJzChangeM2005Add" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodUnitsZJList}" var="a">
				          	   	<option value="${a.enumKey}">${a.enumValue}</option>
				          	  </c:forEach>
						</select>
		            </td>
	        </tr>
	        <tr>
	            <td width="120"  align="right">纵剪带生产指令:</td>
	            <td width="210"  align="right">
	            	<input name="prodPlanNo" readonly="readonly" class="easyui-textbox" data-options="required:true" style="width:200px;"/>
	            </td>  
	            <td width="120" align="right">父级钢卷号:</td>
	            <td width="210" align="right">
	            	<input name="prodParentNo" readonly="readonly" class="easyui-textbox" readonly="readonly" type="text" data-options="required:true,validType:'length[0,20]'"  style="width:200px;"></input>
	            </td>         
	        </tr>
	        <tr>	          
	            <td width="120" align="right">物料名称:</td>
	            <td width="210" align="right">
	            	<input name="prodName" class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" class="easyui-combobox" readonly="readonly"  panelHeight="auto" data-options="editable:true" style="width:200px;">
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
				</td>
	        </tr>
	        <tr>
	            <td width="120" align="right">规格:</td>
	             <td width="210" align="right">
		            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
		         </td>
	           <td width="120" align="right">质量等级:</td>
	            <td width="210" align="right">
	            	<select name="prodLevel" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			           <option value="">请选择...</option>
			          <c:forEach items="${prodLevelList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">总重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" id="prodRelWeightM2005" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            	 <!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM2005()">称重</a> -->
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM2005()" style="width:70px;">称重</a>
	        	</td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">入库盘数:</td>
	        	<td width="210" align="right">
	            	<input name="sumNum" id="sumNumM2005" class="easyui-numberbox" type="text" data-options="required:true,min:1,precision:0,validType:'length[0,10]'" value="1" style="width:200px;"></input>
	            </td>
	           <td width="120" align="right">生产班次:</td>
	            <td width="210" align="right">
	            	<select name="prodBc" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
			           <option value="">请选择...</option>
			          <c:forEach items="${prodBcList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
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
	            	<input name="prodInputuser" id="prodInputuserM2005Add" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="M2005Add" onclick="submitCheckM2005Form()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearCheckM2005Form()">关闭</a>
	</div>
</div>
<script type="text/javascript">

	//联动称重机获取实际重量
	function getWeightCheckM2005(){
		var params = {};
		$.post("/rxtx/weighingByD",params, function(data){
			if(data.status == 200){
				$("#prodRelWeightM2005").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//提交表单
	function submitCheckM2005Form(){
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#prodInputuserM2005Add").textbox('setValue',inputUser);
		var sumNum = $("#sumNumM2005").numberbox('getValue');
		//有效性验证
		if(!$('#semiAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		console.log($("#semiCheckAddForm").serialize());
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.messager.confirm('确认','是否确认入库？',function(r){
			if (r){
				$('#M2005Add').linkbutton('disable');
				$.post("/semi/add",$("#semiCheckAddForm").serialize(), function(data){
					if(data.status == 200){
						$.messager.alert('提示','确认入库成功!起始单号为：'+data.data,'info',function(){
							if(sumNum<=1){
								$.messager.confirm('确认','是否需要生成PDF标签文件？',function(r){
									if (r){
					        	    	var mapParam = new Map();
					        			mapParam.set("url","/import/importPdf");
					        			mapParam.set("prodId",data.data);
					        			mapParam.set("tradeType","M2005");
					        			BSL.toNewPagePDF(mapParam);
					        	    }
								});
							}
							$("#semiAddWindow").window('close');
							searchM2005Form();
							//$("#presemiList").datagrid("reload");
						});
					}else{
						$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
					}
					$('#M2005Add').linkbutton('enable');
				});
			}
		});
	}
	
	function clearCheckM2005Form(){
		$("#semiAddWindow").window('close');
	}
	
	//根据生产机组回显正在生产的指令等信息
	function onplanJzChangeM2005Add(){
		var params = {
			'planJz':$("#prodMakeJzM2005Add").combobox("getValue")
		};
		var result = {};
		$.post("/semi/getHalfPlanExeInfo", params, function(data) {
			if (data.status == 200) {
				result.prodPlanNo = data.data.planId;
				result.prodName = data.data.makeName;
				result.prodLuno = data.data.planLuno;
				result.prodMaterial = data.data.prodMaterial;
				params = {"exePlanId":result.prodPlanNo};
				$.post("/semi/getRawExeInfo", params, function(data) {
					if (data.status == 200) {
						result.prodParentNo = data.data.prodId;
						$("#semiCheckAddForm").form("load",result);
					} else {
						$.messager.alert('提示', data.msg);
						$("#semiCheckAddForm").form('reset');
					}
				});
			} else {
				$.messager.alert('提示', data.msg);
				$("#semiCheckAddForm").form('reset');
			}
		});
	}
	
</script>
