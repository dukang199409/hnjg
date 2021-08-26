package com.bsl.controller.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bsl.pojo.BslCodeTableKey;
import com.bsl.pojo.BslEnumInfo;
import com.bsl.service.enums.EnumService;

@Component
public class AutoEnumUtil {
	
	@Autowired
	private EnumService enumService;
	
	/**
	 * 读数据库，自动回显所有枚举
	 * @param page
	 * @param request
	 */
	public void addEnums(String page, HttpServletRequest request) {
		//因为人员角色需要将管理员角色枚举剔除，所以要额外判断，其它页面都走else逻辑
		if("M0003".equals(page) || "M0003-add".equals(page) 
				|| "M0002".equals(page) || "M0002-add".equals(page)) {
			List<BslEnumInfo> list = enumService.getBslEnumInfoByEngName("userType");
			for (BslEnumInfo bslEnumInfo : list) {
				// 去除管理员
				if("00".equals(bslEnumInfo.getEnumKey())){
					list.remove(bslEnumInfo);
					break;
				}
			}
			request.setAttribute("userTypeList", list);
		} else {
			List<BslCodeTableKey> enumList = enumService.getPageEnumEngKeys(page);
			for (BslCodeTableKey bslCodeTableKey : enumList) {
				List<BslEnumInfo> list = enumService.getBslEnumInfoByEngName(bslCodeTableKey.getEnumKey());
				if(list != null && list.size() > 0)
					request.setAttribute(bslCodeTableKey.getEnumKey()+"List", list);
			}
		}
	}
}
