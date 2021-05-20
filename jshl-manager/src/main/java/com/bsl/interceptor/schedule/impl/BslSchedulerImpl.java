package com.bsl.interceptor.schedule.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bsl.common.pojo.BSLException;
import com.bsl.dao.JedisClient;
import com.bsl.mapper.BslLuStockInfoMapper;
import com.bsl.mapper.BslProductInfoMapper;
import com.bsl.mapper.BslProductPhotoInfoMapper;
import com.bsl.mapper.BslStockChangeDetailHMapper;
import com.bsl.mapper.BslStockChangePhotoMapper;
import com.bsl.pojo.BslLuStockInfo;
import com.bsl.pojo.BslProductInfo;
import com.bsl.pojo.BslProductInfoExample;
import com.bsl.pojo.BslProductPhotoInfo;
import com.bsl.pojo.BslStockChangePhoto;
import com.bsl.select.DictItemOperation;
import com.bsl.select.ErrorCodeInfo;
import com.bsl.interceptor.schedule.BslSchedulerService;

/**
 * 每天晚上11点55跑批
 * @author 杜康
 *
 */
@Component
public class BslSchedulerImpl implements BslSchedulerService{
	
    @Autowired
	private JedisClient jedisClient;
    @Value("REDIS_NEXT_PLAN_ID")
	private String REDIS_NEXT_PLAN_ID;
	@Value("REDIS_TODAT_KEY")
	private String REDIS_TODAT_KEY;
	@Value("REDIS_NEXT_RAW_ID")
	private String REDIS_NEXT_RAW_ID;
	@Value("REDIS_NEXT_STOCKCHANGE_ID")
	private String REDIS_NEXT_STOCKCHANGE_ID;
	@Value("REDIS_NEXT_PROD_PLAN_ID_KEY")
	private String REDIS_NEXT_PROD_PLAN_ID;
	@Value("REDIS_NEXT_PLAN_DETAIL_ID")
	private String REDIS_NEXT_PLAN_DETAIL_ID;
	@Value("REDIS_NEXT_SEMI_PROD_ID")
	private String REDIS_NEXT_SEMI_PROD_ID;
	@Value("REDIS_NEXT_PROD_ID")
	private String REDIS_NEXT_PROD_ID;
	@Value("REDIS_NEXT_SALE_ID")
	private String REDIS_NEXT_SALE_ID;
	@Value("REDIS_NEXT_CHANGE_STATUS_ID")
	private String REDIS_NEXT_CHANGE_STATUS_ID;
	@Value("REDIS_NEXT_SALE_PLAN_ID")
	private String REDIS_NEXT_SALE_PLAN_ID;
	@Value("REDIS_NEXT_CAR_ID")
	private String REDIS_NEXT_CAR_ID;
	@Value("REDIS_NEXT_RECEIPT_ID")
	private String REDIS_NEXT_RECEIPT_ID;
	@Value("REDIS_NEXT_QUALITY_ID")
	private String REDIS_NEXT_QUALITY_ID;
	@Value("REDIS_NEXT_DCLPROD_ID")
	private String REDIS_NEXT_DCLPROD_ID;
	@Value("REDIS_NEXT_PROD_W_ID")
	private String REDIS_NEXT_PROD_W_ID;
	@Autowired	 
	BslProductInfoMapper bslProductInfoMapper;
	@Autowired	 
	BslProductPhotoInfoMapper bslProductPhotoInfoMapper;
	@Autowired	 
	BslLuStockInfoMapper bslLuStockInfoMapper;
	@Autowired	 
	BslStockChangePhotoMapper bslStockChangePhotoMapper;
	@Autowired	 
	BslStockChangeDetailHMapper bslStockChangeDetailHMapper;
	
	
    @Scheduled(cron="0 40 23 * * ? ")   //每天晚上11点55跑批
    @Override
    public void addBslScheduler(){
    	 DictItemOperation.log.info("===========批量开始："+new Date());
    	 System.out.println("批量开始 ");
    	 //重置序列id
         resetId();
         //插入库存日照
         insertProductPhoto();
         //插入单炉库存重量日照
         insertBslLuStockInfo();
         //插入库存变动重量日汇总
         insertBslStockChangeInfo();
         //删除一年之前的库存日照
         deleteProductPhoto();
         //将一年前的已分剪、已发货、处理完成的数据历史化
         //insertHistoryProductInfo();
         //将一年前的库存变动数据历史化
         insertHistoryStockChangeInfo();
    }
    
