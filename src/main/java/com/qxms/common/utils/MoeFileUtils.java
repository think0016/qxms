package com.qxms.common.utils;

import org.apache.commons.lang3.StringUtils;

public class MoeFileUtils {
	
	public static String getExtname(String filename){
		String[] arr = StringUtils.split(filename,".");
		if(arr == null){
			return "";
		}
		int temp = arr.length-1;
		return arr[temp];
	}
	
	public static boolean isImg(String ContentType){
		boolean flag = false;
		if("image/jpeg".equals(ContentType) ||"image/png".equals(ContentType) || "image/gif".equals(ContentType) || "image/bmp".equals(ContentType)){
			flag = true;
		}
		return flag;
		
	}
}
