package com.qxms.interceptor.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.jfinal.kit.PropKit;

public class CommonHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		// TODO Auto-generated method stub
		PropKit.use("commoncfg.properties");
		request.setAttribute("sitetitle", PropKit.get("sitename"));
		
		
		next.handle(target, request, response, isHandled);
	}

}