    /**
     * 重置序列
     */
    public void resetId(){
    	DictItemOperation.log.info("===========重置序列id开始");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(new Date());
		
		//日期更新成当前日期yyyyMMdd
		jedisClient.set(REDIS_TODAT_KEY, dateStr);
		//planId半成品生产批号重置为1
		jedisClient.set(REDIS_NEXT_PLAN_ID, "0");
		//prodId卷板序号重置为1
		jedisClient.set(REDIS_NEXT_RAW_ID, "0");
		//trans_serno库存变动流水号重置为1
		jedisClient.set(REDIS_NEXT_STOCKCHANGE_ID, "0");
		//trans_serno产品生产指令号重置为1
		jedisClient.set(REDIS_NEXT_PROD_PLAN_ID, "0");
		//trans_serno指令详细流水号重置为1
		jedisClient.set(REDIS_NEXT_PLAN_DETAIL_ID, "0");
		//半成品入库产品编号
		jedisClient.set(REDIS_NEXT_SEMI_PROD_ID, "0");
		//产品入库产品编号
		jedisClient.set(REDIS_NEXT_PROD_ID, "0");
		//销售详细编号
		jedisClient.set(REDIS_NEXT_SALE_ID, "0");
		//状态修改流水
		jedisClient.set(REDIS_NEXT_CHANGE_STATUS_ID, "0");
		//销售出库编号
		jedisClient.set(REDIS_NEXT_SALE_PLAN_ID, "0");
		//原料入库通知单编号
		jedisClient.set(REDIS_NEXT_RECEIPT_ID, "0");
		//产品车辆流水号
		jedisClient.set(REDIS_NEXT_CAR_ID, "0");
		//证明书编号
		jedisClient.set(REDIS_NEXT_QUALITY_ID, "1");
		//待处理品编号
		jedisClient.set(REDIS_NEXT_DCLPROD_ID, "0");
		//外协产品编号
		jedisClient.set(REDIS_NEXT_PROD_W_ID, "0");
		//TODO 重置id在此处写
		// ....
		
		DictItemOperation.log.info("===========重置序列id完成");
		
    }
    
