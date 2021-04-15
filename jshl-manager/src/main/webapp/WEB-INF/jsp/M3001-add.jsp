<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="prodPlanAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	       		<td width="120" align="right">用料规格:</td>
            	 <td width="210" align="right">
	            	<input name="prodNorm" id="prodNormM3001Add" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]',events:{blur:onProdNormBlurM3001}" style="width:200px;"></input>
	            </td>
	             <td width="120" align="right">用料钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" id="prodMaterialM3001Add" class="easyui-combobox" panelHeight="auto" data-options="editable:true,required:true,onChange:onProdMaterialChangeM3001" style="width:200px;">
			          <option value="">请选择...</option>
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	        </tr>
	         <tr>
	          	<td width="120" align="right">用料产品类型:</td>
	            <td width="210" align="right">
	            	<select name="prodType" id="prodTypeM3001Add" class="easyui-combobox" panelHeight="auto" data-options="editable:true,required:true" style="width:200px;">
			          <option value="">请选择...</option>
			           <c:forEach items="${prodTypeNeedList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">生产订单号:</td>
	            <td width="210"  align="right">
	            	<input name="prodOrder" class="easyui-textbox" data-options="required:false,validType:'length[0,20]'" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	         <tr>
	        	<td width="120"  align="right">库存重量/吨:</td>
	            <td width="210"  align="right">
	            	<input name="prodLeftWeight" id="prodLeftWeightM3001Add" class="easyui-numberbox" type="text"  readonly="readonly"  data-options="required:false,min:0,precision:3,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	            <td width="120"  align="right">库存数量/卷:</td>
	            <td width="210"  align="right">
	            	<input name="prodLeftNum" id="prodLeftNumM3001Add" class="easyui-numberbox" type="text"  readonly="readonly"  data-options="required:false,min:0,precision:0,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	        	<td width="120"  align="right">用料重量/吨:</td>
	            <td width="210"  align="right">
	            	<input name="prodWeight" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	            <td width="120"  align="right">用料数量/条:</td>
	            <td width="210"  align="right">
	            	<input name="prodNum" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,12]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120"  align="right">制造产品名称:</td>
	            <td width="210"  align="right">
	            	<input name="makeName" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">制造产品规格:</td>
	             <td width="210" align="right">
	            	<input name="makeProdNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
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
			          <c:forEach items="${prodUnitsSCList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
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
	        	<td width="120" align="right">工厂:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" name="company" data-options="required:false,validType:'length[0,20]'" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	         <tr>
	            <td width="120" align="right">备注:</td>
	            <td width="210"  align="right">
	            	<input class="easyui-textbox" name="remark" data-options="required:false,validType:'length[0,120]'" style="text; width: 200px;"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	        	<td width="120" align="right">录入人:</td>
	            <td width="210"  align="right">
	        		<input name="inputuser" id="inputuserM3001Add" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="M3001Add" onclick="submitFormM3001Add()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearFormM3001Add()">重置</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitFormM3001Add(){
		//有效性验证
		if(!$('#prodPlanAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		var inputUser = $("#user_id").html();
		$("#inputuserM3001Add").textbox('setValue',inputUser);
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$('#M3001Add').linkbutton('disable');
		$.post("/prodPlan/add",$("#prodPlanAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增产品生产指令成功!产品生产批号为：'+data.data,'info',function(){
					$("#prodPlanAddWindow").window('close');
					$("#prodPlanList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
			$('#M3001Add').linkbutton('enable');
		});
	}
	
	function clearFormM3001Add(){
		$('#prodPlanAddForm').form('reset');
	}
	
	//根据规格回显库存纵剪带的钢种、炉号、库存数量、库存重量等信息
	function onProdNormBlurM3001(){
		var params = {'prodNorm':$("#prodNormM3001Add").textbox("getValue")};
		$.post("/leftInfo/haflProdInfo",params,function(data){
			if(data.status == 200){
				if(data.data.prodMaterials.length > 0){
					//添加钢种枚举
					$("#prodMaterialM3001Add").combobox('setValue', '');
					//$("#planLunoM3001Add").combobox('setValue', '');
					$("#prodMaterialM3001Add").combobox('loadData', {});
					//$("#planLunoM3001Add").combobox('loadData', {});
					var dataSource = [];
					for(var i=0;i<data.data.prodMaterials.length;i++){
						var value = data.data.prodMaterials[i];
						var text = BSL.formatProdMaterial(value);
						dataSource.push({"value":value,"text":text});
					}
					$("#prodMaterialM3001Add").combobox("loadData",dataSource);
				}
				//添加炉号枚举
				/* var dataSource2 = [];
				for(var i=0;i<data.data.prodLunos.length;i++){
					var value = data.data.prodLunos[i];
					dataSource2.push({"value":value,"text":value});
				}
				$("#planLunoM3001Add").combobox("loadData",dataSource2); */
				//回显数量
				$("#prodLeftWeightM3001Add").numberbox("setValue",data.data.sumWeight);
				$("#prodLeftNumM3001Add").numberbox("setValue",data.data.prodCount);
				
			} else {
				$.messager.alert('提示', data.msg);
			}
		});
	}
	
	//根据规格/钢种回显库存纵剪带的炉号、库存数量、库存重量等信息
	function onProdMaterialChangeM3001(){
		var params = {'prodNorm':$("#prodNormM3001Add").textbox("getValue"),
				'prodMaterial':$("#prodMaterialM3001Add").combobox("getValue")}
		$.post("/leftInfo/haflProdInfo",params,function(data){
			if(data.status == 200){
				//添加炉号枚举
				/* $("#planLunoM3001Add").combobox('setValue', '');
				$("#planLunoM3001Add").combobox('loadData', {});
				var dataSource2 = [];
				for(var i=0;i<data.data.prodLunos.length;i++){
					var value = data.data.prodLunos[i];
					dataSource2.push({"value":value,"text":value});
				}
				$("#planLunoM3001Add").combobox("loadData",dataSource2); */
				//回显数量
				$("#prodLeftWeightM3001Add").numberbox("setValue",data.data.sumWeight);
				$("#prodLeftNumM3001Add").numberbox("setValue",data.data.prodCount);
				
			} else {
				$.messager.alert('提示', data.msg);
			}
		});
	}
	
	//根据规格/钢种/炉号回显库存纵剪带的库存数量、库存重量等信息
	function onProdLunoChangeM3001(){
		var params = {'prodNorm':$("#prodNormM3001Add").textbox("getValue"),
				'prodMaterial':$("#prodMaterialM3001Add").combobox("getValue"),
				'prodLuno':$("#planLunoM3001Add").combobox("getValue")}
		$.post("/leftInfo/haflProdInfo",params,function(data){
			if(data.status == 200){
				//回显数量
				$("#prodLeftWeightM3001Add").numberbox("setValue",data.data.sumWeight);
				$("#prodLeftNumM3001Add").numberbox("setValue",data.data.prodCount);
				
			} else {
				$.messager.alert('提示', data.msg);
			}
		});
	}
</script>
