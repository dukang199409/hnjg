package com.bsl.controller.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsl.common.pojo.BSLException;
import com.bsl.common.pojo.ParamConfig;
import com.bsl.common.utils.BSLResult;
import com.bsl.common.utils.SerialPortUtils;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;

/**
 * 称重
 * @author duk
 * @date 2019年5月21日
 *
 */
@Controller
@RequestMapping("/rxtx")
public class RxtxController {
	
	@RequestMapping(value = "/weighing", method = RequestMethod.POST)
	@ResponseBody
	public BSLResult weighing(){	
		DictItemOperation.log.info("===========称重开始===========");// 实例化串口操作类对象
        SerialPortUtils serialPort = new SerialPortUtils();
		try{
			String data = "";
			String dataHex = "";
			// data验证规则
		    String regData = "^[+-][0-9A-Z]{9}$";
		    //dataHex验证规则
		    String regdataHex = "^022[BD]\\d{18}03$";
		    //验证数值
		    String dataCheck = "";
		    // 编译正则表达式
		    Pattern patternData = Pattern.compile(regData);
		    Pattern patternDataHex = Pattern.compile(regdataHex);
		    Matcher matcherData;
		    Matcher matcherDataHex;
	        // 创建串口必要参数接收类并赋值，赋值串口号，波特率，校验位，数据位，停止位
	        ParamConfig paramConfig = new ParamConfig("COM3", 9600, 0, 8, 1);
			for (int i = 0; i < 15; i++) {
		        // 初始化设置,打开串口，开始监听读取串口数据
		        serialPort.init(paramConfig);
		        //停止0.3秒用来读取数据,不然读取不到
				Thread.sleep(300);
		        //获取数据
		        data = serialPort.getData();
		        dataHex = serialPort.getDataHex();
		        // 关闭串口
		        serialPort.closeSerialPort();
		        //验证是否满足规则
		        matcherData = patternData.matcher(data);
		        matcherDataHex = patternDataHex.matcher(dataHex);
		        DictItemOperation.log.info("===========data:"+data);
		        DictItemOperation.log.info("===========dataHex:"+dataHex);
		        if(matcherData.matches() && matcherDataHex.matches()){
		        	dataCheck = DictItemOperation.bytesToHexString(data.getBytes());
		        	if(dataHex.equals("02"+dataCheck+"03")){
		        		break;
		        	}
		        }
			}
	        if(StringUtils.isBlank(data) || StringUtils.isBlank(dataHex)){
	        	return BSLResult.build(ErrorCodeInfo.错误类型_查询无记录,"称重失败，数值为空");
	        }
	        //校验完成开始取值.+000000100 
	        //1符号位 2-7 数据位 8小数点位(从右到左) 9-10校验位
	        int xsd = Integer.parseInt(data.substring(7,8));
	        float math = Float.parseFloat(data.substring(1,7));
	        DictItemOperation.log.info("===========数字:"+math);
	        for (int i = 0; i < xsd; i++) {
	        	math = math*0.1f;
			}
	        //公斤要转化为吨
	        math = math/1000;
	        //保留三位小数
	        math = ((float)Math.round(math*1000))/1000;
	        DictItemOperation.log.info("===========小数位:"+xsd);
	        DictItemOperation.log.info("===========实重/公斤:"+math);
	        DictItemOperation.log.info("===========称重成功===========");
	        return BSLResult.ok(math);
		}catch(Exception e){
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
		finally {
			 serialPort.closeSerialPort();
		}
	}
	
	@RequestMapping(value = "/weighingByD", method = RequestMethod.POST)
	@ResponseBody
	public BSLResult weighingByD(){	
		DictItemOperation.log.info("===========称重开始===========");// 实例化串口操作类对象
        SerialPortUtils serialPort = new SerialPortUtils();
        try{
			String data = "";
			String dataHex = "";
			// data验证规则
		    String regData = "^=[0-9]*$";
		    //dataHex验证规则
		    String regdataHex = "^3D[0-9A-Z]*$";
		    //验证数值
		    String dataCheck = "";
		    // 编译正则表达式
		    Pattern patternData = Pattern.compile(regData);
		    Pattern patternDataHex = Pattern.compile(regdataHex);
		    Matcher matcherData;
		    Matcher matcherDataHex;
	        // 创建串口必要参数接收类并赋值，赋值串口号，波特率，校验位，数据位，停止位
	        ParamConfig paramConfig = new ParamConfig("COM4", 2400, 0, 8, 1);
			for (int i = 0; i < 15; i++) {
		        // 初始化设置,打开串口，开始监听读取串口数据
		        serialPort.init(paramConfig);
		        //停止0.3秒用来读取数据,不然读取不到
				Thread.sleep(300);
		        //获取数据
		        data = serialPort.getData();
		        dataHex = serialPort.getDataHex();
		        // 关闭串口
		        serialPort.closeSerialPort();
		        //验证是否满足规则
		        matcherData = patternData.matcher(data);
		        matcherDataHex = patternDataHex.matcher(dataHex);
		        DictItemOperation.log.info("===========data:"+data);
		        DictItemOperation.log.info("===========dataHex:"+dataHex);
		        if(matcherData.matches() && matcherDataHex.matches()){
		        	dataCheck = DictItemOperation.bytesToHexString(data.getBytes());
		        	if(dataHex.equals(dataCheck)){
		        		break;
		        	}
		        }
			}
	        if(StringUtils.isBlank(data) || StringUtils.isBlank(dataHex)){
	        	return BSLResult.build(ErrorCodeInfo.错误类型_查询无记录,"称重失败，数值为空");
	        }
	        //校验完成开始取值 去等号取反
	        String dataString = data.substring(1);
	        StringBuilder sbBuilder = new StringBuilder(dataString);
	        sbBuilder = sbBuilder.reverse();
	        float math = Float.parseFloat(sbBuilder.toString());
	        DictItemOperation.log.info("===========数字:"+math);
	        //公斤要转化为吨
	        math = math/1000;
	        //保留三位小数
	        math = ((float)Math.round(math*1000))/1000;
	        DictItemOperation.log.info("===========数值:"+dataString);
	        DictItemOperation.log.info("===========实重/公斤:"+math);
	        DictItemOperation.log.info("===========称重成功===========");
	        return BSLResult.ok(math);
		}catch(Exception e){
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}
		finally {
			 serialPort.closeSerialPort();
		}
	}
	
	//有线带端口的扫码枪
	@RequestMapping(value = "/getIdBySM", method = RequestMethod.POST)
	@ResponseBody
	public BSLResult getIdBySM(){	
		DictItemOperation.log.info("===========扫码开始===========");
		String data = "";
		// 实例化串口操作类对象
        SerialPortUtils serialPort = new SerialPortUtils();
		try{
	        // 创建串口必要参数接收类并赋值，赋值串口号，波特率，校验位，数据位，停止位
	        ParamConfig paramConfig = new ParamConfig("COM5", 115200, 0, 8, 1);
	        // 初始化设置,打开串口，开始监听读取串口数据
	        serialPort.init(paramConfig);
			for (int i = 0; i < 10; i++) {
		        //停止1秒用来读取数据
				Thread.sleep(1000);
		        //获取数据
		        data = serialPort.getData();
		        //验证是否满足规则
		        DictItemOperation.log.info("===========data:"+data);
		        if(!StringUtils.isBlank(data)){
		        	break;
		        }
			}
			if(StringUtils.isBlank(data)){
				 DictItemOperation.log.info("===========扫码失败===========");
				 return BSLResult.ok("");
			}else {
				 DictItemOperation.log.info("===========扫码成功===========");
			}
	        return BSLResult.ok(data);
		}catch(Exception e){
			if(e instanceof BSLException){
				//端口被占用时提示等待
				if(((BSLException) e).getErrorCode() == 305){
					return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,"端口被占用，请等待几秒重试！请不要重复点击。");
				}
			}
			return BSLResult.build(ErrorCodeInfo.错误类型_交易异常,e.getMessage());
		}finally {
			serialPort.closeSerialPort();
		}
	}
	
}
