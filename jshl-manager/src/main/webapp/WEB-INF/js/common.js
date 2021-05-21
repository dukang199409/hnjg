Date.prototype.format = function(format){ 
    var o =  { 
    "M+" : this.getMonth()+1, //month 
    "d+" : this.getDate(), //day 
    "h+" : this.getHours(), //hour 
    "m+" : this.getMinutes(), //minute 
    "s+" : this.getSeconds(), //second 
    "q+" : Math.floor((this.getMonth()+3)/3), //quarter 
    "S" : this.getMilliseconds() //millisecond 
    };
    if(/(y+)/.test(format)){ 
    	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
    for(var k in o)  { 
	    if(new RegExp("("+ k +")").test(format)){ 
	    	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	    } 
    } 
    return format; 
};

var TT = BSL = {
	// 编辑器参数
	kingEditorParams : {
		//指定上传文件参数名称
		filePostName  : "uploadFile",
		//指定上传文件请求的url。
		uploadJson : '/pic/upload',
		//上传类型，分别为image、flash、media、file
		dir : "image"
	},
	
	toExcel: function(mapParam){
		//向后台发送请求
		var form = $("<form>");//定义一个form表单
		form.attr("style","display:none");
		form.attr("target","");
		form.attr("method","post");
		form.attr("action",mapParam.get("url"));
		//将表单放到body中
		$("body").append(form);
		mapParam.forEach(function (item, key, mapObj) {
			//添加input
			var temp = $("<input>");
			temp.attr("type","hidden");
			temp.attr("name",key);
			temp.attr("value",item);
			form.append(temp);
		});
		form.submit();
	},
	toPDF: function(mapParam){
		//向后台发送请求
		var form = $("<form>");//定义一个form表单
		form.attr("style","display:none");
		form.attr("target","");
		form.attr("method","post");
		form.attr("action",mapParam.get("url"));
		//将表单放到body中
		$("body").append(form);
		mapParam.forEach(function (item, key, mapObj) {
			//添加input
			var temp = $("<input>");
			temp.attr("type","hidden");
			temp.attr("name",key);
			temp.attr("value",item);
			form.append(temp);
		});
		form.submit();
	},
	toNewPagePDF: function(mapParam){
		//向后台发送请求
		var form = $("<form>");//定义一个form表单
		form.attr("style","display:none");
		form.attr("target","");
		form.attr("method","post");
		form.attr("action",mapParam.get("url"));
		form.attr("target","_blank");//打开新页面
		//将表单放到body中
		$("body").append(form);
		mapParam.forEach(function (item, key, mapObj) {
			//添加input
			var temp = $("<input>");
			temp.attr("type","hidden");
			temp.attr("name",key);
			temp.attr("value",item);
			form.append(temp);
		});
		form.submit();
	},
	// 格式化时间
	formatFullDateTime : function(val,row){
		if(val == "" || val==null){
			return val;
		}
		var now = new Date(val);
    	return now.format("yyyy-MM-dd hh:mm:ss");
	},
	formatDateTime : function(val,row){
		if(val == "" || val==null){
			return val;
		}
		var now = new Date(val);
    	return now.format("yyyy-MM-dd");
	},
	// 格式化连接
	formatUrl : function(val,row){
		if(val){
			return "<a href='"+val+"' target='_blank'>查看</a>";			
		}
		return "";
	},
	// 格式化价格
	formatPrice : function(val,row){
		return (val/1000).toFixed(2);
	},
	/**
	 * enumEngName 枚举英文名
	 * enumKey 枚举key
	 * isStyle 是否给表格数据加样式：1-红色字体样式
	 */
	formatEnumCommon : function(enumEngName, enumKey, isStyle){
		if(enumKey == "" || enumKey == null || enumKey == undefined){
			return "";
		}
		//ajax设置为同步
		$.ajaxSettings.async = false;
		var enumV = "";
		$.post("/enum/queryValue/"+enumEngName+"/"+enumKey, null, function(data){
			if(isStyle=="1"){
				enumV = '<span style="color:red;">'+data+'</span>';
			} else {
				enumV = data;
			}
//			if(data.status == 200){
//				for (var i = 0; i < data.data.length; i++) {
//					if(data.data[i].enumKey == val) {
//						if(isStyle=="1"){
//							enumV = '<span style="color:red;">'+data.data[i].enumValue+'</span>';
//						} else {
//							enumV = data.data[i].enumValue;
//						}
//					}
//				}
//			}else{
//				$.messager.alert('提示',data.msg);
//			}
		}); 
		if(enumV == ""){
			return val;
		}
		return enumV;
	},
	// 格式化用户的状态
	formatItemStatus : function(val,row){
		//return BSL.formatEnumCommon("userStatus", val, val == "1" ? "1" : "0");
        if (val == '0'){
            return '正常';
        } else if(val == '1'){
        	return '<span style="color:red;">停用</span>';
        } else {
        	return val;
        }
    },
 // 格式化来料客户
    formatCustomer : function(val,row){
		//return BSL.formatEnumCommon("customer", val,"0");
        if (val == '00'){
            return '湖南中联重科建筑起重机械有限责任公司';
        } else{
        	return val;
        }
    },
    // 格式化用户角色
	formatUserType : function(val,row){
		//return BSL.formatEnumCommon("userType", val, "0");
        if (val == '00'){
            return '00-管理员';
        } else if(val == '01'){
        	return '01-总经理';
        } else if(val == '02'){
        	return '02-副总经理';
        } else if(val == '03'){
        	return '03-营销人员';
        } else if(val == '04'){
        	return '04-生产计划调度员';
        } else if(val == '05'){
        	return '05-原料库管员';
        } else if(val == '06'){
        	return '06-纵剪机组上料员';
        } else if(val == '07'){
        	return '07-纵剪机组入库员';
        } else if(val == '08'){
        	return '08-纵剪机组库管员';
        } else if(val == '09'){
        	return '09-成型机组上料员';
        } else if(val == '10'){
        	return '10-成型机组入库员';
        } else if(val == '11'){
        	return '11-成型机组库管员';
        } else if(val == '12'){
        	return '12-技术主管';
        } else if(val == '13'){
        	return '13-质量主管';
        } else if(val == '14'){
        	return '14-生产主管';
        } else if(val == '15'){
        	return '15-纵剪机组机长';
        } else if(val == '16'){
        	return '16-成型机组机长';
        } else if(val == '17'){
        	return '17-财务人员';
        } else if(val == '18'){
        	return '18-销售开单员';
        } else if(val == '19'){
        	return '19-外协厂管理员';
        } else {
        	return val;
        }
    },
    // 格式化销售出库标准
	formatSaleType : function(val,row){
		//return BSL.formatEnumCommon("saleType", val, "0");
        if (val == '0'){
            return '按重量销售出库';
        } else if(val == '1'){
        	return '按数量销售出库';
        } else if(val == '2'){
        	return '按产品编号销售出库';
        } else {
        	return val;
        }
    },
    // 格式化生产机组信息
	formatPlanJz : function(val,row){
		//return BSL.formatEnumCommon("saleType", val, "0");
        if (val == '1'){
            return '纵剪1组';
        } else if(val == '2'){
        	return '纵剪2组';
        } else if(val == '3'){
        	return '1号线';
        } else if(val == '4'){
        	return '2号线';
        } else {
        	return val;
        }
    },
    // 格式化交易代码
    formatTransCode : function(val,row){
    	//return BSL.formatEnumCommon("transCode", val, "0");
        if (val == '1901'){
            return '入库';
        } else if(val == '1902'){
        	return '制造出库';
        } else if(val == '1903'){
        	return '销售出库';
        } else if(val == '1904'){
        	return '剩余重新入库';
        } else if(val == '1905'){
        	return '未用退回';
        } else if(val == '1906'){
        	return '完成';
        } else if(val == '1907'){
        	return '删除';
        } else if(val == '1908'){
        	return '退货';
        } else if(val == '1909'){
        	return '磅差处理';
        } else if(val == '1910'){
        	return '接收入库';
        } else if(val == '1911'){
        	return '接收入库退回';
        } else {
        	return val;
        }
    },
    //格式化日期
    formatDate : function myparser(val,row) {
    	var date=new Date(val);
    	var year=date.getFullYear();
    	var month=date.getMonth()+1;
    	month=month>9?month:('0'+month);
    	var day=date.getDate();
    	day=day>9?day:('0'+day);
    	var hh=date.getHours();
    	hh=hh>9?hh:('0'+hh);
    	var mm=date.getMinutes();
    	mm=mm>9?mm:('0'+mm);
    	var ss=date.getSeconds();
    	ss=ss>9?ss:('0'+ss);
    	var time=year+'-'+month+'-'+day;
    	   return time;
	},
    // 格式化0-否 1-是
	formatIsOrNotStatus : function(val,row){
//		return BSL.formatEnumCommon("bsHasguarantee", val, "0");
        if (val == '0'){
            return '否';
        } else if(val == '1'){
        	return '是';
        } else {
        	return val;
        }
    },
    // 格式化收发单状态
	formatBsStatus : function(val,row){
		//return BSL.formatEnumCommon("bsStatus", val, "0");
        if (val == '0'){
            return '创建';
        } else if(val == '1'){
        	return '进行中';
        } else if(val == '2'){
        	return '已完成';
        } else if(val == '3'){
        	return '已发货';
        }else {
        	return val;
        }
    },
    // 入库仓库
	formatProdRuc : function(val,row){
		//return BSL.formatEnumCommon("bsStatus", val, "0");
        if (val == '0'){
            return '原料仓';
        } else if(val == '1'){
        	return '半成品仓库';
        } else if(val == '2'){
        	return '1号线-仓库';
        } else if(val == '3'){
        	return '2号线-仓库';
        } else if(val == '4'){
        	return '1号线-待处理仓';
        } else if(val == '5'){
        	return '2号线-待处理仓';
        } else if(val == '6'){
        	return '委外仓';
        } else {
        	return val;
        }
    },
    //格式化指令状态
    formatPlanStatus : function(val,row){
    	//return BSL.formatEnumCommon("planStatus", val, "0");
        if (val == '0'){
            return '创建';
        } else if(val == '1'){
        	return '进行中';
        } else if(val == '2'){
        	return '暂停';
        } else if(val == '3'){
        	return '生产完成';
        } else if(val == '4'){
        	return '强制终止';
        } else {
        	return val;
        }
    },
    //格式化生产班次
    formatProdBcStatus : function(val,row){
    	//return BSL.formatEnumCommon("planStatus", val, "0");
        if (val == '0'){
            return '纵剪1班';
        } else if(val == '1'){
        	return '纵剪2班';
        } else if(val == '2'){
        	return '1号线-1班';
        } else if(val == '3'){
        	return '1号线-2班';
        } else if(val == '4'){
        	return '2号线-1班';
        } else if(val == '5'){
        	return '2号线-2班';
        } else if(val == '6'){
        	return '外协厂';
        } else {
        	return val;
        }
    },
    //格式化产品库存状态
    formatProdStatus : function(val,row){
    	//return BSL.formatEnumCommon("prodStatus", val, "0");
        if (val == '0'){
            return '创建未入库';
        } else if(val == '1'){
        	return '在库';
        } else if(val == '2'){
        	return '已出库';
        } else if(val == '3'){
        	return '已分剪';
        } else if(val == '4'){
        	return '已发货';
        } else if(val == '5'){
        	return '出库待发货';
        } else if(val == '6'){
        	return '处理完成';
        } else if(val == '7'){
        	return '已转场';
        } else {
        	return val;
        }
    },
    //格式化产品库存状态
    formatHalfProdStatus : function(val,row){
    	//return BSL.formatEnumCommon("prodStatus", val, "0");
        if (val == '1'){
        	return '在库';
        } else if(val == '2'){
        	return '已出库';
        } else if(val == '3'){
        	return '已完成';
        } else if(val == '4'){
        	return '已发货';
        } else if(val == '5'){
        	return '出库待发货';
        }else if(val == '6'){
        	return '处理完成';
        }else {
        	return val;
        }
    },
    //格式化产品类型
    formatProdType : function(val,row){
    	//return BSL.formatEnumCommon("prodType", val, "0");
        if (val == '0'){
            return '卷板';
        } else if(val == '1'){
        	return '半成品';
        } else if(val == '2'){
        	return '产品';
        } else if(val == '3'){
        	return '废品';
        } else if(val == '4'){
        	return '待处理品';
        }else{
        	return val;
        }
    },
    //格式化销售详细状态
    formatSaleStatus : function(val,row){
    	//return BSL.formatEnumCommon("saleStatus", val, "0");
        if (val == '0'){
            return '未达发货标准';
        } else if(val == '1'){
        	return '已达发货标准';
        } else if(val == '2'){
        	return '已发货';
        } else {
        	return val;
        }
    },
   //格式化制造纵剪带用途
    formatMakeType : function(val,row){
    	//return BSL.formatEnumCommon("makeType", val, "0");
        if (val == '0'){
            return '自用';
        } else if(val == '1'){
        	return '外销';
        } else if(val == '2'){
        	return '共用';
        } else {
        	return val;
        }
    },
    //格式化销售出库限制方式
    formatSaleFlag : function(val,row){
    	//return BSL.formatEnumCommon("saleFlag", val, "0");
        if (val == '0'){
            return '按重量标准出库';
        } else if(val == '1'){
        	return '按数量标准出库';
        } else {
        	return val;
        }
    },
    //格式化提货方式
	formatBsGettype : function(val,row){
		//return BSL.formatEnumCommon("bsGettype", val, "0");
        if (val == '0'){
            return '客户自提';
        } else if(val == '1'){
        	return '配送';
        } else if(val == '2'){
        	return '代办运输';
        } else {
        	return val;
        }
    },
    //格式化收发类别
	formatBsFlag : function(val,row){
		//return BSL.formatEnumCommon("bsFlag", val, "0");
        if (val == '0'){
            return '自购卷板';
        } else if(val == '1'){
        	return '客户来料';
        } else if(val == '2'){
        	return '半成品发货';
        } else if(val == '3'){
        	return '成品发货';
        } else if(val == '4'){
        	return '废品发货';
        } else if(val == '5'){
        	return '卷板销售发货';
        } else if(val == '6'){
        	return '卷板退货';
        } else if(val == '7'){
        	return '待处理品处理发货';
        } else if(val == '8'){
        	return '委外仓成品发货';
        } else if(val == '9'){
        	return '委外仓废品发货';
        } else if(val == '10'){
        	return '成品转场发货';
        } else if(val == '11'){
        	return '废品转场发货';
        } else {
        	return val;
        }
    },
    //格式化状态维护类型
	formatChangeType : function(val,row){
		//return BSL.formatEnumCommon("changeType", val, "0");
        if (val == '0'){
            return '产品状态维护';
        } else if(val == '3'){
        	return '废品重量维护';
        } else if(val == '2'){
        	return '通知单状态维护';
        } else {
        	return val;
        }
    }, 
    //格式化产品标志
	formatProdDclFlag : function(val,row){
		//return BSL.formatEnumCommon("changeType", val, "0");
        if (val == '0'){
            return '本厂';
        } else if(val == '1'){
        	return '转场';
        } else if(val == '2'){
        	return '加工';
        } else {
        	return val;
        }
    },
    //格式化产品来源
    formatProdSource : function(val,row){
    	//return BSL.formatEnumCommon("prodSource", val, "0");
        if (val == '0'){
            return '自购入库';
        } else if(val == '1'){
        	return '客户来料';
        } else if(val == '2'){
        	return '剩余入库';
        } else if(val == '3'){
        	return '退货入库';
        } else {
        	return val;
        }
    },
    //格式化规格
	formatProdNorm : function(val,row){
		//return BSL.formatEnumCommon("prodNorm", val, "0");
        if (val == '0'){
            return 'F86*86*8';
        } else if(val == '1'){
        	return 'F96*96*8';
        } else if(val == '2'){
        	return 'F100*100*6';
        } else if(val == '3'){
        	return 'F106*106*8';
        } else if(val == '4'){
        	return 'F108*108*10';
        } else if(val == '5'){
        	return 'F118*118*10';
        } else if(val == '6'){
        	return 'F135*135*10';
        } else if(val == '7'){
        	return 'F135*135*12';
        } else if(val == '8'){
        	return 'F118*118*10';
        } else if(val == '9'){
        	return 'F180*180*12.5';
        } else {
        	return val;
        }
    },
    //格式化钢种
	formatProdMaterial : function(val,row){
		//return BSL.formatEnumCommon("prodMaterial", val, "0");
        if (val == '0'){
            return '510L';
        } else if(val == '1'){
        	return 'GR.B';
        } else if(val == '2'){
        	return 'Q235B';
        } else if(val == '3'){
        	return 'Q345B';
        } else if(val == '4'){
        	return 'Q345D';
        } else if(val == '5'){
        	return 'Q345E';
        } else if(val == '6'){
        	return 'Q355B';
        } else if(val == '7'){
        	return 'Q355C';
        } else if(val == '8'){
        	return 'Q355D';
        } else if(val == '9'){
        	return 'Q420';
        } else if(val == 'A'){
        	return 'Q450NQR1';
        } else if(val == 'B'){
        	return 'Q460C';
        } else if(val == 'C'){
        	return 'Q550D';
        } else if(val == 'D'){
        	return 'QSTE500';
        } else if(val == 'E'){
        	return 'QStE700TM';
        } else if(val == 'F'){
        	return 'S355J0H';
        } else if(val == 'G'){
        	return 'S355J2H';
        } else if(val == 'H'){
        	return 'S355JR';
        } else if(val == 'I'){
        	return 'SPA-H';
        } else if(val == 'J'){
        	return 'SS400';
        } else if(val == 'K'){
        	return 'Q380LW';
        } else {
        	return val;
        }
    },
    //格式化质量等级
    formatProdLevel : function(val,row){
    	//return BSL.formatEnumCommon("prodLevel", val, "0");
        if (val == '0'){
            return 'GB/T6728-2017';
        } else if(val == '1'){
        	return 'GB/T6725-2017';
        } else if(val == '2'){
        	return 'GB/T6723-2017';
        } else if(val == '3'){
        	return 'GB/T3274-2017';
        } else if(val == '4'){
        	return 'GB/T3273-2015';
        } else if(val == '5'){
        	return 'GB/T26080-2010';
        } else if(val == '6'){
        	return 'EN 10219';
        } else if(val == '7'){
        	return 'EN 10210';
        } else if(val == '8'){
        	return 'ASTM A500';
        } else if(val == '9'){
        	return '双方协议标准';
        } else if(val == 'A'){
        	return '湖南巨工企标';
        } else if(val == 'B'){
        	return 'QZLJQ 105005-2014';
        }else {
        	return val;
        }
    },
    //格式化废品类型
    formatWasteType : function(val,row){
    	//return BSL.formatEnumCommon("wasteType", val, "0");
        if (val == '0'){
            return '边丝废品';
        } else if(val == '1'){
        	return '料头';
        } else if(val == '2'){
        	return '压块';
        } else if(val == '3'){
        	return '型材废钢';
        } else if(val == '4'){
        	return '废管';
        }else if(val == '5'){
        	return '锯末铁屑';
        }else if(val == '6'){
        	return '其他';
        }else{
        	return val;
        }
    },
  //格式化生产部门
    formatMakeDept : function(val,row){
    	//return BSL.formatEnumCommon("planDepartment", val, "0");
        if (val == '0'){
            return '纵剪机组';
        } else if(val == '1'){
        	return '生产机组';
        } else if(val == '2'){
        	return '生产机组2';
        } else {
        	return val;
        }
    },
    init : function(data){
    	// 初始化图片上传组件
    	this.initPicUpload(data);
    	// 初始化选择类目组件
    	this.initItemCat(data);
    },
    // 初始化图片上传组件
    initPicUpload : function(data){
    	$(".picFileUpload").each(function(i,e){
    		var _ele = $(e);
    		_ele.siblings("div.pics").remove();
    		_ele.after('\
    			<div class="pics">\
        			<ul></ul>\
        		</div>');
    		// 回显图片
        	if(data && data.pics){
        		var imgs = data.pics.split(",");
        		for(var i in imgs){
        			if($.trim(imgs[i]).length > 0){
        				_ele.siblings(".pics").find("ul").append("<li><a href='"+imgs[i]+"' target='_blank'><img src='"+imgs[i]+"' width='80' height='50' /></a></li>");
        			}
        		}
        	}
        	//给“上传图片按钮”绑定click事件
        	$(e).click(function(){
        		var form = $(this).parentsUntil("form").parent("form");
        		//打开图片上传窗口 富文本插件
        		KindEditor.editor(TT.kingEditorParams).loadPlugin('multiimage',function(){
        			var editor = this;
        			editor.plugin.multiImageDialog({
						clickFn : function(urlList) {
							var imgArray = [];
							KindEditor.each(urlList, function(i, data) {
								imgArray.push(data.url);
								form.find(".pics ul").append("<li><a href='"+data.url+"' target='_blank'><img src='"+data.url+"' width='80' height='50' /></a></li>");
							});
							form.find("[name=image]").val(imgArray.join(","));
							editor.hideDialog();
						}
					});
        		});
        	});
    	});
    },
    
    // 初始化选择类目组件
    initItemCat : function(data){
    	$(".selectItemCat").each(function(i,e){
    		var _ele = $(e);
    		if(data && data.cid){
    			_ele.after("<span style='margin-left:10px;'>"+data.cid+"</span>");
    		}else{
    			_ele.after("<span style='margin-left:10px;'></span>");
    		}
    		_ele.unbind('click').click(function(){
    			$("<div>").css({padding:"5px"}).html("<ul>")
    			.window({
    				width:'500',
    			    height:"450",
    			    modal:true,
    			    closed:true,
    			    iconCls:'icon-save',
    			    title:'选择类目',
    			    onOpen : function(){
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/item/cat/list',
    			    		animate:true,
    			    		onClick : function(node){
    			    			if($(this).tree("isLeaf",node.target)){
    			    				// 填写到cid中
    			    				_ele.parent().find("[name=cid]").val(node.id);
    			    				_ele.next().text(node.text).attr("cid",node.id);
    			    				$(_win).window('close');
    			    				if(data && data.fun){
    			    					data.fun.call(this,node);
    			    				}
    			    			}
    			    		}
    			    	});
    			    },
    			    onClose : function(){
    			    	$(this).window("destroy");
    			    }
    			}).window('open');
    		});
    	});
    },
    
    createEditor : function(select){
    	return KindEditor.create(select, TT.kingEditorParams);
    },
    
    /**
     * 创建一个窗口，关闭窗口后销毁该窗口对象。<br/>
     * 
     * 默认：<br/>
     * width : 80% <br/>
     * height : 80% <br/>
     * title : (空字符串) <br/>
     * 
     * 参数：<br/>
     * width : <br/>
     * height : <br/>
     * title : <br/>
     * url : 必填参数 <br/>
     * onLoad : function 加载完窗口内容后执行<br/>
     * 
     * 
     */
    createWindow : function(params){
    	$("<div>").css({padding:"5px"}).window({
    		width : params.width?params.width:"80%",
    		height : params.height?params.height:"80%",
    		modal:true,
    		title : params.title?params.title:" ",
    		href : params.url,
		    onClose : function(){
		    	$(this).window("destroy");
		    },
		    onLoad : function(){
		    	if(params.onLoad){
		    		params.onLoad.call(this);
		    	}
		    }
    	}).window("open");
    },
    
    closeCurrentWindow : function(){
    	$(".panel-tool-close").click();
    },
    
    changeItemParam : function(node,formId){
    	$.getJSON("/item/param/query/itemcatid/" + node.id,function(data){
			  if(data.status == 200 && data.data){
				 $("#"+formId+" .params").show();
				 var paramData = JSON.parse(data.data.paramData);
				 var html = "<ul>";
				 for(var i in paramData){
					 var pd = paramData[i];
					 html+="<li><table>";
					 html+="<tr><td colspan=\"2\" class=\"group\">"+pd.group+"</td></tr>";
					 
					 for(var j in pd.params){
						 var ps = pd.params[j];
						 html+="<tr><td class=\"param\"><span>"+ps+"</span>: </td><td><input autocomplete=\"off\" type=\"text\"/></td></tr>";
					 }
					 
					 html+="</li></table>";
				 }
				 html+= "</ul>";
				 $("#"+formId+" .params td").eq(1).html(html);
			  }else{
				 $("#"+formId+" .params").hide();
				 $("#"+formId+" .params td").eq(1).empty();
			  }
		  });
    },
    getSelectionsIds : function (select){
    	var list = $(select);
    	var sels = list.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    },
    
    /**
     * 初始化单图片上传组件 <br/>
     * 选择器为：.onePicUpload <br/>
     * 上传完成后会设置input内容以及在input后面追加<img> 
     */
    initOnePicUpload : function(){
    	$(".onePicUpload").click(function(){
			var _self = $(this);
			KindEditor.editor(TT.kingEditorParams).loadPlugin('image', function() {
				this.plugin.imageDialog({
					showRemote : false,
					clickFn : function(url, title, width, height, border, align) {
						var input = _self.siblings("input");
						input.parent().find("img").remove();
						input.val(url);
						input.after("<a href='"+url+"' target='_blank'><img src='"+url+"' width='80' height='50'/></a>");
						this.hideDialog();
					}
				});
			});
		});
    },
    
    
};

