<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="rawCheckAddForm" class="itemForm" method="post">
	   <table>
	        <tr>
	            <td width="120"  align="right">炉(批)号:</td>
	            <td width="210"  align="right">
	            	<input name="prodLuno" class="easyui-textbox" data-options="required:false,validType:'length[0,32]'" style="width:200px;"/>
	            </td>              
	        </tr>
	        <tr>	
	            <td width="120" align="right">物料名称:</td>
	            <td width="210" align="right">
	            	<input name="prodName" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	             <td width="120" align="right">规格:</td>
	             <td width="210" align="right">
	            	<input name="prodNorm" class="easyui-textbox" type="text" data-options="required:true,validType:'length[0,40]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">钢种:</td>
	            <td width="210" align="right">
	            	<select name="prodMaterial" class="easyui-combobox" panelHeight="auto" data-options="editable:true" style="width:200px;">
			          <option value="">请选择...</option>
			          <c:forEach items="${prodMaterialList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          </c:forEach>
					</select>
	            </td>
	            <td width="120" align="right">定尺/米:</td>
	            <td width="210" align="right">
	            	<input name="prodLength" id="prodLengthM1003" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">原料来料重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRecordWeight" id="prodRecordWeightM1003" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]',events:{blur:onRelWeightBlur}" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">来料复磅重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodRelWeight" id="prodRelWeightM1003" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]',events:{blur:onRelWeightBlur}"  style="width:200px;"></input>
	            	 <!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM1003()">称重</a> -->
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightCheckM1003()" style="width:70px;">称重</a>
	        	</td>	            
	        </tr>
	        <tr>	  
	        	<td width="120" align="right">原料入库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="prodPrintWeight" id="prodPrintWeightM1003" class="easyui-numberbox" type="text" data-options="required:true,min:0,precision:3,validType:'length[0,10]'"  style="width:200px;"></input>
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
	            	<input id="crtdateM1003" name="crtdateM1003" class="easyui-datebox" type="text" data-options="required:false" panelHeight="225px" style="width:200px;"></input>
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
	            <td width="120" align="right">审批人:</td>
	            <td width="210" align="right">
	            	<input name="prodCheckuser" id="prodCheckuserM1003" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">是否提示:</td>
	            <td width="210" align="right">
	            	<input id="isAlertM1003Check" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
		    </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" id="checkBeforeSubmitM1003Cfm" onclick="checkBeforeSubmitM1003Cfm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeCheckM1003Form()">关闭</a>
	</div>
</div>
<script type="text/javascript">

	//联动称重机获取实际重量
	function getWeightCheckM1003(){
		var params = {};
		$.post("/rxtx/weighingByD",params, function(data){
			if(data.status == 200){
				$("#prodRelWeightM1003").numberbox('setValue',data.data);
				onRelWeightBlur();
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//实际重量失去焦点时计算应打印重量
	function onRelWeightBlur(){
		$("#isAlertM1003Check").textbox("setValue","0");
		var recordWeight = $("#prodRecordWeightM1003").numberbox("getValue");
		var relWeight = $("#prodRelWeightM1003").numberbox("getValue");
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
				$("#isAlertM1003Check").textbox("setValue","1");
			}else{
				printWeight = recordWeight;
			}
		}
		$("#prodPrintWeightM1003").numberbox("setValue",printWeight);
	}
	
	//提交之前校验磅差
	function checkBeforeSubmitM1003Cfm(){
		//提交之前校验磅差
		var isAlert = $("#isAlertM1003Check").textbox("getValue");
		if(isAlert == "1"){
			$.messager.confirm('确认','磅差负偏差过大，确认继续录入？',function(r){
        	    if (r){
        	    	submitCheckM1003Form();
        	    }
			})
		}else{
			submitCheckM1003Form();
		}	
	}
	
	//提交表单
	function submitCheckM1003Form(){
		//记录复核人员信息
		var checkUser = $("#user_id").html(); 
		$("#prodCheckuserM1003").textbox('setValue',checkUser);
		
		//有效性验证
		if(!$('#rawCheckAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		$.messager.confirm('确认','是否确认入库？',function(r){
			if (r){
				$('#checkBeforeSubmitM1003Cfm').linkbutton('disable');
				$.post("/raw/cfmRaw?crtdateM1003="+$('#crtdateM1003').datebox("getValue"),$("#rawCheckAddForm").serialize(), function(data){
					if(data.status == 200){
						var prodId = data.data;
						$.messager.alert('提示','入库成功!单号为：'+prodId,'info',function(){
							$.messager.confirm('确认','是否需要生成PDF标签文件？',function(r){
								if (r){
				        	    	var mapParam = new Map();
				        			mapParam.set("url","/import/importPdf");
				        			mapParam.set("prodId",prodId);
				        			mapParam.set("tradeType","M1003");
				        			BSL.toNewPagePDF(mapParam);
				        	    }
							});
							$("#rawCheckAddWindow").window('close');
							searchM1003Form();
							//$("#preRawList").datagrid("reload");
						});
					}else{
						$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
					}
					$('#checkBeforeSubmitM1003Cfm').linkbutton('enable');
				});
			}
		});
	}
	
	function closeCheckM1003Form(){
		$("#rawCheckAddWindow").window('close');
	}
</script>
