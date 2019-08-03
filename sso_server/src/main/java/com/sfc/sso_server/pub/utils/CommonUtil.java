package com.sfc.sso_server.pub.utils;

import java.util.List;

/**
 * 
 * 工具类
 * 
 */
public class CommonUtil {
	/**
	 * 校验list
	 * @param list
	 * @return
	 */
	public static <T> boolean checkList(List<T> list) {
		if(list!=null && !list.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 时间+五位随机字符
	 * @param date
	 * @return
	 */
	/*public static String getReandomUUID(Date date){
		String uuid = DateUtil.formatDate2Str(new Date(),"yyyyMMddHHmmss");
		uuid += "-"+UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
		return uuid;
	}*/
}
