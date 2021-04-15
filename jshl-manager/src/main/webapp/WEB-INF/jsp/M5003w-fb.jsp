<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="pEditByIding:10px 10px 10px 10px">
	<form id="saleAllFbFormWx" class="itemForm" method="post">
	   <table>
	   		<tr>
	   			<td width="120" align="right">销售出库通知单号:</td>
	            <td width="210" align="right">
	            	<input name="bsId" id="bsIdFbAllM5003w" class="easyui-textbox" type="text" readonly="readonly" style="width:200px;"></input>
	            </td>
	            <td width="120" align="right">通知单类别: </td>
	            <td width="210" align="right">
	            	<select name="bsFlag" class="easyui-combobox" readonly="readonly" panelHeight="auto" data-options="editable:true" style="width:200px;">
			         	  <option value="">请选择...</option>
				          <c:forEach items="${bsFlagList}" var="a">
			          	   	<option value="${a.enumKey}">${a.enumValue}</option>
			          	  </c:forEach>
					</select>
	            </td>	           
	        </tr>
	        <tr>
	            <td width="120" align="right">应出库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="bsWeight" class="easyui-numberbox" readonly="readonly" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        	<td width="120" align="right">已经出库重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="bsOutWeight" id="bsOutWeightFbM5003w" class="easyui-numberbox" readonly="readonly" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td width="120" align="right">复磅重量/吨:</td>
	            <td width="210" align="right">
	            	<input name="bsFbWeight" id = "bsAllFbWeightM5003w" class="easyui-numberbox" type="text" data-options="required:false,min:0,precision:3,validType:'length[0,10]'" style="width:200px;"></input>
	            </td>
	            <td width="120" align="left">
	        		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getWeightFBM5003w()" style="width:70px;">称重</a>
	        	</td>
	        </tr>
	    </table>
	    <div style="margin-top:10px" align="center">
		    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM5003wFb()">提交</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeM5003wFb()">关闭</a>
		</div>
	</form>
</div>
<script type="text/javascript">
	
	//联动称重机获取实际重量
	function getWeightFBM5003w(){
		var params = {};
		$.post("/rxtx/weighing",params, function(data){
			if(data.status == 200){
				$("#bsAllFbWeightM5003w").numberbox('setValue',data.data);
			}else{
				$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
			}
		});
	}
	
	//提交表单
	function submitM5003wFb(){
		//比对复磅重量
		var bsOutWeight = $("#bsOutWeightFbM5003w").numberbox('getValue');
		var bsFbWeight = $("#bsAllFbWeightM5003w").numberbox('getValue');
		if(bsFbWeight == '' || bsFbWeight == 0){
			$.messager.alert('提示','复磅重量不能为空!');
			return;
		}
		var bc = (bsFbWeight - bsOutWeight)/bsOutWeight;
		var params = {'bsId':$("#bsIdFbAllM5003w").textbox('getValue'),'bsFbweight':bsFbWeight};
		if(bc < -0.003 || bc > 0.003){
			$.messager.confirm('确认','销售复磅重量与实际出库重量相差超过正负3/1000，确认继续录入复磅重量？',function(r){
        	    if(r){
        	    	$.post("/doSalePlan/saleFbAll",params, function(data){
    					if(data.status == 200){
    						$.messager.alert('提示','复磅成功!单号为：'+data.data,'info',function(){
    							$("#saleAllFbWindowWx").window('close');
    							searchM5003wForm();
    						});
    					}else{
    						$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
    					}
    				});
        	    }
			})
		}else{
			$.post("/doSalePlan/saleFbAll",params, function(data){
				if(data.status == 200){
					$.messager.alert('提示','复磅成功!单号为：'+data.data,'info',function(){
						$("#saleAllFbWindowWx").window('close');
						searchM5003wForm();
					});
				}else{
					$.messager.alert('提示','错误码：'+data.status+',错误信息：'+data.msg);
				}
			});
		}
		
	}
	
	function closeM5003wFb(){
		$("#saleAllFbWindowWx").window('close');
	}
	

</script>