    /**
     * 插入库存日照
     */
    public void insertProductPhoto(){
    	 DictItemOperation.log.info("===========插入库存日照开始");
    	 //查询当前(已入库已出库)库存信息
    	 BslProductInfoExample bslProductInfoExample = new BslProductInfoExample();
    	 com.bsl.pojo.BslProductInfoExample.Criteria criteriaProductInfo = bslProductInfoExample.createCriteria();
    	 criteriaProductInfo.andProdStatusEqualTo(DictItemOperation.产品状态_已入库);
    	 List<BslProductInfo> bslProductInfos = bslProductInfoMapper.selectByExample(bslProductInfoExample);
    	 if(bslProductInfos!=null && bslProductInfos.size()>0){
    		 BslProductPhotoInfo bslProductPhotoInfo = new BslProductPhotoInfo();
    		 for (BslProductInfo bslProductInfo : bslProductInfos) {
    			 //循环插入
    			 bslProductPhotoInfo.setTransDate(new Date());
    			 bslProductPhotoInfo.setProdId(bslProductInfo.getProdId());
    			 bslProductPhotoInfo.setProdStatus(bslProductInfo.getProdStatus());
    			 int result = bslProductPhotoInfoMapper.insert(bslProductPhotoInfo);
    			 if(result<0){
    			      throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
    			 }else if(result==0){
    				  throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"插入失败，编号"+bslProductInfo.getProdId());
    			 }
			}
    	 }
    	 DictItemOperation.log.info("===========插入库存日照完成");
    }
    
    /**
     * 插入单炉库存重量日照
     */
    public void insertBslLuStockInfo(){
    	 DictItemOperation.log.info("===========插入单炉库存重量日照开始");
    	 List<BslLuStockInfo> bslLuStockInfos = bslLuStockInfoMapper.selectOneDayInfo();
    	 if(bslLuStockInfos != null && bslLuStockInfos.size()>0){
    		 for (BslLuStockInfo bslLuStockInfo : bslLuStockInfos) {
    			 int insert = bslLuStockInfoMapper.insert(bslLuStockInfo);
    			 if(insert<0){
	   			      throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
	   			 }else if(insert==0){
	   				  throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"插入失败，炉号"+bslLuStockInfo.getProdLuno());
	   			 }
			}
    	 }
    	 DictItemOperation.log.info("===========插入单炉库存重量日照完成");
    }
    
    /**
     * 插入库存变动重量日汇总
     */
    public void insertBslStockChangeInfo(){
    	 DictItemOperation.log.info("===========插入库存变动重量日汇总开始");
    	 List<BslStockChangePhoto> bslStockChangePhotos = bslStockChangePhotoMapper.selectStockChangeSumInfo();
    	 if(bslStockChangePhotos != null && bslStockChangePhotos.size()>0){
    		 for (BslStockChangePhoto bslStockChangePhoto : bslStockChangePhotos) {
    			 int insert = bslStockChangePhotoMapper.insert(bslStockChangePhoto);
    			 if(insert<0){
	   			      throw new BSLException(ErrorCodeInfo.错误类型_数据库错误,"sql执行异常！");
	   			 }else if(insert==0){
	   				  throw new BSLException(ErrorCodeInfo.错误类型_查询无记录,"插入失败，交易码："+bslStockChangePhoto.getTransCode()+";产品类型："+bslStockChangePhoto.getProdType());
	   			 }
			}
    	 }
    	 DictItemOperation.log.info("===========插入库存变动重量日汇总结束");
    }
    
    /**
     * 删除一年之前的库存日照
     */
    public void deleteProductPhoto(){
    	 DictItemOperation.log.info("===========删除一年之前的库存日照开始");
    	 bslProductPhotoInfoMapper.deleteProductPhotoInfoOnyears();
    	 DictItemOperation.log.info("===========删除一年之前的库存日照结束");
    }
    
    /**
     * 将一年前的已分剪、已发货、处理完成的数据历史化
     */
    public void insertHistoryProductInfo(){
		 DictItemOperation.log.info("===========历史化一年前的产品数据开始");
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		 
		 Calendar c = Calendar.getInstance();
		 c.setTime(new Date());
		 c.add(Calendar.YEAR, -1);
		 Date y = c.getTime();
		 String dateStr = sdf.format(y);
		 
		 int insertHistoryProductInfo = bslProductInfoMapper.insertHistoryProductInfo(dateStr);
		 if(insertHistoryProductInfo > 0){
			 DictItemOperation.log.info("===========删除一年前的产品数据开始");
			 bslProductInfoMapper.deleteHistoryProductInfo(dateStr);
		 }
		 
		 DictItemOperation.log.info("===========历史化一年前的产品数据结束");
    }
    
    /**
     * 将一年前的库存变动数据数据历史化
     */
    public void insertHistoryStockChangeInfo(){
		 DictItemOperation.log.info("===========历史化一年前的库存变动数据开始");
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		 
		 Calendar c = Calendar.getInstance();
		 c.setTime(new Date());
		 c.add(Calendar.YEAR, -1);
		 Date y = c.getTime();
		 String dateStr = sdf.format(y);
		 
		 int insertHistoryStockChangeInfo = bslStockChangeDetailHMapper.insertHistoryChangeInfo(dateStr);
		 if(insertHistoryStockChangeInfo > 0){
			 DictItemOperation.log.info("===========删除一年前的库存变动数据开始");
			 bslStockChangeDetailHMapper.deleteHistoryChangeInfo(dateStr);
		 }
		 
		 DictItemOperation.log.info("===========历史化一年前的库存变动数据结束");
    }
    
    
    
}  