package com.qxms.interceptor.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

public class MenuHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		// TODO Auto-generated method stub
		
		System.out.println("----------------MenuHandler----------------");
		
		next.handle(target, request, response, isHandled);
	}

}
