<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="pEditing:10px 10px 10px 10px">
	<form id="qualityEditForm" class="itemForm" method="post">
	   <table>
	       <tr>
	        	<td width="120" align="right">炉(批)号:</td>
	            <td width="210" align="right">
	            	<input name="luId" class="easyui-textbox" type="text" readonly="readonly" data-options="required:true,validType:'length[0,32]'" style="width:200px;"></input>
	            </td>
	       		<td width="120" align="right">工厂:</td>
	            <td width="210" align="right">
	            	<input name="luCompany" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,100]'" style="width:200px;"></input>
	            </td>
	        </tr>
	       <tr>
	            <td width="120" align="right">化学成份C(x100):</td>
	            <td width="210" align="right">
	            	<input name="chemicalC"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">化学成份Mn(x100):</td>
	            <td width="210" align="right">
	            	<input name="chemicalMn"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr> 
	        <tr>
	            <td width="120" align="right">化学成份Si(x100):</td>
	            <td width="210" align="right">
	            	<input name="chemicalSi"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">化学成份S(x1000):</td>
	            <td width="210" align="right">
	            	<input name="chemicalS"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	         <tr>
	            <td width="120" align="right">化学成份P(x1000):</td>
	            <td width="210" align="right">
	            	<input name="chemicalP"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">化学成份Ti(x100):</td>
	            <td width="210" align="right">
	            	<input name="chemicalTi"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	         <tr>
	            <td width="120" align="right">化学成份Ni(x100):</td>
	            <td width="210" align="right">
	            	<input name="chemicalNi"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">化学成份Cr(x100):</td>
	            <td width="210" align="right">
	            	<input name="chemicalCr"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	         <tr>
	            <td width="120" align="right">化学成份Cu(x100):</td>
	            <td width="210" align="right">
	            	<input name="chemicalCu"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">化学成份Nb(x100):</td>
	            <td width="210" align="right">
	            	<input name="chemicalNb"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">力学性能屈服点σs(MPa):</td>
	            <td width="210" align="right">
	            	<input name="mechanicalS"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">力学性能抗拉强度σb(Mpa):</td>
	            <td width="210" align="right">
	            	<input name="mechanicalB"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">力学性能伸长率(%):</td>
	            <td width="210" align="right">
	            	<input name="mechanicalL"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	            <td width="120" align="right">弯曲180ºd=a:</td>
	            <td width="210" align="right">
	            	<input name="bendwc"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">压扁:</td>
	            <td width="210" align="right">
	            	<input name="bendyb"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	        </tr>
	          <tr> 
	            <td width="120" align="right">V型冲击力温度T(℃):</td>
	            <td width="210" align="right">
	            	<input name="impactT"  class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:0,validType:'length[0,10]'" style="width:200px;"></input>
	            </td><td width="120" align="right">V型冲击力数值1(J):</td>
	            <td width="210" align="right">
	            	<input name="impactN1"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	        </tr>
	         <tr> 
	           <td width="120" align="right">V型冲击力数值2(J):</td>
	            <td width="210" align="right">
	            	<input name="impactN2"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">V型冲击力数值3(J):</td>
	            <td width="210" align="right">
	            	<input name="impactN3"  class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,20]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr> 
	        	<td width="120" align="right">备注:</td>
	            <td width="210" align="right">
	            	<input name="remark" class="easyui-textbox" type="text" data-options="required:false,validType:'length[0,120]'" style="width:200px;" maxLength="10"></input>
	            </td>
	        </tr>
	        <tr hidden="true">
	            <td width="120" align="right">录入人:</td>
	            <td width="210" align="right">
	            	<input name="inputUser" id="inputUserM5001Edit" class="easyui-textbox" type="text" data-options="required:false" style="width:200px;"></input>
	            </td>
	        </tr>	
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM1005Edit()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeM1005Edit()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//提交表单
	function submitM1005Edit(){
		//记录复核人员信息
		var checkUser = $("#user_id").html(); 
		$("#inputUserM5001Edit").textbox('setValue',checkUser);
		
		//有效性验证
		if(!$('#qualityEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//ajax的post方式提交表单
		//$("#itemEditForm").serialize()将表单序列号为key-value形式的字符串
		$.post("/quality/edit",$("#qualityEditForm").serialize(), function(data){
			if(data.status == 200){
				var luId = data.data;
				$.messager.alert('提示','修改成功!炉号为：'+luId,'info',function(){
					$("#qualityEditWindow").window('close');
					searchM1005Form();
				});
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	function closeM1005Edit(){
		$("#qualityEditWindow").window('close');
	}
</script>
