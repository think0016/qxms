package com.qxms.interceptor.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.qxms.model.User;
import com.qxms.utils.SysUtils;

public class MenuHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		// TODO Auto-generated method stub
		User user = (User)request.getSession().getAttribute("cache_user");
		
		if(user !=null ){
			String html = SysUtils.getMenuList_HTML(user.getUid().toString());
			request.setAttribute("menuhtml", html);
		}
		
		next.handle(target, request, response, isHandled);
	}

}
