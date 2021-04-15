package com.bsl.select;

import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

import com.bsl.controller.common.DataGridToExcelController;

public class DictItemOperation {
	
	public static final String 收发标志_原料入库通知单 = "0";
	public static final String 收发标志_销售出库通知单 = "1";
	
	public static final String 人员角色_管理员 = "00";
	public static final String 人员角色_总经理 = "01";
	public static final String 人员角色_副总经理 = "02";
	public static final String 人员角色_营销人员 = "03";
	public static final String 人员角色_财务人员 = "17";
	public static final String 管理员 = "000000";
	
	public static final String 用户状态_正常 = "0";
	public static final String 用户状态_停用 = "1";
	
	public static final String 产品类型_卷板 = "0";
	public static final String 产品类型_半成品 = "1";
	public static final String 产品类型_成品 = "2";
	public static final String 产品类型_废品 = "3";
	public static final String 产品类型_待处理品 = "4";
	
	public static final String 产品状态_创建未入库 = "0";
	public static final String 产品状态_已入库 = "1";
	public static final String 产品状态_已出库 = "2";
	public static final String 产品状态_已完成 = "3";
	public static final String 产品状态_已发货 = "4";
	public static final String 产品状态_出库待发货 = "5";
	public static final String 产品状态_处理完成 = "6";
	public static final String 产品状态_已转场 = "7";
	
	public static final String 入库仓库_委外仓 = "6";
	
	public static final String 产品来源_自购入库 = "0";
	public static final String 产品来源_厂家入库 = "1";
	public static final String 产品来源_剩余入库 = "2";
	public static final String 产品来源_退货入库 = "3";
	
	public static final String 产品外协厂标志_本厂 = "0";
	public static final String 产品外协厂标志_转场 = "1";
	public static final String 产品外协厂标志_加工 = "2";
	
	public static final String 是否标志_是 = "1";
	public static final String 是否标志_否 = "0";
	
	public static final String 收发类别_自购卷板 = "0";
	public static final String 收发类别_外接卷板 = "1";
	public static final String 收发类别_半成品发货 = "2";
	public static final String 收发类别_成品发货 = "3";
	public static final String 收发类别_废品发货 = "4";
	public static final String 收发类别_卷板销售发货 = "5";
	public static final String 收发类别_卷板退货 = "6";
	public static final String 收发类别_待处理品处理发货 = "7";
	public static final String 收发类别_委外仓成品发货 = "8";
	public static final String 收发类别_委外仓废品发货 = "9";
	public static final String 收发类别_成品转场发货 = "10";
	public static final String 收发类别_废品转场发货 = "11";
	
	public static final String 提货方式_客户自提 = "0";
	public static final String 提货方式_配送 = "1";
	public static final String 提货方式_代办运输 = "2";
	
	public static final String 通知单状态_创建 = "0";
	public static final String 通知单状态_进行中 = "1";
	public static final String 通知单状态_已完成 = "2";
	public static final String 通知单状态_已发货 = "3";
	
	public static final String 库存变动交易码_入库 = "1901";
	public static final String 库存变动交易码_制造出库 = "1902";
	public static final String 库存变动交易码_出售出库 = "1903";
	public static final String 库存变动交易码_剩余重新入库 = "1904";
	public static final String 库存变动交易码_未用退回 = "1905";
	public static final String 库存变动交易码_完成 = "1906";
	public static final String 库存变动交易码_删除 = "1907";
	public static final String 库存变动交易码_退货 = "1908";
	public static final String 库存变动交易码_磅差处理 = "1909";
	public static final String 库存变动交易码_接收入库 = "1910";
	public static final String 库存变动交易码_接收入库退回 = "1911";

	public static final String 废品类型_边丝废品 = "0";
	public static final String 废品类型_料头 = "1";
	public static final String 废品类型_压块 = "2";
	public static final String 废品类型_型材废钢 = "3";
	public static final String 废品类型_废管 = "4";
	public static final String 废品类型_锯末铁屑 = "5";
	public static final String 废品类型_其他 = "6";
	
	public static final String 指令类别_半成品生产指令= "0";
	public static final String 指令类别_成品生产指令 = "1";
	
	public static final String 状态强制维护类别_产品状态维护= "0";
	public static final String 状态强制维护类别_收发货通知单状态维护 = "1";
	public static final String 状态强制维护类别_生产指令维护= "2";
	public static final String 状态强制维护类别_废品重量维护 = "3";
	
	public static final String 指令状态_创建 = "0";
	public static final String 指令状态_进行中 = "1";
	public static final String 指令状态_已完成 = "3";
	public static final String 指令状态_暂停 = "2";
	public static final String 指令状态_强制终止 = "4";
	
	public static final String 卷板操作标志_未用退回 = "1";
	public static final String 卷板操作标志_出库 = "2";
	public static final String 卷板操作标志_完成 = "3";
	
	public static final String 销售单出库标志_未达标准 = "0";
	public static final String 销售单出库标志_已达标准= "1";
	public static final String 销售单出库标志_已发货 = "2";
	
	public static final String 销售单出库标准_按重量销售出库 = "0";
	public static final String 销售单出库标准_按数量销售出库= "1";
	public static final String 销售单出库标准_按产品编号销售出库 = "2";
	
	public static final String 纵剪带用途_自用 = "0";
	public static final String 纵剪带用途_外销 = "1";
	public static final String 纵剪带用途_公用 = "2";
	
	public static final String 处理类型_退货处理 = "2";
	public static final String 处理类型_磅差处理 = "1";
	
	public static final SimpleDateFormat 日期转换实例 = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat 日期转换实例yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat 日期转换实例时分秒 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
     * 数组转换成十六进制字符串
     * @param byte[]
     * @return HexString
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
    
    //日志记录实例
    public static final Logger log = Logger.getLogger(DataGridToExcelController.class.getClass());
    
    //钢种转换
    public static final String getNumByKegMetarial(String val){
    	if (val == "510L"){
            return "0";
        } else if(val.equals("GR.B")){
        	return "1";
        } else if(val.equals("Q235B")){
        	return "2";
        } else if(val.equals("Q345B")){
        	return "3";
        } else if(val.equals("Q345D")){
        	return "4";
        } else if(val.equals("Q345E")){
        	return "5";
        } else if(val.equals("Q355B")){
        	return "6";
        } else if(val.equals("Q355C")){
        	return "7";
        } else if(val.equals("Q355D")){
        	return "8";
        } else if(val.equals("Q420")){
        	return "9";
        } else if(val.equals("Q450NQR1")){
        	return "A";
        } else if(val.equals("Q460C")){
        	return "B";
        } else if(val.equals("Q550D")){
        	return "C";
        } else if(val.equals("QSTE500")){
        	return "D";
        } else if(val.equals("QStE700TM")){
        	return "E";
        } else if(val.equals("S355J0H")){
        	return "F";
        } else if(val.equals("S355J2H")){
        	return "G";
        } else if(val.equals("S355JR")){
        	return "H";
        } else if(val.equals("SPA-H")){
        	return "I";
        } else if(val.equals("SS400")){
        	return "J";
        }else {
        	return val;
        }
    }

}
