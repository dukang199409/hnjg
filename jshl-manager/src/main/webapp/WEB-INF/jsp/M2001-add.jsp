<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="planAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	        	<td width="120" align="right">用料规格:</td>
	            <td width="210" align="right">
	            	<input name="prodNorm" id="prodNormM2001Add" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]',events:{blur:onProdNormBlurM2001}" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">用料钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" id="prodMaterialM2001Add" class="easyui-combobox" panelHeight="auto" data-options="editable:true,required:true,onChange:onProdMaterialChangeM2001" style="width:200px;">
			          <option value="">请选择...</option>
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	         <tr>
	        	<td width="120"  align="right">库存重量/吨:</td>
	            <td width="210"  align="right">
	            	<input name="prodLeftWeight" id="prodLeftWeightM2001Add" class="easyui-numberbox" type="text"  readonly="readonly"  data-options="required:false,min:0,precision:3,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	            <td width="120"  align="right">库存数量/卷:</td>
	            <td width="210"  align="right">
	            	<input name="prodLeftNum" id="prodLeftNumM2001Add" class="easyui-numberbox" type="text"  readonly="readonly"  data-options="required:false,min:0,precision:0,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120"  align="right">用料重量/吨:</td>
	            <td width="210"  align="right">
	            	<input name="prodWeight" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	            <td width="120"  align="right">用料数量/卷:</td>
	            <td width="210"  align="right">
	            	<input name="prodNum" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120"  align="right">制造产品名称:</td>
	            <td width="210"  align="right">
	            	<input name="makeName" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">制造纵剪带用途:</td>
	            <td width="210" align="right">
	            	<select name="makeType" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${makeTypeList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>
	            <td width="120"  align="right">生产部门:</td>
	            <td width="210"  align="right">
	            	<select name="planDepartment" panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${planDepartmentList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>
	            <td width="120"  align="right">生产机组:</td>
	            <td width="210"  align="right">
	            	<select name="planJz" panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${prodUnitsZJList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">生产订单号:</td>
	            <td width="210"  align="right">
	            	<input name="prodOrder" class="easyui-textbox" data-options="required:false,validType:'length[0,20]'" style="text; width: 200px;"></input>
	            </td>
	       		<td width="120" align="right">工厂:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" name="company" data-options="required:false,validType:'length[0,20]'" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">来料客户:</td>
	            <td width="210" align="right">
	            	<select name="customer" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${customerList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">备注:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" name="remark" data-options="required:false,validType:'length[0,120]'" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	        	<td width="120" align="right">录入人:</td>
	            <td width="210"  align="right">
	        		<input name="inputuser" id="inputuserM2001Add" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="M2001Add" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">

	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#planAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var inputUser = $("#user_id").html();
		$("#inputuserM2001Add").textbox('setValue',inputUser);
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$('#M2001Add').linkbutton('disable');
		$.post("/plan/add",$("#planAddForm").serialize(),function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增纵剪带生产指令成功!纵剪带指令号为：'+data.data,'info',function(){
					$("#planAddWindow").window('close');
					$("#planList").datagrid("reload");
				});
			} else {
				$.messager.alert('提示', data.msg);
			}
			$('#M2001Add').linkbutton('enable');
		});
	}
	
	//重置
	function clearForm(){
		$('#planAddForm').form('reset');
	}
	
	//根据规格回显库存卷板的钢种、炉号、库存数量、库存重量等信息
	function onProdNormBlurM2001(){
		var params = {'prodNorm':$("#prodNormM2001Add").textbox("getValue")};
		$.post("/leftInfo/rawInfo",params,function(data){
			if(data.status == 200){
				if(data.data.prodMaterials.length > 0){
					//添加钢种枚举
					$("#prodMaterialM2001Add").combobox('setValue', '');
					//$("#planLunoM2001Add").combobox('setValue', '');
					$("#prodMaterialM2001Add").combobox('loadData', {});
					//$("#planLunoM2001Add").combobox('loadData', {});
					var dataSource = [];
					for(var i=0;i<data.data.prodMaterials.length;i++){
						var value = data.data.prodMaterials[i];
						var text = BSL.formatProdMaterial(value);
						dataSource.push({"value":value,"text":text});
					}
					$("#prodMaterialM2001Add").combobox("loadData",dataSource);
				}
				//添加炉号枚举
				/* var dataSource2 = [];
				for(var i=0;i<data.data.prodLunos.length;i++){
					var value = data.data.prodLunos[i];
					dataSource2.push({"value":value,"text":value});
				}
				$("#planLunoM2001Add").combobox("loadData",dataSource2); */
				//回显数量
				$("#prodLeftWeightM2001Add").numberbox("setValue",data.data.sumWeight);
				$("#prodLeftNumM2001Add").numberbox("setValue",data.data.prodCount);
				
			} else {
				$.messager.alert('提示', data.msg);
			}
		});
	}
	
	//根据规格/钢种回显库存卷板的炉号、库存数量、库存重量等信息
	function onProdMaterialChangeM2001(){
		var params = {'prodNorm':$("#prodNormM2001Add").textbox("getValue"),
				'prodMaterial':$("#prodMaterialM2001Add").combobox("getValue")}
		$.post("/leftInfo/rawInfo",params,function(data){
			if(data.status == 200){
				//添加炉号枚举
				/* $("#planLunoM2001Add").combobox('setValue', '');
				$("#planLunoM2001Add").combobox('loadData', {});
				var dataSource2 = [];
				for(var i=0;i<data.data.prodLunos.length;i++){
					var value = data.data.prodLunos[i];
					dataSource2.push({"value":value,"text":value});
				}
				$("#planLunoM2001Add").combobox("loadData",dataSource2); */
				//回显数量
				$("#prodLeftWeightM2001Add").numberbox("setValue",data.data.sumWeight);
				$("#prodLeftNumM2001Add").numberbox("setValue",data.data.prodCount);
				
			} else {
				$.messager.alert('提示', data.msg);
			}
		});
	}
	
	//根据规格/钢种/炉号回显库存卷板的库存数量、库存重量等信息
	function onProdLunoChangeM2001(){
		var params = {'prodNorm':$("#prodNormM2001Add").textbox("getValue"),
				'prodMaterial':$("#prodMaterialM2001Add").combobox("getValue"),
				'prodLuno':$("#planLunoM2001Add").combobox("getValue")}
		$.post("/leftInfo/rawInfo",params,function(data){
			if(data.status == 200){
				//回显数量
				$("#prodLeftWeightM2001Add").numberbox("setValue",data.data.sumWeight);
				$("#prodLeftNumM2001Add").numberbox("setValue",data.data.prodCount);
				
			} else {
				$.messager.alert('提示', data.msg);
			}
		});
	}
	
</script>
