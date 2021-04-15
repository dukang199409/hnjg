<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="dclProdAddForm" class="itemForm" method="post">
	   <table>
	   		<tr> 
	        	 <td width="120"  align="right">生产机组:</td>
		            <td width="210"  align="right">
		            	<select name="prodMakeJz" id="prodMakeJzM3101Add"  panelHeight="auto" class="easyui-combobox" data-options="editable:true,required:true,onChange:onplanJzChangeM3101Add" style="width:200px;">
				          <option value="">请选择...</option>
				          <c:forEach items="${prodUnitsSCList}" var="a">
				          	   	<option value="${a.enumKey}">${a.enumValue}</option>
				          	  </c:forEach>
						</select>
		            </td>
	        </tr>
	        <tr>
	            <td width="120"  align="right">产品生产指令:</td>
	            <td width="210"  align="right">
	            	<input name="prodPlanNo" id="prodPlanNoM3101Add" readonly="readonly" class="easyui-textbox" data-options="required:true" style="width:200px;"/>
	            </td>            
	            <td width="120" align="right">产品名称:</td>
	            <td width="210" align="right">
	            	<input name="prodName" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>      
	        </tr>
	        <tr>	
	            <td width="120" align="right">产品钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" id="prodMaterialM3101Add" class="easyui-combobox" panelHeight="auto" readonly="readonly" data-options="editable:true" style="width:200px;">
			           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
				</td>
	            <td width="120" align="right">产品规格:</td>
	             <td width="210" align="right">
	            	<input name="prodNorm" id="prodNormM3101Add" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>     
	            <td width="120" align="right">父级盘号:</td>
	             <td width="210" align="right">
	            	<select name="prodParentNo" id="prodParentNoM3101Add" class="easyui-combobox" panelHeight="auto" data-options="editable:true,onChange:onProdParentNoChangeM3101Add" style="width:200px;">
			            	<option value="">请选择</option>
					</select>
				</td>
				<td width="120" align="right">该盘总重量:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeightParent" id="prodRelWeightParentM3101Add"  readonly="readonly"   class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	        </tr>
	         <tr>     
	           <td width="120" align="right">该盘已入包数:</td>
	            <td width="210" align="right">
	            	<input name="prodRuNum" id="prodRuNumM3101Add"  readonly="readonly"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,32]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">该盘已入重量:</td>
	            <td width="210" align="right">
	            	<input name="prodRuWeight" id="prodRuWeightM3101Add"  readonly="readonly"   class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">产品定尺(米):</td>
	            <td width="210" align="right">
	            	<input name="prodLength" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">产品总重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" id="prodRelWeightM3101" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM3101()" style="width:70px;">称重</a>
	        	</td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">入库待处理品数量:</td>
	        	<td width="210" align="right">
	            	<input name="sumNum" id="sumNumM3101" class="easyui-numberbox" type="text" data-options="required:true,min:1,precision:0,validType:'length[0,10]'" value="1" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">单包件数:</td>
	        	<td width="210" align="right">
	            	<input name="prodNum" id="prodNumM3101" class="easyui-numberbox" type="text" data-options="required:true,min:1,precision:0,validType:'length[0,10]'" value="1" style="width:200px;"></input>
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
	            	<select name="prodRuc" id="prodRucM3101Add" class="easyui-combobox" panelHeight="auto" data-options="required:true,editable:true" style="width:200px;">
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
	            	<input name="prodInputuser" id="prodInputuserM3101Add" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="M3101Add" onclick="submitM3101AddForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeM3101AddForm()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//联动称重机获取实际重量
	function getWeightCheckM3101(){
		var params = {};
		$.post("/rxtx/weighing",params, function(data){
			if(data.status == 200){
				$("#prodRelWeightM3101").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//选择盘号回显已入库包数
	function onProdParentNoChangeM3101Add(){
		var pardParentNo = $("#prodParentNoM3101Add").textbox('getValue');
		var params = {"prodId":pardParentNo};
		$.post("/prodManager/getProdRuNums",params, function(data){
			if(data.status == 200){
				$("#prodRelWeightParentM3101Add").textbox('setValue',data.data.prodRelWeight+'');
				$("#prodRuNumM3101Add").textbox('setValue',data.data.prodRuNum+'');
				$("#prodRuWeightM3101Add").textbox('setValue',data.data.prodRuWeight+'');
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//提交表单
	function submitM3101AddForm(){
		
		//记录录入人员信息
		var inputUser = $("#user_id").html();
		$("#prodInputuserM3101Add").textbox('setValue',inputUser);
		var sumNum = $("#sumNumM3101").numberbox('getValue');
		//有效性验证
		if(!$('#dclProdAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.messager.confirm('确认','是否确认入库？',function(r){
			if (r){
				$('#M3101Add').linkbutton('disable');
				$.post("/dclProd/addProd",$("#dclProdAddForm").serialize(), function(data){
					if(data.status == 200){
						var prodId = data.data;
						$.messager.alert('提示','待处理品入库成功!起始单号为：'+prodId,'info',function(){
							if(sumNum<=1){
								$.messager.confirm('确认','是否需要打印PDF标签文件？',function(r){
									if (r){
					        	    	var mapParam = new Map();
					        			mapParam.set("url","/import/importPdf");
					        			mapParam.set("prodId",prodId);
					        			mapParam.set("tradeType","M3101");
					        			BSL.toNewPagePDF(mapParam);
					        	    }
								});
							}
							$("#dclProdAddWindow").window('close');
							searchM3101Form();
							//$("#receiptList").datagrid("reload");
						});
					}else{
						$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
					}
					$('#M3101Add').linkbutton('enable');
				});
			}
		});
	}
	
	function closeM3101AddForm(){
		$("#dclProdAddWindow").window('close');
	}
	
	//根据生产机组回显正在生产的指令等信息
	function onplanJzChangeM3101Add(){
		var jz = $("#prodMakeJzM3101Add").combobox("getValue");
		var params = {
			'planJz':jz
		};
		if(jz == "3"){
			$("#prodRucM3101Add").combobox('setValue','4');
		}else if(jz == "4"){
			$("#prodRucM3101Add").combobox('setValue','5');
		}
		
		$.post("/prodManager/getPlanExeInfo", params, function(data) {
			if (data.status == 200) {
				$("#prodPlanNoM3101Add").textbox('setValue',data.data.planId);
				$("#prodNormM3101Add").textbox('setValue',data.data.makeProdNorm);
				$("#prodMaterialM3101Add").combobox('setValue',data.data.prodMaterial);
				//给父级盘号赋值
				params = {
					'planId':data.data.planId
				};
				$.post("/prodManager/getParentPh", params, function(data2) {
					if (data2.status == 200) {
						$("#prodParentNoM3101Add").combobox('setValue', '');
						var dataSource = [];
						console.log(data2.data);
						for(var i=0;i<data2.data.length;i++){
							var value = data2.data[i];
							var text = value;
							dataSource.push({"value":value,"text":text});
						}
						$("#prodParentNoM3101Add").combobox("loadData",dataSource);
					} else {
						$.messager.alert('提示', data.msg);
						return false;
					}
					
				});
				
			} else {
				$.messager.alert('提示', data.msg);
				$('#prodPlanNoM3101Add').textbox('setValue','');
				$('#prodNormM3101Add').textbox('setValue','');
				$('#prodMaterialM3101Add').combobox('setValue','');
				return false;
			}
		});
	}
	
</script>
