<%@ page language="java"  pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<style>
	tr{height:30px}
</style>
<div style="padding:10px 10px 10px 10px">
	<form id="M1005ImportForm" method="post" action="/quality/import" enctype="multipart/form-data" >
	     <table>
	     	<tr>
	            <td width="100" align="right">请选择文件：</td>
	            <td width="310" align="left">
	            	<input type="file" id="importExcel" name="importExcel"/>
	            	<!--<input id="importExcel" class="easyui-filebox" name ="url" data-options="prompt:'请选择Excel文件',
   						buttonText:'选择文件', accept:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel'" style="width:400px">-->
	            </td>  
	        </tr>
	    </table>
	</form>
	<div style="margin-top:10px" align="center">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitM1005Import()">导入</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetM1005Import()">取消</a>
	</div>
</div>
<script type="text/javascript">

	//提交表单
	function submitM1005Import(){
		$("#M1005ImportForm").attr("target","_blank");//打开新页面
		$("#M1005ImportForm").attr("action","/quality/import");
		$("#M1005ImportForm").submit();
		$("#qualityImportWindow").window('close');
	} 

	function resetM1005Import(){
		$("#qualityImportWindow").window('close');
	}
	
</script>
