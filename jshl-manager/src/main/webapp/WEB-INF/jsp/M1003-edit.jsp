<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="rawEditForm" class="itemForm" method="post">
	  <table>
	        <tr>
	            <td width="120" align="right">原料物料编码:</td>
	            <td width="210" align="right">
	            	<input name="prodId" class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,20]'"  style="width:200px;"></input>
	            </td>      
	        </tr>
	        <tr>	      
	        	<td width="120"  align="right">炉(批)号:</td>
	            <td width="210"  align="right">
	            	<input name="prodLuno" class="easyui-textbox" data-options="required:false,validType:'length[0,32]'" style="width:200px;"/>
	            </td>     
	            <td width="120" align="right">物料名称:</td>
	            <td width="210" align="right">
	            	<input name="prodName" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">规格:</td>
	            <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">钢种:</td>
	            <td width="210" align="right">
		            	<select name="prodMaterial" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
				          <option value="">请选择...</option>
				           <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          		</c:forEach>
						</select>
		            </td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">定尺/米:</td>
	            <td width="210" align="right">
	            	<input name="prodLength" id="prodLengthM1003" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">原料来料重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRecordWeight" id="prodRecordWeightM1003Edit" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]',events:{blur:onRelWeightBlurEdit}" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	        	<td width="120" align="right">来料复磅重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" id="prodRelWeightM1003Edit" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]',events:{blur:onRelWeightBlurEdit}"  style="width:200px;"></input>
	            	 <!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM1003()">称重</a> -->
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightEditM1003()" style="width:70px;">称重</a>
	        	</td>	            
	        </tr>
	        <tr>	  
	        	<td width="120" align="right">原料入库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodPrintWeight" id="prodPrintWeightM1003Edit" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
	            </td>  
	        	<td width="120" align="right">厂家:</td>
	            <td width="210" align="right">
	            	<input name="prodCompany" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>        
	        </tr>
	        <tr>	   
	             <td width="120" align="right">客户:</td>
	            <td width="210" align="right">
	            	<input name="prodCustomer" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>	
	           <td width="120" align="right">钢厂钢卷号:</td>
	            <td width="210" align="right">
	            	<input name="prodOrirawid" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;" maxLength="10"></input>
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
	            <td width="120" align="right">入库日期:</td>
	            <td width="210" align="right">
	            	<input id="crtDateM1003Edit" name="crtDateM1003Edit" class="easyui-datebox" type="text" panelHeight="225px" data-options="required:false" style="width:200px;"></input>
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
	            <td width="120" align="right">是否提示:</td>
	            <td width="210" align="right">
	            	<input id="isAlertM1003Edit" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">入库日期:</td>
	            <td width="210"  align="right">
	        		<input name="crtDateM1003EditHidden" id="crtDateM1003EditHidden" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	        	</td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="checkBeforeSubmitEditM1003()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeEditM1003Form()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//联动称重机获取实际重量
	function getWeightEditM1003(){
		var params = {};
		$.post("/rxtx/weighingByD",params, function(data){
			if(data.status == 200){
				$("#prodRelWeightM1003Edit").numberbox('setValue',data.data);
				onRelWeightBlurEdit();
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//实际重量失去焦点时计算应打印重量
	function onRelWeightBlurEdit(){
		$("#isAlertM1003Edit").textbox("setValue","0");
		var recordWeight = $("#prodRecordWeightM1003Edit").numberbox("getValue");
		var relWeight = $("#prodRelWeightM1003Edit").numberbox("getValue");
		if(recordWeight > 0){
			var printWeight = 0;
			//实际重量为空 ，返回空
			if(relWeight == null || relWeight == ""){
				printWeight = 0;		
			}else if(recordWeight == null || recordWeight == "" ){
				//记载重量为空则填实际重量
				printWeight = relWeight;
			}else{
				var bc = (relWeight - recordWeight)/recordWeight;
				if(bc<-0.003){
					printWeight = relWeight;
					$("#isAlertM1003Edit").textbox("setValue","1");
				}else{
					printWeight = recordWeight;
				}
			}
			$("#prodPrintWeightM1003Edit").numberbox("setValue",printWeight);
		}else{
			$("#prodPrintWeightM1003Edit").numberbox("setValue",relWeight);
		}
	}
	
	//提交之前校验磅差
	function checkBeforeSubmitEditM1003(){
		//提交之前校验磅差
		var isAlert = $("#isAlertM1003Edit").textbox("getValue");
		if(isAlert == "1"){
			$.messager.confirm('确认','磅差负偏差过大，确认继续录入？',function(r){
	    	    if (r){
	    	    	submitEditM1003Form();
	    	    }
			})
		}else{
			submitEditM1003Form();
		}	
	}
	
	//提交表单
	function submitEditM1003Form(){
		//有效性验证
		if(!$('#rawEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		$("#crtDateM1003EditHidden").textbox('setValue',$('#crtDateM1003Edit').datebox("getValue"));
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/raw/edit",$("#rawEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改入库信息成功!单号为：'+data.data,'info',function(){
					$("#rawEditWindow").window('close');
					searchM1003Form();
					//$("#preRawList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeEditM1003Form(){
		$("#rawEditWindow").window('close');
	}
</script>
