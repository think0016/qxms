package com.qxms.interceptor.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.qxms.model.User;
import com.qxms.utils.SysUtils;

public class VerifyLoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub		
		
		User user = (User)inv.getController().getSession().getAttribute("cache_user");
		if(user == null){
			//System.out.println("---未登录---");
			//inv.getController().redirect("/login");
			inv.getController().setAttr("msg1", "登录失效请重新登录");
			inv.getController().forwardAction("/login");
		}else{
			//生成菜单列表
			String html = SysUtils.getMenuList_HTML(user.getUid().toString());
			//request.setAttribute("menuhtml", html);
			inv.getController().setAttr("menuhtml", html);
			inv.getController().setAttr("cache_user", user);
			inv.getController().setAttr("cache_cnickname", user.getNickname());
			inv.getController().setAttr("cache_dname", user.getDepartment().getDname());
			inv.invoke();
		}
	}

}
